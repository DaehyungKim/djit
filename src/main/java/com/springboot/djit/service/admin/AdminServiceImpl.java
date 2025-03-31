package com.springboot.djit.service.admin;

import com.springboot.djit.dto.admin.ApplicationResponseDto;
import com.springboot.djit.dto.admin.ApplicationSummaryDto;
import com.springboot.djit.entity.Application;
import com.springboot.djit.repository.ApplicationRepository;

import lombok.Generated;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
	private final ApplicationRepository applicationRepository;
	private final ModelMapper modelMapper;

	public Page<ApplicationSummaryDto> paging(Pageable pageable) {
		int page = pageable.getPageNumber() - 1;
		int pageLimit = 10;
		Page<Application> applications = applicationRepository
				.findAll(PageRequest.of(page, pageLimit, Sort.by(Direction.DESC, "createdAt")));
		Page<ApplicationSummaryDto> applicationSummaryDto = applications
				.map(application -> modelMapper.map(application, ApplicationSummaryDto.class));
		return applicationSummaryDto;
	}

	public ApplicationResponseDto getApplication(Long number) {
		Application application = applicationRepository.findById(number)
				.orElseThrow(() -> new RuntimeException("ID " + number + "에 해당하는 Application을 찾을 수 없습니다."));
		return modelMapper.map(application, ApplicationResponseDto.class);
	}

}