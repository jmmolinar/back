package cl.blackgps.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.blackgps.back.entities.Estado;
import cl.blackgps.back.repositories.BaseRepository;
import cl.blackgps.back.repositories.EstadoRepository;

@Service
public class EstadoServiceImpl extends BaseServiceImpl<Estado, Integer> implements EstadoService{
    
    @Autowired
    EstadoRepository estadoRepository;

    public EstadoServiceImpl(BaseRepository<Estado, Integer> baseRepository){
        super(baseRepository);
    }
}
