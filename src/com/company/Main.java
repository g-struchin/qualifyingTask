package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.CharBuffer;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {

        //long number = (long) (Math.random() * 9223372036854L);

        BigInteger number = BigInteger.probablePrime(72, new Random());

        String url = "http://numbersapi.com/"+number+"/trivia";

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

        /*System.out.println(response.toString());
        System.out.println();*/


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

        System.out.println("Частоты:");
        for (Map.Entry entry: frequency.entrySet()) {

            System.out.println(entry);

        }


        float averageFrequency = (float)sumChar/frequency.size();
        int divFrequency = sumChar/frequency.size();

        ArrayList<Character> nearChars = new ArrayList<Character>();
        int nearCountChar = 0;

        /*System.out.println(sumChar);
        System.out.println(frequency.size());*/
        System.out.println("Среднее значение частоты "+sumChar+"/"+frequency.size()+"="+averageFrequency);

        for (Map.Entry entry: frequency.entrySet()) {
            if(nearCountChar == 0) {
                nearCountChar = (int)entry.getValue();
            }
            if ((int)entry.getValue() == nearCountChar) {
                nearChars.add((Character) entry.getKey());
                continue;
            } else
            if (sumChar%frequency.size()*2==frequency.size()){
                if (nearCountChar > (int)divFrequency){
                    if ((int)entry.getValue() == divFrequency-(nearCountChar-divFrequency-1)) {
                        nearChars.add((Character) entry.getKey());
                        nearCountChar = (int) entry.getValue();
                        continue;
                    }
                } else{
                    if ((int)entry.getValue() == divFrequency+divFrequency-nearCountChar+1) {
                        nearChars.add((Character) entry.getKey());
                        nearCountChar = (int) entry.getValue();
                        continue;
                    }
                }
            }
            Integer val = (Integer) entry.getValue();
            float d1 = (float)val - averageFrequency;
            d1 = Math.abs(d1);
            float d2 = (float)nearCountChar - averageFrequency;
            d2 = Math.abs(d2);
            if (d1 < d2) {
                nearChars.clear();
                nearCountChar = (int) entry.getValue();
                nearChars.add((Character) entry.getKey());
            }
        }

        StringJoiner joiner = new StringJoiner(", ");

        for (Character ch: nearChars) {

            joiner.add(ch.toString()+"("+(int)ch+")");
        }

        System.out.println("Символы, которые соответствуют условию наиболее близкого значения частоты к среднему значанию: "+joiner);

        //nearChars.forEach(System.out::println);
    }
}