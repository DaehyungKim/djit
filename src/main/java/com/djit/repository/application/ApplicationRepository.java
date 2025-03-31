package com.djit.repository.application;

import org.springframework.data.jpa.repository.JpaRepository;

import com.djit.entity.application.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
