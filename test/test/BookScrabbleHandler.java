package test;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class BookScrabbleHandler implements ClientHandler {
    PrintWriter out;
    Scanner in;

    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {
        out = new PrintWriter(outToClient);
        in = new Scanner(inFromclient);
        String[] words = in.next().split(",");
        String method = words[0];
        String[] arr = new String[words.length - 1];
        System.arraycopy(words, 1, arr, 0, words.length - 1);
        if (method.equals("Q")) {
            DictionaryManager dm = new DictionaryManager();
            if (dm.query(arr))
                out.println("true");
            else
                out.println("false");

        } else {
            DictionaryManager dm = new DictionaryManager();
            if (dm.challenge(arr))
                out.println("true");
            else
                out.println("false");
        }
        out.flush();
    }

    @Override
    public void close() {
        in.close();
        out.close();
    }
}
