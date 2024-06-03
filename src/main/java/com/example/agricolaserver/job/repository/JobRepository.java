package com.example.agricolaserver.job.repository;

import com.example.agricolaserver.job.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,String> {
}
