package com.mario.api.v1.response;

import com.mario.api.v1.resource.response.HelloWorldResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HelloWorldResponseTest {

    @Test
    void testBuilder() {
        var response = HelloWorldResponse.builder()
                .message("hello world").build();

        assertThat(response.getMessage()).isEqualTo("hello world");
    }
}
