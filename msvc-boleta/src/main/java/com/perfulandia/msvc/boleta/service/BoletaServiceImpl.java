package com.perfulandia.msvc.boleta.service;

import com.perfulandia.msvc.boleta.clients.ProductoClientRest;
import com.perfulandia.msvc.boleta.exceptions.BoletaException;
import com.perfulandia.msvc.boleta.model.entities.Boleta;
import com.perfulandia.msvc.boleta.repository.BoletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoletaServiceImpl implements BoletaService {
        @Autowired
        private BoletaRepository boletaRepository;
        @Autowired
        private ProductoClientRest productoClientRest;

        @Override
        public List<Boleta> listarBoleta () {
            return this.boletaRepository.findAll();
        }

        @Override
        public Boleta findById(Long id) {
            return this.boletaRepository.findById(id).orElseThrow(
                    () -> new BoletaException("La boleta con id"+id+" no se encuentra en la base de datos")
            );
        }

        @Override
        public Boleta save (Boleta boleta) {
            return this.boletaRepository.save(boleta);
        }

        @Override
    public void eliminarBoleta (Long id) {
            boletaRepository.deleteById(id);
        }

}
