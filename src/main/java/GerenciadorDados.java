import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;

@NoArgsConstructor
@ToString
public class GerenciadorDados {
    Map<String, ArrayList<OperacaoBancaria>> map = new HashMap<>();

    public void adicionar(OperacaoBancaria operacaoBancaria) {
        ArrayList<OperacaoBancaria> operationsList = new ArrayList<>();
        String idConta = operacaoBancaria.getContaBancaria().getId();
        ArrayList<OperacaoBancaria> contaOperations = map.get(idConta);
        if (contaOperations == null) {
            operationsList.add(operacaoBancaria);
            map.put(idConta, operationsList);
        } else {
            operationsList = contaOperations;
            ArrayList<OperacaoBancaria> listaOrdenada = adicionaOrdenado(operationsList, operacaoBancaria);
            map.put(idConta, listaOrdenada);
        }
    }

    public ArrayList<OperacaoBancaria> getKey(String chave) {
        return map.get(chave);
    }

    public Set<String> keys() {
        return map.keySet();
    }

    private ArrayList<OperacaoBancaria> adicionaOrdenado(ArrayList<OperacaoBancaria> operationsList, OperacaoBancaria operacaoBancaria) {
        boolean checkEquals;
        ArrayList<OperacaoBancaria> listaOrdenada = new ArrayList<>();
        Date novaData = operacaoBancaria.getDataHoraOperacao();
        long novaDataEpoch = novaData.getTime();
        boolean controle = true;
        int i = 0;
        while (i < operationsList.size()) {
            Date itemData = operationsList.get(i).getDataHoraOperacao();
            long itemDataEpoch = itemData.getTime();
            if (controle) {
                if (novaDataEpoch == itemDataEpoch) {
                    checkEquals = operationsList.get(i).getOperador().equals(operacaoBancaria.getOperador()) && operationsList.get(i).getTipo().equals(operacaoBancaria.getTipo()) && Objects.equals(operationsList.get(i).getValor(), operacaoBancaria.getValor());
                    if (checkEquals) {
                        i = i + 1;
                    } else {
                        listaOrdenada.add(operacaoBancaria);
                        controle = false;
                    }
                }
                if (novaDataEpoch > itemDataEpoch) {
                    listaOrdenada.add(operationsList.get(i));
                    i = i + 1;
                }
                if (novaDataEpoch < itemDataEpoch) {
                    listaOrdenada.add(operacaoBancaria);
                    controle = false;
                }
            } else {
                listaOrdenada.add(operationsList.get(i));
                i = i + 1;
            }
        }
        if (controle) {
            listaOrdenada.add(operacaoBancaria);
        }
        return listaOrdenada;
    }
}

