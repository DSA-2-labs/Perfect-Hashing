package Hashing;

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
        n *= n;
        int closestPowerOf2 = 1;
        int tmpBits = 0;
        while(closestPowerOf2 < n) {
            closestPowerOf2 <<= 1;
            tmpBits++;
        }
        this.rebuild = 0;
        this.N = closestPowerOf2;
        this.b = tmpBits;
        this.hashTable = new Pair[N];
        this.elementCounter = 0;
        this.firsttime = true;
    }


    @Override
    public boolean insert(Pair pair) {
        if(this.elementCounter == 0 && this.firsttime) {
            this.hashFunction = MatrixRandomGenerator.generate(this.b, 32);
//            hashFunction.print();
            this.firsttime = false;
        }
        else if(this.elementCounter == N) {
//            System.out.println("Size is exceeded!");
            return false;
        }
        int key = pair.key;
        Object value = pair.value;
        int index = calcIndex(key);

        if (this.hashTable[index] != null && this.hashTable[index].key != key) {
            this.rehash();
            return this.insert(pair);
        }
        else if (this.hashTable[index] != null && this.hashTable[index].key == key){
//            System.out.println("Word already exists!");
            return false;
        }
        else {
            this.elementCounter++;
            this.hashTable[index] = new Pair();
            this.hashTable[index].key = key;
            this.hashTable[index].value = value;
//            System.out.println("Word inserted successfully!");
            return true;
        }
    }

    @Override
    public int batchInsert(List<Pair> pairs) {
        int counter = 0;
        for(Pair p: pairs) {
            if(this.insert(p)) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public boolean delete(Pair pair) {
        int key = pair.key;
        int index = calcIndex(key);
        if (this.hashTable[index] == null) {
//            System.out.println("Word doesn't exist!");
            return false;
        }
        else if(this.hashTable[index].key == key) {
            this.elementCounter--;
            this.hashTable[index] = null;
//            System.out.println("Word deleted successfully!");
            return true;
        }
//        System.out.println("No words entered yet!");
        return false;
    }

    @Override
    public int batchDelete(Pair[] pairs) {
        int counter = 0;
        for(Pair p: pairs) {
            if(this.delete(p)) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public Object lookup(int key) {
        int index = calcIndex(key);
        return (this.hashTable[index] != null && this.hashTable[index].key == key)? this.hashTable[index].value : null;
    }

    @Override
    public Object[] BatchLookup(int[] keys) {
        Object[] values = new Object[keys.length];
        for(int i = 0; i < keys.length; i++) {
            values[i] = this.lookup(keys[i]);
        }
        return values;
    }

    @Override
    public boolean searchForKey(int key) {
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

    private int calcIndex(int key) {
        Matrix keyMatrix = Matrix.convertToMatrix(key);
        Matrix indexMatrix = this.hashFunction.multiply(keyMatrix);
        return Matrix.convertMatrixToIndex(indexMatrix);
    }
    public int getN()
    {
        return this.N;
    }
    public void print(){
        for(int i = 0; i < N; i++){
            if(this.hashTable[i] == null)
                continue;
            System.out.printf("Index %d -> key: %d, value: ", i, this.hashTable[i].key);
            System.out.println(this.hashTable[i].value);
        }
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
    public boolean collisionCheck(int key){
        int index = calcIndex(key);
        return this.hashTable[index] != null && this.hashTable[index].key != key;
    }
}
