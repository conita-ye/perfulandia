package com.perfulandia.msvc.inventario;

import com.perfulandia.msvc.inventario.clients.ProductoClientRest;
import com.perfulandia.msvc.inventario.model.Producto;
import com.perfulandia.msvc.inventario.model.entities.Inventario;
import com.perfulandia.msvc.inventario.repository.InventarioRepository;
import com.perfulandia.msvc.inventario.service.InventarioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;




@ExtendWith(MockitoExtension.class)
public class InventarioServiceTests {

	@Mock
	private ProductoClientRest productoClientRest;

	@Mock
	private InventarioRepository inventarioRepository;

	@InjectMocks
			private InventarioServiceImpl inventarioService;
	private Producto productoTest;
	private Inventario inventarioTest;



	@BeforeEach
	public void setUP();{
		productoTest = new Producto();


	}




	{
	}

}
