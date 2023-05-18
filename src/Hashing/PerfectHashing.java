package Hashing;

public interface PerfectHashing {
    boolean insert(Pair pair);
    int batchInsert(Pair[] pairs);
    boolean delete(Pair pair);
    int batchDelete(Pair[] pairs);
    Object lookup(int key);
    Object[] BatchLookup(int[] keys);
    boolean searchForKey(int key);
    void rehash();
    int getRebuildCounter();
}
