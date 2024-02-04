package com.example.demo.service;

import com.example.demo.dto.AuditPoolDTO;
import com.example.demo.entity.AuditPool;
import com.example.demo.repository.AuditPoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuditPoolService {

    @Autowired
    private final AuditPoolRepository auditPoolRepository;
    private static final String CSV_HEADER = "ID,Content,BioId,ConfigID";

    public AuditPoolService(AuditPoolRepository auditPoolRepository) {
        this.auditPoolRepository = auditPoolRepository;
    }

//    public ResponseEntity<byte[]> exportToCSV(List<AuditPoolDTO> objects) {
//        try {
//            StringWriter writer = new StringWriter();
//            writer.append(CSV_HEADER);
//            writer.append("\n");
//
//            for (AuditPoolDTO object : objects) {
//                writer.append(String.valueOf(object.getAuditPool().getId()));
//                writer.append(",");
//                writer.append(escapeSpecialCharacters(object.getAuditPool().getContent()));
//                writer.append(",");
//                writer.append(escapeSpecialCharacters(object.getAuditPool().getBioId()));
//                writer.append(",");
//                writer.append(String.valueOf(object.getConfigID()));
//                writer.append("\n");
//            }
//
//            byte[] csvBytes = writer.toString().getBytes();
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//            headers.setContentDispositionFormData("attachment", "data.csv");
//
//            return ResponseEntity
//                    .ok()
//                    .headers(headers)
//                    .body(csvBytes);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity
//                    .badRequest()
//                    .body(null);
//        }
//    }

    private static String escapeSpecialCharacters(String value) {
        String escapedValue = value;
        if (value.contains("\"")) {
            escapedValue = value.replace("\"", "\"\"");
        }
        return escapedValue;
    }

    public List<AuditPoolDTO> getListAuditPoolDTO() {
        List<AuditPoolDTO> auditPoolDTOS = new ArrayList<>();
        List<String> bioids = new ArrayList<>();
        AuditPoolDTO auditPoolDTO;
        List<AuditPool> auditPools;
        bioids.add("1");
        bioids.add("1");
        bioids.add("2");
        bioids.add("20");

        try {
            for (String bioid : bioids) {
                auditPools = auditPoolRepository.findByAuditPoolBioId(bioid);
                if (!(auditPools.isEmpty())) {
                    for (AuditPool auditPool : auditPools) {
                        auditPoolDTO = new AuditPoolDTO(auditPool);
                        auditPoolDTOS.add(auditPoolDTO);
                    }
                }
            }
            for (AuditPoolDTO poolDTO : auditPoolDTOS) {
                System.out.println(poolDTO.toString());
            }
        } catch (Exception exception) {
            System.out.println("Has any error: " + exception);
        }

        return auditPoolDTOS;
    }

    public byte[] generateCSV(List<AuditPoolDTO> objects) throws IOException {
        StringWriter writer = new StringWriter();
        writer.append(CSV_HEADER);
        writer.append("\n");

        for (AuditPoolDTO object : objects) {
            writer.append(String.valueOf(object.getAuditPool().getId()));
            writer.append(",");
            writer.append(escapeSpecialCharacters(object.getAuditPool().getContent()));
            writer.append(",");
            writer.append(escapeSpecialCharacters(object.getAuditPool().getBioId()));
            writer.append(",");
            writer.append(String.valueOf(object.getConfigID()));
            writer.append("\n");
        }

        return writer.toString().getBytes();
    }


    /*
    * C2
    *
    * public byte[] generateCSV(List<AuditPoolDTO> objects) throws IOException {
    StringBuilder builder = new StringBuilder();
    builder.append(CSV_HEADER);
    builder.append("\n");

    for (AuditPoolDTO object : objects) {
        builder.append(object.getAuditPool().getId());
        builder.append(",");
        builder.append(escapeSpecialCharacters(object.getAuditPool().getContent()));
        builder.append(",");
        builder.append(object.getConfigID());
        builder.append("\n");
    }

    return builder.toString().getBytes();
}
    * */


    /*
    * C3
    *
    * public byte[] generateCSV(List<AuditPoolDTO> objects) throws IOException {
    try (StringBuilder builder = new StringBuilder()) {
        builder.append(CSV_HEADER);
        builder.append("\n");

        for (AuditPoolDTO object : objects) {
            builder.append(object.getAuditPool().getId());
            builder.append(",");
            builder.append(escapeSpecialCharacters(object.getAuditPool().getContent()));
            builder.append(",");
            builder.append(object.getConfigID());
            builder.append("\n");
        }

        return builder.toString().getBytes();
    }
}
    * */

/*
* C4
*
* public byte[] generateCSV(List<AuditPoolDTO> objects) throws IOException {
    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
        outputStream.write(CSV_HEADER.getBytes());
        outputStream.write("\n".getBytes());

        for (AuditPoolDTO object : objects) {
            outputStream.write(String.valueOf(object.getAuditPool().getId()).getBytes());
            outputStream.write(",".getBytes());
            outputStream.write(escapeSpecialCharacters(object.getAuditPool().getContent()).getBytes());
            outputStream.write(",".getBytes());
            outputStream.write(String.valueOf(object.getConfigID()).getBytes());
            outputStream.write("\n".getBytes());
        }

        return outputStream.toByteArray();
    }
}
* */
}
