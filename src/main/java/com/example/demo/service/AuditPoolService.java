package com.example.demo.service;

import com.example.demo.dto.AuditPoolDTO;
import com.example.demo.entity.AuditPool;
import com.example.demo.repository.AuditPoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuditPoolService {

    @Autowired
    private AuditPoolRepository auditPoolRepository;
    private static final String CSV_HEADER = "ID,Content,BioId,ConfigID";

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
        AuditPoolDTO auditPoolDTO = null;
        List<AuditPool> auditPools = null;
        bioids.add("1");
        bioids.add("1");
        bioids.add("2");
        bioids.add("20");

        for (int i = 0; i < bioids.size(); i++) {
            auditPools = auditPoolRepository.findByAuditPoolBioId(bioids.get(i));
            if (!(auditPools.isEmpty())) {
                for (AuditPool auditPool : auditPools) {
                    auditPoolDTO = new AuditPoolDTO(auditPool);
                    auditPoolDTOS.add(auditPoolDTO);
                }
            }
        }

        for (int i = 0; i < auditPoolDTOS.size(); i++) {
            System.out.println(auditPoolDTOS.get(i).toString());
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
}
