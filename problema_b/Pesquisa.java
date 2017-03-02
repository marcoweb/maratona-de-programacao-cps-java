import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;

public class Pesquisa {
    public static void main(String[] args) throws IOException {
        String nome_arquivo_entrada = "pesquisa.in";
        String nome_arquivo_saida = "pesquisa.out";

        try {
            FileInputStream arquivo_entrada = new FileInputStream(nome_arquivo_entrada);
            BufferedReader reader = new BufferedReader(new InputStreamReader(arquivo_entrada));

            FileOutputStream saida = new FileOutputStream(nome_arquivo_saida);

            DecimalFormat decimalFormat = new DecimalFormat("#,##0.0");

            String linha;

            String cidade = "";
            String eleitores = "";
            String data = "";
            int votos[] = null;
            String candidatos[] = null;
            int amostragem = 0;

            String temp[];

            while ((linha = reader.readLine()) != null) {
                if (cidade.equals("") && votos == null) {
                    temp = linha.split(";");
                    cidade = temp[0];
                    eleitores = temp[1];
                    data = temp[2];

                    linha = reader.readLine();
                    temp = linha.split(";");
                    votos = new int[temp.length + 3];
                    candidatos = new String[temp.length + 3];
                    for (int i = 0;i < temp.length;i++) {
                        candidatos[i] = temp[i];
                        votos[i] = 0;
                    }
                    candidatos[temp.length] = "Nulos";
                    votos[temp.length] = 0;
                    candidatos[temp.length + 1] = "Brancos";
                    votos[temp.length + 1] = 0;
                    candidatos[temp.length + 2] = "Indecisos";
                    votos[temp.length + 2] = 0;
                    amostragem = 0;
                } else {
                    switch (linha) {
                        case "Z":
                            saida.write((cidade + " " + eleitores + " " + data + " " + amostragem + " " + (candidatos.length - 3) +  "\n").getBytes());
                            for (int i = 0;i < candidatos.length;i++) {
                                float calc = (float) votos[i] * 100 / amostragem;
                                saida.write((candidatos[i] + " " + decimalFormat.format(calc) + "\n").getBytes());
                            }
                            cidade = "";
                            votos = null;
                            break;
                        case "N":
                            votos[candidatos.length - 3]++;
                            amostragem ++;
                            break;
                        case "B":
                            votos[candidatos.length - 2]++;
                            amostragem++;
                            break;
                        case "I":
                            votos[candidatos.length - 1]++;
                            amostragem++;
                            break;
                        default:
                            votos[Integer.parseInt(linha) - 1]++;
                            amostragem++;
                            break;
                    }
                }
            }
            arquivo_entrada.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}