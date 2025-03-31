package com.springboot.djit.controller.admin;


import com.springboot.djit.dto.admin.ApplicationResponseDto;
import com.springboot.djit.dto.admin.ApplicationSummaryDto;
import com.springboot.djit.dto.admin.ConsultationDto;
import com.springboot.djit.service.admin.AdminService;
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
	public ResponseEntity<Void> consultationApplication(@RequestBody ConsultationDto consultationDto) {
		this.LOGGER.info("상담 예약 신청");
		this.LOGGER.info(consultationDto.getNumber() + " : number 값 확인");
		this.LOGGER.info(consultationDto.getName() + " : name 값 확인");
		this.LOGGER.info(consultationDto.getConsultationDate() + " : consultationDate 값 확인");
		this.LOGGER.info(consultationDto.getConsultationTime() + " : consultationTime 값 확인");
		return ResponseEntity.ok().build();
	}
}