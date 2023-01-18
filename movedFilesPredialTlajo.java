import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class movedFilesPredialTlajo {
  
    public static void main(String[] argv) {
        String dateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDateTime.now());
        

         String folderPro = "path where the files are";
        // String folderPro = "C:\\Users\\soporte\\Desktop\\desasignado";
        String ruta = "path where the files will be moved";
        String filePro = "path where is the base of excel";
        String lineRead = "";
        //
        File carpeta = new File(ruta + "\\Desasignacion " + dateTime);
        ArrayList<String> storeValues = new ArrayList<String>();
        ArrayList<String> archivosNoEncontrados = new ArrayList<String>();

        // Create a named list in excel
        try {         
            BufferedReader br = new BufferedReader(new FileReader(filePro));
            int count = 0;
            br.readLine();
            while ((lineRead = br.readLine()) != null) {
                storeValues.add(lineRead);
                count++;
            }
            System.out.println(String.format("Lines reads [%d], Array [%d]", count, storeValues.size()));
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }

    
        if (!carpeta.exists()) {
            if (carpeta.mkdirs()) {
                System.out.println("Directorio creado");
            } else {
                System.out.println("Error al crear directorio");
            }
        }

        for (String string : storeValues) {
            File from = new File(folderPro+"\\"+string);
            File to = new File(carpeta+"\\"+string);
            try {
                if(from.exists()){
                    moveFile(from, to);
                    System.out.println("File moved successfully.");
                }else{
                    archivosNoEncontrados.add(string);
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public static void moveFile(File src, File dest) throws IOException {
        Files.move(src.toPath(), dest.toPath());
    }
}
