package com.example.demo.controller;

import com.example.demo.dto.AuditPoolDTO;
import com.example.demo.dto.MyResponseDTO;
import com.example.demo.service.AuditPoolService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
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


//    @GetMapping("/export-excel")
//    public ResponseEntity<byte[]> exportExcel() throws IOException {
//        // Load template file
//        ClassPathResource resource = new ClassPathResource("template.xlsx");
//        InputStream inputStream = resource.getInputStream();
//        Workbook workbook = new XSSFWorkbook(inputStream);
//
//        // Modify data in the template (for example)
//        Sheet sheet = workbook.getSheetAt(0);
//        Row row = sheet.getRow(0);
//        Cell cell = row.createCell(0);
//        cell.setCellValue("Hello");
//        row.createCell(1).setCellValue("World");
//
//        // Prepare response
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        workbook.write(outputStream);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
//        headers.setContentDispositionFormData("attachment", "exported_file.xlsx");
//        headers.setContentLength(outputStream.size());
//
//        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
//    }


    @GetMapping("/export-excel")
    public ResponseEntity<byte[]> exportExcel() throws IOException {
        // Create a new workbook
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet 1");

        LocalDate localDate = LocalDate.now();

        // Fill title 1
        Row titleRow1 = sheet.createRow(1);
        Cell titleCell = titleRow1.createCell(0);

        titleCell.setCellValue("BÁO CÁO TỔNG HỢP DỮ LIỆU HẬU KIỂM");

        //merge row
        //from row
        //to row
        //from column
        //to column
        sheet.addMergedRegion( new CellRangeAddress(
                1,
                1,
                0,
                3
        ));

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        Font font = workbook.createFont();
        font.setBold(true);
        cellStyle.setFont(font);

        titleCell.setCellStyle(cellStyle);

        // fill data title 2
        Row titleRow2 = sheet.createRow(2);
        Cell titleCell1 = titleRow2.createCell(0);
        titleCell1.setCellValue("Từ ngày: " + localDate + " tới ngày chưa biết");


        sheet.addMergedRegion( new CellRangeAddress(
                2,
                2,
                0,
                3
        ));

        //in dam, can giua
        CellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setAlignment(HorizontalAlignment.CENTER);
        Font font1 = workbook.createFont();
        font1.setBold(true);
        cellStyle1.setFont(font1);

        titleCell1.setCellStyle(cellStyle1);


        // Fill data (example)
        Row headerRow = sheet.createRow(4);
        headerRow.createCell(0).setCellValue("Name");
        headerRow.createCell(1).setCellValue("Age");

        Row dataRow1 = sheet.createRow(5);
        dataRow1.createCell(0).setCellValue("John");
        dataRow1.createCell(1).setCellValue(30);

        Row dataRow2 = sheet.createRow(6);
        dataRow2.createCell(0).setCellValue("Alice");
        dataRow2.createCell(1).setCellValue(25);


//        // Tạo dòng header và điền dữ liệu vào
//        Row headerRow = sheet.createRow(0);
//        headerRow.createCell(0).setCellValue("Header 1");
//        headerRow.createCell(1).setCellValue("Header 2");
//        headerRow.createCell(2).setCellValue("Header 3");
//
//        // Tạo dòng dữ liệu và điền dữ liệu vào
//        Row dataRow = sheet.createRow(1);
//        dataRow.createCell(0).setCellValue("Data 1");
//        dataRow.createCell(1).setCellValue("Data 2");
//        dataRow.createCell(2).setCellValue("Data 3");
//
//        setBorder(sheet, headerRow.getRowNum(), dataRow.getRowNum(), headerRow.getLastCellNum());

        // Write workbook to output stream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);

        // Prepare response
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment", "exported_file.xlsx");
        headers.setContentLength(outputStream.size());

        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }

    // Phương thức để thiết lập viền cho các ô trong phạm vi từ hàng đầu tiên đến hàng cuối cùng và từ cột đầu tiên đến cột cuối cùng
    private static void setBorder(Sheet sheet, int startRow, int endRow, int lastCellNum) {
        for (int i = startRow; i <= endRow; i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                row = sheet.createRow(i);
            }
            for (int j = 0; j < lastCellNum; j++) {
                Cell cell = row.getCell(j);
                if (cell == null) {
                    cell = row.createCell(j);
                }
                CellStyle style = cell.getCellStyle();
                if (style == null) {
                    style = sheet.getWorkbook().createCellStyle();
                }
                style.setBorderTop(BorderStyle.THIN);
                style.setBorderBottom(BorderStyle.THIN);
                style.setBorderLeft(BorderStyle.THIN);
                style.setBorderRight(BorderStyle.THIN);
                cell.setCellStyle(style);
            }
        }
    }
}
