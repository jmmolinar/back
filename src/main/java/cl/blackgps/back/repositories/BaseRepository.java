package cl.blackgps.back.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/*import cl.blackgps.back.entities.Base;*/

@NoRepositoryBean /* Para que de ésta intarface no se puedan crear instancias*/
/*public interface BaseRepository <E extends Base, ID extends Serializable> extends JpaRepository<E, ID> {*/

public interface BaseRepository <E, ID extends Serializable> extends JpaRepository<E, ID> {

}

/*public interface BaseRepository <E, ID extends Serializable> extends CrudRepository<E, ID> {

}*/
