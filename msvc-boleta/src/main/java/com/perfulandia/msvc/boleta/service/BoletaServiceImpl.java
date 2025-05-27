package com.perfulandia.msvc.boleta.service;

import com.perfulandia.msvc.boleta.model.Boleta;
import com.perfulandia.msvc.boleta.repository.BoletaRepository;
import com.perfulandia.msvc.boleta.service.BoletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class BoletaServiceImpl {
        @Autowired
        private BoletaRepository boletaRepository;

        @Override
        public List<Boleta> finAll() {
            return this.boletaRepository.findAll();
        }

        @Override
        public Boleta findById(Long id) {
            return this.boletaRepository.findById(id).orElseThrow(
                    () -> new BoletaException ("La boleta con id"+id+" no se encuentra en la base de datos")
            );
        }

        @Override
        public Boleta save(Boleta boleta) {
            return this.boletaRepository.save(boleta);
        }
}
