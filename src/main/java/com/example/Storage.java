package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static ArrayList<Costs> readCosts() {
        try {
            return tryReadCosts();
        } catch (FileNotFoundException e) {
            System.err.println("Hiba a fájl olvasása közben: " + e.getMessage());
            return new ArrayList<Costs>();
        }
    }

    public static ArrayList<Costs> tryReadCosts() throws FileNotFoundException {
        ArrayList<Costs> costList = new ArrayList<Costs>();
        File file = new File("costs.txt");
        try(Scanner sc = new Scanner(file)) {
            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(";");
                Costs cost = new Costs();
                cost.setId(Integer.parseInt(parts[0]));
                cost.setName(parts[1]);
                cost.setPrice(Integer.parseInt(parts[2]));
                cost.setCategory(parts[3]);
                cost.setComment(parts[4]);
                costList.add(cost);
            }
        }
        return costList;
    }

    public static void writeCosts(ArrayList<Costs> costList) {
        try {
            tryWriteCosts(costList);
        } catch (IOException e) {
            System.err.println("Hiba a fájl írása közben: " + e.getMessage());
        }
    }

    public static void tryWriteCosts(ArrayList<Costs> costList) throws IOException {
        FileWriter fw = new FileWriter("costs.txt");
        fw.write("id;name;price;category;comment\n");
        for (Costs cost : costList) {
            fw.write(
                cost.getId() + ";" +
                cost.getName() + ";" +
                cost.getPrice() + ";" +
                cost.getCategory() + ";" +
                cost.getComment() + "\n"
            );
        }
        fw.close();
    }
}