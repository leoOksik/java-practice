import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
// RecursiveTask  позволяет создавать разветвляющиеся поток кот можно собирать воедино
public class FolderSizeCalculator  extends RecursiveTask <Long> {

    private File folder;

    public FolderSizeCalculator (File folder) {
        this.folder = folder;
    }
    @Override
    protected Long compute() {

        if (folder.isFile()) {
            return folder.length();
        }
        long sum = 0;
        List<FolderSizeCalculator> subTasks = new LinkedList<>();
        File [] files = folder.listFiles();

        for (File file: files) {
            FolderSizeCalculator task = new FolderSizeCalculator(file);
            task.fork(); // запускаем асинхронно
            subTasks.add(task);
        }
        for (FolderSizeCalculator task : subTasks) {
            sum += task.join(); // сработает когда потом завершит свое выполнение
            // разветвляется большое дерево поток и потом лни собираютмя воедино здесь
        }
        return sum;
    }
}
