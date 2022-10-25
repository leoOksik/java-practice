import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        School obj = new School();
        obj.setCourses("baseCourse.txt");
        obj.getCourse();
    }
}
