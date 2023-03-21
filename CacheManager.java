package test;

import java.util.HashSet;

public class CacheManager {
	int size;
    CacheReplacementPolicy policy;
    HashSet<String> set = new HashSet<String>();
    int count;

    public CacheManager(int size, CacheReplacementPolicy policy) {
        this.size = size;
        this.policy = policy;
        this.count = 0;
    }

    public boolean query(String word) {
        if (set.contains(word)) {
            return true;
        } else {
            return false;
        }
    }

    public void add(String word) {
        if (query(word)) {
            policy.add(word);
        } else {
            if (count < size) {
                set.add(word);
                policy.add(word);
                count++;
            } else {
                String removeWord = policy.remove();
                set.remove(removeWord);
                set.add(word);
                policy.add(word);
            }
        }
    }
}
