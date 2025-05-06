package view.console;

import Model.validations.UserDataValidations;
import exceptions.InvalidCPException;
import exceptions.InvalidEmailException;
import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        String option = "";
        do {
            System.out.println("1.-Tester checkId");
            System.out.println("2.-Tester checkFormatDate");
            System.out.println("3.-Tester checkCalculateAge");
            System.out.println("4.-Tester checkPostalCode");
            System.out.println("5.-Tester checkNumeric");
            System.out.println("6.-Tester checkAlplhabetic");
            System.out.println("7.-Tester checkEmail");
            System.out.println("8.-Tester checkName");
            System.out.println("z.-Salir");

            System.out.print("Option");
            option = scan.next();

            switch (option) {
                case "1":
                    testCheckId();
                    break;
                case "2":
                    testCheckFormatDate();
                    break;
                case "3":
                    testCheckCalculateAge();
                    break;
                case "4":
                    testCheckPostalCode();
                    break;
                case "5":
                    testCheckNumeric();
                    break;
                case "6":
                    testCheckAlphabetic();
                    break;
                case "7":
                    testCheckEmail();
                    break;
                case "8":
                    testCheckName();
                    break;
                case "z":
                    System.out.println("Saliendo del programa");
                    break;

                default:
                    System.out.println("Incorrect Option");
            }
        } while (option.equals("z"));
    }

    private static void testCheckId() {
        System.out.println("-NIF VALIDATOR-");
        System.out.print("Enter your ID: ");
        String ID = scan.next();
        boolean idOK = UserDataValidations.checkId(1, ID);
        if (idOK) {
            System.out.println("Correct ID");
        } else {
            System.out.println("Wrong ID");
        }
    }

    private static void testCheckFormatDate() {
        System.out.println("DATE VALIDATOR (Format: dd/mm/yyyy )");
        String DATE = "";
        DATE = scan.next();
        boolean idOK = UserDataValidations.CheckFormatDate(DATE);
        if (idOK) {
            System.out.println("Correct Date");
        } else {
            System.out.println("Wrong Date");
        }
    }

    private static void testCheckCalculateAge() {
        System.out.println("AGE VALIDATOR");
        String birthDate = "";
        birthDate = scan.next();
        int AGE = UserDataValidations.calculateAge(birthDate);
        if (AGE != -1) {
            System.out.println("Correct Date");
            System.out.println("Your age is " + AGE + "years old");
        } else {
            System.out.println("Wrong Date");
        }
    }

    private static void testCheckPostalCode() {
        System.out.println("POSTAL CODE VALIDATOR");
        String ZIP = scan.next();
        try {
            UserDataValidations.validatePostalCode(ZIP);
            System.out.println("Correct Postal Code");
        } catch (InvalidCPException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void testCheckNumeric() {
        System.out.println("NUMBER VALIDATOR");
        String number = "";
        number = scan.next();
        boolean idOK = UserDataValidations.CheckNumeric(number);
        if (idOK) {
            System.out.println("Correct number");
        } else {
            System.out.println("Wrong number");
        }
    }

    private static void testCheckAlphabetic() {
        System.out.println("ALPHABETIC VALIDATOR");
        String words = "";
        words = scan.next();
        boolean idOK = UserDataValidations.CheckAlphabetic(words);
        if (idOK) {
            System.out.println("Correct Words");
        } else {
            System.out.println("Wrong Words");
        }
    }

    private static void testCheckEmail() {
        System.out.println("EMAIL VALIDATOR");
        String email = scan.next();
        try {
            UserDataValidations.validateEmail(email);
            System.out.println("Correct Email");
        } catch (InvalidEmailException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void testCheckName() {
        System.out.println("NAME VALIDATOR");
        String name = "";
        name = scan.next();
        boolean nameVal = UserDataValidations.CheckName(name);
        if (nameVal) {
            System.out.println("Correct Words");
        } else {
            System.out.println("Wrong Words");
        }
    }
}
