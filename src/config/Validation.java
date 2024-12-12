package config;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validation {
    public static boolean checkEmail(String email) {


        return (email.matches(emailPattern()));

    }
    public static String emailPattern (){
        return "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    }

    public static boolean checkPasswort(String passwort) {
        return passwort.matches(passworPattern());
    }
    public static String passworPattern(){

        return "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>]).{6,}$";

    }
    public static int checkName() {
        int command = 0;
        boolean invalidCommand;

        System.out.print("Команданы киргизиңиз: ");
        do {
            try {
                command = new Scanner(System.in).nextInt();
                invalidCommand = false;
            } catch (InputMismatchException e) {
                invalidCommand = true;
                System.err.print("Жөнөкөй команда эмес. Туура команда киргизиңиз: ");
                new Scanner(System.in).nextLine(); // Боштукты тазалоо
            }
        } while (invalidCommand);

        return  command;
    }
}
