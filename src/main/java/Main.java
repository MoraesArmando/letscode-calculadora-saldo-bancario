public class Main {
    public static void main(String[] args) {

        String CSV_Path = "src/main/resources/operacoes.csv";
        Arquivo arquivo = new Arquivo(CSV_Path);
        GerenciadorDados dados = arquivo.abrirCSV();

        CriarExtratos criarExtratos = new CriarExtratos(dados);
        String pathSave = "src/main/";
        criarExtratos.fazerExtratos(pathSave);


    }
}
