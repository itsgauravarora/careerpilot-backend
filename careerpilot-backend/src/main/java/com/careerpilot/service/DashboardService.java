package com.careerpilot.service;

import com.careerpilot.dto.DashboardStats;
import com.careerpilot.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final JobApplicationRepository jobRepository;

    public DashboardService(JobApplicationRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public DashboardStats getStats() {

        return new DashboardStats(
                jobRepository.count(),
                jobRepository.countByStatus("Applied"),
                jobRepository.countByStatus("Interview"),
                jobRepository.countByStatus("Offer"),
                jobRepository.countByStatus("Rejected")
        );
    }
}