package com.perfulandia.msvc.boleta.controllers;

import com.perfulandia.msvc.boleta.model.entities.Boleta;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoletaControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void shouldReturnAllBoletaWhenListIsRequested(){
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/boletas", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int boletasCount = documentContext.read("$.length()");
        assertThat(boletasCount).isEqualTo(2);

        JSONArray ids = documentContext.read("$..idBoleta");
        assertThat(ids).containsExactlyInAnyOrder(1,2);

        JSONArray names = documentContext.read("$..nombreCompleto");
        assertThat(names).containsExactlyInAnyOrder("Macarena", "Espi Espi");
    }

    @Test
    public void shouldReturnAnBoletaWhenFindById(){
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/boletas/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number idBoleta = documentContext.read("$.idBoleta");
        assertThat(idBoleta).isEqualTo(1);

        String nombreCompleto = documentContext.read("$.nombreCompleto");
        assertThat(nombreCompleto).isEqualTo("Maca Espi");
    }

    @Test
    public void shouldReturnAnBoletaWithUnknownId(){
        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/boletas/9999", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number status = documentContext.read("$.status");
        assertThat(status).isEqualTo(404);
    }

    @Test
    @DirtiesContext
    public void shouldCreateANewBoleta(){
        Boleta boleta = new Boleta ();
        ResponseEntity<String> response = restTemplate.postForEntity("/api/v1/boletas",boleta, String.class);
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number idBoleta = documentContext.read("$.idBoleta");
        assertThat(idBoleta).isEqualTo(3);
    }
}
