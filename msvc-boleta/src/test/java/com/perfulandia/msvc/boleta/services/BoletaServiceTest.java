package com.perfulandia.msvc.boleta.services;

import com.perfulandia.msvc.boleta.clients.ClienteClientRest;
import com.perfulandia.msvc.boleta.clients.SucursalClientRest;
import com.perfulandia.msvc.boleta.model.Cliente;
import com.perfulandia.msvc.boleta.model.Sucursal;
import com.perfulandia.msvc.boleta.model.entities.Boleta;
import com.perfulandia.msvc.boleta.repository.BoletaRepository;
import com.perfulandia.msvc.boleta.service.BoletaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BoletaServiceTest {

    @Mock
    private BoletaRepository boletaRepository;

    @Mock
    private ClienteClientRest clienteClientRest;

    @Mock
    private SucursalClientRest sucursalClientRest;

    @InjectMocks
    private BoletaServiceImpl boletaService;

    private Boleta boletaTest;
    private Cliente clienteTest;
    private Sucursal sucursalTest;

    @BeforeEach
    public void setUp() {
        boletaTest = new Boleta();
        boletaTest.setIdBoleta(Long.valueOf(1L));
        boletaTest.setFechaEmision("21-04-2024");
        boletaTest.setNombreCliente("Macarena");
        boletaTest.setIdSucursal(Long.valueOf(1L));
        boletaTest.setIdCliente(Long.valueOf(1L));

        clienteTest = new Cliente();
        clienteTest.setIdCliente(Long.valueOf(1L));
        clienteTest.setApellCli("Espinoza");
        clienteTest.setNombreCli("Macarena");
        clienteTest.setDireccion("Cerro Alegre");
        clienteTest.setTelefono(934747373);
        clienteTest.setCorreoElectronico("mac.espinoza@duocuc.cl");

        sucursalTest.setIdSucursal(Long.valueOf(1L));
        sucursalTest.setDireccion("Av. Argentina");
        sucursalTest.setCiudad("Valparaíso");
        sucursalTest.setNombre("Perfulandia");
    }

        @Test
        @DisplayName("Creación de test")
        public void shouldCreateBoleta(){

            when(clienteClientRest.findById(Long.valueOf(1L))).thenReturn(clienteTest);
            when(sucursalClientRest.findById(Long.valueOf(1L))).thenReturn(sucursalTest);

            when(boletaRepository.save(any(Boleta.class))).thenReturn(boletaTest);

            Boleta result = boletaService.save(boletaTest);

            assertThat(result).isNotNull();
            assertThat(result).isEqualTo(boletaTest);

            verify(boletaRepository, times(1)).findById(Long.valueOf(1L));
            verify(clienteClientRest, times(1)).findById(Long.valueOf(1L));
            verify(sucursalClientRest, times(1)).findById(Long.valueOf(1L));
    }

}

