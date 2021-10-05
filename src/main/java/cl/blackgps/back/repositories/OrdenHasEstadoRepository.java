package cl.blackgps.back.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import cl.blackgps.back.entities.OrdenHasEstado;
import cl.blackgps.back.entities.OrdenHasEstadoId;

@Repository
public interface OrdenHasEstadoRepository extends BaseRepository<OrdenHasEstado, OrdenHasEstadoId>{

    @Query(value = "SELECT * FROM orden_has_estado WHERE orden_has_estado.orden_id_orden = :id", nativeQuery = true)
    List<OrdenHasEstado> consultarOrdenHasEstado(@Param("id") Integer id);

    /** Guardar OrdenHasEstado */
    /*@Modifying
    @Transactional
    @Query(
        value = "INSERT INTO orden_has_estado "
        + "(fecha_asignado, id_usuario, estado_id_estado, orden_id_orden) "
        + "values (:fechaAsignado, :idUsuario, :idEstado, :idOrden)",
        nativeQuery = true    
    )
    void saveOrdenHasEstado(LocalDateTime fechaAsignado, int idUsuario, Integer idEstado, Integer idOrden);*/

}
