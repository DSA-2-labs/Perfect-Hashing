package Tests;
import Application.FileReader.FileReader;
import Hashing.HashN2;
import Hashing.Pair;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class N2Tests {
    HashN2 h ;
    private void insertcases(Object...objs)
    {
        h = new HashN2(objs.length);
        for (Object object: objs)
        {
            Pair p = new Pair(object.hashCode(),object);
            h.insert(p);
        }
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
        h=new HashN2(10);
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

    }
    @Test
    public void Test6()
    {

    }@Test
    public void Test7()
    {

    }@Test
    public void Test8()
    {

    }@Test
    public void Test9()
    {

    }@Test
    public void Test10()
    {

    }@Test
    public void Test11()
    {

    }@Test
    public void Test12()
    {

    }@Test
    public void Test13()
    {

    }@Test
    public void Test14()
    {

    }@Test
    public void Test15()
    {

    }@Test
    public void Test16()
    {

    }@Test
    public void Test17()
    {

    }@Test
    public void Test18()
    {

    }@Test
    public void Test19()
    {

    }@Test
    public void Test20()
    {

    }
}
