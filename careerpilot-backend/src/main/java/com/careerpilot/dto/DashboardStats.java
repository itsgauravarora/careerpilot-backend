package com.careerpilot.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStats {

    private long totalJobs;
    private long appliedJobs;
    private long interviewJobs;
    private long offerJobs;
    private long rejectedJobs;
}