package test;

import java.util.*;

public class LRU implements CacheReplacementPolicy{
    private LinkedList <String> list = new LinkedList<String>();


    public LRU() {
    }

    public void add(String word) {
        int index;
        index = list.indexOf(word);
        if (index != -1) {
            String existWord = list.remove(index);
            list.addFirst(existWord);
        } else {
            list.addFirst(word);
            }
        }

    public String remove() {
        String last = list.getLast();
        return last;
    }
}
