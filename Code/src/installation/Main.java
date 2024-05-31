package installation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        // пункт 1
        createDir("C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/src", list);
        createDir("C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/res", list);
        createDir("C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/savegames", list);
        createDir("C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/temp", list);

        //пункт 2
        createDir("C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/src/main", list);
        createDir("C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/src/test", list);

        //пункт 3
        createFile("C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/src/main/Main.java", list);
        createFile("C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/src/main/Utils.java", list);

        // пункт 4
        createDir("C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/res/drawables", list);
        createDir("C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/res/vectors", list);
        createDir("C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/res/icons", list);

        // пункт 5
        createFile("C://Users/yaros/OneDrive/Рабочий стол/Homework_3/Games/temp/temp.txt", list);

        // запись результатов создания в temp.txt
        for (String s : list) {
            writeInFile(s);
        }
    }

    public static void createDir(String name, List<String> list) {
        if (new File(name).mkdirs()) {
            list.add("Дирректория " + name + " была создана");
        } else {
            list.add("Дирректория " + name + " не была создана");
        }
    }

    public static void createFile(String name, List<String> list) {
        File newFile = new File(name);
        try {
            if (newFile.createNewFile()) {
                list.add("Файл " + name + " был создан");
            }
        } catch (IOException ex) {
            list.add(ex.getMessage());
        }
    }

    public static void writeInFile(String text) {
        try (FileWriter writer = new FileWriter("temp.txt", false)) {
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}