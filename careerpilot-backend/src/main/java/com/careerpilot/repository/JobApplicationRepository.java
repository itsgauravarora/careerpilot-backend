package com.careerpilot.repository;

import com.careerpilot.entity.JobApplication;
import com.careerpilot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {

    List<JobApplication> findByUser(User user);

    List<JobApplication>
    findByUserAndCompanyNameContainingIgnoreCase(
            User user,
            String companyName);

    List<JobApplication>
    findByUserAndStatus(
            User user,
            String status);
}