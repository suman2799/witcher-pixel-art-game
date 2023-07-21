package res.csv;

import java.io.FileWriter;
import java.io.IOException;

public class CreateZerosCSV {

    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("src/res/csv/zeros.csv");
            for (int i = 0; i < 17; i++) {
                for (int j = 0; j < 30; j++) {
                    writer.append("0");
                    if (j != 29) {
                        writer.append(",");
                    }
                }
                writer.append("\n");
            }
            writer.close();
            System.out.println("CSV file created successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
