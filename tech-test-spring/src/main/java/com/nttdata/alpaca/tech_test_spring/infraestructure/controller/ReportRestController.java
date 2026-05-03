package com.nttdata.alpaca.tech_test_spring.infraestructure.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.alpaca.tech_test_spring.application.services.ReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/api/customers")
@RequiredArgsConstructor
public class ReportRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportRestController.class);
	
	private final ReportService reportService;
}
