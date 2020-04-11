package com.batch.scheduled.util;


import com.batch.scheduled.model.cloudfileservice.GetFileData;
import com.batch.scheduled.model.dbservice.InformApplicationFiles;
import com.batch.scheduled.model.dbservice.InformEvaluationForms;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BatchUtils {

    @Value( "${formatted-strings.applicationFile.fileName}" )
    private String applicationFileNameFormat;
    @Value( "${formatted-strings.applicationFile.keyField}" )
    private String applicationKeyField;
    @Value( "${formatted-strings.applicationFile.category}" )
    private String applicationCategory;
    @Value( "${formatted-strings.applicationFile.folderId}" )
    private int applicationFolderId;

    @Value( "${formatted-strings.evaluationform.fileName}" )
    private String  evaluationFileNameFormat;
    @Value( "${formatted-strings.evaluationform.keyField}" )
    private String  evaluationKeyField;
    @Value( "${formatted-strings.evaluationform.category}" )
    private String  evaluationCategory;
    @Value( "${formatted-strings.evaluationform.folderId}" )
    private int evaluationformFolderId;

    public GetFileData createGetFileDataObjectForApplicaitonFiles(InformApplicationFiles baseObject){
        GetFileData createdObject = new GetFileData();
        createdObject.setFolderId(applicationFolderId);
        createdObject.setCategory(applicationCategory);
        //keyField : _{kategori ; KOSGEB Başvuru Formu, HamleProjeId ; {remoteEntityId} , VergiNo ; {ApplicantTaxNumber},
        // KOSGEB_BasvuruId ; {entityId}, kurumDosyaId ; {attachmentId}, dosyaOlusumZamani ; {fileCreationDate}, dosyaUploadDate ; {fileUploadDate}}
        String fileName = applicationFileNameFormat.replace("{remoteEntityId}",baseObject.getEntityId()+"").replace("{entityId}",baseObject.getApplicationId()+"");
        String keyField = applicationKeyField.replace("{remoteEntityId}",baseObject.getEntityId()+"")
                .replace("{ApplicantTaxNumber}",baseObject.getApplicantTaxNumber())
                .replace("{entityId}",baseObject.getApplicationId()+"")
                .replace("{attachmentId}",baseObject.getWillBeInformedFileId().toString())
                .replace("{localFileName}",baseObject.getLocalFileName())
                .replace("{fileCreationDate}",baseObject.getFileCreationDate().toString())
                .replace("{fileUploadDate}", LocalDateTime.now().toString())
                .replace(";",":");
        createdObject.setFileName(fileName);
        createdObject.setKeyField(keyField);
        return createdObject;
    }

    public GetFileData createGetFileDataObjectForEvaluationForms(InformEvaluationForms baseObject){
        GetFileData createdObject = new GetFileData();
        createdObject.setFolderId(evaluationformFolderId);
        createdObject.setCategory(evaluationCategory);
        String fileName = evaluationFileNameFormat.replace("{remoteEntityId}",baseObject.getEntityId()+"").replace("{entityId}",baseObject.getApplicationId()+"");
        String keyField = evaluationKeyField.replace("{remoteEntityId}",baseObject.getEntityId()+"")
                .replace("{ApplicantTaxNumber}",baseObject.getApplicantTaxNumber())
                .replace("{entityId}",baseObject.getApplicationId()+"")
                .replace("{attachmentId}",baseObject.getWillBeInformedEvaluationFormId()+"")
                .replace("{fileCreationDate}",baseObject.getFileCreationDate().toString())
                .replace("{fileUploadDate}", LocalDateTime.now().toString())
                .replace(";",":");
        createdObject.setFileName(fileName);
        createdObject.setKeyField(keyField);
        return createdObject;
    }
}
