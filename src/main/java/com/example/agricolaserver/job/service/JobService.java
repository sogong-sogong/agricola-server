package com.example.agricolaserver.job.service;

import com.example.agricolaserver.auxiliaryequipment.domain.AuxiliaryEquipment;
import com.example.agricolaserver.auxiliaryequipment.dto.AuxiliaryEquipmentDTO;
import com.example.agricolaserver.job.domain.Job;
import com.example.agricolaserver.job.dto.JobDTO;
import com.example.agricolaserver.job.repository.JobRepository;
import com.example.agricolaserver.member.domain.Member;
import com.example.agricolaserver.room.domain.Room;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;

@RequiredArgsConstructor
@Transactional
@Service
public class JobService {
    private final JobRepository jobRepository;

    private static final Map<Integer, JobDTO> JOB_INFO_MAP = new HashMap<>();
    
    static {
        JOB_INFO_MAP.put(1, new JobDTO(1, 0)); // 숙련 벽돌공
        JOB_INFO_MAP.put(2, new JobDTO(2, 0)); // 상담가
        JOB_INFO_MAP.put(3, new JobDTO(3, 0)); // 보조 경작자
        JOB_INFO_MAP.put(4, new JobDTO(4, 0)); // 산울타리지기
        JOB_INFO_MAP.put(5, new JobDTO(5, 0)); // 장작 채집자
        JOB_INFO_MAP.put(6, new JobDTO(6, 0)); // 재산 관리인
        JOB_INFO_MAP.put(7, new JobDTO(7, 0)); // 농번기 일꾼
        JOB_INFO_MAP.put(8, new JobDTO(8, 0)); // 가마 때는 사람
        JOB_INFO_MAP.put(9, new JobDTO(9, 0)); // 쟁기 몰이꾼
        JOB_INFO_MAP.put(10, new JobDTO(10, 0)); // 초벽질공
        JOB_INFO_MAP.put(11, new JobDTO(11, 0)); // 목사
        JOB_INFO_MAP.put(12, new JobDTO(12, 0)); // 지붕 다지는 사람
        JOB_INFO_MAP.put(13, new JobDTO(13, 0)); // 소 사육사
        JOB_INFO_MAP.put(14, new JobDTO(14, 0)); // 골조 건축업자
        JOB_INFO_MAP.put(15, new JobDTO(15, 0)); // 버섯 따는 사람
        JOB_INFO_MAP.put(16, new JobDTO(16, 0)); // 나무꾼
        JOB_INFO_MAP.put(17, new JobDTO(17, 0)); // 돼지 사육사
        JOB_INFO_MAP.put(18, new JobDTO(18, 0)); // 하인
        JOB_INFO_MAP.put(19, new JobDTO(19, 0)); // 외양간 건축가
        JOB_INFO_MAP.put(20, new JobDTO(20, 0)); // 벽 건축가
        JOB_INFO_MAP.put(21, new JobDTO(21, 0)); // 집사
        JOB_INFO_MAP.put(22, new JobDTO(22, 0)); // 학자
        JOB_INFO_MAP.put(23, new JobDTO(23, 0)); // 오두막집살이
        JOB_INFO_MAP.put(24, new JobDTO(24, 0)); // 가축 상인
        JOB_INFO_MAP.put(25, new JobDTO(25, 0)); // 작살꾼
        JOB_INFO_MAP.put(26, new JobDTO(26, 0)); // 허풍선이
        JOB_INFO_MAP.put(27, new JobDTO(27, 0)); // 잡화상인
        JOB_INFO_MAP.put(28, new JobDTO(28, 0)); // 돌 자르는 사람
    }

    // 각 멤버별로 카드를 분배하는 코드
    // member1: jobid 1~7번 카드, member2: jobid 8~14번 카드, member3: jobid 15~21번 카드, member4: jobid 22~28번 카드를 받음.
    // public void initCard(Room room, Member member) {
    //     int startId = (int) ((member.getId() - 1) * 7 + 1);
    //     int endId = (int) (member.getId() * 7);
    //     for (int i = startId; i <= endId; i++) {
    //         JobDTO info = JOB_INFO_MAP.get(i);
    //         if (info != null) {
    //             Job job = Job.builder().id(Integer.toString(info.getJobId())).member(member).room(room).open(false).build();
    //             jobRepository.save(job);
    //         }
    //     }
    // }

    // 시나리오 카드 분배
    // member1에게 6번 카드(재산 관리인) 분배
    public void initScenarioCard(Room room, Member member) {
        if (member.getId() == 1) {
            JobDTO info = JOB_INFO_MAP.get(6);
            if (info != null) {
                Job job = Job.builder()
                             .id(Integer.toString(info.getJobId()))
                             .member(member)
                             .room(room)
                             .open(false)
                             .build();
                jobRepository.save(job);
            }
        }
    }
    
    // 직업 조회 메소드 (memberid를 이용해 데이터베이스에서 조회)
    public List<JobDTO> getJobsByMemberId(Long memberId) {
        List<Job> jobs = jobRepository.findAllByMember_Id(memberId);
        return jobs.stream()
                .map(this::mapToJobDTO)
                .collect(Collectors.toList());
    }

    // Job 객체를 JobDTO 객체로 매핑하는 메소드
    private JobDTO mapToJobDTO(Job job) {
        return new JobDTO(
                job.getMember().getId(),
                job.getRoom().getId(),
                Integer.parseInt(job.getId()),
                job.isOpen() ? 1 : 0
        );
    }
}
