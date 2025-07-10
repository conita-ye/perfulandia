package com.perfulandia.msvc.sucursal.services;


import com.perulandia.msvc.sucursal.model.entities.Sucursal;
import com.perulandia.msvc.sucursal.repository.SucursalRepository;
import com.perulandia.msvc.sucursal.service.SucursalService;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SucursalServiceTest {
    @Mock
    private SucursalRepository sucursalRepository;

    @Mock
    private SucursalService sucursalService;

    private Sucursal sucursalPrueba;

    private List<Sucursal> sucursales = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        this.sucursalPrueba = new Sucursal();
        Faker faker = new Faker(Locale.of("es","CL"));
        for(int i=0; i<10; i++){
            Sucursal sucursalCreate = new Sucursal();

            sucursalCreate.setNombre("Sucursal" + faker.company().name());
            sucursalCreate.setDireccion(faker.address().fullAddress());
            sucursalCreate.setCiudad(faker.address().city());

            sucursales.add(sucursalCreate);
        }
    }

    // BUSCAR TODA LAS SUCURSALES
    @Test
    @DisplayName("Se debe listar todas las sucursales")
    public void shouldfindAllSucursales(){
        List<Sucursal> sucursales = this.sucursales;
        sucursales.add(this.sucursalPrueba);
        when(sucursalRepository.findAll()).thenReturn(sucursales);

        List<Sucursal> result = sucursalService.findAll();

        assertThat(result).hasSize(101);
        assertThat(result).contains(sucursalPrueba);
        verify(sucursalRepository, times(1)).findAll();
    }

    // BUSCAR 1 SUCURSAL
    @Test
    @DisplayName("Se debe bsucar una sucursal")
    public void shouldFindById(){
        when(sucursalRepository.findById(1L)).thenReturn(java.util.Optional.of(this.sucursalPrueba));

        Sucursal result = sucursalService.findById(1L);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(sucursalPrueba);
        verify(sucursalRepository, times(1)).findById(1L);
    }


}
