package com.perfulandia.msvc.producto.controllers;


import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.perfulandia.msvc.producto.model.entities.Producto;
import jakarta.servlet.http.HttpServletResponse;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductoControllerTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void shouldReturnProductoWhenListIsRequested(){
        ResponseEntity<String> response = testRestTemplate.getForEntity("/api/v1/productos", String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int productoCount = documentContext.read("length()");
        assertThat(productoCount).isEqualTo(2);

        JSONArray ids = documentContext.read("$.[*].id");
        assertThat(ids).containsExactlyInAnyOrder(1L,2L);

        JSONArray nombres = documentContext.read("$.[*].nombreProducto");
        assertThat(nombres).containsExactlyInAnyOrder("CherriPlane","SweetTot");
    }

    @Test
    public void shouldReturnProductoWhenFindByIdIsRequested(){
        ResponseEntity<String> response = testRestTemplate.getForEntity("/api/v1/productos/1", String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(1L);

        String nombreProducto = documentContext.read("$.nombreProducto");
        assertThat(nombreProducto).isEqualTo("CherriPlane");
    }

    @Test
    public void shouldReturnNotFoundWhenFindByIdIsRequested(){
        ResponseEntity<String> response = testRestTemplate.getForEntity("/api/v1/productos/99", String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.NOT_FOUND);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number status = documentContext.read("$.status");
        assertThat(status).isEqualTo(HttpServletResponse.SC_NOT_FOUND);

        String message = documentContext.read("$.message");
        assertThat(message).isEqualTo("El producto con la id 99 no se encuentra en la base de datos");
    }

    @Test
    public void shouldReturnProductoWhenSaveIsRequested(){
        String productoJson = "{\"nombreProducto\":\"CherriPlane\",\"categoria\":\"Avion\",\"precio\":1000000.0,\"stock\":100,\"fechaElaboracion\":\"2021-01-01\",\"fechaVencimiento\":\"2021-01-31\"}";
        ResponseEntity<String> response = testRestTemplate.postForEntity("/api/v1/productos", productoJson, String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void shouldReturnBadRequestWhenSaveIsRequested(){
        String productoJson = "{\"nombreProducto\":\"CherriPlane\",\"categoria\":\"Avion\",\"precio\":1000000.0,\"stock\":100}";
        ResponseEntity<String> response = testRestTemplate.postForEntity("/api/v1/productos", productoJson, String.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void shouldCreateANewProducto(){
        Producto producto = new Producto();
        ResponseEntity<Producto> response = testRestTemplate.postForEntity("/api/v1/productos", producto, Producto.class);
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isEqualTo(1);
    }
}
