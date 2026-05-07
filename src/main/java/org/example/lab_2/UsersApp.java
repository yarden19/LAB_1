package org.example.lab_2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class UsersApp {

    private ArrayList<User> list = new ArrayList<>();
    public void readData(){
        try {
            //Move on the file
            File myFile = new File("users.txt");
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
                    this.list.add(u);
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

        this.list.sort(Comparator.comparing(User::getGmail));

        //Sort and print the users
        try (PrintWriter writer = new PrintWriter("out.txt")) {
            for (User u : this.list) {
                writer.println(u.getGmail() + " " + u.getPassword());
            }
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public boolean check_exist(String gmail,String password)
    {
        for(User u : this.list)
        {
            if (u.getGmail().equals(gmail) && u.getPassword().equals(password))
                return true;
        }
        return false;
    }
}
