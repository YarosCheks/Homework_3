package conservation;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        // пункт 1
        GameProgress gp1 = new GameProgress(100, 50, 20, 75.5);
        GameProgress gp2 = new GameProgress(89, 35, 40, 74.5);
        GameProgress gp3 = new GameProgress(93, 70, 43, 79.38);

        // пункт 2
        saveGame("C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/savegames/save1.dat", gp1);
        saveGame("C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/savegames/save2.dat", gp2);
        saveGame("C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/savegames/save3.dat", gp3);

        // пункт 3
        zipFiles("C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/savegames/zip.zip",
                "C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/savegames/save1.dat");
        zipFiles("C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/savegames/zip.zip",
                "C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/savegames/save2.dat");
        zipFiles("C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/savegames/zip.zip",
                "C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/savegames/save3.dat");

        // пункт 4
        deleteFile("C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/savegames/save1.dat");
        deleteFile("C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/savegames/save2.dat");
        deleteFile("C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/savegames/save3.dat");
    }

    public static void saveGame(String name, GameProgress gp) {
        // откроем входной поток для записи в файл
        try (FileOutputStream fos = new FileOutputStream(name);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            // запишем экземпляр класса в файл
            oos.writeObject(gp);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String zipName, String fileName) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipName));
             FileInputStream fis = new FileInputStream(fileName)) {
            ZipEntry ze = new ZipEntry("packed_savegames");
            zos.putNextEntry(ze);
            // считываем содержимое файла в массиве byte
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            // добавляем содержимое к архиву
            zos.write(buffer);
            // закрываем текущую запись для новой записи
            zos.closeEntry();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void deleteFile(String name) {
        File file = new File(name);
        if (file.delete()) {
            System.out.println("Файл удалён");
        }
    }
}
