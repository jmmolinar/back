package cl.blackgps.back.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.blackgps.back.entities.TipoOrden;
import cl.blackgps.back.services.TipoOrdenServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v2/tiposordenes")
public class TipoOrdenController extends BaseControllerImpl<TipoOrden, TipoOrdenServiceImpl> {
    
}
