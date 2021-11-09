package cl.blackgps.back.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.blackgps.back.entities.ActivoOld;

@Repository
public interface ActivoRepositoryOld extends CrudRepository<ActivoOld, Integer>{

    public abstract ArrayList<ActivoOld> findByAnio(int anio);
    public abstract ArrayList<ActivoOld> findByVehiculoIdVehiculo(int vehiculoIdVehiculo);
    public abstract ArrayList<ActivoOld> findByDadoDeBaja(int dadoDeBaja);
    public abstract ArrayList<ActivoOld> findByAreaIdArea(int areaIdArea);
    public abstract ArrayList<ActivoOld> findByTipoActivoIdTipoActivo(int tipoActivoIdTipoActivo);
    public abstract ArrayList<ActivoOld> findByBodegaActivosIdBodegaActivos(int bodegaActivosIdBodegaActivos);
    
}
