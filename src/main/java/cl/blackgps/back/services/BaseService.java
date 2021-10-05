package cl.blackgps.back.services;

import java.io.Serializable;
import java.util.List;

/*import cl.blackgps.back.entities.Base;*/

/*public interface BaseService<E extends Base, ID extends Serializable> {*/
public interface BaseService<E, ID extends Serializable> {

    public List<E> findAll() throws Exception;
    /*public E findById(Integer id) throws Exception;*/
    public E findById(ID id) throws Exception;
    public E save(E entity) throws Exception;
    /*public E update(Integer id, E entity) throws Exception;
    public boolean delete(Integer id) throws Exception;*/
    public E update(ID id, E entity) throws Exception;
    public boolean delete(ID id) throws Exception;
    //public void delete(ID id) throws Exception;
    
}
