package cl.blackgps.back.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.blackgps.back.repositories.BaseRepository;
import cl.blackgps.back.repositories.DocumentoRepository;
import cl.blackgps.back.entities.Documento;

@Service
public class DocumentoServiceImpl extends BaseServiceImpl<Documento, Integer> implements DocumentoService{

    @Autowired
    //private DocumentoRepository documentoRepository;
    DocumentoRepository documentoRepository;

    public DocumentoServiceImpl(BaseRepository<Documento, Integer> baseRepository){
        super(baseRepository);
    }
}