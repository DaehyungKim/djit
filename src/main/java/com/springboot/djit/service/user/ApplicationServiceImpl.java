package com.springboot.djit.service.user;

import com.springboot.djit.dto.admin.ApplicationResponseDto;
import com.springboot.djit.dto.user.ApplicationDto;
import com.springboot.djit.entity.Application;
import com.springboot.djit.repository.ApplicationRepository;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import lombok.Generated;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

	private final Logger LOGGER = LoggerFactory.getLogger(ApplicationServiceImpl.class);
	private final ModelMapper modelMapper;
	private final ApplicationRepository applicationRepository;

	public ApplicationResponseDto saveApplication(ApplicationDto applicationDto) {
		Application application = modelMapper.map(applicationDto, Application.class);

		application.setCreatedAt(LocalDateTime.now());
		application.setUpdatedAt(LocalDateTime.now());

		Application savedApplication = applicationRepository.save(application);

		return modelMapper.map(savedApplication, ApplicationResponseDto.class);
	}

}
