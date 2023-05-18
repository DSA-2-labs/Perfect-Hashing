package Hashing;

public class HashN implements PerfectHashing {
    public HashN(int n) {

    }

    @Override
    public boolean insert(Pair pair) {
        return false;
    }

    @Override
    public int batchInsert(Pair[] pairs) {
        return 0;
    }

    @Override
    public boolean delete(Pair pair) {
        return false;
    }

    @Override
    public int batchDelete(Pair[] pairs) {
        return 0;
    }

    @Override
    public Object lookup(int key) {
        return null;
    }

    @Override
    public Object[] BatchLookup(int[] keys) {
        return null;
    }

    @Override
    public boolean searchForKey(int key) {
        return false;
    }

    @Override
    public void rehash() {

    }

    @Override
    public int getRebuildCounter() {
        return 0;
    }
}
