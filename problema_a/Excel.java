import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileOutputStream;

public class Excel {
    public static void main(String[] args) throws IOException {
        String nome_arquivo_entrada = "excel.in";
        String nome_arquivo_saida = "excel.out";

        try {
            FileInputStream arquivo_entrada = new FileInputStream(nome_arquivo_entrada);
            BufferedReader reader = new BufferedReader(new InputStreamReader(arquivo_entrada));

            FileOutputStream saida = new FileOutputStream(nome_arquivo_saida);

            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.equals("0"))
                    break;
                
                char temp[] = linha.toCharArray();
                int valor = 0;
                for (int x = 0; x < temp.length ; x++) {
                    valor += (((int) temp[x] - 64) * Math.pow(26, temp.length - 1 - x));
                }
                saida.write((valor + "\n").getBytes());
            }
            arquivo_entrada.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}