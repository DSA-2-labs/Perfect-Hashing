package Tests;
import Hashing.HashN2;
import Hashing.Pair;
import org.junit.Test;

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
        h.insert(new Pair());
    }
    @Test
    public void Test3()//mainly test delete function
    {

    }
    @Test
    public void Test4()//mainly test for batch insert
    {}
    @Test
    public void Test5()//mainly test for batch delete
    {}
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
