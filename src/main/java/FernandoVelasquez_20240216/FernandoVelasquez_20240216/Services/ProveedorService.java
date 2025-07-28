package FernandoVelasquez_20240216.FernandoVelasquez_20240216.Services;

import FernandoVelasquez_20240216.FernandoVelasquez_20240216.Entities.ProveedorEntity;
import FernandoVelasquez_20240216.FernandoVelasquez_20240216.Exceptions.ExceptionProveedorNoEncontrado;
import FernandoVelasquez_20240216.FernandoVelasquez_20240216.Exceptions.ExceptionProveedorNoRegistrado;
import FernandoVelasquez_20240216.FernandoVelasquez_20240216.Models.DTO.ProveedorDTO;
import FernandoVelasquez_20240216.FernandoVelasquez_20240216.Repositories.ProveedorRepository;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProveedorService {
    //Tenemos que inyectar el repository sobre el service

    @Autowired
    ProveedorRepository repo;

    //Convertir los datos entity a DTO
    private ProveedorDTO convertiraDTO(ProveedorEntity entity){
        ProveedorDTO dto = new ProveedorDTO();
        dto.setProviderID(entity.getProviderID());
        dto.setProviderName(entity.getProviderName());
        dto.setProviderPhone(entity.getProviderPhone());
        dto.setProviderAddress(entity.getProviderAddress());
        dto.setProviderEmail(entity.getProviderEmail());
        dto.setProviderCode(entity.getProviderCode());
        dto.setProviderStatus(entity.getProviderStatus());
        dto.setProviderComments(entity.getProviderComments());
        return dto;
    }

    //Convertir los datos DTO a entity
    private ProveedorEntity convertiraEntiy(@Valid ProveedorDTO json){
        ProveedorEntity entity = new ProveedorEntity();
        entity.setProviderName(json.getProviderName());
        entity.setProviderPhone(json.getProviderPhone());
        entity.setProviderAddress(json.getProviderAddress());
        entity.setProviderEmail(json.getProviderEmail());
        entity.setProviderCode(json.getProviderCode());
        entity.setProviderStatus(json.getProviderStatus());
        entity.setProviderComments(json.getProviderComments());
        return entity;
    }



    //Para metodo get
    public List<ProveedorDTO> obtenerProveedores() {
        List <ProveedorEntity> lista = repo.findAll();
        return lista.stream()
                .map(this::convertiraDTO)
                .collect(Collectors.toList());
    }

    //Para metodo post
    public ProveedorDTO InsertarDatos(@Valid ProveedorDTO json) {
        //validacion para verificar si los datos estan correctos
        if (json == null){
            throw new IllegalArgumentException("Los datos del proveedor no pueden venir vacias");
        }
        try {
            //Convertimos los datos de tipo DTO a entity
            ProveedorEntity entity = convertiraEntiy(json);
            ProveedorEntity respuesta = repo.save(entity);
            return convertiraDTO(respuesta);
        }catch (Exception e){
            log.error("Error al registrar un proveedor: " + e.getMessage());
            throw new ExceptionProveedorNoRegistrado("Error al registrar un proveedor");
        }
    }


    public ProveedorDTO actualizarProveedor(Long providerID, @Valid ProveedorDTO json) {
        ProveedorEntity proveedorExiste = repo.findById(providerID).orElseThrow(() -> new ExceptionProveedorNoEncontrado("Proveedor no encontrado"));
        proveedorExiste.setProviderName(json.getProviderName());
        proveedorExiste.setProviderPhone(json.getProviderPhone());
        proveedorExiste.setProviderAddress(json.getProviderAddress());
        proveedorExiste.setProviderEmail(json.getProviderEmail());
        proveedorExiste.setProviderCode(json.getProviderCode());
        proveedorExiste.setProviderStatus(json.getProviderStatus());
        proveedorExiste.setProviderComments(json.getProviderComments());
        ProveedorEntity proveedorActualizado = repo.save(proveedorExiste);
        return convertiraDTO(proveedorActualizado);
    }


    public void removerProveedor(Long providerID) {
        if (!repo.existsById(providerID)){
            log.warn("El usuario que intenta eliminar no existe");
            throw new ExceptionProveedorNoEncontrado("Usuario con el id: " + providerID + " no encontrado");
        }
        repo.deleteById(providerID);
        log.info("Usuario Eliminado Correctamente");
    }


    public ProveedorDTO obtenerProveedorPorId(Long providerID) {
        ProveedorEntity proveedorEncontrado = repo.findById(providerID)
                .orElseThrow(() -> new ExceptionProveedorNoEncontrado("Proveedor con el id: " + providerID + " no encontrado"));
        return convertiraDTO(proveedorEncontrado);
    }
}
