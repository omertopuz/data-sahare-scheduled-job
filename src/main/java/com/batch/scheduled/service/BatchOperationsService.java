package com.batch.scheduled.service;

import com.batch.scheduled.model.cloudfileservice.FileInfo;
import com.batch.scheduled.model.cloudfileservice.GetFileData;
import com.batch.scheduled.model.dbservice.*;
import com.batch.scheduled.model.fileserver.RequestGetFile;
import com.batch.scheduled.model.fileserver.ResponseFileServer;
import com.batch.scheduled.model.portalservice.*;
import com.batch.scheduled.util.BatchUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BatchOperationsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BatchOperationsService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BatchUtils batchUtils;

    @Value( "${services.url.dbservice}" )
    public String urlDbService;
    @Value( "${services.url.portalservice}" )
    public String urlPortalService;
    @Value( "${services.url.cloudfileservice}" )
    public String urlCloudFileService;
    @Value( "${services.url.fileserverservice}" )
    public String urlFileServerService;
    @Value( "${services.url.pdfreportdataservice}" )
    public String urlPdfReportData;

//    @Scheduled(cron = "*/50 * * * * *")
//    public void doBatch(){
//        LOGGER.info("Batch started : ");
//    }

    @Scheduled(cron="${scheduler.cron.expression.informstate}")
    public void informAllStates(){
        LOGGER.info("================================================================================================================");
        LOGGER.info("informAllStates method started");
        InformStates[] informStateList =  restTemplate.getForObject(urlDbService+"will-be-informed-entities", InformStates[].class);
        LOGGER.info("Method Invoke : db-service/will-be-informed-entities, retrieved " + informStateList.length + " record from db service");
        List<RequestUpdateDetailEntity> detailEntityUpdateList = new ArrayList<>();
        for (InformStates response: informStateList) {
            InformStateRequest informStateRequest = new InformStateRequest(Integer.parseInt(response.getRemoteEntityId()),response.getWillBeInformedStateString(),response.getUpdateDateString());
            try {
                InformStateResponse responseInformStates =  restTemplate.postForObject(urlPortalService+"informstate",informStateRequest, InformStateResponse.class);
                LOGGER.info("Method Invoke : portal-service/informstate, request entity " + informStateRequest.toString() + " response : " + responseInformStates.toString());
                if (responseInformStates.isResult()){
                    detailEntityUpdateList.add(new RequestUpdateDetailEntity(response.getDetailId(),
                            new HashMap<String, String>() {{put("InformedProgramStateIdByUs", response.getWillBeInformedState()+"");
                            }}));
                }
            }catch (Exception ex){
                LOGGER.error("Error: portal-service/informstate method. Request Entity :" + informStateRequest.toString(),ex);
            }
        }

        if (detailEntityUpdateList.size()>0){
            ResponseUpdateDetailEntity updateResponse =  restTemplate.postForObject(urlDbService+"will-be-informed-entities",detailEntityUpdateList, ResponseUpdateDetailEntity.class);
            LOGGER.info("Method Invoke : db-service/informstate, request entity size : " + detailEntityUpdateList.size() + " response : " + updateResponse.toString());
        }
        LOGGER.info("informAllStates method started");
    }

    @Scheduled(cron="${scheduler.cron.expression.sendapplicationfiles}")
    public void sendApplicationFiles(){
        LOGGER.info("================================================================================================================");
        LOGGER.info("sendApplicationFiles method started");
        InformApplicationFiles[] informFilesList =  restTemplate.getForObject(urlDbService+"inform-application-files", InformApplicationFiles[].class);
        LOGGER.info("Method Invoke : db-service/inform-application-files, retrieved " + informFilesList.length + " record from db service");
        List<RequestUpdateDetailEntity> detailEntityUpdateList = new ArrayList<>();
        for (InformApplicationFiles response: informFilesList) {
            RequestGetFile requestGetFile = new RequestGetFile(response.getWillBeInformedFileId());
            try {
                ResponseFileServer responseFileServer = restTemplate.postForObject(urlFileServerService+"file-response",requestGetFile,ResponseFileServer.class);
                LOGGER.info("Method Invoke : file-server-service/file-response, request entity " + requestGetFile.toString() + " response : " + responseFileServer.toString());

                GetFileData uploadFileRequest = batchUtils.createGetFileDataObjectForApplicaitonFiles(response);
                FileInfo fileInfo =  restTemplate.postForObject(urlCloudFileService+"fileupload",uploadFileRequest, FileInfo.class);
                LOGGER.info("Method Invoke : cloud-file-service/fileupload, request entity " + uploadFileRequest.toString() + " response : " + fileInfo.toString());

                InformFileRequest informFileRequest = new InformFileRequest(
                        fileInfo.getFileMetaDataId(),
                        fileInfo.getFileName(),
                        response.getEntityId(),
                        response.getWillBeInformedFileId().toString()
                );

                InformFileResponse informFileResponse =  restTemplate.postForObject(urlPortalService+"informfilemetadata",informFileRequest, InformFileResponse.class);
                LOGGER.info("Method Invoke : portal-service/informstate, request entity " + informFileRequest.toString() + " response : " + informFileResponse.toString());

                if (informFileResponse.isResult()){
                    detailEntityUpdateList.add(new RequestUpdateDetailEntity(response.getDetailId(),
                            new HashMap<String, String>() {{put("SentApplicationFileAttachmentId", response.getWillBeInformedFileId()+"");
                            }}));
                }
            }catch (Exception ex){
                LOGGER.error("Error: ile-server-service/file-response method. Request Entity :" + requestGetFile.toString(),ex);
            }
        }

        if (detailEntityUpdateList.size()>0){
            ResponseUpdateDetailEntity updateResponse =  restTemplate.postForObject(urlDbService+"will-be-informed-entities",detailEntityUpdateList, ResponseUpdateDetailEntity.class);
            LOGGER.info("Method Invoke : db-service/informstate, request entity size : " + detailEntityUpdateList.size() + " response : " + updateResponse.toString());
        }
        LOGGER.info("sendApplicationFiles method started");
    }

    @Scheduled(cron="${scheduler.cron.expression.sendapplicationfiles}")
    public void sendEvaluationForms(){
        LOGGER.info("================================================================================================================");
        LOGGER.info("sendEvaluationForms method started");
        InformEvaluationForms[] informEvaluationForms =  restTemplate.getForObject(urlDbService+"inform-evaluation-forms", InformEvaluationForms[].class);
        LOGGER.info("Method Invoke : db-service/inform-evaluation-forms, retrieved " + informEvaluationForms.length + " record from db service");
        List<RequestUpdateDetailEntity> detailEntityUpdateList = new ArrayList<>();
        for (InformEvaluationForms response: informEvaluationForms) {
            PdfFileRequest pdfFileRequest = new PdfFileRequest(response.getApplicationId(),"KobiProjectControlForm");
            try {
                PdfFileResponse pdfFileResponse = restTemplate.postForObject(urlPdfReportData+"reportfiledata",pdfFileRequest,PdfFileResponse.class);
                LOGGER.info("Method Invoke : pdf-report-data-service/reportfiledata, request entity " + pdfFileRequest.toString() + " response : " + pdfFileResponse.toString());

                GetFileData uploadFileRequest = batchUtils.createGetFileDataObjectForEvaluationForms(response);
                FileInfo fileInfo =  restTemplate.postForObject(urlCloudFileService+"fileupload",uploadFileRequest, FileInfo.class);
                LOGGER.info("Method Invoke : cloud-file-service/fileupload, request entity " + uploadFileRequest.toString() + " response : " + fileInfo.toString());

                if (fileInfo.getFileMetaDataId()!= null){
                    detailEntityUpdateList.add(new RequestUpdateDetailEntity(response.getDetailId(),
                            new HashMap<String, String>() {{put("SentPreEvaluationFormId", response.getWillBeInformedEvaluationFormId()+"");
                            }}));
                }
            }catch (Exception ex){
                LOGGER.error("Error: pdf-report-data-service/reportfiledata method. Request Entity :" + pdfFileRequest.toString(),ex);
            }
        }

        if (detailEntityUpdateList.size()>0){
            ResponseUpdateDetailEntity updateResponse =  restTemplate.postForObject(urlDbService+"will-be-informed-entities",detailEntityUpdateList, ResponseUpdateDetailEntity.class);
            LOGGER.info("Method Invoke : db-service/informstate, request entity size : " + detailEntityUpdateList.size() + " response : " + updateResponse.toString());
        }
        LOGGER.info("sendEvaluationForms method started");
    }
}
