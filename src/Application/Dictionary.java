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
    public boolean insert_word(String key)
    {
        Pair actualkey = new Pair();
        actualkey.key = key.hashCode();
        actualkey.value = key;
        return dict.insert(actualkey);
    }
    public boolean delete_word(String key)
    {
        Pair actualkey = new Pair();
        actualkey.key = key.hashCode();
        actualkey.value = key;
        return dict.delete(actualkey);
    }
    public boolean search_word(String key)
    {
        return dict.searchForKey(key.hashCode());
    }
    public int Batch_Insert(String fname) throws RuntimeException
    {
//        ArrayList<Boolean> result=new ArrayList<>();
        int counter=0;
        for (Object word:FileReader.loadfile(fname))
        {
            boolean added = insert_word((String) word);
            counter = added? counter+1 : counter;
//            String x= added ? "Word inserted successfully!" : "Word already exists!";
//            System.out.println(word+" : "+ x);
//            result.add(added);
        }
        dict.print();
        return counter;
    }
    public int Batch_Delete(String fname) throws RuntimeException
    {
//        ArrayList<Boolean> result=new ArrayList<>();
        int counter = 0;
        for (Object word:FileReader.loadfile(fname))
        {
            boolean deleted = delete_word((String) word);
            counter=deleted? counter+1 : counter;
//            String x= deleted ? "Word deleted successfully!" : "Word doesn't exist!";
//            System.out.println(word+" : "+ x);
//            result.add(deleted);
        }
        return counter;
    }
    public ArrayList<Boolean> search_multiword(String fname) throws RuntimeException
    {
        ArrayList<Boolean> result=new ArrayList<>();
        for (Object word: FileReader.loadfile(fname)){
            boolean found = search_word((String) word);
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


}
