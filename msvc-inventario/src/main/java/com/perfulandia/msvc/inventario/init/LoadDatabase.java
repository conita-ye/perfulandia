package com.perfulandia.msvc.inventario.init;


import com.perfulandia.msvc.inventario.model.entities.Inventario;
import com.perfulandia.msvc.inventario.repository.InventarioRepository;
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

public class LoadDatabase implements CommandLineRunner {
    @Autowired
    private InventarioRepository inventarioRepository;

   private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

   @Override
    public  void  run (String... args)throws Exception {
       Faker faker = new Faker(Locale.of("es", "CL"));
       for (int i = 0; i < 10; i ++) {
           Inventario inventario = new Inventario();
       }
   }

}
