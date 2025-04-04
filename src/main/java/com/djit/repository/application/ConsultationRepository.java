package com.djit.repository.application;

import org.springframework.data.jpa.repository.JpaRepository;


import com.djit.entity.application.Consultation;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

}
