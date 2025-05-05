package console;

import static Funciones.Funciones.countChars;
import static Funciones.Funciones.countWords;
import static Funciones.Funciones.deleteFile;
import static Funciones.Funciones.overWriteFile;
import static Funciones.Funciones.printPDF;
import static Funciones.Funciones.showFiles;
import static Funciones.Funciones.showListFiles;
import static Funciones.Funciones.swapWords;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String opcion = "";
        sc.useDelimiter("\n");

        for (; !opcion.equals("z");) {
            System.out.println("Menu:");
            System.out.println("1 - Crear Carpeta\n" + "2 - Exercice 2\n" + "3 - Exercice 3\n" + "4 - Exercice 4\n" + "5 - Exercice 5\n" + "6 - Exercice 6\n" + "7 - Exercice 7");
            System.out.println("Z - Salir");
            System.out.println("Enter option:");
            opcion = sc.next();
            switch (opcion) {
                case "1":
                    ejercicio1();
                    break;
                case "2":
                    ejercicio2();
                    break;
                case "3":
                    ejercicio3();
                    break;
                case "4":
                    ejercicio4();
                    break;
                case "5":
                    ejercicio5();
                    break;
                case "6":
                    ejercicio6();
                    break;
                case "7":
                    ejercicio7();
                    break;
                case "8":
                    ejercicio8();
                    break;
                case "9":
                    ejercicio9();
                    break;
                case "10":
                    ejercicio10();
                    break;
                case "z":
                    System.out.println("Adios");
                    break;
                default:
                    System.out.println("Opcion incorrecta");
            }
        }
    }

    static void ejercicio1() {
        String fileName = "";
        System.out.println("Nombre de la carpeta nueva: ");
        sc.useDelimiter("\n");
        Funciones.Funciones.createFolder(fileName);
        System.out.println("Carpeta creada con exito");
    }

    static void ejercicio2() {
        String path = System.getProperty("user.dir");
        String fileName = "miArchivo";

        String content = "Hola, este es el contenido del archivo.\n¡Línea nueva!";

        Funciones.Funciones.createFile(path, fileName, content);

        System.out.println("Archivo creado con éxito.");
    }

    static void ejercicio3() {
        try {
            String[] archivos = showListFiles("C:/ejemplo");  // Este path es ignorado en tu método actual

            for (String ruta : archivos) {
                System.out.println("Ruta completa: " + ruta);
            }

        } catch (IOException e) {
            System.out.println("Error al listar archivos: " + e.getMessage());
        }

    }

    static void ejercicio4() {
        try {
            String ruta = "C:/Users/TuUsuario/Desktop"; // Ajusta a tu carpeta real
            String resultado = showFiles(ruta, "", "");
            System.out.println("Ruta seleccionada: " + resultado);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    static void ejercicio5() {

        String path = "src\\recursos";
        String fileName = "elg.txt";
        String newContent = "Este contenido ha reemplazado al anterior.";

        boolean resultado = overWriteFile(path, fileName, newContent);

        if (resultado) {
            System.out.println("Archivo sobrescrito correctamente.");
        } else {
            System.out.println("El archivo no existe o hubo un error.");
        }
    }

    static void ejercicio6() {
        deleteFile("/ruta/a/tu/archivo", "archivo.txt");
    }

    static void ejercicio7() {
        String path = "C:/Users/TuUsuario/Desktop";
        String fileName = "archivo.txt";
        int cantidad = countChars(path, fileName);
        if (cantidad != -1) {
            System.out.println("Número total de caracteres: " + cantidad);
        }

    }

    static void ejercicio8() {
        String path = "C:/Users/TuUsuario/Desktop";
        String fileName = "archivo.txt";
        int cantidadPalabras = countWords(path, fileName);
        if (cantidadPalabras != -1) {
            System.out.println("Número total de palabras: " + cantidadPalabras);
        }

    }

    static void ejercicio9() {
        String path = "C:/Users/TuUsuario/Desktop";
        String fileName = "archivo.txt";
        String oldWord = "hola";
        String newWord = "hello";

        String resultado = swapWords(path, fileName, oldWord, newWord);
        if (!resultado.isEmpty()) {
            System.out.println("Contenido con palabras reemplazadas:\n");
            System.out.println(resultado);
        }

    }

    static void ejercicio10() {
        String path = "C:/Users/TuUsuario/Desktop";
        String fileName = "saludo";

        printPDF(path, fileName);
    }
}
