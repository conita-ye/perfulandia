package com.perfulandia.msvc.producto.init;


import com.perfulandia.msvc.producto.model.entities.Producto;
import com.perfulandia.msvc.producto.repository.ProductoRepository;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


import java.util.Locale;

@Profile("dev")
@Component
public class LoadDataBase implements CommandLineRunner {
    @Autowired
    private ProductoRepository productoRepository;

    private static final Logger logger = LoggerFactory.getLogger(LoadDataBase.class);

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.of("es","CL"));
        for (int i = 0; i < 10; i++) {
            Producto producto = new Producto();

            producto.setNombreProducto(faker.commerce().productName());
            producto.setCategoria(faker.commerce().department());

            // mas que nada en el codigo "msvc-hospital" es para idedntificar el run del medico,
            // en este caso deberiamos identificar el producto que se va a generar



        }
    }
}
