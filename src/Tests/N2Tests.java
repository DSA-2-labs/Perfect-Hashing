package Tests;
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
        for (Object object: s)
            h.insert(new Pair(object.hashCode(),object));
    }
    private ArrayList<Pair> insertbatch(Object...objs)
    {
        ArrayList<Pair> p = new ArrayList<>();
        for (Object obj : objs) {
            p.add(new Pair(obj.hashCode(), objs));
        }
        return p;
    }
    private Pair[] deletebatch(Object...objs)
    {
        Pair[] p = new Pair[objs.length];
        for (int i = 0; i < objs.length; i++) {
            p[i]=new Pair(objs[i].hashCode(),objs);
        }
        return p;
    }
    private boolean search(Object...objs)
    {
        for (int i = 0; i < objs.length; i++) {
            if(!h.searchForKey(objs[i].hashCode()))
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
        assertTrue(h.searchForKey("ahmed".hashCode()));
        assertTrue(h.searchForKey("adham".hashCode()));
        assertTrue(h.searchForKey("ali".hashCode()));
        assertTrue(h.searchForKey("belal".hashCode()));
        assertTrue(h.searchForKey("bassem".hashCode()));
        assertTrue(h.searchForKey("kareem".hashCode()));
        assertTrue(h.searchForKey("hussein".hashCode()));
        assertTrue(h.searchForKey("hassan".hashCode()));
        assertTrue(h.searchForKey("mohamed".hashCode()));
        assertTrue(h.searchForKey("mahmoud".hashCode()));
        assertTrue(h.searchForKey("hesham".hashCode()));
        assertTrue(h.searchForKey("marwan".hashCode()));
        assertFalse(h.searchForKey("hassam".hashCode()));

        //test for insert functionality
        assertTrue(h.insert(new Pair("sameh".hashCode(),"sameh")));
        assertTrue(h.searchForKey("sameh".hashCode()));
        assertTrue(h.delete(new Pair("sameh".hashCode(),"sameh")));
        assertFalse(h.searchForKey("sameh".hashCode()));//false as this object is deleted
    }
    @Test
    public void Test2()//mainly test insert function
    {
        h=new HashN2(3);
        Pair[] p = {new Pair("Serendipity".hashCode(),"Serendipity"),
                new Pair("Mellifluous".hashCode(),"Mellifluous"),
                new Pair("Ephemeral".hashCode(),"Ephemeral"),
                new Pair("Quixotic".hashCode(),"Quixotic"),
                new Pair("Effervescent".hashCode(),"Effervescent"),
                new Pair("Resplendent".hashCode(),"Resplendent"),
                new Pair("Ebullient".hashCode(),"Ebullient"),
                new Pair("Labyrinthine".hashCode(),"Labyrinthine"),
                new Pair("Petrichor".hashCode(),"Petrichor"),
                new Pair("Susurrous".hashCode(),"Susurrous")
        };
        for (int i = 0; i < p.length; i++) {
            assertTrue(h.insert(p[i]));
            assertFalse(h.insert(p[i]));
        }
    }
    @Test
    public void Test3()//mainly test delete function
    {
        insertcases("ahmed","adham","ali","belal","bassem","kareem","hussein",
                "hassan","mohamed","mahmoud","hesham","marwan");
        Pair[] p =
                {new Pair("ahmed".hashCode(),"ahmed"),
                new Pair("adham".hashCode(),"adham"),
                new Pair("ali".hashCode(),"ali"),
                new Pair("belal".hashCode(),"belal"),
                new Pair("bassem".hashCode(),"bassem"),
                new Pair("kareem".hashCode(),"kareem"),
                new Pair("hussein".hashCode(),"hussein"),
                new Pair("hassan".hashCode(),"hassan"),
                new Pair("mohamed".hashCode(),"mohamed"),
                new Pair("mahmoud".hashCode(),"mahmoud"),
                new Pair("hesham".hashCode(),"hesham"),
                new Pair("marwan".hashCode(),"marwan")
        };
        for (int i = 0; i < p.length; i++) {
            assertTrue(h.delete(p[i]));
            assertFalse(h.delete(p[i]));
        }
    }
    @Test
    public void Test4()//mainly test for batch insert
    {
        ArrayList<Pair> pair = FileReader.loadpairslist("/home/mahmoud/Test1.txt");
        h = new HashN2(pair.size());
        assertEquals(10000,h.batchInsert(pair));
    }
    @Test
    public void Test5()//mainly test for batch delete
    {
        Pair[] pair = FileReader.loadpairsarray("/home/mahmoud/Test1.txt");
        h = new HashN2(pair.length);
        assertEquals(0,h.batchDelete(pair));

        ArrayList<Pair> p = FileReader.loadpairslist("/home/mahmoud/Test1.txt");
        assertEquals(10000,h.batchInsert(p));
        assertEquals(10000,h.batchDelete(pair));

    }
    @Test
    public void Test6()//random tests
    {
        insertcases("kareem","kareme","meerak","areemk","math","hatm","hat","bat","cat","mat","rat","art");
        assertFalse(h.searchForKey("karmee".hashCode()));
        assertFalse(h.delete(new Pair("karmee".hashCode(),"karmee")));
        assertTrue(h.insert(new Pair("karmee".hashCode(),"karmee")));

    }@Test
    public void Test7()//random tests
    {
        h=new HashN2(2);
        assertEquals(2,h.batchInsert(insertbatch("messi","messi ")));
        assertEquals(2,h.batchDelete(deletebatch("messi","messi ")));
    }@Test
    public void Test8()
    {
        h=new HashN2(2);
        assertEquals(2,h.batchInsert(insertbatch("ronaldo","ronaldo","Ronaldo","Ronaldo")));
    }@Test
    public void Test9()
    {
        h=new HashN2(2);
        assertEquals(4,h.batchInsert(insertbatch("column","colman","colling","cooperate","goal")));
        assertFalse(h.searchForKey("goal".hashCode()));
        assertEquals(4,h.batchDelete(deletebatch("goal","column","colman","colling","cooperate")));
    }@Test
    public void Test10()
    {
        h=new HashN2(2520);
        ArrayList<Pair> p = FileReader.loadpairslist("/home/mahmoud/Test2.txt");
        Pair[]p2=FileReader.loadpairsarray("/home/mahmoud/Test2.txt");
        assertEquals(2520,h.batchInsert(p));
        assertFalse(h.insert(new Pair("example".hashCode(),"example")));
        assertTrue(search("example","exalmpe","elpmaxe","elmaxep"));
        assertEquals(2520,h.batchDelete(p2));


    }
    @Test
    public void Test11()
    {
        h=new HashN2(60);
        ArrayList<Pair> p = FileReader.loadpairslist("/home/mahmoud/Test3.txt");
        Pair[]p2=FileReader.loadpairsarray("/home/mahmoud/Test3.txt");
        assertEquals(60,h.batchInsert(p));
        assertFalse(h.insert(new Pair("Apple".hashCode(),"Apple")));
        assertTrue(search("Apple","Alppe","Aplpe"));
        assertEquals(60,h.batchDelete(p2));
        assertFalse(search("Apple","Alppe","Aplpe"));
    }
    @Test
    public void Test12()
    {
        h=new HashN2(5000);
        ArrayList<Pair> p = FileReader.loadpairslist("/home/mahmoud/Test4.txt");
        Pair[]p2=FileReader.loadpairsarray("/home/mahmoud/Test4.txt");
        assertEquals(5040,h.batchInsert(p));
        assertFalse(h.insert(new Pair("wake up".hashCode(),"wake up")));
        assertTrue(h.delete(new Pair("wake up".hashCode(),"wake up")));
        assertTrue(search("wakeup ","wa keup"," wakeup"));
        assertEquals(5039,h.batchDelete(p2));
        assertFalse(search("wakeup ","wa keup"," wakeup"));
    }@Test
    public void Test13()
    {
        h=new HashN2(10000);
        ArrayList<Pair> p = FileReader.loadpairslist("/home/mahmoud/Test5.txt");
        Pair[]p2=FileReader.loadpairsarray("/home/mahmoud/Test5.txt");
        assertEquals(10080,h.batchInsert(p));
        assertFalse(h.insert(new Pair("lotfobal".hashCode(),"lotfobal")));
        assertTrue(search("football","lotfobal","foltbola"));
        assertEquals(10080,h.batchDelete(p2));
        assertFalse(search("football","lotfobal","foltbola"));
    }@Test
    public void Test14()
    {
        h=new HashN2(20000);
        ArrayList<Pair> p = FileReader.loadpairslist("/home/mahmoud/Test6.txt");
        Pair[]p2=FileReader.loadpairsarray("/home/mahmoud/Test6.txt");
        assertEquals(20160,h.batchInsert(p));
        assertFalse(h.insert(new Pair("mid exam".hashCode(),"mid exam")));
        assertTrue(search("max emid","mid emax","mid exam"));
        assertEquals(20160,h.batchDelete(p2));
        assertFalse(search("max emid","mid emax","mid exam","mid "));
    }@Test
    public void Test15()
    {
        insertcases("hot","Hot",""," ","hOt","hoT","HOT","hOT","HOt","HoT");
        assertTrue(h.searchForKey("".hashCode()));
    }@Test
    public void Test16()
    {
        insertcases("messi","messi","messi");
        assertTrue(h.delete(new Pair("messi".hashCode(),"messi")));
        assertFalse(h.searchForKey("messi".hashCode()));
    }@Test
    public void Test17()
    {
        insertcases("Alice 781","John 443","David 982","Emily 599","Michael 88","Daniel 246","Bob 915","Sophia 348","Olivia 21",
                "Mervat 1000");
        assertTrue(h.searchForKey("Emily 599".hashCode()));
        assertFalse(h.searchForKey("Alice 780".hashCode()));
        assertEquals(2,h.batchDelete(deletebatch("David 982","Mervat 1000","Ali 781","Michael 89")));
        assertFalse(h.searchForKey("David 982".hashCode()));
        assertFalse(h.searchForKey("Mervat 1000".hashCode()));
        assertEquals(3,h.batchInsert(insertbatch("Ali 781","Hoda 30","Mahmoud 70","John 443","Olivia 21")));
    }
    @Test
    public void Test18()
    {
        h=new HashN2(50);
        ArrayList<Pair> P = FileReader.loadpairslist("/home/mahmoud/Test7.txt");
        Pair[]p2=FileReader.loadpairsarray("/home/mahmoud/Test7.txt");
        assertEquals(57,h.batchInsert(P));
        assertEquals(57,h.batchDelete(p2));
    }
    @Test
    public void Test19()
    {
        insertcases("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa");
        assertFalse(h.searchForKey("aaaaaaa a".hashCode()));
        assertFalse(h.searchForKey("aaaaaaa ".hashCode()));
        assertTrue(h.searchForKey("aaaaaaa".hashCode()));
    }@Test
    public void Test20()
    {
        insertcases(
                "Othman Grandparent",
                "Layla Grandmother"
                ,"Ali father",
                "Karma Mother",
                "Sameh child1",
                "Sara child2",
                "Mona child3",
                "Ola grandchild1",
                "Maha grandchild2",
                "Hoda grandchild3",
                "Toka grandchild4",
                "Kareem grandchild5",
                "Omar grandchild6",
                "Louai grandchild7"
        );
        assertFalse(h.delete(new Pair("Louai grandchild0".hashCode(),"Louai grandchild0")));
        assertTrue(h.delete(new Pair("Louai grandchild7".hashCode(),"Louai grandchild7")));
        assertFalse(h.searchForKey("Louai grandchild7".hashCode()));
        assertTrue(h.insert(new Pair("Louai grandchild7".hashCode(),"Louai grandchild7")));
        assertTrue(h.searchForKey("Louai grandchild7".hashCode()));
    }
}
