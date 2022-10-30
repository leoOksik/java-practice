import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
// RecursiveTask  позволяет создавать разветвляющиеся поток кот можно собирать воедино
public class FolderSizeCalculator  extends RecursiveTask <Long> {

    private Node node;

    public FolderSizeCalculator (Node node) {
        this.node = node;
    }
    @Override
    protected Long compute() {

        File folder = node.getFolder();
        if (folder.isFile()) {
            return folder.length();
        }
        long sum = 0;
        List<FolderSizeCalculator> subTasks = new LinkedList<>();
        File [] files = folder.listFiles();

        for (File file: files) {

            Node child = new Node(file); // вызываем новый поток на этом child
            FolderSizeCalculator task = new FolderSizeCalculator(child);
            task.fork(); // запускаем асинхронно
            subTasks.add(task);
            child.addChild(child);
        }
        for (FolderSizeCalculator task : subTasks) {
            sum += task.join(); // сработает когда потом завершит свое выполнение
            // разветвляется большое дерево поток и потом лни собираютмя воедино здесь
        }
        node.setSize(sum);
        return sum;
    }
}
