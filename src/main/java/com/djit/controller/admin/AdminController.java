package com.djit.controller.admin;

import com.djit.controller.main.DjitController.CalendarDay;
import com.djit.dto.application.ApplicationResponseDto;
import com.djit.dto.application.ApplicationSummaryDto;
import com.djit.dto.application.ConsultationDto;
import com.djit.dto.application.ConsultationResponseDto;
import com.djit.entity.application.Consultation;
import com.djit.service.admin.AdminService;

import jakarta.servlet.http.HttpServletRequest;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
	private final AdminService adminService;

	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@GetMapping("/list")
	public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
		LOGGER.info("Admin list page");
		Page<ApplicationSummaryDto> ApplicationSummaryDto = adminService.paging(pageable);
		int blockLimit = 5;
		int startPage = ((int) Math.ceil((double) pageable.getPageNumber() / (double) blockLimit) - 1) * blockLimit + 1;
		int endPage = Math.min(startPage + blockLimit - 1, ApplicationSummaryDto.getTotalPages());
		model.addAttribute("list", ApplicationSummaryDto);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "/admin/list";
	}

	@GetMapping("/applicationDetail/{number}")
	public String applicationDetail(@PageableDefault(page = 1) Pageable pageable, @PathVariable Long number,
			Model model) {
		LOGGER.info(number + " : number 값 확인");
		ApplicationResponseDto applicationResponseDto = adminService.getApplication(number);

		model.addAttribute("applicationResponseDto", applicationResponseDto);
		model.addAttribute("pageable", pageable);
		return "/admin/applicationDetail";
	}

	@PostMapping("/consultation")
	public ResponseEntity<Map<String, String>> consultationApplication(@RequestBody ConsultationDto consultationDto) {
		LOGGER.info("상담 예약 신청");
		ConsultationResponseDto savedDto = adminService.consultationApplication(consultationDto);
		String consultationDateTimeStr = savedDto.getConsultationDateTimeStr();
		Map<String, String> responseBody = Map.of("message", "상담 예약 성공", "consultationDateTimeStr",
				consultationDateTimeStr);
		return ResponseEntity.ok(responseBody);

	}
	
	
}