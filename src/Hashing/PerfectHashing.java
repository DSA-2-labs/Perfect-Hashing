package Hashing;

public interface PerfectHashing {
    void build(Pair[] pairs);
    void insert(Pair pair);
    Object lookup(int key);
    Object BatchLookup(int[] keys);
    void rehash();
    int getRebuildCounter();
}
