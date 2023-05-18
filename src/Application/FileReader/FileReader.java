package Application.FileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileReader {
    public static ArrayList<Object> loadfile(String fname) {
        ArrayList<Object> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(fname))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
