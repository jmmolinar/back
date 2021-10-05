package cl.blackgps.back.services;

/*import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.blackgps.back.repositories.BaseRepository;
import cl.blackgps.back.repositories.TipoActivoRepository;
import cl.blackgps.back.entities.TipoActivo;

@Service
public class TipoActivoServiceImpl extends BaseServiceImpl<TipoActivo, Integer> implements TipoActivoService{

    @Autowired
    //private TipoActivoRepository tipoActivoRepository;
    TipoActivoRepository tipoActivoRepository;

    public TipoActivoServiceImpl(BaseRepository<TipoActivo, Integer> baseRepository){
        super(baseRepository);
    }
}


/* public class TipoActivoService implements BaseService<TipoActivo> {

    private TipoActivoRepository tipoActivoRepository;

    public TipoActivoService(TipoActivoRepository tipoActivoRepository) {
        this.tipoActivoRepository = tipoActivoRepository;
    }

    @Override
    @Transactional
    public List<TipoActivo> findAll() throws Exception {
        try{
            List<TipoActivo> entities = tipoActivoRepository.findAll();
            return entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public TipoActivo findById(Integer id) throws Exception {
        try{
            Optional<TipoActivo> entityOptional = tipoActivoRepository.findById(id);
            return entityOptional.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public TipoActivo save(TipoActivo entity) throws Exception {
        try{
            entity = tipoActivoRepository.save(entity);
            return entity;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public TipoActivo update(Integer id, TipoActivo entity) throws Exception {
        try{
            Optional<TipoActivo> entityOptional = tipoActivoRepository.findById(id);
            TipoActivo tipoActivo = entityOptional.get();
            tipoActivo = tipoActivoRepository.save(entity);
            return tipoActivo;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(Integer id) throws Exception {
        try{
            if(tipoActivoRepository.existsById(id)){
                tipoActivoRepository.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}*/
