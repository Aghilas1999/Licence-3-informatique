package DataHandler;

import java.util.Objects;
import java.util.Scanner;


public class User {
    static Scanner scanner = new Scanner(System.in);

    public String username;

    protected int id;

    public User(String username) {
        this.username = username;
    }
    public User(String username, int id ) {
        this(username);
        this.id = id;
    }



    @Override
    public boolean equals(Object o) {
        if (o instanceof User) {
            return ((User) o).username.equals(username);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.username);
        return hash;
    }


    public static String[] getLogs() {
        String[] logs = new String[2];
        System.out.println("username : ");
        logs[0] = scanner.nextLine();
        System.out.println("password : ");
        logs[1] = scanner.nextLine();
        return logs;
    }

    @Override
    public String toString() {
        return "DataHandler.User{" +
                "username='" + username + '\'' +
                ", id=" + id +
                '}';
    }
}
