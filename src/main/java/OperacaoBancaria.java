import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Data
@Setter
@Builder
@ToString
public class OperacaoBancaria {

    private Date dataHoraOperacao;
    private String operador;
    private String tipo;
    private Double valor;
    private ContaBancaria contaBancaria;

}

