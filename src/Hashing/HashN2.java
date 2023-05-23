package Hashing;

import pairds.pairds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HashN2 implements PerfectHashing {
    private int rebuild;
    private int N;
    private int b; //number of bits: M = 2^b
    public Pair[] hashTable;
    private Matrix hashFunction;
    private int elementCounter;
    private boolean firsttime;
    public HashN2(int n) {
        try {
            n *= n;
            int closestPowerOf2 = 1;
            int tmpBits = 0;
            while (closestPowerOf2 < n) {
                closestPowerOf2 <<= 1;
                tmpBits++;
            }
            this.rebuild = 0;
            this.N = closestPowerOf2;
            //System.out.println(this.N);
            this.b = tmpBits;
            this.hashTable = new Pair[N];
            this.elementCounter = 0;
            this.firsttime = true;
        }catch (Exception e){
            System.out.println(e);
        }
    }



    @Override
    public boolean insert(Pair pair) {
        if(this.elementCounter == 0 && this.firsttime) {
            this.hashFunction = MatrixRandomGenerator.generate(this.b, 64);
            this.firsttime = false;
        }
        else
            if(this.elementCounter == N) {
            return false;
        }
        long key = pair.key;
        Object value = pair.value;
        int index = calcIndex(key);

        if (this.hashTable[index] != null && this.hashTable[index].key != key) {
            this.rehash();
            return this.insert(pair);
        }
        else if (this.hashTable[index] != null && this.hashTable[index].key == key){
            return false;
        }
        else {
            this.elementCounter++;
            this.hashTable[index] = new Pair();
            this.hashTable[index].key = key;
            this.hashTable[index].value = value;
            return true;
        }
    }

    @Override
    public pairds batchInsert(List<Pair> pairs) {
        pairds pair = new pairds();
        for(Pair p: pairs) {
            if(this.insert(p)) {
                pair.success++;
            }
            else
            {
                pair.fail++;
            }
        }
        return pair;
    }

    @Override
    public boolean delete(Pair pair) {
        if(this.elementCounter==0)
            return false;
        long key = pair.key;
        int index = calcIndex(key);
        if (this.hashTable[index] == null) {
            return false;
        }
        else if(this.hashTable[index].key == key) {
            this.elementCounter--;
            this.hashTable[index] = null;
            return true;
        }
        return false;
    }

    @Override
    public pairds batchDelete(Pair[] pairs) {
        pairds pair = new pairds();
        if (this.elementCounter==0) return pair;
        for(Pair p: pairs) {
            if(this.delete(p)) {
                pair.success++;
            }
            else
            {
                pair.fail++;
            }
        }
        return pair;
    }

    @Override
    public Object lookup(long key) {
        int index = calcIndex(key);
        return (this.hashTable[index] != null && this.hashTable[index].key == key)? this.hashTable[index].value : null;
    }

    @Override
    public Object[] BatchLookup(long[] keys) {
        Object[] values = new Object[keys.length];
        for(int i = 0; i < keys.length; i++) {
            values[i] = this.lookup(keys[i]);
        }
        return values;
    }

    @Override
    public boolean searchForKey(long key) {
        int index = calcIndex(key);
        return this.hashTable[index] != null && this.hashTable[index].key == key;
    }

    @Override
    public void rehash() {
        this.rebuild++;
        this.elementCounter = 0;
        this.firsttime = true;
        List<Pair> tmp = new ArrayList<>();
        for(Pair p: hashTable) {
            if (p != null) tmp.add(p);
        }
        this.hashTable = new Pair[this.N];
        this.batchInsert(tmp);
    }

    @Override
    public int getRebuildCounter() {
        return this.rebuild;
    }

    private int calcIndex(long key) {
        Matrix keyMatrix = Matrix.convertToMatrix(key);
        Matrix indexMatrix = this.hashFunction.multiply(keyMatrix);
        return Matrix.convertMatrixToIndex(indexMatrix);
    }
    public int getN()
    {
        return this.N;
    }
    public void print(){
//        for(int i = 0; i < N; i++){
//            if(this.hashTable[i] == null)
//                continue;
//            System.out.printf("Index %d -> key: %d, value: ", i, this.hashTable[i].key);
//            System.out.println(this.hashTable[i].value);
//        }
    }
    public ArrayList<Pair> getPairs(){
        ArrayList<Pair> pairs = new ArrayList<>();
        for(int i = 0; i < N; i++){
            if(this.hashTable[i] == null)
                continue;
            pairs.add(this.hashTable[i]);
        }
        return pairs;
    }
    public void setRebuild(int rebuild) {
        this.rebuild = rebuild;
    }



    public void setN(int n) {
        N = n;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public Pair[] getHashTable() {
        return hashTable;
    }

    public void setHashTable(Pair[] hashTable) {
        this.hashTable = hashTable;
    }

    public Matrix getHashFunction() {
        return hashFunction;
    }

    public void setHashFunction(Matrix hashFunction) {
        this.hashFunction = hashFunction;
    }

    public int getElementCounter() {
        return elementCounter;
    }

    public void setElementCounter(int elementCounter) {
        this.elementCounter = elementCounter;
    }
    public boolean collisionCheck(long key){
        int index = calcIndex(key);
        return this.hashTable[index] != null && this.hashTable[index].key != key;
    }
}
