package cl.blackgps.back.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.blackgps.back.entities.Documento;
import cl.blackgps.back.services.DocumentoServiceImpl;

@RestController
@CrossOrigin(origins = "*") /* Para permitir el acceso a la API desde distintos origenes*/
@RequestMapping(path = "api/v2/documentos")
public class DocumentoController extends BaseControllerImpl<Documento, DocumentoServiceImpl> {
    
}