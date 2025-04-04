package com.djit.service.admin;

import com.djit.dto.application.ApplicationResponseDto;
import com.djit.dto.application.ApplicationSummaryDto;
import com.djit.dto.application.ConsultationDto;
import com.djit.dto.application.ConsultationResponseDto;
import com.djit.entity.application.Application;
import com.djit.entity.application.Consultation;
import com.djit.repository.application.ApplicationRepository;
import com.djit.repository.application.ConsultationRepository;
import com.djit.service.application.ApplicationServiceImpl;

import lombok.Generated;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
	private final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);
	private final ApplicationRepository applicationRepository;
	private final ConsultationRepository counsultationRepository;
	private final ModelMapper modelMapper;

	@Override
	public Page<ApplicationSummaryDto> paging(Pageable pageable) {
		int page = pageable.getPageNumber() - 1;
		int pageLimit = 10;
		Page<Application> applications = applicationRepository
				.findAll(PageRequest.of(page, pageLimit, Sort.by(Direction.DESC, "createdAt")));
		Page<ApplicationSummaryDto> applicationSummaryDto = applications
				.map(application -> modelMapper.map(application, ApplicationSummaryDto.class));
		return applicationSummaryDto;
	}

	@Override
	@Transactional(readOnly = true)
	public ApplicationResponseDto getApplication(Long number) {
		Application application = applicationRepository.findById(number)
				.orElseThrow(() -> new RuntimeException("ID " + number + "에 해당하는 Application을 찾을 수 없습니다."));
		ApplicationResponseDto applicationResponseDto = modelMapper.map(application, ApplicationResponseDto.class);
		if (applicationResponseDto.getConsultation() != null) {
			applicationResponseDto.getConsultation().formatAndSetDateTimeString();
		}
		return applicationResponseDto;
	}

	@Override
	@Transactional
	public ConsultationResponseDto consultationApplication(ConsultationDto consultationDto) {
		LOGGER.info("상담 예약 서비스 호출");
		Application application = applicationRepository.findById(consultationDto.getNumber())
				.orElseThrow(() -> new RuntimeException());
		this.LOGGER.info(application.getName() + " : application name 값 확인");
		Consultation consultation = application.getConsultation();
		if (consultation == null) {
			consultation = Consultation.createConsultation(application, consultationDto.getConsultationDateTime());
			consultation = counsultationRepository.save(consultation);
		}else {
			consultation.updateConsultation(consultationDto.getConsultationDateTime());	
		}
		ConsultationResponseDto responseDto = modelMapper.map(consultation, ConsultationResponseDto.class);
		responseDto.formatAndSetDateTimeString();
		return responseDto;
		
		
		

	}

}