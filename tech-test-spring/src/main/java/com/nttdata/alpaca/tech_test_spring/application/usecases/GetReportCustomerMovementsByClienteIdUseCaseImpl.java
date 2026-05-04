package com.nttdata.alpaca.tech_test_spring.application.usecases;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.nttdata.alpaca.tech_test_spring.domain.models.ReportCustomerMovement;
import com.nttdata.alpaca.tech_test_spring.domain.ports.in.GetReportCustomerMovementsByClienteId;
import com.nttdata.alpaca.tech_test_spring.domain.ports.out.ClienteRepositoryPort;
import com.nttdata.alpaca.tech_test_spring.domain.ports.out.ReportCustomerMovementRepositoryPort;
import com.nttdata.alpaca.tech_test_spring.infraestructure.repositories.IClienteRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class GetReportCustomerMovementsByClienteIdUseCaseImpl implements GetReportCustomerMovementsByClienteId {
   
    private final ReportCustomerMovementRepositoryPort reportCustomerRepository;
    private final ClienteRepositoryPort clienteRepositoryPort;

    @Override
    public Flux<ReportCustomerMovement> getByClienteId(Long clienteId) {
        return clienteRepositoryPort.getClienteById(clienteId)
        		.flatMapMany(cliente -> 
        			reportCustomerRepository.getAllMovementsByCustomerName(cliente.getNombre())
        		);
    }

    public Flux<ReportCustomerMovement> getByClienteIdAndDateRange(Long clienteId, LocalDateTime startDate, LocalDateTime endDate) {
        return clienteRepositoryPort.getClienteById(clienteId)
        		.flatMapMany(cliente -> 
        			reportCustomerRepository.getAllMovementsByCustomerNameAndDateRange(cliente.getNombre(), startDate, endDate)
        		);
    }
}
