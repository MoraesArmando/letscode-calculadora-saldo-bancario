import lombok.AllArgsConstructor;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
public class CriarExtratos {
    GerenciadorDados gerenciadorDados;

    public void gerarExtrato(String pathSave) {
        Set<String> contasSet = gerenciadorDados.keys();
        String[] contasArray = new String[contasSet.size()];
        contasSet.toArray(contasArray);
        for (String s : contasArray) {
            imprimirExtrato(pathSave, s);
        }
    }

    public void imprimirExtrato(String pathSave, String conta) {
        double saldo = 0.0;
        long data;
        String file = pathSave + "\\" + conta + ".txt";

        ArrayList<OperacaoBancaria> transacaoBancaria = gerenciadorDados.getKey(conta);

        try {
            FileWriter arquio = new FileWriter(file);
            PrintWriter arquivoDados = new PrintWriter(arquio);

            arquivoDados.printf("Banco " + transacaoBancaria.get(0).getContaBancaria().getBanco() + "\n")
                    .printf("Agência: ... " + transacaoBancaria.get(0).getContaBancaria().getAgencia() + "\n")
                    .printf("Conta: ..... " + transacaoBancaria.get(0).getContaBancaria().getConta() + "\n\n")
                    .printf("Data\t\t\t\tTipo\t\tValor\t\tOperator")
                    .printf("\n\n");

            for (OperacaoBancaria item : transacaoBancaria) {

                arquivoDados.printf(formataDataExtrato(item.getDataHoraOperacao()) + "\t");

                saldo = getSaldo(saldo, arquivoDados, item);
                arquivoDados.printf(item.getOperador())
                .print("\t\n");
            }
            arquivoDados.printf("\nSaldo: ........................ ")
                    .printf(Double.toString(saldo));
            arquio.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private double getSaldo(double saldo, PrintWriter arquivoDados, OperacaoBancaria item) {
        if (item.getTipo().equals("SAQUE")) {

            saldo -= item.getValor();
            arquivoDados.printf(item.getTipo() + "\t\t")
                    .printf("-%.2f\t\t", item.getValor());
        }
        if (item.getTipo().equals("DEPOSITO")) {

            saldo += item.getValor();
            arquivoDados.printf(item.getTipo() + "\t")
                    .printf("+%.2f\t\t", item.getValor());
        }
        return saldo;
    }

    private String formataDataExtrato(Date dataHoraOperacao) {
        long data = dataHoraOperacao.getTime();
        String pattern = "dd-MM-yy HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date(data));

    }


}