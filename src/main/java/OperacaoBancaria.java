import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.util.Date;

@Data
@Setter
@Builder
public class OperacaoBancaria {
    private Date dataHoraOperacao;
    private String operador;
    private String tipo;
    private Double valor;
    private ContaBancaria contaBancaria;
}

