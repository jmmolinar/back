package cl.blackgps.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.blackgps.back.entities.TipoOrden;
import cl.blackgps.back.repositories.BaseRepository;
import cl.blackgps.back.repositories.TipoOrdenRepository;

@Service
public class TipoOrdenServiceImpl extends BaseServiceImpl<TipoOrden, Integer> implements TipoOrdenService{
    
    @Autowired
    TipoOrdenRepository tipoOrdenRepository;

    public TipoOrdenServiceImpl(BaseRepository<TipoOrden, Integer> baseRepository){
        super(baseRepository);
    }
}
