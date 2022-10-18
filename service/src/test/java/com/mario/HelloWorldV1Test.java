package com.mario;

import com.mario.api.v1.resource.response.HelloWorldResponse;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.core.Response;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(DropwizardExtensionsSupport.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HelloWorldV1Test {

    private static final String TEST_CONFIG_PATH = ResourceHelpers.resourceFilePath("server-test.yml");

    private static final DropwizardAppExtension<HelloWorldConfiguration> app =
            new DropwizardAppExtension<>(HelloWorldTestApplication.class, TEST_CONFIG_PATH);

    final static String LOCAL_HOST = "http://localhost:";

    @Test
    public void testHello() {
        Response response = app.client().target(LOCAL_HOST + app.getLocalPort() + "/v1/hello/mario")
                .request().get();

        var helloWorldResponse = response.readEntity(HelloWorldResponse.class);
        assertThat(helloWorldResponse.getMessage()).isEqualTo("Hello mario");
    }
}
