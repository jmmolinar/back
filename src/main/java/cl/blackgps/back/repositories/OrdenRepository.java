package cl.blackgps.back.repositories;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.blackgps.back.entities.Orden;

@Repository
public interface OrdenRepository extends BaseRepository<Orden, Integer> {

    /** Guardar OrdenHasEstado */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO orden_has_estado " + "(fecha_asignado, id_usuario, estado_id_estado, orden_id_orden) "
            + "values (:fechaAsignado, :idUsuario, :idEstado, :idOrden)", nativeQuery = true)
    void saveOrdenHasEstado(LocalDateTime fechaAsignado, int idUsuario, Integer idEstado, Integer idOrden);

}
