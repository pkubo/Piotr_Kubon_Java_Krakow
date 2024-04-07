package org.example;

import com.ocado.BasketSplitter;

import java.io.IOException;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BasketSplitter basketSplitter = new BasketSplitter("C:\\Users\\pkubo\\Desktop\\progr\\maven2\\src\\main\\resources\\config.json");
        List<String> items = new ArrayList<>(Arrays.asList("Cocoa Butter", "Tart - Raisin And Pecan", "Table Cloth 54x72 White", "Flower - Daisies", "Fond - Chocolate", "Cookies - Englishbay Wht"));

        System.out.println(basketSplitter.split(items));
    }
}