package com.igor.springcontracttest;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.net.URI;
import java.util.Objects;

import static org.springframework.cloud.contract.spec.internal.MediaTypes.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(
        ids = {"com.igor:producer-stubs:0.0.1-SNAPSHOT:8180"}
        , stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class StubTest {

    @Test
    void shouldReturnOkWHenPersonIsAtLeast18Age()  {

        ResponseEntity<BeerGrantStatus> result = new TestRestTemplate().exchange(
                RequestEntity.post(URI.create("http://localhost:8180/check"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\n" +
                                "  \"age\": 22,\n" +
                                "  \"name\": \"igor\"\n" +
                                "}"), BeerGrantStatus.class);

        BDDAssertions.then(result.getStatusCode().value()).isEqualTo(200);
        BDDAssertions.then(result.getBody()).isEqualTo(new BeerGrantStatus("OK"));

    }

    @Test
    void shouldReturnNotOkWHenPersonIsBelow18Age()  {
        ResponseEntity<BeerGrantStatus> result = new TestRestTemplate().exchange(
                RequestEntity.post(URI.create("http://localhost:8180/check"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\n" +
                                "  \"age\": 17,\n" +
                                "  \"name\": \"Łudasz\"\n" +
                                "}"), BeerGrantStatus.class);

        BDDAssertions.then(result.getStatusCode().value()).isEqualTo(200);
        BDDAssertions.then(result.getBody()).isEqualTo(new BeerGrantStatus("NOT_OK"));

    }
}
