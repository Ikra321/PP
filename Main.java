import java.io.*;

public class Main {
    public static void main(String[] args) {
    
        System.out.println("Введите строку:");

        try {
             InputStreamReader isr = new InputStreamReader(System.in, "cp866");
             BufferedReader br = new BufferedReader(isr);
             StringBuffer textBuffer = new StringBuffer();
            while (true) {
                String line = br.readLine();
                if (line == null || line.isEmpty()) {
                    break;
                }
                textBuffer.append(line);

                String fullText = textBuffer.toString();
                Task.printMostFrequentChars(fullText);
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода.");
        }
    }
}