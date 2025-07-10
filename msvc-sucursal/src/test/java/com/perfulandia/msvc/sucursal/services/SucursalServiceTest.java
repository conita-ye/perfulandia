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
    public void setUp() {
        this.sucursalPrueba = new Sucursal();
        sucursalPrueba.setIdSucursal(1L);
        sucursalPrueba.setNombre("Sucursal Principal");
        sucursalPrueba.setDireccion("Av. Principal 123");
        sucursalPrueba.setCiudad("Santiago");

        Faker faker = new Faker(Locale.of("es", "CL"));
        for (int i = 0; i < 10; i++) {
            Sucursal sucursalCreate = new Sucursal();
            sucursalCreate.setIdSucursal((long) (i + 2));
            sucursalCreate.setNombre("Sucursal " + faker.company().name());
            sucursalCreate.setDireccion(faker.address().fullAddress());
            sucursalCreate.setCiudad(faker.address().city());
            sucursales.add(sucursalCreate);
        }
    }

    // BUSCAR TODAS LAS SUCURSALES
    @Test
    @DisplayName("Se debe listar todas las sucursales")
    public void shouldFindAllSucursales() {
        List<Sucursal> sucursales = this.sucursales;
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
    public void shouldFindById() {
        when(sucursalRepository.findById(1L)).thenReturn(java.util.Optional.of(this.sucursalPrueba));

        Sucursal result = sucursalService.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(sucursalPrueba);
        verify(sucursalRepository, times(1)).findById(1L);
    }

    // BUSCAR SUCURSAL QUE NO EXISTE
    @Test
    @DisplayName("Debe lanzar excepción cuando no encuentra sucursal por ID")
    public void shouldThrowExceptionWhenSucursalNotFound() {
        Long idInexistente = 999L;
        when(sucursalRepository.findById(idInexistente)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> sucursalService.findById(idInexistente))
                .isInstanceOf(SucursalException.class)
                .hasMessageContaining("La sucursal con id " + idInexistente + " no se encuentra en la base de datos");

        verify(sucursalRepository, times(1)).findById(idInexistente);
    }

    // GUARDAR SUCURSAL
    @Test
    @DisplayName("Se debe guardar una nueva sucursal")
    public void shouldSaveSucursal() {
        when(sucursalRepository.save(any(Sucursal.class))).thenReturn(sucursalPrueba);

        Sucursal result = sucursalService.save(sucursalPrueba);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(sucursalPrueba);
        assertThat(result.getNombre()).isEqualTo("Sucursal Principal");
        verify(sucursalRepository, times(1)).save(any(Sucursal.class));
    }

    // GUARDAR SUCURSAL NULA
    @Test
    @DisplayName("Debe lanzar excepción al guardar sucursal nula")
    public void shouldThrowExceptionWhenSavingNullSucursal() {
        assertThatThrownBy(() -> sucursalService.save(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("La sucursal no puede ser nula");

        verify(sucursalRepository, never()).save(any(Sucursal.class));
    }

    // GUARDAR SUCURSAL CON NOMBRE VACÍO
    @Test
    @DisplayName("Debe lanzar excepción al guardar sucursal con nombre vacío")
    public void shouldThrowExceptionWhenSavingSucursalWithEmptyName() {
        Sucursal sucursalSinNombre = new Sucursal();
        sucursalSinNombre.setNombre("");
        sucursalSinNombre.setDireccion("Dirección válida");
        sucursalSinNombre.setCiudad("Ciudad válida");

        assertThatThrownBy(() -> sucursalService.save(sucursalSinNombre))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("El nombre de la sucursal es obligatorio");

        verify(sucursalRepository, never()).save(any(Sucursal.class));
    }
}
