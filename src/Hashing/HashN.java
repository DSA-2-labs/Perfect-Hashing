package Hashing;

import java.util.ArrayList;
import java.util.List;

public class HashN implements PerfectHashing {
    private int N;
    private int b; //number of bits: M = 2^b
    private HashN2[] hashTable;
    private Matrix hashFunction;
    ArrayList<ArrayList<Pair>> SecondLevelTemp;
    private int elementCounter;
    private int lastindex;

    private Pair lastpair;
    private int rebuildCounter;

    private boolean firsttime;
    public HashN(int n) {
        int closestPowerOf2 = 1;
        int tmpBits = 0;
        while(closestPowerOf2 < n) {
            closestPowerOf2 <<= 1;
            tmpBits++;
        }
        this.N = closestPowerOf2;
        this.b = tmpBits;
        this.hashTable = new HashN2[this.N];
        this.elementCounter = 0;
        this.rebuildCounter = 0;
        this.SecondLevelTemp=new ArrayList<ArrayList<Pair>>();
        for(int i=0;i<this.N;i++)
            this.SecondLevelTemp.add(new ArrayList<Pair>());
        this.lastindex=-1;
        this.firsttime=true;
    }

    @Override
    public boolean insert(Pair pair) {
        if(this.elementCounter == 0&&this.firsttime) {
            this.hashFunction = MatrixRandomGenerator.generate(this.b, 32);
            hashFunction.print();
            this.firsttime = false;
        }
        int key = pair.key;
        Object value = pair.value;
        int index = calcIndex(key);
        this.lastindex=index;
        this.lastpair=pair;

        if (this.hashTable[index] == null) {
            this.hashTable[index] = new HashN2(1);
            this.hashTable[index].insert(pair);
            this.elementCounter++;
            return true;
        }
        else if (this.hashTable[index].searchForKey(key)){
            System.out.println("Word already exists!");
            return false;
        }
        //if there is a collision in the second level
        else if(hashTable[index].collisionCheck(key)) {
            System.out.println("collision in the second level");
            this.rehash();
            this.elementCounter++;
            return true;
        }
        else {
            if(this.hashTable[index].getElementCounter()!=0)
                this.rebuildCounter++;
            if(this.hashTable[index].insert(pair)){
                this.elementCounter++;
                return true;
            }
            return false;
        }

    }

    @Override
    public int batchInsert(List<Pair> pairs) {
        int counter = 0;
        for(Pair pair : pairs) {
            int key = pair.key;
            Object value = pair.value;
            int index = calcIndex(key);
            this.SecondLevelTemp.get(index).add(pair);
        }
        for(int i=0;i<this.N;i++)
        {
            if(this.SecondLevelTemp.get(i).size()>0)
            {
                if(this.hashTable[i]==null||this.hashTable[i].getElementCounter()==0){
                    this.hashTable[i]=new HashN2(this.SecondLevelTemp.get(i).size());
                    int count=this.hashTable[i].batchInsert(this.SecondLevelTemp.get(i));
                    counter+=count;
                    elementCounter+=count;
                    rebuildCounter+=this.SecondLevelTemp.get(i).size()-1;
                }
                else{
                    for(Pair pair:this.SecondLevelTemp.get(i)) {
                        if (this.insert(pair)) {
                            System.out.println("inserted successfully");
                            counter++;
                        }
                    }
                }
                this.SecondLevelTemp.get(i).clear();
            }
        }
        this.print();
        return counter;
    }

    @Override
    public boolean delete(Pair pair) {
        int key = pair.key;
        int index = calcIndex(key);
        if(this.hashTable[index] == null) {
            System.out.println("Word doesn't exist!");
            return false;
        }
        else if(this.hashTable[index].searchForKey(key)) {
            this.hashTable[index].delete(pair);
            this.elementCounter--;
            return true;
        }
        else {
            System.out.println("Word doesn't exist!");
            return false;
        }
    }

    @Override
    public int batchDelete(Pair[] pairs) {
        int counter = 0;
        for(Pair pair : pairs) {
            int key = pair.key;
            int index = calcIndex(key);
            if(this.hashTable[index] == null) {
                System.out.println("Word doesn't exist!");
            }
            else if(this.hashTable[index].searchForKey(key)) {
                this.hashTable[index].delete(pair);
                counter++;
            }
            else {
                System.out.println("Word doesn't exist!");
            }
        }
        return counter;
    }

    @Override
    public Object lookup(int key) {
        int index = calcIndex(key);
        if(this.hashTable[index] == null) {
            System.out.println("Word doesn't exist!");
            return null;
        }
        else if(this.hashTable[index].searchForKey(key)) {
            return this.hashTable[index].lookup(key);
        }
        else {
            System.out.println("Word doesn't exist!");
            return null;
        }
    }

    @Override
    public Object[] BatchLookup(int[] keys) {
        Object[] values = new Object[keys.length];
        for(int i = 0; i < keys.length; i++) {
            int key = keys[i];
            int index = calcIndex(key);
            if(this.hashTable[index] == null) {
                System.out.println("Word doesn't exist!");
                values[i] = null;
            }
            else if(this.hashTable[index].searchForKey(key)) {
                values[i] = this.hashTable[index].lookup(key);
            }
            else {
                System.out.println("Word doesn't exist!");
                values[i] = null;
            }
        }
        return values;
    }

    @Override
    public boolean searchForKey(int key) {
        int index = calcIndex(key);
        if(this.hashTable[index] == null) {
            System.out.println("Word doesn't exist!");
            return false;
        }
        else if(this.hashTable[index].searchForKey(key)) {
            return true;
        }
        else {
            System.out.println("Word doesn't exist!");
            return false;
        }
    }

    @Override
    public void rehash() {
        ArrayList<Pair> temp = hashTable[this.lastindex].getPairs();
        temp.add(this.lastpair);
        this.rebuildCounter+=this.hashTable[this.lastindex].getRebuildCounter()+1;
        this.hashTable[this.lastindex] = new HashN2(temp.size());
        this.hashTable[this.lastindex].batchInsert(temp);
    }

    @Override
    public int getRebuildCounter() {
        for(int i = 0; i < this.N; i++) {
            if(this.hashTable[i] != null) {
                this.rebuildCounter += this.hashTable[i].getRebuildCounter();
            }
        }
        return this.rebuildCounter;
    }

    @Override
    public void print() {
        for(int i = 0; i < this.N; i++) {
            if(this.hashTable[i] != null) {
                System.out.println("first level index: " + i);
                this.hashTable[i].print();
            }
        }
    }
    private int calcIndex(int key) {
        Matrix keyMatrix = Matrix.convertToMatrix(key);
        Matrix indexMatrix = this.hashFunction.multiply(keyMatrix);
        return Matrix.convertMatrixToIndex(indexMatrix);
    }

}
