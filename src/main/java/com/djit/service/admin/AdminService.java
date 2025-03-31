package com.djit.service.admin;

import com.djit.dto.admin.ApplicationResponseDto;
import com.djit.dto.admin.ApplicationSummaryDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {
	Page<ApplicationSummaryDto> paging(Pageable pageable);

	ApplicationResponseDto getApplication(Long number);
}