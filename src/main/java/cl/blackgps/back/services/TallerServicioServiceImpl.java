package cl.blackgps.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.blackgps.back.repositories.BaseRepository;
import cl.blackgps.back.repositories.TallerServicioRepository;
import cl.blackgps.back.entities.TallerServicio;

@Service
public class TallerServicioServiceImpl extends BaseServiceImpl<TallerServicio, Integer> implements TallerServicioService{

    @Autowired
    TallerServicioRepository tallerServicioRepository;

    public TallerServicioServiceImpl(BaseRepository<TallerServicio, Integer> baseRepository){
        super(baseRepository);
    }
}