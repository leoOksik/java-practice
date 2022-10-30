import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main {


    public static void main(String[] args) {

        //System.out.println(getHumanReadableSize(240640));
        // System.exit(0); // остановить выполнение на этой строке
       // System.out.println(getSizeFromHumanReadable("235K"));
        //System.exit(0);   // остановить выполнение на этой строке

        // C: /Users/USERNAME/...
        String folderPath = "/home/oksana/Загрузки/s";
        File file = new File (folderPath);
        Node root = new Node(file); // добавляет в корень нашу папку
        // эта нода будет содержать все папки все дерево всех папок

        long start = System.currentTimeMillis();

        FolderSizeCalculator calculator = new FolderSizeCalculator(root); // запускаем код по этой папке
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator);
        System.out.println("Forkjoin " + size);
        System.out.println("Node " + root.getSize());  // у этой ноды мы можем запрашивать напрямую размер
        // печать всей ноды
        System.out.println("All node " + root);

        long duration = System.currentTimeMillis() - start;
        System.out.println("Time " +  duration + " ms"); // такой вариант лучше использовать

        // forkjoinpool управляет количеством потоков кот одновременно работают
        // они запускаются не все сразу

        System.out.println("Folder get " + getFolderSize(file));
        // выведет размер блока данных в кот хранится список ссылок на все папки и файлы лежащие
        // в данной папке

        System.out.println(System.getProperties().getProperty("user.dir"));

    }

    public static long getFolderSize (File folder) {

        // многопоточность
        //MyThread thread = new MyThread(1);
       // MyThread thread2 = new MyThread(2);

        //thread.start();
        //thread2.start();

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
