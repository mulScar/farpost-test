package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws IOException {
        // TODO: 11.02.2023 filter keys
        String key = args[0];
        String availabilityLevel = args[1];
        String key_2 = args[2];
        String responseTime = args[3];

        String file = "../access.log";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String s = reader.readLine();
        System.out.println("FILE READER");
        ResponseInfo info = ResponseInfo.parse(s);
        System.out.println(info);

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nSCANNER");
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            ResponseInfo info1 = ResponseInfo.parse(str);
            System.out.println(info1);
        }
        scanner.close();
    }
}
