import lombok.AllArgsConstructor;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
public class CriarExtratos {
    GerenciadorDados dados;

    public void gerarExtrato(String pathSave) {
        Set<String> contasSet = dados.keys();
        String[] contasArray = new String[contasSet.size()];
        contasSet.toArray(contasArray);
        for (String s : contasArray) {
            imprimirExtrato(pathSave, s);
        }
    }

    public void imprimirExtrato(String pathSave, String conta) {
        double saldo = 0.0;
        long dateEpoch;
        String separator = System.getProperty("file.separator");
        String file = pathSave + separator + conta + ".txt";

        ArrayList<OperacaoBancaria> trasacoes = dados.getKey(conta);

        try {
            FileWriter arq = new FileWriter(file);
            PrintWriter gravarArq = new PrintWriter(arq);

            gravarArq.printf("Banco " + trasacoes.get(0).getContaBancaria().getBanco() + "\n");

            gravarArq.printf("Agência: ... " + trasacoes.get(0).getContaBancaria().getAgencia() + "\n");
            gravarArq.printf("Conta: ..... " + trasacoes.get(0).getContaBancaria().getConta() + "\n\n");

            gravarArq.printf("Data \t\t\t");

            gravarArq.printf("Tipo \t\t");
            gravarArq.printf("Valor \t\t");
            gravarArq.printf("\n\n");
            for (OperacaoBancaria item : trasacoes) {

                dateEpoch = item.getDataHoraOperacao().getTime();
                String pattern = "dd-MM-yy HH:mm:ss";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String date = simpleDateFormat.format(new Date(dateEpoch));
                gravarArq.printf(date + "\t");

                if (item.getTipo().equals("SAQUE")) {

                    saldo = saldo - item.getValor();
                    gravarArq.printf(item.getTipo() + "\t\t");
                    gravarArq.printf("-%.2f\t\t", item.getValor());
                }
                if (item.getTipo().equals("DEPOSITO")) {

                    saldo += item.getValor();
                    gravarArq.printf(item.getTipo() + "\t");
                    gravarArq.printf("+%.2f\t\t", item.getValor());
                }

                gravarArq.print("\t\n");
            }
            gravarArq.printf("\nSaldo: ...............................\t");
            gravarArq.printf(Double.toString(saldo));
            arq.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}