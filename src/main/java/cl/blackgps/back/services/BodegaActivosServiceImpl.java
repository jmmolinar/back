package cl.blackgps.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.blackgps.back.repositories.BaseRepository;
import cl.blackgps.back.repositories.BodegaActivosRepository;
import cl.blackgps.back.entities.BodegaActivos;

@Service
public class BodegaActivosServiceImpl extends BaseServiceImpl<BodegaActivos, Integer> implements BodegaActivosService{

    @Autowired
    BodegaActivosRepository bodegaActivosRepository;

    public BodegaActivosServiceImpl(BaseRepository<BodegaActivos, Integer> baseRepository){
        super(baseRepository);
    }
}
