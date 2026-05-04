package com.nttdata.alpaca.tech_test_spring.application.services;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import com.nttdata.alpaca.tech_test_spring.application.factory.ReportGeneratorFactory;
import com.nttdata.alpaca.tech_test_spring.application.mapper.ReportCustomerMovementMapper;
import com.nttdata.alpaca.tech_test_spring.config.utils.ReportFormat;
import com.nttdata.alpaca.tech_test_spring.domain.models.ReportCustomerMovement;
import com.nttdata.alpaca.tech_test_spring.domain.ports.in.GetReportCustomerMovementsByClienteId;
import com.nttdata.alpaca.tech_test_spring.domain.ports.in.SaveReportCustomerMovement;


import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReportCustomerMovementService {

    private final SaveReportCustomerMovement saveReportCustomerMovement;
    private final GetReportCustomerMovementsByClienteId getReportCustomerMovementsByClienteId;
    private final ReportGeneratorFactory reportGeneratorFactory;
    

    public Mono<ReportCustomerMovement> save(ReportCustomerMovement movement) {
        return saveReportCustomerMovement.save(movement);
    }

    public Flux<ReportCustomerMovement> getAllReportMovementsByClienteId(Long clienteId) {
        return getReportCustomerMovementsByClienteId.getByClienteId(clienteId);
    }

    public Mono<Object> generateReport(Long clienteId, LocalDateTime startDate, LocalDateTime endDate, String format) {
        return getReportCustomerMovementsByClienteId.getByClienteIdAndDateRange(clienteId, startDate, endDate)
                .map(ReportCustomerMovementMapper::toReportItem)
                .collectList()
                .flatMap(data -> {
                    return reportGeneratorFactory.getGenerator(ReportFormat.valueOf(format.toUpperCase()))
                            .generate(data);
                });
    }
}
