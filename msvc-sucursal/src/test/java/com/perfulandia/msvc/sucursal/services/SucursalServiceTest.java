package com.perfulandia.msvc.sucursal.services;

import com.perulandia.msvc.sucursal.exception.SucursalException;
import com.perulandia.msvc.sucursal.model.entities.Sucursal;
import com.perulandia.msvc.sucursal.repository.SucursalRepository;
import com.perulandia.msvc.sucursal.service.SucursalServiceImpl;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SucursalServiceTest {
    @Mock
    private SucursalRepository sucursalRepository;

    @InjectMocks
    private SucursalServiceImpl sucursalService;

    private Sucursal sucursalPrueba;
    private List<Sucursal> sucursales = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        this.sucursalPrueba = new Sucursal();
        this.sucursalPrueba.setIdSucursal(1L);
        this.sucursalPrueba.setNombre("Sucursal Principal");
        this.sucursalPrueba.setDireccion("Av. Principal 123");
        this.sucursalPrueba.setCiudad("Santiago");

        Faker faker = new Faker(Locale.of("es","CL"));
        for(int i=0; i<10; i++){
            Sucursal sucursalCreate = new Sucursal();
            sucursalCreate.setIdSucursal((long)(i + 2));
            sucursalCreate.setNombre("Sucursal " + faker.company().name());
            sucursalCreate.setDireccion(faker.address().fullAddress());
            sucursalCreate.setCiudad(faker.address().city());
            sucursales.add(sucursalCreate);
        }
    }

    // BUSCAR TODAS LAS SUCURSALES
    @Test
    @DisplayName("Se debe listar todas las sucursales")
    public void shouldFindAllSucursales(){
        List<Sucursal> sucursales = new ArrayList<>(this.sucursales);
        sucursales.add(this.sucursalPrueba);

        when(sucursalRepository.findAll()).thenReturn(sucursales);

        List<Sucursal> result = sucursalService.findAll();

        assertThat(result).hasSize(11);
        assertThat(result).contains(sucursalPrueba);
        verify(sucursalRepository, times(1)).findAll();
    }

    // BUSCAR 1 SUCURSAL
    @Test
    @DisplayName("Se debe buscar una sucursal")
    public void shouldFindById(){
        when(sucursalRepository.findById(1L)).thenReturn(Optional.of(this.sucursalPrueba));

        Sucursal result = sucursalService.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(sucursalPrueba);
        assertThat(result.getNombre()).isEqualTo("Sucursal Principal");
        verify(sucursalRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando no encuentra sucursal por ID")
    public void shouldThrowExceptionWhenSucursalNotFound(){
        when(sucursalRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> sucursalService.findById(999L))
                .isInstanceOf(SucursalException.class)
                .hasMessageContaining("Sucursal no encontrada con ID: 999");

        verify(sucursalRepository, times(1)).findById(999L);
    }

    // GUARDAR SUCURSAL
    @Test
    @DisplayName("Se debe guardar una sucursal")
    public void shouldSaveSucursal(){
        Sucursal nuevaSucursal = new Sucursal();
        nuevaSucursal.setNombre("Nueva Sucursal");
        nuevaSucursal.setDireccion("Calle Nueva 456");
        nuevaSucursal.setCiudad("Valparaíso");

        Sucursal sucursalGuardada = new Sucursal();
        sucursalGuardada.setIdSucursal(2L);
        sucursalGuardada.setNombre("Nueva Sucursal");
        sucursalGuardada.setDireccion("Calle Nueva 456");
        sucursalGuardada.setCiudad("Valparaíso");

        when(sucursalRepository.save(any(Sucursal.class))).thenReturn(sucursalGuardada);

        Sucursal result = sucursalService.save(nuevaSucursal);

        assertThat(result).isNotNull();
        assertThat(result.getIdSucursal()).isEqualTo(2L);
        assertThat(result.getNombre()).isEqualTo("Nueva Sucursal");
        assertThat(result.getDireccion()).isEqualTo("Calle Nueva 456");
        assertThat(result.getCiudad()).isEqualTo("Valparaíso");
        verify(sucursalRepository, times(1)).save(nuevaSucursal);
    }

    @Test
    @DisplayName("Debe lanzar excepción al guardar sucursal nula")
    public void shouldThrowExceptionWhenSavingNullSucursal(){
        assertThatThrownBy(() -> sucursalService.save(null))
                .isInstanceOf(SucursalException.class)
                .hasMessageContaining("La sucursal no puede ser nula");

        verify(sucursalRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debe lanzar excepción al guardar sucursal con nombre vacío")
    public void shouldThrowExceptionWhenSavingSucursalWithEmptyName(){
        Sucursal sucursalSinNombre = new Sucursal();
        sucursalSinNombre.setNombre("");
        sucursalSinNombre.setDireccion("Dirección válida");
        sucursalSinNombre.setCiudad("Ciudad válida");

        assertThatThrownBy(() -> sucursalService.save(sucursalSinNombre))
                .isInstanceOf(SucursalException.class)
                .hasMessageContaining("El nombre de la sucursal es obligatorio");

        verify(sucursalRepository, never()).save(any());
    }
}
