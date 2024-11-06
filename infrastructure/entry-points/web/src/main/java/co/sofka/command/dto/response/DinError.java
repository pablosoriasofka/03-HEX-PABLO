package co.sofka.command.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DinError {

    private String tipo;
    private String fecha;
    private String origen;
    private String codigo;
    private String codigoErrorProveedor;
    private String mensaje;
    private String detalle;
}
