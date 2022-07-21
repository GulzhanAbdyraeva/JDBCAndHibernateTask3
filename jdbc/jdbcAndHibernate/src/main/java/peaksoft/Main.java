package peaksoft;

import peaksoft.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl us = new UserServiceImpl();
        us.createUsersTable();
        us.saveUser("Gulzhan", "Abdyraeva", (byte) 20);
        System.out.println(us.getAllUsers());
//        us.getAllUsers().forEach(System.out::println);
        us.cleanUsersTable();
        us.removeUserById(1);
        us.dropUsersTable();

    }
}
