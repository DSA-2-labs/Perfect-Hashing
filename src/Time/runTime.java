package Time;
import Application.Dictionary;

public class runTime {
    public void Print_Time_BuildCount(String fname, int size, String word){
        Dictionary dictionaryN = new Dictionary("Hash_N",size);
        Dictionary dictionaryN2 = new Dictionary("Hash_N2",size);
        dictionaryN.Batch_Insert(fname);
        dictionaryN2.Batch_Insert(fname);

        System.out.println("N rebuild number : "+dictionaryN.getrebuild());
        System.out.println("N2 rebuild number : "+dictionaryN2.getrebuild());


        long currentTime = System.nanoTime();
        if (dictionaryN.search_word(word)) {
            System.out.println("TRUE");
        }
        long currentTime2 = System.nanoTime();
        System.out.println("N Time: " + (currentTime2 - currentTime));

        currentTime = System.nanoTime();
        if(dictionaryN2.search_word(word)){
            System.out.println("TRUE");
        }
        currentTime2 = System.nanoTime();
        System.out.println("N2 TIME: "+(currentTime2-currentTime));
    }
}
