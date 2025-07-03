package com.perfulandia.msvc.carrocompras.init;

import com.perfulandia.msvc.carrocompras.model.entities.CarroCompras;
import com.perfulandia.msvc.carrocompras.repository.CarroComprasRepository;
import net.datafaker.Faker;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;


@Profile("dev")
@Component

public class LoadDataBase implements CommandLineRunner {
    @Autowired
    private CarroComprasRepository carroComprasRepository;

    private static final Logger logger = LoggerFactory.getLogger(LoadDataBase.class);

    @Override
    public void run (String... args) throws Exception {
        Faker faker = new Faker(Locale.of("es", "CL"));
        for (int i = 0; i < 10; i++) {
            CarroCompras carroCompras = new CarroCompras();
        }
    }
}
