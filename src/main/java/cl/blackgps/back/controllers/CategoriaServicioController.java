package cl.blackgps.back.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.blackgps.back.entities.CategoriaServicio;
import cl.blackgps.back.services.CategoriaServicioServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v2/categorias")
public class CategoriaServicioController extends BaseControllerImpl<CategoriaServicio, CategoriaServicioServiceImpl> {
    
}
