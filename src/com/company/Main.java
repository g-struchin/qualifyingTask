package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {

        char c = '9';
        System.out.println((int)c);

        String url = "http://numbersapi.com/100500/trivia";

        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println(response.toString());
        System.out.println();


        //for(char c : response.)


        //char[] mychar = response.chars().toArray(char[]::new);

        response.chars()
                .filter(i -> (int)i >=48 && (int)i <= 57 || (int)i >=65 && (int)i <= 90 || (int)i >=97 && (int)i <= 122)
                .forEach(i -> System.out.print((char)i));

        System.out.println();

        int sumChar = 0;
        int countChar = 0;
        HashMap<Character, Integer> frequency = new HashMap<>();

        for(char charR : response.toString().toCharArray()) {
            if(charR >= '0' && charR <= '9' || charR >= 'A' && charR <= 'Z' || charR >= 'a' && charR <= 'z') {
                sumChar++;
                countChar = frequency.getOrDefault(charR,0);
                countChar++;
                frequency.put(charR, countChar);
            }
        }

        for (Map.Entry entry: frequency.entrySet()) {

            System.out.println(entry);

        }
        System.out.println(sumChar);
        System.out.println(frequency.size());
    }
}