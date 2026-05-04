package com.nttdata.alpaca.tech_test_spring.infraestructure.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.alpaca.tech_test_spring.application.services.ReportCustomerMovementService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/v1/api/customers")
@RequiredArgsConstructor
@Slf4j
public class ReportRestController {

    private final ReportCustomerMovementService reportCustomerMovementService;

    @GetMapping("/reports/{clienteId}")
    public Mono<ResponseEntity<Object>> generateReport(
            @PathVariable Long clienteId,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam(defaultValue = "json") String format) {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startLocalDate = LocalDate.parse(startDate, formatter);
        LocalDate endLocalDate = LocalDate.parse(endDate, formatter);
        
        LocalDateTime start = startLocalDate.atStartOfDay();
        LocalDateTime end = endLocalDate.atTime(23, 59, 59);
        
        return reportCustomerMovementService.generateReport(clienteId, start, end, format)
                .map(reportData -> {
                    if (format.equalsIgnoreCase("excel")) {
                        byte[] excelData = (byte[]) reportData;
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                        headers.setContentDispositionFormData("attachment", "report.xlsx");
                        return ResponseEntity.ok()
                                .headers(headers)
                                .body((Object) excelData);
                    } else {
                        return ResponseEntity.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(reportData);
                    }
                });
    }
}
