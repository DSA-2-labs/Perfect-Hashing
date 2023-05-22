package Hashing;

import java.util.List;

public interface PerfectHashing {
    boolean insert(Pair pair);
    int batchInsert(List<Pair> pairs);
    boolean delete(Pair pair);
    int batchDelete(Pair[] pairs);
    Object lookup(long key);
    Object[] BatchLookup(long[] keys);
    boolean searchForKey(long key);
    void rehash();
    int getRebuildCounter();
    void print();
}
