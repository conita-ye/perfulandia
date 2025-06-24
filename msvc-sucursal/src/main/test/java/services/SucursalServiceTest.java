package com.perfulandia.msvc.sucursal.controllers;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.perfulandia.msvc.sucursal.model.Sucursal;
import com.perfulandia.msvc.sucursal.service.SucursalService;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

    @SpringBootTest
    @AutoConfigureMockMvc
    class SucursalControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private SucursalService sucursalService;

        @Test
        void shouldReturnListOfSucursales() throws Exception {
            // Given
            Sucursal sucursal1 = new Sucursal(1L, "Sucursal Central", "Av. Principal 123", "Lima");
            Sucursal sucursal2 = new Sucursal(2L, "Sucursal Norte", "Calle Norte 456", "Trujillo");

            sucursalService.save(sucursal1);
            sucursalService.save(sucursal2);

            // When
            MvcResult result = mockMvc.perform(get("/api/v1/sucursales")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            // Then
            DocumentContext documentContext = JsonPath.parse(result.getResponse().getContentAsString());

            JSONArray ids = documentContext.read("$.[*].idSucursal");
            assertThat(ids).containsExactlyInAnyOrder(1, 2);

            JSONArray nombres = documentContext.read("$.[*].nombre");
            assertThat(nombres).containsExactlyInAnyOrder("Sucursal Central", "Sucursal Norte");

            JSONArray ciudades = documentContext.read("$.[*].ciudad");
            assertThat(ciudades).containsExactlyInAnyOrder("Lima", "Trujillo");
        }

        @Test
        void shouldCreateNewSucursal() throws Exception {
            // Given
            String newSucursal = """
                {
                    "nombre": "Sucursal Sur",
                    "direccion": "Av. Sur 789",
                    "ciudad": "Arequipa"
                }
                """;

            // When
            MvcResult result = mockMvc.perform(post("/api/v1/sucursales")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(newSucursal))
                    .andExpect(status().isCreated())
                    .andReturn();

            // Then
            DocumentContext documentContext = JsonPath.parse(result.getResponse().getContentAsString());

            String nombre = documentContext.read("$.nombre");
            assertThat(nombre).isEqualTo("Sucursal Sur");

            String ciudad = documentContext.read("$.ciudad");
            assertThat(ciudad).isEqualTo("Arequipa");
        }
    }
}
