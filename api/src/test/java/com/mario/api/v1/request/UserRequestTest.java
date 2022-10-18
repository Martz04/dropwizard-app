package com.mario.api.v1.request;

import com.mario.api.v1.resource.request.UserRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserRequestTest {

    @Test
    public void testBuilder() {
        var userRequest = UserRequest.builder()
                .fullName("Test")
                .jobTitle("java test")
                .build();

        assertThat(userRequest.getFullName()).isEqualTo("Test");
        assertThat(userRequest.getJobTitle()).isEqualTo("java test");
    }
}
