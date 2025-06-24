package com.perfulandia.msvc.cliente.init;

import com.perfulandia.msvc.cliente.model.entities.Cliente;
import com.perfulandia.msvc.cliente.repository.ClienteRepository;
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
    private ClienteRepository clienteRepository;

    private static final Logger logger = LoggerFactory.getLogger(LoadDataBase.class);

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.of("es", "CL"));
        for (int i = 0; i < 10; i++) {
            Cliente cliente = new Cliente();

        }
    }
}
