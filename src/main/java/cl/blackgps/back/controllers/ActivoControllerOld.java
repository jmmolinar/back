package cl.blackgps.back.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.blackgps.back.entities.ActivoOld;
import cl.blackgps.back.services.ActivoServiceOld;

@RestController
/*@RequestMapping*/
@RequestMapping(path = "api/v1")
public class ActivoControllerOld {

    @Autowired
    ActivoServiceOld activoServiceOld;

    @GetMapping("/activos")
    public ArrayList<ActivoOld> obtenerActivos(){
        return activoServiceOld.obtenerActivos();
    }

    @PostMapping("/guardar")
    public ActivoOld guardarActivo(@RequestBody ActivoOld activo){
        return this.activoServiceOld.guardarActivo(activo);
        /*return activoService.guardarActivo(activo);*/
    }

    /*@GetMapping( path = "/{id}")*/
    @GetMapping("/activos/{id}")
    public Optional<ActivoOld> obtenerActivoPorId(@PathVariable("id") int idActivo){
        return this.activoServiceOld.obtenerPorId(idActivo);
    }

    @GetMapping("/anios/{id}")
    public ArrayList<ActivoOld> obtenerActivosPorAnio(@PathVariable("id") int anio){
        return this.activoServiceOld.obtenerPorAnio(anio);
    }

    @GetMapping("/vehiculos/{id}")
    public ArrayList<ActivoOld> obtenerActivosPorVehiculos(@PathVariable("id") int idVehiculo){
        return this.activoServiceOld.obtenerPorVehiculos(idVehiculo);
    }

    @GetMapping("/tipos/{id}") /** CONSULTANDO LOS TIPOS CON URL ESPECÍFICA DE TIPOS */
    public ArrayList<ActivoOld> obtenerActivosPorTipo(@PathVariable("id") int tipoActivoIdTipoActivo){
        return this.activoServiceOld.obtenerPorTipo(tipoActivoIdTipoActivo);
    }
    
    /** CONSULTANDO MEDIANTE /query?tipo=valor */
    @GetMapping("/query") 
    public ArrayList<ActivoOld> obtenerActivoPorTipoConQuery(@RequestParam("tipo") int tipoActivoIdTipoActivo){
        return this.activoServiceOld.obtenerPorTipo(tipoActivoIdTipoActivo);
    }

    /** CONSULTANDO MEDIANTE /consulta?area=valor */
    @GetMapping("/consulta") 
    public ArrayList<ActivoOld> obtenerActivoPorAreaConQuery(@RequestParam("area") int areaIdArea){
        return this.activoServiceOld.obtenerPorArea(areaIdArea);
    }

    @DeleteMapping("/activos/{id}") /* SELECCIONANDO DELETE EN POSTMAN */
    public String eliminarPorId(@PathVariable("id") int idActivo){
        boolean ok = this.activoServiceOld.eliminarActivo(idActivo);
        if(ok){
            return "Se eliminó el activo con idActivo: " + idActivo;
        } else {
            return "No se pudo eliminar el activo con idActivo: " + idActivo;
        }
    }

}
