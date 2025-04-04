package com.djit.service.admin;

import com.djit.dto.application.ApplicationResponseDto;
import com.djit.dto.application.ApplicationSummaryDto;
import com.djit.dto.application.ConsultationDto;
import com.djit.dto.application.ConsultationResponseDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {
	Page<ApplicationSummaryDto> paging(Pageable pageable);

	ApplicationResponseDto getApplication(Long number);
	
	ConsultationResponseDto consultationApplication(ConsultationDto consultationDto);
}