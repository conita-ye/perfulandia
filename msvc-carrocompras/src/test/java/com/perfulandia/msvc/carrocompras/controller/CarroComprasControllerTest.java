package com.perfulandia.msvc.carrocompras.services;

import com.perfulandia.msvc.carrocompras.clients.ProductoClientRest;
import com.perfulandia.msvc.carrocompras.model.Cliente;
import com.perfulandia.msvc.carrocompras.model.Sucursal;
import com.perfulandia.msvc.carrocompras.model.entities.CarroCompras;
import com.perfulandia.msvc.carrocompras.repository.CarroComprasRepository;
import com.perfulandia.msvc.carrocompras.service.CarroComprasServiceImpl;
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
public class CarroComprasServiceTest {

    @Mock
    private CarroComprasRepository carroComprasRepository;

    @Mock
    private ProductoClientRest productoClientRest;

    @InjectMocks
    private CarroComprasServiceImpl carroComprasService;

    private CarroCompras carroComprasTest;
    private Cliente clienteTest;
    private Sucursal sucursalTest;

    @BeforeEach
    public void setUp() {
        // Crear un CarroCompras de prueba
        carroComprasTest = new CarroCompras();
        carroComprasTest.setId(Long.valueOf(1L));
        carroComprasTest.setIdCliente(Long.valueOf(1L));
        carroComprasTest.setTotal(200.0);
        carroComprasTest.setCantidadProductos(2);

        clienteTest = new Cliente();
        clienteTest.setIdCliente(Long.valueOf(1L));
        clienteTest.setApellCli("Balladares");
        clienteTest.setNombreCli("Valentina");
        clienteTest.setDireccion("Playa Ancha");
        clienteTest.setTelefono(220632245);
        clienteTest.setCorreoElectronico("val.@duocuc.cl");

        sucursalTest = new Sucursal(); // Inicialización correcta de sucursalTest
        sucursalTest.setIdSucursal(Long.valueOf(1L));
        sucursalTest.setDireccion("Av. Argentina");
        sucursalTest.setCiudad("Valparaíso");
        sucursalTest.setNombre("Perfulandia");
    }

    @Test
    @DisplayName("Crear carro de compras")
    public void shouldCreateCarroCompras() {
        // Simular el comportamiento del repositorio para guardar el carro
        when(carroComprasRepository.save(any(CarroCompras.class))).thenReturn(carroComprasTest);

        // Crear el carro de compras
        CarroCompras result = carroComprasService.save(carroComprasTest);

        // Verificar que el resultado no sea nulo
        assertThat(result).isNotNull();
        // Verificar que el carro creado es igual al carro de prueba
        assertThat(result).isEqualTo(carroComprasTest);

        // Verificar que el repositorio fue llamado una vez con el objeto CarroCompras
        verify(carroComprasRepository, times(1)).save(any(CarroCompras.class));
    }

    @Test
    @DisplayName("Actualizar carro de compras")
    public void shouldUpdateCarroCompras() {
        // Crear un carro de compras actualizado
        CarroCompras carroActualizado = new CarroCompras();
        carroActualizado.setId(Long.valueOf(1L));
        carroActualizado.setIdCliente(Long.valueOf(1L));
        carroActualizado.setTotal(300.0);
        carroActualizado.setCantidadProductos(3);

        // Simular que el carro de compras ya existe en la base de datos
        when(carroComprasRepository.findById(Long.valueOf(1L))).thenReturn(java.util.Optional.of(carroComprasTest));
        // Simular que el repositorio guarda el carro actualizado
        when(carroComprasRepository.save(any(CarroCompras.class))).thenReturn(carroActualizado);

        // Llamar al método de actualización
        CarroCompras result = carroComprasService.update(Long.valueOf(1L), carroActualizado);

        // Verificar que el resultado no sea nulo
        assertThat(result).isNotNull();
        // Verificar que el carro actualizado tiene los valores correctos
        assertThat(result.getTotal()).isEqualTo(300.0);
        assertThat(result.getCantidadProductos()).isEqualTo(3);

        // Verificar que los métodos del repositorio fueron llamados correctamente
        verify(carroComprasRepository, times(1)).findById(Long.valueOf(1L));
        verify(carroComprasRepository, times(1)).save(any(CarroCompras.class));
    }

    @Test
    @DisplayName("Eliminar carro de compras")
    public void shouldDeleteCarroCompras() {
        // Simular que el carro existe
        when(carroComprasRepository.findById(Long.valueOf(1L))).thenReturn(java.util.Optional.of(carroComprasTest));

        // Llamar al método de eliminar
        carroComprasService.delete(Long.valueOf(1L));

        // Verificar que el repositorio de eliminación fue llamado
        verify(carroComprasRepository, times(1)).deleteById(Long.valueOf(1L));
    }

    @Test
    @DisplayName("Obtener carro de compras por ID")
    public void shouldGetCarroComprasById() {
        // Simular que el carro de compras existe en el repositorio
        when(carroComprasRepository.findById(Long.valueOf(1L))).thenReturn(java.util.Optional.of(carroComprasTest));

        // Llamar al método de obtener carro por ID
        CarroCompras result = carroComprasService.findById(Long.valueOf(1L));

        // Verificar que el carro obtenido no sea nulo
        assertThat(result).isNotNull();
        // Verificar que el carro obtenido es el esperado
        assertThat(result).isEqualTo(carroComprasTest);

        // Verificar que el repositorio fue llamado una vez con el ID
        verify(carroComprasRepository, times(1)).findById(Long.valueOf(1L));
    }

    @Test
    @DisplayName("Verificar si un producto se encuentra en el carrito")
    public void shouldVerifyProductoInCarro() {
        // Simular la respuesta del servicio de productos
        when(productoClientRest.findById(Long.valueOf(1L))).thenReturn(new ProductoDTO(1L, "Producto Test", 100.0));

        // Llamar al método de agregar producto al carrito
        boolean result = carroComprasService.addProductoToCarro(Long.valueOf(1L), Long.valueOf(1L), 2);

        // Verificar que el producto se agregó correctamente
        assertThat(result).isTrue();

        // Verificar que el cliente consultó el servicio de productos
        verify(productoClientRest, times(1)).findById(Long.valueOf(1L));
    }
}
