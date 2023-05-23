package Tests;
import Application.Dictionary;
import Application.FileReader.FileReader;
import Hashing.HashN2;
import Hashing.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;


public class N2Tests {
    private HashN2 h ;
    private void insertcases(Object...objs)
    {
        Set<Object> s = new HashSet<>();
        Collections.addAll(s, objs);
        h = new HashN2(s.size());
        for (Object object: objs)
        {
            h.insert(new Pair(Dictionary.stringToLong((String) object),object));}
    }
    private ArrayList<Pair> insertbatch(Object...objs)
    {
        ArrayList<Pair> p = new ArrayList<>();
        for (Object obj : objs) {
            p.add(new Pair(Dictionary.stringToLong((String) obj), objs));
        }
        return p;
    }
    private Pair[] deletebatch(Object...objs)
    {
        Pair[] p = new Pair[objs.length];
        for (int i = 0; i < objs.length; i++) {
            p[i]=new Pair(Dictionary.stringToLong((String) objs[i]),objs);
        }
        return p;
    }
    private boolean search(Object...objs)
    {
        for (Object obj : objs) {
            if (!h.searchForKey(Dictionary.stringToLong((String) obj)))
                return false;
        }
        return true;
    }
    @Test
    public void Test1()//mainly test search_for_key function
    {
        insertcases("ahmed","adham","ali","belal","bassem","kareem","hussein",
                "hassan","mohamed","mahmoud","hesham","marwan");

        //test for search functionality
        assertTrue(h.searchForKey( Dictionary.stringToLong("ahmed")));
        assertTrue(h.searchForKey( Dictionary.stringToLong("adham")));
        assertTrue(h.searchForKey( Dictionary.stringToLong("ali")));
        assertTrue(h.searchForKey( Dictionary.stringToLong("belal")));
        assertTrue(h.searchForKey( Dictionary.stringToLong("bassem")));
        assertTrue(h.searchForKey( Dictionary.stringToLong("kareem")));
        assertTrue(h.searchForKey( Dictionary.stringToLong("hussein")));
        assertTrue(h.searchForKey( Dictionary.stringToLong("hassan")));
        assertTrue(h.searchForKey( Dictionary.stringToLong("mohamed")));
        assertTrue(h.searchForKey( Dictionary.stringToLong("mahmoud")));
        assertTrue(h.searchForKey( Dictionary.stringToLong("hesham")));
        assertTrue(h.searchForKey( Dictionary.stringToLong("marwan")));
        assertFalse(h.searchForKey(Dictionary.stringToLong("hassam")));

        //test for insert functionality
        assertTrue(h.insert(new Pair(Dictionary.stringToLong("sameh"),"sameh")));
        assertTrue(h.searchForKey(   Dictionary.stringToLong("sameh")   ));
        assertTrue(h.delete(new Pair(Dictionary.stringToLong("sameh"),"sameh")));
        assertFalse(h.searchForKey(  Dictionary.stringToLong("sameh")));//false as this object is deleted
    }
    @Test
    public void Test2()//mainly test insert function
    {
        h=new HashN2(3);
        Pair[] p = {
                new Pair(Dictionary.stringToLong("Serendip"),"Serendip"),
                new Pair(Dictionary.stringToLong("Melliflu"),"Melliflu"),
                new Pair(Dictionary.stringToLong("Ephemera"),"Ephemera"),
                new Pair(Dictionary.stringToLong("Quixotic"),"Quixotic"),
                new Pair(Dictionary.stringToLong("Efferves"),"Efferves"),
                new Pair(Dictionary.stringToLong("Resplend"),"Resplend"),
                new Pair(Dictionary.stringToLong("Ebullien"),"Ebullien"),
                new Pair(Dictionary.stringToLong("Labyrint"),"Labyrint"),
                new Pair(Dictionary.stringToLong("Petricho"),"Petricho"),
                new Pair(Dictionary.stringToLong("Susurrou"),"Susurrou")
        };
        for (Pair pair : p) {
            assertTrue(h.insert(pair));
            assertFalse(h.insert(pair));
        }
    }
    @Test
    public void Test3()//mainly test delete function
    {
        insertcases("ahmed","adham","ali","belal","bassem","kareem","hussein",
                "hassan","mohamed","mahmoud","hesham","marwan");
        Pair[] p = {
                new Pair(Dictionary.stringToLong("ahmed"),"ahmed"),
                new Pair(Dictionary.stringToLong("adham"),"adham"),
                new Pair(Dictionary.stringToLong("ali"),"ali"),
                new Pair(Dictionary.stringToLong("belal"  ),"belal"),
                new Pair(Dictionary.stringToLong("bassem" ),"bassem"),
                new Pair(Dictionary.stringToLong("kareem" ),"kareem"),
                new Pair(Dictionary.stringToLong("hussein"),"hussein"),
                new Pair(Dictionary.stringToLong("hassan" ),"hassan"),
                new Pair(Dictionary.stringToLong("mohamed"),"mohamed"),
                new Pair(Dictionary.stringToLong("mahmoud"),"mahmoud"),
                new Pair(Dictionary.stringToLong("hesham" ),"hesham"),
                new Pair(Dictionary.stringToLong("marwan" ),"marwan")
        };
        for (Pair pair : p) {
            assertTrue(h.delete(pair));
            assertFalse(h.delete(pair));
        }
    }
    @Test
    public void Test4()//mainly test for batch insert
    {
        ArrayList<Pair> pair = FileReader.loadpairslist("/home/mahmoud/Test1.txt");
        h = new HashN2(pair.size());
        assertEquals(10000,h.batchInsert(pair).success);
    }
    @Test
    public void Test5()//mainly test for batch delete
    {
        Pair[] pair = FileReader.loadpairsarray("/home/mahmoud/Test1.txt");
        h = new HashN2(pair.length);
        assertEquals(0,h.batchDelete(pair).success);

        ArrayList<Pair> p = FileReader.loadpairslist("/home/mahmoud/Test1.txt");
        assertEquals(10000,h.batchInsert(p).success);
        assertEquals(10000,h.batchDelete(pair).success);

    }
    @Test
    public void Test6()//random tests
    {
        insertcases("kareem","kareme","meerak","areemk","math","hatm","hat","bat","cat","mat","rat","art");
        assertFalse(h.searchForKey(   Dictionary.stringToLong("karmee")));
        assertFalse(h.delete(new Pair(Dictionary.stringToLong("karmee"),"karmee")));
        assertTrue(h.insert(new Pair( Dictionary.stringToLong("karmee"),"karmee")));

    }@Test
    public void Test7()//random tests
    {
        h=new HashN2(2);
        assertEquals(2,h.batchInsert(insertbatch("messi","messi ")).success);
        assertEquals(2,h.batchDelete(deletebatch("messi","messi ")).success);
    }@Test
    public void Test8()
    {
        h=new HashN2(2);
        assertEquals(2,h.batchInsert(insertbatch("ronaldo","ronaldo","Ronaldo","Ronaldo")).success);
    }@Test
    public void Test9()
    {
        h=new HashN2(2);
        assertEquals(4,h.batchInsert(insertbatch("column","colman","colling","cooperate","goal")).success);
        assertFalse(h.searchForKey(Dictionary.stringToLong("goal")));
        assertEquals(4,h.batchDelete(deletebatch("goal","column","colman","colling","cooperate")).success);
    }@Test
    public void Test10()
    {
        h=new HashN2(2520);
        ArrayList<Pair> p = FileReader.loadpairslist("/home/mahmoud/Test2.txt");
        Pair[]p2=FileReader.loadpairsarray("/home/mahmoud/Test2.txt");
        assertEquals(2520,h.batchInsert(p).success);
        assertFalse(h.insert(new Pair(Dictionary.stringToLong("example"),"example")));
        assertTrue(search("example","exalmpe","elpmaxe","elmaxep"));
        assertEquals(2520,h.batchDelete(p2).success);
    }
    @Test
    public void Test11()
    {
        h=new HashN2(60);
        ArrayList<Pair> p = FileReader.loadpairslist("/home/mahmoud/Test3.txt");
        Pair[]p2=FileReader.loadpairsarray("/home/mahmoud/Test3.txt");
        assertEquals(60,h.batchInsert(p).success);
        assertFalse(h.insert(new Pair(Dictionary.stringToLong("Apple"),"Apple")));
        assertTrue(search("Apple","Alppe","Aplpe"));
        assertEquals(60,h.batchDelete(p2).success);
        assertFalse(search("Apple","Alppe","Aplpe"));
    }
    @Test
    public void Test12()
    {
        h=new HashN2(5040);
        ArrayList<Pair> p = FileReader.loadpairslist("/home/mahmoud/Test4.txt");
        Pair[]p2=FileReader.loadpairsarray("/home/mahmoud/Test4.txt");
        assertEquals(5040,h.batchInsert(p).success);
        assertFalse(h.insert(new Pair(Dictionary.stringToLong("wake up"),"wake up")));
        assertTrue(h.delete(new Pair( Dictionary.stringToLong("wake up") ,"wake up")));
        assertTrue(search("wakeup ","wa keup"," wakeup"));
        assertEquals(5039,h.batchDelete(p2).success);
        assertFalse(search("wakeup ","wa keup"," wakeup"));
    }@Test
    public void Test13()
    {
        h=new HashN2(10080);
        ArrayList<Pair> p = FileReader.loadpairslist("/home/mahmoud/Test5.txt");
        Pair[]p2=FileReader.loadpairsarray("/home/mahmoud/Test5.txt");
        assertEquals(10080,h.batchInsert(p).success);
        assertFalse(h.insert(new Pair(Dictionary.stringToLong("lotfobal"),"lotfobal")));
        assertTrue(search("football","lotfobal","foltbola"));
        assertEquals(10080,h.batchDelete(p2).success);
        assertFalse(search("football","lotfobal","foltbola"));
    }
    @Test
    public void Test14()
    {
        h=new HashN2(20160);
        ArrayList<Pair> p = FileReader.loadpairslist("/home/mahmoud/Test6.txt");
        Pair[]p2=FileReader.loadpairsarray("/home/mahmoud/Test6.txt");
        assertEquals(20160,h.batchInsert(p).success);
        assertFalse(h.insert(new Pair(Dictionary.stringToLong("mid exam"),"mid exam")));
        assertTrue(search("max emid","mid emax","mid exam"));
        assertEquals(20160,h.batchDelete(p2).success);
        assertFalse(search("max emid","mid emax","mid exam","mid "));
    }
    @Test
    public void Test15()
    {
        insertcases("hot","Hot","/"," ","hOt","hoT","HOT","hOT","HOt","HoT");
        assertTrue(h.searchForKey(Dictionary.stringToLong(" ")));
    }@Test
    public void Test16()
    {
        insertcases("messi","messi","messi");
        assertTrue(h.delete(new Pair(Dictionary.stringToLong("messi"),"messi")));
        assertFalse(h.searchForKey(  Dictionary.stringToLong("messi")));
    }@Test
    public void Test17()
    {
        insertcases("Alic 781","John 44","David 98","Emily 59","Michil 8","Dany 246","Bob 915","Sobhy 34","Oliv 21",
                "Mervat 1");
        assertTrue(h.searchForKey( Dictionary.stringToLong("Emily 59") ));
        assertFalse(h.searchForKey(Dictionary.stringToLong("Alic 780")));
        assertEquals(2,h.batchDelete(deletebatch("David 98","Mervat 1","Ali 781","Micheil 8")).success);
        assertFalse(h.searchForKey(Dictionary.stringToLong("David 98"  )));
        assertFalse(h.searchForKey(Dictionary.stringToLong("Mervat 1")));
        assertEquals(3,h.batchInsert(insertbatch("Ali 781","Hoda 30","Mahmod 7","John 44","Oliv 21")).success);
    }
    @Test
    public void Test18()
    {
        h=new HashN2(57);
        ArrayList<Pair> P = FileReader.loadpairslist("/home/mahmoud/Test7.txt");
        Pair[]p2=FileReader.loadpairsarray("/home/mahmoud/Test7.txt");
        assertEquals(57,h.batchInsert(P).success);
        assertEquals(57,h.batchDelete(p2).success);
    }
    @Test
    public void Test19()
    {
        insertcases("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa");
        assertFalse(h.searchForKey( Dictionary.stringToLong("aaaaaaa a")));
        assertFalse(h.searchForKey( Dictionary.stringToLong("aaaaaaa " )));
        assertTrue(h.searchForKey(  Dictionary.stringToLong("aaaaaaa"  )));
    }@Test
    public void Test20()
    {
        insertcases(
                "Otman Gp",
                "Layla Gm"
                ,"Ali pa",
                "Karma Ma",
                "Sameh ch",
                "Sara ch2",
                "Mona ch3",
                "Ola gc1",
                "Maha gc2",
                "Hoda gc3",
                "Toka gc4",
                "Karim g5",
                "Omar gc6",
                "Loua gc7"
        );
        assertFalse(h.delete(new Pair(Dictionary.stringToLong("Loua gc0"),"Loua gc0")));
        assertTrue(h.delete(new Pair( Dictionary.stringToLong("Loua gc7"),"Loua gc7")));
        assertFalse(h.searchForKey(   Dictionary.stringToLong("Loua gc7")));
        assertTrue(h.insert(new Pair( Dictionary.stringToLong("Loua gc7"),"Loua gc7")));
        assertTrue(h.searchForKey(    Dictionary.stringToLong("Loua gc7")));
    }
}
