package com.careerpilot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Schema(description = "User Registration Request")
public class RegisterRequest {

    @Schema(example = "Gaurav Arora")
    private String fullName;

    @Schema(example = "gaurav@test.com")
    private String email;

    @Schema(example = "password123")
    private String password;
}