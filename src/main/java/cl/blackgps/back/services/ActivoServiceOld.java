package cl.blackgps.back.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.blackgps.back.entities.ActivoOld;
import cl.blackgps.back.repositories.ActivoRepositoryOld;

@Service
public class ActivoServiceOld {

    @Autowired
    ActivoRepositoryOld activoRepositoryOld;

    public ArrayList<ActivoOld> obtenerActivos(){
        return (ArrayList<ActivoOld>) activoRepositoryOld.findAll();
    }

    public ActivoOld guardarActivo(ActivoOld activo){
        return activoRepositoryOld.save(activo);
    }

    public Optional<ActivoOld> obtenerPorId(int idActivo){
        return activoRepositoryOld.findById(idActivo);
    }

    public ArrayList<ActivoOld> obtenerPorAnio(int anio){
        return activoRepositoryOld.findByAnio(anio);
    }

    public ArrayList<ActivoOld> obtenerPorVehiculos(int idVehiculo){
        return activoRepositoryOld.findByIdVehiculo(idVehiculo);
    }

    public ArrayList<ActivoOld> obtenerPorTipo(int tipoActivoIdTipoActivo){
        return activoRepositoryOld.findByTipoActivoIdTipoActivo(tipoActivoIdTipoActivo);
    }

    public ArrayList<ActivoOld> obtenerPorArea(int areaIdArea){
        return activoRepositoryOld.findByAreaIdArea(areaIdArea);
    }



    public boolean eliminarActivo(int idActivo){
        try{
            activoRepositoryOld.deleteById(idActivo);
            return true;
        }catch(Exception err){
            return false;
        }
    }
    
}
