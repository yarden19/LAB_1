package org.example.lab_2;

import java.time.LocalDateTime;

public class User {

    private String gmail;
    private String password;

    private boolean isBlocked = false;
    private LocalDateTime lockTime = null;
    private int failedAttempts = 0;

    public User(String gmail, String pass) {

        if (gmail.length() > 50)
            throw new RuntimeException("Username is too long, try something shorter");

        int atIndex = gmail.indexOf("@");
        int lastDotIndex = gmail.lastIndexOf(".");

        if (atIndex == -1 || lastDotIndex == -1 || lastDotIndex < atIndex) {
            throw new RuntimeException("Please enter a valid Email as username");
        }

        String part1 = gmail.substring(0, atIndex);
        String part2 = gmail.substring(atIndex + 1, lastDotIndex);
        String part3 = gmail.substring(lastDotIndex + 1);

        boolean isPart1Valid = part1.matches("^[a-zA-Z0-9._%+-]+$");
        boolean isPart2Valid = part2.matches("^[a-zA-Z0-9][a-zA-Z0-9.-]*$");
        boolean isPart3Valid = part3.matches("^[a-zA-Z]{2,}$");

        if (isPart1Valid && isPart2Valid && isPart3Valid) {
            this.gmail = gmail;
        } else {
            throw new RuntimeException("Please enter a valid Email as username");
        }

        if (pass.length() < 8)
            throw new RuntimeException("Your password is too short, add more characters");

        if (pass.length() > 12)
            throw new RuntimeException("Your password is too long, try a shorter one");

        String passwordRegex = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*()])[a-zA-Z0-9!@#$%^&*()]+$";

        if (pass.matches(passwordRegex)) {
            this.password = pass;
        } else {
            throw new RuntimeException("Please enter a valid password");
        }

        this.failedAttempts = 0;
        this.isBlocked = false;
        this.lockTime = null;
    }


    public String getGmail() { return this.gmail; }
    public String getPassword() { return this.password; }

    public boolean getIsBlocked() { return this.isBlocked; }
    public void setIsBlocked(boolean value) { this.isBlocked = value; }

    public LocalDateTime getLockTime() { return this.lockTime; }
    public void setLockTime(LocalDateTime t) { this.lockTime = t; }

    public int getFailedAttempts() { return failedAttempts; }

    public void incrementFailedAttempts() {
        this.failedAttempts++;
    }

    public void resetAccountStatus() {
        this.failedAttempts = 0;
        this.isBlocked = false;
        this.lockTime = null;
    }
}