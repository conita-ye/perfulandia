package services;


import com.perulandia.msvc.sucursal.model.entities.Sucursal;
import com.perulandia.msvc.sucursal.repository.SucursalRepository;
import com.perulandia.msvc.sucursal.service.SucursalServiceImpl;
import com.perulandia.msvc.sucursal.exception.SucursalException;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SucursalServiceTest {
    @Mock
    private SucursalRepository sucursalRepository;

    @InjectMocks
    private SucursalServiceImpl sucursalService;
    private Sucursal sucursalPrueba;
    private List<Sucursal> sucursal = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        this.sucursalPrueba = new Sucursal(
        );
        Faker faker = new Faker(Locale.of("es","CL"));
        for(int i=0;i<100;i++){
            Sucursal sucursal = new Sucursal();

            String nombre = faker.company().name();
            String direccion = faker.address().fullAddress();
            String telefono = faker.phoneNumber().cellPhone();
            String email = faker.internet().emailAddress();
            String web = faker.internet().domainName();

        }
    }

    @Test
    @DisplayName("Debe buscar un medico un I que n existe")
    public void shouldNotFindMedicoId(){
        Long idInexistente = (Long) 999L;
        when(sucursalRepository.findById(idInexistente)).thenReturn(Optional.empty());
        assertThatThrownBy(()->{
            sucursalService.findById(idInexistente);
        }).isInstanceOf(sucursalException.class)
                .hasMessageContaining("El medico con id " +
                        idInexistente + " no se encuentra en la base de datos");
        verify(sucursalRepository, times(1)).findById(idInexistente);
    }

    @Test
    @DisplayName("Debe guardar un nuevo medico")
    public void shouldSaveMedico() {
        when(sucursalRepository.save(any(Sucursal.class))).thenReturn(sucursalPrueba);
        Sucursal result = sucursalService.save(sucursalPrueba);
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(sucursalPrueba);
        verify(sucursalRepository, times(1)).save(any(Sucursal.class));
    }
