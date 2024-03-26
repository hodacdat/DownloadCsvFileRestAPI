package com.example.demo.service;

import com.example.demo.dto.AuditPoolDTO;
import com.example.demo.dto.MyResponseDTO;
import com.example.demo.entity.AuditPool;
import com.example.demo.repository.AuditPoolRepository;
import com.example.demo.repository.GroupOfColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

@Service
public class AuditPoolService {

    @Autowired
    private final AuditPoolRepository auditPoolRepository;

    @Autowired
    private final GroupOfColumnRepository groupOfColumnRepository;
    private static final String CSV_HEADER = "ID,Content,BioId,ConfigID";

    public AuditPoolService(AuditPoolRepository auditPoolRepository, GroupOfColumnRepository groupOfColumnRepository) {
        this.auditPoolRepository = auditPoolRepository;
        this.groupOfColumnRepository = groupOfColumnRepository;
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
        bioids.add("3");
        String titleMore = "a1,a2,a3";

        long curGroup = 1L;
        try {
            titleMore = groupOfColumnRepository.findByCurId(curGroup).getNameOfColumn();
            List<String> stringList = Arrays.asList(titleMore.split(","));

            for (String a : stringList) {
                System.out.println("++++++++++++" + a);
            }
            try {
                for (String bioid : bioids) {
                    auditPools = auditPoolRepository.findByAuditPoolBioId(bioid);
                    if (!(auditPools.isEmpty())) {
                        for (AuditPool auditPool : auditPools) {
                            auditPoolDTO = new AuditPoolDTO(auditPool);
                            if (curGroup == 1L) {
                                if (stringList.contains("cl1")) {
                                    auditPoolDTO.setDynamicColumn("cl1", 100);
                                }
                                if (stringList.contains("cl2")) {
                                    auditPoolDTO.setDynamicColumn("cl2", 200);
                                }
                                if (stringList.contains("cl3")) {
                                    auditPoolDTO.setDynamicColumn("cl3", 300);
                                }
                            }
                            auditPoolDTOS.add(auditPoolDTO);
                        }
                    }
                }
            } catch (Exception exception) {
                System.out.println("Has any error: " + exception);
            }
        } catch (Exception e) {
            System.out.println("Has any error: " + e);

        }
        return auditPoolDTOS;
    }

    public List<MyResponseDTO> getExampleDataById(Long id) {
        // Retrieve data from the data source
        // Perform any necessary logic to determine dynamic columns
        List<MyResponseDTO> myResponseDTOS = new ArrayList<>();
        AuditPool auditPool = auditPoolRepository.findByAuditPoolId(1L);
        for (int i = 0; i < 2; i++) {

            MyResponseDTO responseDTO = new MyResponseDTO(auditPool);

            // Populate static columns
            responseDTO.setId(id);

            // Conditionally add dynamic columns based on ID
            if (id == 1) {
                responseDTO.setDynamicColumn("column1", 1);
                responseDTO.setDynamicColumn("column2", 2);
            } else if (id == 2) {
                responseDTO.setDynamicColumn("column3", 3);
                responseDTO.setDynamicColumn("column4", 4);
                responseDTO.setDynamicColumn("column5", 5);
            }

            myResponseDTOS.add(responseDTO);
        }

        return myResponseDTOS;
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
