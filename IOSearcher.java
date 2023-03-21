package test;
import java.io.BufferedReader;
import java.io.FileReader;

public class IOSearcher {

    static boolean search(String word, String...fileNames){
        for (String file:fileNames){
            try{
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    if (line.contains(word)){
                        bufferedReader.close();
                        return true;
                    }
                }
                bufferedReader.close();
            } catch (Exception e){
                System.out.println(e);
            }
        }
        return false;
    }
}
