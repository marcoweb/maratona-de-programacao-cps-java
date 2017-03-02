import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.HashMap;

public class Reciclagem {
    public static void main(String[] args) throws IOException {
        String nome_arquivo_entrada = "reciclagem.in";
        String nome_arquivo_saida = "reciclagem.out";

        try {
            FileInputStream arquivo_entrada = new FileInputStream(nome_arquivo_entrada);
            BufferedReader reader = new BufferedReader(new InputStreamReader(arquivo_entrada));

            FileOutputStream saida = new FileOutputStream(nome_arquivo_saida);

            String linha;
            
            String[] definicao;
            HashMap<String, Integer> resultado = new HashMap<>();

            while ((linha = reader.readLine()) != null) {
                if(linha.equals("0"))
                    break;
                definicao = linha.split(" ");
                resultado.put("ME", 0);
                resultado.put("PA", 0);
                resultado.put("PL", 0);
                resultado.put("VI", 0);
                for (int i = 0; i < Integer.parseInt(definicao[0]); i++) {
                    String[] carga = (reader.readLine()).split(" ");
                    for (int j = 0; j < carga.length; j++) {
                        if (definicao[j + 1].equals("ME")
                            || definicao[j + 1].equals("PA")
                            || definicao[j + 1].equals("PL")
                            || definicao[j + 1].equals("VI")) {
                                resultado.put(
                                    definicao[j + 1],
                                    resultado.get(definicao[j + 1]) + Integer.parseInt(carga[j]));
                        }
                    }
                }
                saida.write((definicao[0] + " "
                    + resultado.get("ME") + " "
                    + resultado.get("PA") + " "
                    + resultado.get("PL") + " "
                    + resultado.get("VI") + "\n").getBytes());
            }
            arquivo_entrada.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}