package com.springboot.djit.service.user;

import com.springboot.djit.dto.admin.ApplicationResponseDto;
import com.springboot.djit.dto.user.ApplicationDto;



public interface ApplicationService {
	
	

	   ApplicationResponseDto saveApplication(ApplicationDto applicationDto);

	 

}
