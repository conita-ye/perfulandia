package com.perfulandia.msvc.boleta.init;

import com.perfulandia.msvc.boleta.model.entities.Boleta;
import com.perfulandia.msvc.boleta.repository.BoletaRepository;
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
    private BoletaRepository boletaRepository;

    private static final Logger logger = LoggerFactory.getLogger(LoadDataBase.class);

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.of("es", "CL"));
        for (int i = 0; i < 10; i++) {
            Boleta boleta = new Boleta();

        }
    }
}
