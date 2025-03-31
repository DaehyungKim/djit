package com.springboot.djit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.djit.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
