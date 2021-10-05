package cl.blackgps.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.blackgps.back.entities.CategoriaServicio;
import cl.blackgps.back.repositories.BaseRepository;
import cl.blackgps.back.repositories.CategoriaServicioRepository;

@Service
public class CategoriaServicioServiceImpl extends BaseServiceImpl<CategoriaServicio, Integer> implements CategoriaServicioService{

    @Autowired
    CategoriaServicioRepository categoriaServicioRepository;

    public CategoriaServicioServiceImpl(BaseRepository<CategoriaServicio, Integer> baseRepository){
        super(baseRepository);
    }
}