package Hashing;

import java.util.List;
import pairds.pairds;

public interface PerfectHashing {
    boolean insert(Pair pair);
    pairds batchInsert(List<Pair> pairs);
    boolean delete(Pair pair);
    pairds batchDelete(Pair[] pairs);
    Object lookup(long key);
    Object[] BatchLookup(long[] keys);
    boolean searchForKey(long key);
    void rehash();
    int getRebuildCounter();
    void print();
}
