package com.company;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader{

    private ArrayList<Integer> fileData;

    public Reader(String fileName) {
      Scanner scanner;
      try {
          scanner = new Scanner(new File("src/fichier/" + fileName));
          readFileData(scanner);
          scanner.close();
      }catch(Exception e){System.out.println(e.toString());}

     }


    private void readFileData(Scanner scanner){
      fileData = new ArrayList();
      while(scanner.hasNextInt()) {
        fileData.add(scanner.nextInt());
      }
    }

    public ArrayList getFileData() {
        return fileData;
    }
}
