package cl.blackgps.back.services;

import java.util.List;

import cl.blackgps.back.entities.Activo;
import cl.blackgps.back.dto.ActivoDTO;

public interface ActivoService extends BaseService<Activo, Integer>{
    
    //Query SQL que viene de ActivoRepository
    List<Activo> search(String area) throws Exception;

    //Optional<Activo> consultarActivo(int identificador) throws Exception;

    List<Activo> consultarActivo(int identificador) throws Exception;

    void eliminarActivo(int id) throws Exception;

    List<ActivoDTO> consultarActivoDTO(int identificador) throws Exception;

}
