package cl.blackgps.back.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import org.springframework.web.bind.annotation.PathVariable;

import cl.blackgps.back.entities.Activo;
import cl.blackgps.back.dto.ActivoDTO;

@Repository
public interface ActivoRepository extends BaseRepository<Activo, Integer> {

    // Query SQL
    // De la mano con ActivoService
    @Query(value = "SELECT * FROM activo WHERE activo.area_id_area LIKE %:area%", nativeQuery = true)
    List<Activo> searchNativo(@Param("area") String area);

    // Query SQL
    // De la mano con ActivoService
    @Query(value = "SELECT " 
            + "activo.id_activo, " 
            + "activo.id_vehiculo, "
            + "activo.area_id_area, " 
            + "activo.bodega_activos_id_bodega_activos, "
            + "bodega_activos.nombre AS 'BODEGA', " 
            + "activo.tipo_activo_id_tipo_activo, "
            + "tipo_activo.nombre AS 'TIPO', " 
            + "activo.anio AS anio, " 
            + "plan_mantenimiento.id_plan_mantenimiento, "
            + "plan_mantenimiento.nombre, " 
            + "activo.dado_de_baja " 
            + "FROM " 
            + "activo " 
            + "LEFT JOIN "
            + "tipo_activo ON activo.tipo_activo_id_tipo_activo = tipo_activo.id_tipo_activo " 
            + "LEFT JOIN "
            + "bodega_activos ON activo.bodega_activos_id_bodega_activos = bodega_activos.id_bodega_activos "
            + "LEFT JOIN "
            + "activo_has_plan_mantenimiento ON activo.id_activo = activo_has_plan_mantenimiento.activo_id_activo "
            + "LEFT JOIN "
            + "plan_mantenimiento ON activo_has_plan_mantenimiento.plan_mantenimiento_id_plan_mantenimiento = plan_mantenimiento.id_plan_mantenimiento "
            + "WHERE " 
            + "activo.id_activo = :identificador " 
            + "ORDER BY activo.id_activo", nativeQuery = true)
    List<Activo> consultarActivo(@Param("identificador") int identificador);

    /** ELIMINAR NATIVO */
    @Modifying
    @Transactional
    @Query(
        value = "DELETE FROM activo WHERE id_activo = :id",
        nativeQuery = true    
    )
    void eliminarActivo(int id);
    
    @Query(
        name = "consultarActivoDTO",
        nativeQuery = true)
    List<ActivoDTO> consultarActivoDTO(@Param("identificador") int identificador);
}
