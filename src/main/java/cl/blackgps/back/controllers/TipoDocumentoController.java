package cl.blackgps.back.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.blackgps.back.entities.TipoDocumento;
import cl.blackgps.back.services.TipoDocumentoServiceImpl;

@RestController
@CrossOrigin(origins = "*") /* Para permitir el acceso a la API desde distintos origenes*/
@RequestMapping(path = "api/v2/tiposdocumentos")
public class TipoDocumentoController extends BaseControllerImpl<TipoDocumento, TipoDocumentoServiceImpl> {
    
}