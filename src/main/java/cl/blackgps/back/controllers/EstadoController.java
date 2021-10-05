package cl.blackgps.back.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.blackgps.back.entities.Estado;
import cl.blackgps.back.services.EstadoServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v2/estados")
public class EstadoController extends BaseControllerImpl<Estado, EstadoServiceImpl>{
    
}
