package Application.FileReader;

import Application.Dictionary;
import Hashing.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileReader {
    public static ArrayList<Object> loadfile(String fname) {
        ArrayList<Object> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(fname))) {
            Object line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public static ArrayList<Pair> loadpairslist(String fname) {
        ArrayList<Pair> pairs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(fname))) {
            Object line;
            while ((line = br.readLine()) != null) {
                pairs.add(new Pair(Dictionary.stringToLong((String) line),line));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return pairs;
    }
    public static Pair[] loadpairsarray(String fname) {
        ArrayList<Pair> pairs = new ArrayList<>();
        Pair[] p;
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(fname))) {
            Object line;
            while ((line = br.readLine()) != null) {
                pairs.add(new Pair(Dictionary.stringToLong((String) line),line));
            }
            p = new Pair[pairs.size()];
            for (int i = 0; i < pairs.size(); i++) {
                p[i]=pairs.get(i);
            }
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
        return p;
    }

}
