package cl.blackgps.back.controllers;

//import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.blackgps.back.entities.Activo;
import cl.blackgps.back.services.ActivoServiceImpl;

@RestController
@CrossOrigin(origins = "*") /* Para permitir el acceso a la API desde distintos origenes*/
@RequestMapping(path = "api/v2/activos")
public class ActivoController extends BaseControllerImpl<Activo, ActivoServiceImpl>{

    //Query SQL que viene de ActivoRepository - ActivoService - ActivoServiceImpl
    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String area){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.search(area));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    //Query SQL que viene de ActivoRepository - ActivoService - ActivoServiceImpl
    @GetMapping("/consultaractivo")
    public ResponseEntity<?> consultarActivo(@RequestParam int identificador){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.consultarActivo(identificador));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarActivo(@PathVariable Integer id){
        try{
            System.out.print("Entré a eliminar activo ");
            //return ResponseEntity.status(HttpStatus.NO_CONTENT).body(servicio.eliminarActivo(id));*/
            servicio.eliminarActivo(id);
            return ResponseEntity.status(HttpStatus.GONE).body("Eliminado: " + id);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }


    @GetMapping("/consultaractivodto")
    public ResponseEntity<?> consultarActivoDto(@RequestParam int identificador){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.consultarActivoDTO(identificador));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

}
