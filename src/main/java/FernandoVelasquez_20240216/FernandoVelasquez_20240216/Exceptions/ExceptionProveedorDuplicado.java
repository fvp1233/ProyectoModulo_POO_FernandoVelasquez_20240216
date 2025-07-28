package FernandoVelasquez_20240216.FernandoVelasquez_20240216.Exceptions;

import lombok.Getter;

public class ExceptionProveedorDuplicado extends RuntimeException {

    @Getter
    private String campoDuplicado;

    public ExceptionProveedorDuplicado(String message, String campoDuplicado) {
        super(message);
        this.campoDuplicado = campoDuplicado;
    }

    public ExceptionProveedorDuplicado(String message) {
        super(message);
    }

}
