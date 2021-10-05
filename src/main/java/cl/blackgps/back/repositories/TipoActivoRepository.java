package cl.blackgps.back.repositories;

/*import org.springframework.data.jpa.repository.JpaRepository;*/
/*import org.springframework.data.repository.CrudRepository;*/
import org.springframework.stereotype.Repository;

import cl.blackgps.back.entities.TipoActivo;

@Repository
/*public interface TipoActivoRepository extends JpaRepository<TipoActivo, Integer>{*/
public interface TipoActivoRepository extends BaseRepository<TipoActivo, Integer>{
    
}
