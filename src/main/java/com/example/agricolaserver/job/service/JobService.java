package com.example.agricolaserver.job.service;

import com.example.agricolaserver.job.domain.Job;
import com.example.agricolaserver.job.repository.JobRepository;
import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.room.domain.Room;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class JobService {
    private final JobRepository jobRepository;
    public void initJob(Room room, Member member){
        if(member.getNumber()==1){
            Job job = Job.builder().id("재산 관리인").member(member).room(room).score(1).open(false).build();
            jobRepository.save(job);
        }
    }
}