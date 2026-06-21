package com.careerpilot.service;
import org.springframework.beans.factory.annotation.Autowired;
import com.careerpilot.dto.DashboardStats;
import com.careerpilot.entity.User;
import com.careerpilot.repository.UserRepository;
import com.careerpilot.entity.JobApplication;
import com.careerpilot.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import com.careerpilot.dto.DashboardStats;

import java.util.List;

@Service
public class JobApplicationService {
    @Autowired
    private UserRepository userRepository;

    private final JobApplicationRepository repository;

    public JobApplicationService(JobApplicationRepository repository) {
        this.repository = repository;
    }
    private User getCurrentUser(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    public List<JobApplication> getAllJobs(String email) {

        User user = getCurrentUser(email);

        return repository.findByUser(user);
    }

    public JobApplication saveJob(
            JobApplication job,
            String email) {

        User user = getCurrentUser(email);

        job.setUser(user);

        return repository.save(job);
    }
    public void deleteJob(
            Long id,
            String email) {

        User currentUser =
                getCurrentUser(email);

        JobApplication job =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Job not found"));

        if (!job.getUser().getId()
                .equals(currentUser.getId())) {

            throw new RuntimeException(
                    "Access Denied");
        }

        repository.delete(job);
    }
    public JobApplication updateJob(
            Long id,
            JobApplication updatedJob,
            String email) {

        User currentUser =
                getCurrentUser(email);

        JobApplication job =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Job not found"));

        if (!job.getUser().getId()
                .equals(currentUser.getId())) {

            throw new RuntimeException(
                    "Access Denied");
        }

        job.setCompanyName(
                updatedJob.getCompanyName());

        job.setJobTitle(
                updatedJob.getJobTitle());

        job.setStatus(
                updatedJob.getStatus());

        job.setApplicationDate(
                updatedJob.getApplicationDate());

        job.setNotes(
                updatedJob.getNotes());

        return repository.save(job);
    }
    public List<JobApplication> searchJobs(
            String companyName,
            String email) {

        User user = getCurrentUser(email);

        return repository
                .findByUserAndCompanyNameContainingIgnoreCase(
                        user,
                        companyName);
    }

    public List<JobApplication> getJobsByStatus(String status) {
        return List.of();
    }
    public DashboardStats getDashboardStats(
            String email) {

        User user = getCurrentUser(email);

        List<JobApplication> jobs =
                repository.findByUser(user);

        DashboardStats stats =
                new DashboardStats();

        stats.setTotalJobs(jobs.size());

        stats.setAppliedJobs(
                jobs.stream()
                        .filter(job ->
                                "Applied".equalsIgnoreCase(job.getStatus()))
                        .count());

        stats.setInterviewJobs(
                jobs.stream()
                        .filter(job ->
                                "Interview".equalsIgnoreCase(job.getStatus()))
                        .count());

        stats.setOfferJobs(
                jobs.stream()
                        .filter(job ->
                                "Offer".equalsIgnoreCase(job.getStatus()))
                        .count());

        stats.setRejectedJobs(
                jobs.stream()
                        .filter(job ->
                                "Rejected".equalsIgnoreCase(job.getStatus()))
                        .count());

        return stats;
    }
}
