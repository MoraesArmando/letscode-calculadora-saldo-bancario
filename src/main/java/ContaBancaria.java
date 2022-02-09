import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ContaBancaria {

    private String id;
    private String banco;
    private String agencia;
    private String conta;

}
