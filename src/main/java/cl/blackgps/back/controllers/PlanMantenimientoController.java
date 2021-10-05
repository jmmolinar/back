package cl.blackgps.back.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.blackgps.back.entities.PlanMantenimiento;
import cl.blackgps.back.services.PlanMantenimientoServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v2/planesmantenimientos")
public class PlanMantenimientoController extends BaseControllerImpl<PlanMantenimiento, PlanMantenimientoServiceImpl> {
    
}
