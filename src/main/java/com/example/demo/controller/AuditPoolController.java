package com.example.demo.controller;

import com.example.demo.dto.AuditPoolDTO;
import com.example.demo.dto.MyResponseDTO;
import com.example.demo.service.AuditPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AuditPoolController {

    @Autowired
    private AuditPoolService auditPoolService;

    @GetMapping("/hello")
    public void Test() {
        auditPoolService.getListAuditPoolDTO();
    }

    @PostMapping("/hellov1")
    public ResponseEntity<?> TestDTO() {
        List<AuditPoolDTO> auditPoolDTOS = auditPoolService.getListAuditPoolDTO();
        return ResponseEntity.ok(auditPoolDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getExampleData(@PathVariable Long id) {
        List<MyResponseDTO> responseDTO = auditPoolService.getExampleDataById(id);
        return ResponseEntity.ok(responseDTO);
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
