package loading;

import conservation.GameProgress;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) {
        openZip("C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/savegames/zip.zip",
                "C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/savegames/packed_savegames");

        System.out.println(openProgress("C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/savegames/save1.dat"));
        System.out.println(openProgress("C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/savegames/save2.dat"));
        System.out.println(openProgress("C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/savegames/save3.dat"));
    }

    public static void openZip(String fileName, String folderName) {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(fileName))) {
            while (zis.getNextEntry() != null) {
                // распаковка
                FileOutputStream fos = new FileOutputStream(folderName);
                for (int c = zis.read(); c != -1; c = zis.read()) {
                    fos.write(c);
                }
                fos.flush();
                zis.closeEntry();
                fos.close();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static GameProgress openProgress(String fileName) {
        GameProgress gp = null;
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gp = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return gp;
    }
}