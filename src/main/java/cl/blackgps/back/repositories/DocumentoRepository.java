package cl.blackgps.back.repositories;

import org.springframework.stereotype.Repository;

import cl.blackgps.back.entities.Documento;

@Repository
public interface DocumentoRepository extends BaseRepository<Documento, Integer>{
    
}
