import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileOutputStream;

public class Megasena {
    public static void main(String[] args) throws IOException {
        String nome_arquivo_entrada = "megasena.in";
        String nome_arquivo_saida = "megasena.out";

        try {
            FileInputStream arquivo_entrada = new FileInputStream(nome_arquivo_entrada);
            BufferedReader reader = new BufferedReader(new InputStreamReader(arquivo_entrada));

            FileOutputStream saida = new FileOutputStream(nome_arquivo_saida);

            String temp[];
            int dezenas[] = new int[60];
            for(int i = 0;i < 60;i++)
                dezenas[i] = 0;

            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.equals("0"))
                    break;
                temp = linha.split(";");
                for(int i = 2;i < temp.length;i++)
                    dezenas[Integer.parseInt(temp[i]) - 1] ++;
            }
            int ord_dezenas[] = new int[60];
            for(int i = 0;i < 60;i++)
                ord_dezenas[i] = i+1;

            for(int i = 0;i < dezenas.length - 1;i++) {
                if(dezenas[i] < dezenas[i+1] || ((dezenas[i] == dezenas[i+1]) && ord_dezenas[i] > ord_dezenas[i+1])) {
                    int swap = ord_dezenas[i];
                    ord_dezenas[i] = ord_dezenas[i+1];
                    ord_dezenas[i+1] = swap;
                    swap = dezenas[i];
                    dezenas[i] = dezenas[i+1];
                    dezenas[i+1] = swap;
                    i = -1;
                }
            }

            for (int i = 0;i < dezenas.length;i++) {
                saida.write(((i + 1) + " " + (ord_dezenas[i]) + " " + dezenas[i] + "\n" ).getBytes());
            }
            arquivo_entrada.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}