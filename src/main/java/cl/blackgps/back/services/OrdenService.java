package cl.blackgps.back.services;

import java.util.List;

import cl.blackgps.back.entities.Orden;
import cl.blackgps.back.entities.OrdenHasEstado;

public interface OrdenService extends BaseService<Orden, Integer> {

    ////////////////////////
    public Orden saveOrden(Orden entity, List<OrdenHasEstado> ordenEstados) throws Exception;
    ////////////////////////

}
