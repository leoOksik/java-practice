import java.io.File;

public class Main {

    public static void main(String[] args) {

        // C: /Users/USERNAME/...
        String folderPath = "/home/oksana/Загрузки/s";
        File file = new File (folderPath);
        System.out.println(getFolderSize(file));
        // выведет размер блока данных в кот хранится список ссылок на все папки и файлы лежащие
        // в данной папке

        System.out.println(System.getProperties().getProperty("user.dir"));


    }

    public static long getFolderSize (File folder) {

        if (folder.isFile()) {
            return folder.length();
        }
        long sum = 0;
        File [] files = folder.listFiles();
        for (File file : files) {
            sum += getFolderSize(file);
        }
        return sum;
    }
}
