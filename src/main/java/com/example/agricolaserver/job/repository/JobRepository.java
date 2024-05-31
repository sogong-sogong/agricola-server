package com.example.agricolaserver.job.repository;

import com.example.agricolaserver.job.domain.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, String> {
    List<Job> findAllByMember_Id(Long memberId);
}
