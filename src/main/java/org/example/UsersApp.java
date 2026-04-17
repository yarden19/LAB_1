package org.example;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class UsersApp {
    public static void main(String[] args) {

        ArrayList <User> list = new ArrayList<User>();

        try {
            //Move on the file
            File myFile = new File("users.txt"); // שם הקובץ שלך
            Scanner scanner = new Scanner(myFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.trim().split("\\s+");

                String gmail = parts[0];
                String password = parts[1];

                //Add to the list the correct users
                try
                {
                    User u = new User(gmail,password);
                    list.add(u);
                }
                catch (Exception e)
                {
                    System.out.println(gmail +" " + password + " - " +e.getMessage());
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        list.sort(Comparator.comparing(User::getGmail));

        //Sort and print the users
        try (PrintWriter writer = new PrintWriter("out.txt")) {
            for (User u : list) {
                writer.println(u.getGmail() + " " + u.getPassword());
            }
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }
}
