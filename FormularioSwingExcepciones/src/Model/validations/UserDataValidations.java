package Model.validations;

import exceptions.InvalidCPException;
import exceptions.InvalidEmailException;
import java.util.Calendar;
import java.util.regex.Pattern;

public class UserDataValidations {

    public static boolean checkId(int typeDoc, String id) {
        String[] letras = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};

        if (id.length() != 9) {
            return false;
        }

        String parteNum = id.substring(0, 8);
        if (!CheckNumeric(parteNum)) {
            return false;
        }

        int numero = Integer.parseInt(parteNum);
        int resto = numero % 23;

        return id.endsWith(letras[resto]);
    }

    public static boolean CheckFormatDate(String date) {
        String[] format = date.split("/");

        if (format.length != 3) {
            return false;
        }

        try {
            int day = Integer.parseInt(format[0]);
            int month = Integer.parseInt(format[1]);
            int year = Integer.parseInt(format[2]);

            // Verificación de rangos más realistas
            if (day < 1 || day > 31 || month < 1 || month > 12 || year < 1900 || year > Calendar.getInstance().get(Calendar.YEAR)) {
                return false;
            }

            // Validación de meses con 30 días
            if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
                return false;
            }

            // Verificación de febrero y años bisiestos
            if (month == 2) {
                boolean isLeapYear = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
                if ((isLeapYear && day > 29) || (!isLeapYear && day > 28)) {
                    return false;
                }
            }

        } catch (NumberFormatException e) {
            return false; // En caso de que no sean números válidos
        }

        return true;
    }

    public static int calculateAge(String birthDate) {
        if (!CheckFormatDate(birthDate)) {
            return -1; // Fecha inválida
        }

        String[] parts = birthDate.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]) - 1; // Ajuste del mes (0-11)
        int year = Integer.parseInt(parts[2]);

        Calendar birthCalendar = Calendar.getInstance();
        birthCalendar.set(year, month, day);

        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);

        // Ajuste si el cumpleaños aún no ha pasado este año
        if (today.get(Calendar.MONTH) < birthCalendar.get(Calendar.MONTH)
                || (today.get(Calendar.MONTH) == birthCalendar.get(Calendar.MONTH)
                && today.get(Calendar.DAY_OF_MONTH) < birthCalendar.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }

        if (age < 0 && age < 121) {
            return -1; // Fecha de nacimiento en el futuro
        } else {
            return age;
        }
    }

    public static void CheckPostalCode(String zip) throws InvalidCPException {
        if (zip == null || !zip.matches("\\d{5}")) {
            throw new InvalidCPException("El código postal debe tener 5 cifras numéricas.");
        }
    }

//        return zip.matches("\\d{5}");
    public static boolean CheckNumeric(String number) {
        if (number == null || number.isEmpty()) {
            return false;
        }
        for (char c : number.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean CheckAlphabetic(String words) {
        if (words == null || words.isEmpty()) {
            return false;
        }
        for (char c : words.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public static void CheckEmail(String email) throws InvalidEmailException {
        if (email == null || !email.contains("@")) {
            throw new InvalidEmailException("El correo electrónico no es válido.");
        }
    }
//        if (email == null || email.isEmpty()) {
//            return false;
//        }
//        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
//        return Pattern.matches(emailRegex, email);

    public static boolean CheckName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        if (name.length() < 2 || name.length() > 50) {
            return false;
        }
        for (char c : name.toCharArray()) {
            if (Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

}
