package cl.blackgps.back.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import cl.blackgps.back.entities.Orden;
import cl.blackgps.back.entities.OrdenHasEstado;
import cl.blackgps.back.repositories.BaseRepository;
import cl.blackgps.back.repositories.OrdenRepository;

@Service
public class OrdenServiceImpl extends BaseServiceImpl<Orden, Integer> implements OrdenService{
    
    @Autowired
    OrdenRepository ordenRepository;

    public OrdenServiceImpl(BaseRepository<Orden, Integer> baseRepository){
        super(baseRepository);
    }

    ///////////////////////
    @Override
    @Transactional
    public Orden saveOrden(Orden entity, List<OrdenHasEstado> ordenEstados) throws Exception {
        try{


            Orden newEntity = new Orden();
            newEntity = entity;

            //newEntity = ordenRepository.save(entity);

            for(int i = 0; i < ordenEstados.size(); i++){
                
                if(ordenEstados.get(i).getOrden() == null){
                    System.out.println("Entré al IF");
                    ordenEstados.get(i).setOrden(newEntity);
                    System.out.println("iD ORDEN DENTRO IF: " + ordenEstados.get(i).getOrden().getIdOrden());
                }
                ordenEstados.get(i).setOrden(newEntity);
                //ordenEstados.get(i).setOrdenIdOrden(newEntity.getIdOrden());
                System.out.println("Orden Has Estados en SAVEORDEN");
                System.out.println(ordenEstados.get(i).toString());

                //ordenHasEstadoRepository.saveOrdenHasEstado(oe.get(i).getFechaAsignado(), oe.get(i).getIdUsuario(), oe.get(i).getEstadoIdEstado(),oe.get(i).getOrdenIdOrden());

                //ordenRepository.saveOrdenHasEstado(ordenEstados.get(i).getFechaAsignado(), ordenEstados.get(i).getIdUsuario(), ordenEstados.get(i).getEstadoIdEstado(),ordenEstados.get(i).getOrdenIdOrden());

            }

            newEntity.setOrdenEstados(ordenEstados);
            System.out.println("Estados de la orden:");
            System.out.println(entity.getOrdenEstados());

            newEntity = ordenRepository.save(newEntity);

            return newEntity;
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
    ///////////////////////
}
