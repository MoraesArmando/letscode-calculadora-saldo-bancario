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
        adicionaOperacoes(operacaoBancaria, operationsList, idConta, contaOperations);
    }

    private void adicionaOperacoes(OperacaoBancaria operacaoBancaria, ArrayList<OperacaoBancaria> operationsList, String idConta, ArrayList<OperacaoBancaria> contaOperations) {
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
        boolean check;
        ArrayList<OperacaoBancaria> listaOrdenada = new ArrayList<>();
        Date date = operacaoBancaria.getDataHoraOperacao();
        long dataTime = date.getTime();
        boolean status = true;
        int i = 0;
        status = isStatus(operationsList, operacaoBancaria, listaOrdenada, dataTime, status, i);
        if (status) {
            listaOrdenada.add(operacaoBancaria);
        }
        return listaOrdenada;
    }

    private boolean isStatus(ArrayList<OperacaoBancaria> operationsList, OperacaoBancaria operacaoBancaria, ArrayList<OperacaoBancaria> listaOrdenada, long dataTime, boolean status, int i) {
        boolean check;
        while (i < operationsList.size()) {
            OperacaoBancaria itemAtual = operationsList.get(i);
            Date itemData = itemAtual.getDataHoraOperacao();
            long itemDataTime = itemData.getTime();
            if (status) {
                if (dataTime == itemDataTime) {
                    check = itemAtual.getOperador().equals(operacaoBancaria.getOperador()) && operationsList.get(i).getTipo().equals(operacaoBancaria.getTipo()) && Objects.equals(operationsList.get(i).getValor(), operacaoBancaria.getValor());
                    if (check) {
                        i += 1;
                    } else {
                        listaOrdenada.add(operacaoBancaria);
                        status = false;
                    }
                }
                if (dataTime > itemDataTime) {
                    listaOrdenada.add(operationsList.get(i));
                    i += 1;
                }
                if (dataTime < itemDataTime) {
                    listaOrdenada.add(operacaoBancaria);
                    status = false;
                }
            } else {
                listaOrdenada.add(operationsList.get(i));
                i += 1;
            }
        }
        return status;
    }

}

