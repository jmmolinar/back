package cl.blackgps.back.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

/*import cl.blackgps.back.entities.Base;*/
import cl.blackgps.back.repositories.BaseRepository;

/*public abstract class BaseServiceImpl<E extends Base, ID extends Serializable> implements BaseService<E, ID> {*/
public abstract class BaseServiceImpl<E, ID extends Serializable> implements BaseService<E, ID> {
    protected BaseRepository<E, ID> baseRepository; // protected para que lo puedan utilizar las clases que heredan de BaseService.java

    public BaseServiceImpl(BaseRepository<E, ID> baseRepository){
        this.baseRepository = baseRepository;
    }

    @Override
    @Transactional
    public List<E> findAll() throws Exception {
        try{
            List<E> entities = baseRepository.findAll();
            //List<E> entities = (List<E>)baseRepository.findAll();
            // entities.forEach(System.out::println);
            return entities;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E findById(ID id) throws Exception {
        try{
            Optional<E> entityOptional = baseRepository.findById(id);
            return entityOptional.get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E save(E entity) throws Exception {
        try{
            entity = baseRepository.save(entity);
            return entity;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E update(ID id, E entity) throws Exception {
        try{
            Optional<E> entityOptional = baseRepository.findById(id);
            E entityUpdate = entityOptional.get();
            entityUpdate = baseRepository.save(entity);
            return entityUpdate;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(ID id) throws Exception {
        try{
            if(baseRepository.existsById(id)){
                baseRepository.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /*@Override
    @Transactional
    public void delete(ID id) throws Exception {
        try{
            baseRepository.deleteById(id);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }*/
    
}
