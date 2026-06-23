package com.careerpilot.controller;
import java.security.Principal;
import com.careerpilot.entity.JobApplication;
import com.careerpilot.service.JobApplicationService;
import org.springframework.web.bind.annotation.*;
import com.careerpilot.dto.DashboardStats;
import jakarta.validation.Valid;
import java.util.List;

import com.careerpilot.entity.User;
import com.careerpilot.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/jobs")
public class JobApplicationController {

    private final JobApplicationService service;
    private final UserRepository userRepository;

    public JobApplicationController(
            JobApplicationService service,
            UserRepository userRepository) {

        this.service = service;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<JobApplication> getAllJobs(
            Principal principal) {

        return service.getAllJobs(
                principal.getName());
    }


    @PostMapping
    public JobApplication createJob(
            @RequestBody JobApplication job,
            Principal principal) {

        return service.saveJob(
                job,
                principal.getName());
    }
    @GetMapping("/test")
    public String test() {
        return "CareerPilot API Working!";
    }
    @DeleteMapping("/{id}")
    public String deleteJob(
            @PathVariable Long id,
            Principal principal) {

        service.deleteJob(
                id,
                principal.getName());

        return "Job Deleted Successfully";
    }
    @GetMapping("/delete/{id}")
    public String deleteJobByBrowser(
            @PathVariable Long id,
            Principal principal) {

        service.deleteJob(
                id,
                principal.getName());

        return "Job Deleted Successfully";
    }
    @PutMapping("/{id}")
    public JobApplication updateJob(
            @PathVariable Long id,
            @RequestBody JobApplication job,
            Principal principal) {

        return service.updateJob(
                id,
                job,
                principal.getName());
    }
    @GetMapping("/search")
    public List<JobApplication> searchJobs(
            @RequestParam String company,
            Principal principal) {

        return service.searchJobs(
                company,
                principal.getName());
    }
    @GetMapping("/status")
    public List<JobApplication> getJobsByStatus(
            @RequestParam String value) {

        return service.getJobsByStatus(value);
    }
    @GetMapping("/dashboard")
    public DashboardStats dashboardStats(
            Principal principal) {

        return service.getDashboardStats(
                principal.getName());
    }


}