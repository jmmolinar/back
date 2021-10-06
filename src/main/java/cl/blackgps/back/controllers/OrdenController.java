package cl.blackgps.back.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.blackgps.back.entities.Orden;
import cl.blackgps.back.services.OrdenServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v2/ordenes")
public class OrdenController extends BaseControllerImpl<Orden, OrdenServiceImpl>{

    ////////////////////////////////////////////////
    /*@PostMapping("/guardar")
    public ResponseEntity<?> save(@RequestBody Orden entity){ // Para recibir una entidad debo declararla en el body del PostRequest, por eso el RequestBody
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.saveOrden(entity, entity.getOrdenEstados()));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. desde Orden Controller.\"}");
        }
    }*/
    ////////////////////////////////////////////////
    
}
