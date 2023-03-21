package test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.BitSet;
import java.util.*;
import java.io.*;

public class BloomFilter {
	private int size;
    BitSet bitSet = new BitSet();
    String [] hashArray;

    public BloomFilter(int size, String ...args){
        int argsCount = args.length;
        this.size = size;
        this.hashArray = new String[argsCount];
        for (int i = 0; i < argsCount; i++) {
            hashArray[i] = args[i];
        }
    }

    public void add(String word){
        for (String hash: hashArray){
            try {
                MessageDigest md = MessageDigest.getInstance(hash);
                byte [] bytes = md.digest(word.getBytes());
                BigInteger bigInt = new BigInteger(bytes);
                int index = bigInt.abs().intValue();
                if(index < 0){
                    index = -index;
                }
                bitSet.set(index % size);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public boolean contains(String word){
        for (String hash: hashArray){
            try {
                MessageDigest md = MessageDigest.getInstance(hash);
                byte [] bytes = md.digest(word.getBytes());
                BigInteger bigInt = new BigInteger(bytes);
                int index = bigInt.abs().intValue();
                if(index < 0){
                    index = -index;
                }
                if (!bitSet.get(index % size)){
                    return false;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return true;
    }

    public String toString(){
        String result = "";
        for (int i = 0; i < bitSet.length(); i++) {
            if (bitSet.get(i)) {
                result += "1";
            } else {
                result += "0";
            }
        }
        return result;
    }
}
