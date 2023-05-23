package Application;

import Application.FileReader.FileReader;
import Hashing.HashN;
import Hashing.HashN2;
import Hashing.Pair;
import Hashing.PerfectHashing;

import java.util.ArrayList;

public class Dictionary {
    private PerfectHashing dict;
    public Dictionary(String Hash_Type,int Size)
    {
        if(Hash_Type.equalsIgnoreCase("Hash_N2"))
        {
            dict = new HashN2(Size);
        }
        else if(Hash_Type.equalsIgnoreCase("Hash_N"))
        {
            dict = new HashN(Size);
        }
    }
    public static long stringToLong(String str) {
        StringBuilder binary = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            int ascii = (int) str.charAt(i);
//            System.out.println(str.charAt(i) + "-->" + ascii);
            String binaryStr = Integer.toBinaryString(ascii);
            binary.append(binaryStr);
        }
        return Long.parseLong(binary.toString(), 2);
    }
    public boolean insert_word(Object key)
    {
        if (((String) key).length()==0) return false;
        Pair actualkey = new Pair(stringToLong((String) key), key);
        return dict.insert(actualkey);
    }
    public boolean delete_word(Object key)
    {
        if (((String) key).length()==0) return false;
        Pair actualkey = new Pair(stringToLong((String) key),key);
        return dict.delete(actualkey);
    }
    public boolean search_word(Object key)
    {
        if (((String) key).length()==0) return false;
        return dict.searchForKey(stringToLong((String) key));
    }
    public int Batch_Insert(String fname) throws RuntimeException
    {
        ArrayList<Pair> pairs=new ArrayList<>();

        for (Object word:FileReader.loadfile(fname))
            pairs.add(new Pair(stringToLong((String) word),word));

        return dict.batchInsert(pairs);
    }
    public int Batch_Delete(String fname) throws RuntimeException
    {
        ArrayList<Pair> pairs1 = new ArrayList<>();
        for (Object word:FileReader.loadfile(fname))
        {
            pairs1.add(new Pair(stringToLong((String) word),word));
        }
        Pair[]pairs = new Pair[pairs1.size()];
        for (int i=0;i<pairs.length;i++)
        {
            pairs[i] = pairs1.get(i);
        }
        return dict.batchDelete(pairs);
    }
    public ArrayList<Boolean> search_multiword(String fname) throws RuntimeException
    {
        ArrayList<Boolean> result=new ArrayList<>();
        for (Object word: FileReader.loadfile(fname)){
            boolean found = search_word(word);
            String x= found ? "Found" : "Not Found";
            System.out.println(word+" : "+ x);
            result.add(found);
        }
        return result;
    }
    public int getrebuild()
    {
        return dict.getRebuildCounter();
    }
    public int get_no_elements(){
        if(dict instanceof HashN2)
        {
            return ((HashN2) dict).getElementCounter();
        }
        return -1;
    }
}
