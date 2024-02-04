package com.example.demo.controller;

import com.example.demo.dto.AuditPoolDTO;
import com.example.demo.service.AuditPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AuditPoolController {

    @Autowired
    private AuditPoolService auditPoolService;

    @GetMapping("/hello")
    public void Test(){
        auditPoolService.getListAuditPoolDTO();
    }

//    @GetMapping("/export")
//    public ResponseEntity<byte[]> exportCSV() {
//        List<MyObject> objects = // Your list of objects with id, content, and configid
//                ResponseEntity<byte[]> response = CSVExporter.exportToCSV(objects);
//        return response;
//    }

    @GetMapping("/downloadCSV")
    public ResponseEntity<byte[]> downloadCSV() throws IOException {
        List<AuditPoolDTO> objects = auditPoolService.getListAuditPoolDTO();

        byte[] csvBytes = auditPoolService.generateCSV(objects);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "data.csv");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(csvBytes);
    }
}
