package com.djit.controller.application;


import java.util.HashMap;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.djit.dto.application.ApplicationDto;
import com.djit.dto.application.ApplicationResponseDto;
import com.djit.repository.application.ApplicationRepository;
import com.djit.repository.application.ConsultationRepository;
import com.djit.service.application.ApplicationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/application")
public class ApplicationController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);
	private final ApplicationService applicationService;
	
	
	@PostMapping()
	public ResponseEntity<Map<String, Object>> createApplication(@ModelAttribute ApplicationDto applicationDto) {
	    applicationService.saveApplication(applicationDto);
	    
	    Map<String, Object> response = new HashMap<>();
	    response.put("message", "접수되었습니다.");
	    response.put("redirectUrl", "/pages/main");
	    
	    return ResponseEntity.ok(response);
	}
	
	
	

}
