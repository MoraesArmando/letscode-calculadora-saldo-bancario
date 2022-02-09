import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.Getter;
import lombok.Setter;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class ManipularArquivo {
    private String CSV_Path;

    public ManipularArquivo(String csv_path) {
        this.CSV_Path = csv_path;
    }


    public GerenciadorDados abrirCSV(){
    GerenciadorDados dados = new GerenciadorDados();

    try {
    FileReader fileReader = new FileReader(CSV_Path);
    CSVReader csvReader = new CSVReader(fileReader);

    String[] linha = csvReader.readNext();
        while ((linha = csvReader.readNext()) != null) {
            OperacaoBancaria operacaoBancaria = OperacaoBancaria.builder()
                    .dataHoraOperacao( formatarData(linha[0]))
                    .contaBancaria(new ContaBancaria(linha[1], linha[2], linha[3], linha[4]))
                    .operador(linha[5])
                    .tipo(linha[6])
                    .valor(Double.valueOf(linha[7]))
                    .build();

            dados.adicionar(operacaoBancaria);
        }
        } catch (CsvValidationException | IOException | ParseException e) {
            e.printStackTrace();
        }
        return dados;
    }

    private Date formatarData(String s) throws ParseException {
        SimpleDateFormat formatterDate =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String replace = s.replace("T", " ");
        return formatterDate.parse(replace);
    }


}

