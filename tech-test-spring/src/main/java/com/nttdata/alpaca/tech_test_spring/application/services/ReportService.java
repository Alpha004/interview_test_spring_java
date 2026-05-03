package com.nttdata.alpaca.tech_test_spring.application.services;

import org.springframework.stereotype.Service;

import com.nttdata.alpaca.tech_test_spring.domain.ports.in.GetClienteById;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {

	private final GetClienteById getClienteById;
	
	
}
