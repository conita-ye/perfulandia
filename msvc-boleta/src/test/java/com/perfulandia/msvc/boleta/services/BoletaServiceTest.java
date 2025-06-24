package com.perfulandia.msvc.boleta.services;

import com.perfulandia.msvc.boleta.exceptions.BoletaException;
import com.perfulandia.msvc.boleta.model.entities.Boleta;
import com.perfulandia.msvc.boleta.repository.BoletaRepository;
import com.perfulandia.msvc.boleta.service.BoletaService;
import com.perfulandia.msvc.boleta.service.BoletaServiceImpl;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BoletaServiceTest {

    @Mock
    private BoletaRepository boletaRepository;

    @InjectMocks
    private BoletaServiceImpl boletaService;

    private Boleta boletaPrueba;

    private List<Boleta> boletas = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        this.boletaPrueba = new Boleta ();
        Faker faker = new Faker(Locale.of("es","CL"));
        for(int i=0;i<100;i++){
            Boleta boletaCreate = new Boleta();

            String numeroStr = faker.idNumber().valid().replaceAll("-","");
            String ultimo = numeroStr.substring(numeroStr.length()-1);
            String restante = numeroStr.substring(0, numeroStr.length()-1);

            boletaCreate.setFechaEmision(faker.careProvider().medicalProfession());
            boletaCreate.setNombreCliente(faker.name().fullName());

            boletaCreate.setIdBoleta(restante+"-"+ultimo);
            boletas.add(boletaCreate);
        }
    }

    @Test
    @DisplayName("Debo listar todas las boletas")
    public void shouldFindAllBoletas(){

        List<Boleta> boletas = this.boletas;
        boletas.add(boletaPrueba);
        when(boletaRepository.findAll()).thenReturn(boletas);

        List<Boleta> result = boletaService.findAll();

        assertThat(result).hasSize(101);
        assertThat(result).contains(boletaPrueba);

        verify(boletaRepository, times(1)).findAll();

    }

    @Test
    @DisplayName("Debe buscar una boleta")
    public void shouldFindById(){
        when(boletaRepository.findById(Long.valueOf(1L))).thenReturn(Optional.of(boletaPrueba));

        Boleta result = boletaService.findById(Long.valueOf(1L));
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(boletaPrueba);
        verify(boletaRepository, times(1)).findById(Long.valueOf(1L));

    }

    @Test
    @DisplayName("Debe buscar una boleta un I que n existe")
    public void shouldNotFindBoletaId(){
        Long idInexistente = (Long) 999L;
        when(boletaRepository.findById(idInexistente)).thenReturn(Optional.empty());
        assertThatThrownBy(()->{
            boletaService.findById(idInexistente);
        }).isInstanceOf(BoletaException.class)
                .hasMessageContaining("La boleta con id " +
                        idInexistente + " no se encuentra en la base de datos");
        verify(boletaRepository, times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Debe guardar una nueva boleta")
    public void shouldSaveBoleta(){
        when(boletaRepository.save(any(Boleta.class))).thenReturn(boletaPrueba);
        Boleta result = BoletaService.save(boletaPrueba);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(boletaPrueba);
        verify(boletaRepository, times(1)).save(any(Boleta.class));
    }


}
