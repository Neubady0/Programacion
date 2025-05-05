package Funciones;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import javax.swing.text.Document;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.FileNotFoundException;

public class Funciones {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {

    }

    public static void createFolder(String fileName) {
        String path = System.getProperty("user.dir");
        String separador = File.separator;
        String rutaCarpeta = path + separador + fileName;
        File carpeta = new File(rutaCarpeta);

        if (!carpeta.exists()) {
            carpeta.mkdir();
        }
    }

    public static void createFile(String path, String fileName, String content) {
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            String separador = File.separator;
            File archivo = new File(path + separador + fileName + ".txt");
            fw = new FileWriter(archivo, true);
            bw.write(content);
            bw.flush();
            bw.close();
        } catch (IOException ex) {
            System.out.println("Error de entrada-salida " + ex.getMessage());
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                System.out.println("Error al cerrar el fichero");
            }
        }
    }

    public static String[] showListFiles(String path) throws IOException {
        File carpetaFormacion = new File(System.getProperty("user.dir"));
        String[] listaDocumentos = carpetaFormacion.list();
        String[] rutaDocumentos = new String[listaDocumentos.length];

        for (int i = 0; i < listaDocumentos.length; i++) {
            System.out.println(i + " - " + listaDocumentos[i]);
            rutaDocumentos[i] = System.getProperty("user.dir") + File.separator + listaDocumentos[i];
        }

        return rutaDocumentos;
    }

    public static String showFiles(String path, String fileName, String newContent) throws IOException {
        File carpetaFormacion = new File(System.getProperty("user.dir"));
        String[] listaDocumentos = carpetaFormacion.list();

        // Obtenemos el listado de todos los documentos dentro de la carpeta
        for (int i = 0; i < listaDocumentos.length; i++) {
            System.out.println(i + " - " + listaDocumentos[i]);
        }

        System.out.print("Seleccione el Número de Fichero: ");
        String textoTeclado = br.readLine();
        int numeroDocumento = Integer.parseInt(textoTeclado);

        String rutaDocumento = System.getProperty("user.dir") + File.separator + listaDocumentos[numeroDocumento];
        return rutaDocumento;
    }

    public static boolean overWriteFile(String path, String fileName, String newContent) {
        FileWriter fw = null;
        BufferedWriter bw = null;

        // Construimos la ruta completa al archivo
        File fichero = new File(path + File.separator + fileName);

        // Si no existe, devolvemos false
        if (!fichero.exists()) {
            return false;
        }

        try {
            fw = new FileWriter(fichero, false); // false = sobrescribe el archivo
            bw = new BufferedWriter(fw);

            bw.write(newContent);
            bw.newLine();
            bw.flush();
            return true;

        } catch (IOException ex) {
            System.out.println("Error de entrada-salida: " + ex.getMessage());
            return false;

        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                System.out.println("Error al cerrar el fichero");
            }
        }
    }

    public static void deleteFile(String path, String fileName) {
        File file = new File(path, fileName);  // Se crea un objeto File con la ruta y el nombre del archivo
        boolean deleted = file.delete();  // Intenta borrar el archivo

        if (deleted) {
            System.out.println("Archivo borrado correctamente.");
        } else {
            System.out.println("No se pudo borrar el archivo (puede que no exista).");
        }
    }

    public static int countChars(String path, String fileName) {
        File file = new File(path, fileName);
        int characterCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int character;
            while ((character = reader.read()) != -1) {
                characterCount++;
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return -1;
        }

        return characterCount;
    }

    public static int countWords(String path, String fileName) {
        int wordCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                wordCount += words.length;
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return -1;
        }

        return wordCount;
    }

    public static String swapWords(String path, String fileName, String oldWorld, String newWorld) {

        File file = new File(path, fileName);
        StringBuilder newContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replace(oldWorld, newWorld);
                newContent.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return "";
        }

        return newContent.toString();
    }

    public static void printPDF(String path, String fileName) {
        String fullPath = path + "/" + fileName;

        try (PdfWriter writer = new PdfWriter(fullPath)) {
            PdfDocument pdf = new PdfDocument(writer);

            Document document = new Document(pdf);

            document.add(new Paragraph("¡Hola! Este es un archivo PDF generado con iText."));

            document.close();

            System.out.println("PDF creado exitosamente en: " + fullPath);

        } catch (Exception e) {
            System.out.println("Error al crear el PDF: " + e.getMessage());
        }

    }

}
