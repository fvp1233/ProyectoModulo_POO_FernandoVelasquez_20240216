package FernandoVelasquez_20240216.FernandoVelasquez_20240216.Controllers;

import FernandoVelasquez_20240216.FernandoVelasquez_20240216.Exceptions.ExceptionProveedorDuplicado;
import FernandoVelasquez_20240216.FernandoVelasquez_20240216.Exceptions.ExceptionProveedorNoEncontrado;
import FernandoVelasquez_20240216.FernandoVelasquez_20240216.Models.DTO.ProveedorDTO;
import FernandoVelasquez_20240216.FernandoVelasquez_20240216.Services.ProveedorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apiProveedor")
public class ProveedorController {
    //Hay que inyectar el ProveedorService sobre nuestro controller

    @Autowired
    ProveedorService service;

    //Metodo GET
    @GetMapping("/consultarDatos")
    public List<ProveedorDTO> obtenerDatos(){
        return service.obtenerUsuarios();
    }

    //Metodo POST
    @PostMapping("/registrarDatos")
    public ResponseEntity<?> nuevoProveedor(@Valid @RequestBody ProveedorDTO json, HttpServletRequest request){
        try {
            ProveedorDTO respuesta = service.InsertarDatos(json);
            if (respuesta == null){
                return ResponseEntity.badRequest().body(Map.of(
                        "status", "Insercion Fallida",
                        "errorType", "VALIDATION_ERROR",
                        "message", "Los datos no pudieron ser insertados"
                ));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                        "status", "succes",
                        "data", respuesta
            ));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "error",
                    "message", "Error no controlado al registrar un nuevo Proveedor",
                    "detail", e.getMessage()
            ));
        }
    }

    @PutMapping("/editarProveedor/{id}")
    public ResponseEntity <?> modificarProveedor(
            @PathVariable Long providerID,
            @Valid @RequestBody ProveedorDTO json, BindingResult bindingResult
    ){
            // Verificar si hay errores
                if (bindingResult.hasErrors()){

            //Crear un mapa que almacenara los errores
                Map <String, String> errores = new HashMap<>();

            //Luego tengo que interar cada error uno a uno
                bindingResult.getFieldErrors().forEach(error ->
                        errores.put(error.getField(),error.getDefaultMessage()));
        }
                try {
                    ProveedorDTO dto = service.actualizarProveedor(providerID, json);
                    return ResponseEntity.ok(dto);
                } catch (ExceptionProveedorNoEncontrado e){
                    return ResponseEntity.notFound().build();
                } catch (ExceptionProveedorDuplicado e){
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                            "Error", "Datos duplicados",
                            "Campo", e.getCampoDuplicado()
                    ));
                }
    }

}
