package cl.blackgps.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.blackgps.back.entities.PlanMantenimiento;
import cl.blackgps.back.repositories.BaseRepository;
import cl.blackgps.back.repositories.PlanMantenimientoRepository;

@Service
public class PlanMantenimientoServiceImpl extends BaseServiceImpl<PlanMantenimiento, Integer> implements PlanMantenimientoService{

    @Autowired
    PlanMantenimientoRepository planMantenimientoRepository;


    public PlanMantenimientoServiceImpl(BaseRepository<PlanMantenimiento, Integer> baseRepository) {
        super(baseRepository);
    }
    
    
}
