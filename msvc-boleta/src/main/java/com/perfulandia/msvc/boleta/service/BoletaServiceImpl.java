package com.perfulandia.msvc.boleta.service;

import com.perfulandia.msvc.boleta.clients.CarroComprasClientRest;
import com.perfulandia.msvc.boleta.clients.ClienteClientRest;
import com.perfulandia.msvc.boleta.clients.ProductoClientRest;
import com.perfulandia.msvc.boleta.exceptions.BoletaException;
import com.perfulandia.msvc.boleta.model.CarroCompras;
import com.perfulandia.msvc.boleta.model.Cliente;
import com.perfulandia.msvc.boleta.model.Producto;
import com.perfulandia.msvc.boleta.model.entities.Boleta;
import com.perfulandia.msvc.boleta.repository.BoletaRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoletaServiceImpl implements BoletaService {
        @Autowired
        private BoletaRepository boletaRepository;
        @Autowired
        private CarroComprasClientRest carroComprasClientRest;
        @Autowired
        private ClienteClientRest clienteClientRest;
        @Autowired
        private ProductoClientRest productoClientRest;

        @Override
        public List<Boleta> findAll () {
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

            try {
                CarroCompras carroCompras = this.carroComprasClientRest.findById(boleta.getIdCarroCompras());
            }
            catch (FeignException exception){
                throw new BoletaException("El carro de compras con id"+boleta.getIdCarroCompras() + " no se encuentra en la base de datos"
                        +" por ende no puedo generar el nexo de relación");
            }

            try {
                Cliente cliente = this.clienteClientRest.findById(boleta.getIdCliente());
            }
            catch (FeignException exception){
                throw new BoletaException ("El cliente con id"+boleta.getIdCliente() + " no se encuentra en la base de datos"
                        + " por ende no puedo generar el nexo de relación");
            }

            try{
                Producto producto = this.productoClientRest.findById(boleta.getIdProducto());
            } catch (FeignException exception) {
                throw new BoletaException ("El producto con id"+boleta.getIdProducto() + "no se encuentra en la base de datos"
                        + "por ende no se puede generar el nexo de relacion");
            }

            return this.boletaRepository.save(boleta);
        }


}
