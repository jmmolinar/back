package cl.blackgps.back.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.blackgps.back.entities.Activo;
import cl.blackgps.back.dto.ActivoDTO;
import cl.blackgps.back.repositories.ActivoRepository;
import cl.blackgps.back.repositories.BaseRepository;

@Service
public class ActivoServiceImpl extends BaseServiceImpl<Activo, Integer> implements ActivoService {

    @Autowired
    // private ActivoRepository activoRepository;
    ActivoRepository activoRepository;
    //DocumentoRepository documentoRepository;

    public ActivoServiceImpl(BaseRepository<Activo, Integer> baseRepository) {
        super(baseRepository);
    }

    // Query SQL que viene de ActivoRepository - ActivoService
    @Override
    public List<Activo> search(String area) throws Exception {
        try {
            List<Activo> activos = activoRepository.searchNativo(area);
            return activos;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // Query SQL que viene de ActivoRepository - ActivoService
    @Override
    public List<Activo> consultarActivo(int identificador) throws Exception {
        try {
            List<Activo> activo = activoRepository.consultarActivo(identificador);
            return activo;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    //@Transactional
    public void eliminarActivo(int id) throws Exception {
        try {
            activoRepository.eliminarActivo(id);
            //return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    @Override
    public List<ActivoDTO> consultarActivoDTO(int identificador) throws Exception {
        try {
            List<ActivoDTO> activo = activoRepository.consultarActivoDTO(identificador);
            return activo;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /*
     * @Override public Optional<Activo> consultarActivo(int identificador) throws
     * Exception { try{ Optional<Activo> activo =
     * activoRepository.consultarActivo(identificador); return activo; } catch
     * (Exception e){ throw new Exception(e.getMessage()); } }
     */

}
