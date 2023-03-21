package test;

import java.util.HashMap;
import java.util.Map;

public class DictionaryManager {
    HashMap<String, Dictionary> map;

    DictionaryManager() {
        map = new HashMap<>();
    }

    private static DictionaryManager myDic = null;

    private void addDict(String... args) {
        for (int i = 0; i < args.length - 1; i++) {
            if (!map.containsKey(args[i]))
                map.put(args[i], new Dictionary(args[i]));
        }
    }

    public boolean query(String... args) {
        addDict(args);
        boolean found = false;
        String word = args[args.length - 1];
        for (int i = 0; i < args.length - 1; i++) {
            if (map.get(args[i]).query(word))
                found = true;
        }
        return found;
    }

    public boolean challenge(String... args) {
        addDict(args);
        String word = args[args.length - 1];
        boolean found = false;
        for (int i = 0; i < args.length - 1; i++) {
            if (map.get(args[i]).challenge(word))
                found = true;
        }
        return found;
    }

    public int getSize() {
        return this.map.size();
    }

    public static DictionaryManager get() {
        if (myDic == null)
            myDic = new DictionaryManager();
        return myDic;
    }
}