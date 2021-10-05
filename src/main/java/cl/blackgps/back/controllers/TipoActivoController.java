package cl.blackgps.back.controllers;

/*import org.springframework.beans.factory.annotation.Autowired;*/

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.blackgps.back.entities.TipoActivo;
import cl.blackgps.back.services.TipoActivoServiceImpl;

/* import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import cl.blackgps.back.entities.TipoActivo; */


@RestController
@CrossOrigin(origins = "*") /* Para permitir el acceso a la API desde distintos origenes*/
@RequestMapping(path = "api/v2/tiposactivos")
public class TipoActivoController extends BaseControllerImpl<TipoActivo, TipoActivoServiceImpl>{

}

/*public class TipoActivoController {
    
    //@Autowired
    //private TipoActivoServiceImpl tipoActivoServiceImpl;

    public TipoActivoController(TipoActivoServiceImpl tipoActivoServiceImpl){
        this.tipoActivoServiceImpl = tipoActivoServiceImpl;
    }

    
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(tipoActivoServiceImpl.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tardesss.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id){ // @PathVariable para indicar que accederá a la variable de la URL para obtener el recurso
        try{
            return ResponseEntity.status(HttpStatus.OK).body(tipoActivoServiceImpl.findById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody TipoActivo entity){ // Para recibir una entidad debo declararla en el body del PostRequest, por eso el RequestBody
        try{
            return ResponseEntity.status(HttpStatus.OK).body(tipoActivoServiceImpl.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody TipoActivo entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(tipoActivoServiceImpl.update(id, entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(tipoActivoServiceImpl.delete(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
    
}*/
