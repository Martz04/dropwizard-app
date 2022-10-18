package com.mario.api.v1.resource.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank
    private String fullName;

    @NotBlank
    private String jobTitle;
}
