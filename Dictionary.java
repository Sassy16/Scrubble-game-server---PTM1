package test;

import java.io.BufferedReader;
import java.io.FileReader;


public class Dictionary {
    CacheManager existsWords;
    CacheManager notExistsWords;
    BloomFilter bloomFilter;
    String fileNames[];

    public Dictionary(String...fileNames){
        this.existsWords = new CacheManager(400, new LRU());
        this.notExistsWords = new CacheManager(100, new LFU());
        this.bloomFilter = new BloomFilter(256, "MD5", "SHA1");
        this.fileNames = fileNames;
        for(String file:fileNames){
            try{
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                while ((line = bufferedReader.readLine()) != null){
                    String [] words = line.split(" ");
                    for(String word:words){
                        this.bloomFilter.add(word);
                    }
                }
                bufferedReader.close();
            } catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public boolean query(String word){
        if(this.existsWords.query(word)){
            return true;
        } else if(this.notExistsWords.query(word)){
            return false;
        } else if(this.bloomFilter.contains(word)){
            this.existsWords.add(word);
            return true;
        } else {
            this.notExistsWords.add(word);
            return false;
        }
    }

    public boolean challenge (String word){
        try{
            if (IOSearcher.search(word, this.fileNames)){
                this.existsWords.add(word);
                return true;
            } else {
                this.notExistsWords.add(word);
                return false;
            }
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}
