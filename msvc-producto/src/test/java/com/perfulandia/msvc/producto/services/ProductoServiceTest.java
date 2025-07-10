package com.perfulandia.msvc.producto.services;


import com.perfulandia.msvc.producto.exceptions.ProductoException;
import com.perfulandia.msvc.producto.model.entities.Producto;
import com.perfulandia.msvc.producto.repository.ProductoRepository;
import com.perfulandia.msvc.producto.service.ProductoServiceImpl;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;
    private Producto productoPrueba;
    private List<Producto> productos = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        this.productoPrueba = new Producto();
        Faker faker = new Faker(Locale.of("es","CL"));
        for(int i = 0; i < 100; i++) {
            Producto productoCreate = new Producto();

            productoCreate.setNombreProducto(faker.commerce().productName());
            productoCreate.setFechaElaboracion(LocalDate.now());
            productoCreate.setFechaVencimiento(
                    LocalDate.now()
                            .plusDays(faker.number().numberBetween(1, 365))
                            .toString()
            );
            productoCreate.setCategoria(faker.commerce().department());
            productoCreate.setStock(faker.number().numberBetween(1,1000));
            productoCreate.setPrecio(Double.parseDouble(faker.commerce().price()));

            productos.add(productoCreate);
        }
    }

    // BUSCAR SOLO 1 PRODUCTO
    @Test
    @DisplayName("Se debe listar un Producto")
    public void shouldFindById(){
       when(productoRepository.findById(Long.valueOf(1L))).thenReturn(Optional.of(productoPrueba));

       Producto result = productoService.findById(Long.valueOf(1L));
       assertThat(result).isNotNull();
       assertThat(result).isEqualTo(productoPrueba);
       verify(productoRepository,times(1)).findById(Long.valueOf(1L));
    }

    // BUSCAR UN PRODUCTO POR UN ID NO EXISTENTE
    @Test
    @DisplayName("Debe buscar un id que no existe")
    public void shouldNotFindProductoId(){
        Long idInesistente = (Long) 99L;
        when(productoRepository.findById(idInesistente)).thenReturn(Optional.empty());
        assertThatThrownBy(()->{
            productoService.findById(idInesistente);
        }).isInstanceOf(ProductoException.class)
                .hasMessageContaining(STR."El producto con id\{idInesistente}no se encuentra en la base de datos");
        verify(productoRepository,times(1)).findById(idInesistente);
    }

    // GUARDAR UN PRODUCTO
    @Test
    @DisplayName("Se debe guardar un nuevo producto")
    public void shouldSaveProducto(){
        when(productoRepository.save(productoPrueba)).thenReturn(productoPrueba);
        Producto result = productoService.guardarProducto(productoPrueba);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(productoPrueba);
        verify(productoRepository,times(1)).save(productoPrueba);
    }
}
