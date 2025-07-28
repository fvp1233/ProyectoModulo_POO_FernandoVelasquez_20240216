package FernandoVelasquez_20240216.FernandoVelasquez_20240216.Models.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @EqualsAndHashCode
public class ProveedorDTO {
    //Proveedores(campos) de la tabla tbProvider

    private Long providerID;

    @NotBlank
    @Size(max = 50, message = "El nombre del proveedor no puede exceder los 50 caracteres")
    private String providerName;

    @Size(max = 25, message = "El telefono del proveedor no puede exceder los 25 caracteres")
    private String providerPhone;

    @Size(max = 128, message = "La direccion del proveedor no puede exceder los 128 caracteres")
    private String providerAddress;

    @Size(max = 100, message = "El correo del proveedor no puede exceder los 100 caracteres")
    @Email(message = "El correo debe poseer un formato adecuado")
    private String providerEmail;

    @Size(max = 35, message = "El codigo del proveedor no puede exceder los 35 caracteres")
    private String providerCode;


    @NotBlank
    @Size(max = 1, message = "El estatus del proveedor no puede exceder mas de un caracter")
    private Integer providerStatus;

    @Size(max = 256, message = "Los comentarios del proveedor no puede exceder los 256 caracteres")
    private String providerComments;
}
