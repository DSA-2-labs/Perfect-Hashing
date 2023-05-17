package Hashing;
public class HashN2 implements PerfectHashing {
    private int rebuild;
    private int N;
    private int b; //number of bits: M = 2^b
    private Pair[] hashTable;
    private Matrix hashFunction;

    public HashN2(int n) {
        n *= n;
        int closestPowerOf2 = 1;
        int tmpBits = 0;
        while(closestPowerOf2 < n) {
            closestPowerOf2 <<= 2;
            tmpBits++;
        }
        this.rebuild = 0;
        this.N = closestPowerOf2;
        this.b = tmpBits;
        this.hashTable = new Pair[N];
    }


    @Override
    public void build(Pair[] pairs) {

    }

    @Override
    public void insert(Pair pair) {

    }

    @Override
    public Object lookup(int key) {
        return null;
    }

    @Override
    public Object BatchLookup(int[] keys) {
        return null;
    }

    @Override
    public void rehash() {
        this.hashTable = new Pair[N];
    }

    @Override
    public int getRebuildCounter() {
        return this.rebuild;
    }
}
