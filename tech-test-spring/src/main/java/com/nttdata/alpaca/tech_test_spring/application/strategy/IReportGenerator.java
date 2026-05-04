package com.nttdata.alpaca.tech_test_spring.application.strategy;

import java.util.List;

import com.nttdata.alpaca.tech_test_spring.infraestructure.dto.ReportItem;

import reactor.core.publisher.Mono;

public interface IReportGenerator {

	Mono<Object> generate(List<ReportItem> data);
	
	boolean supports(String format);
}
