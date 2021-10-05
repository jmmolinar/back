package cl.blackgps.back.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.blackgps.back.repositories.BaseRepository;
import cl.blackgps.back.repositories.TipoDocumentoRepository;
import cl.blackgps.back.entities.TipoDocumento;

@Service
public class TipoDocumentoServiceImpl extends BaseServiceImpl<TipoDocumento, Integer> implements TipoDocumentoService{

    @Autowired
    //private TipoDocumentoRepository tipoDocumentoRepository;
    TipoDocumentoRepository tipoDocumentoRepository;

    public TipoDocumentoServiceImpl(BaseRepository<TipoDocumento, Integer> baseRepository){
        super(baseRepository);
    }
}