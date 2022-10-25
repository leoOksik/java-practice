import java.io.*;
import java.util.ArrayList;

public class School extends Course{

    ArrayList <Course> course = new ArrayList<>();
    Course courses = new Course();

    // Course [] course = new Course[100];
    String nameSchool;

    public void setCourse (Course course) {
        this.course.add(course);
    }
    public void setCourse (ArrayList <Course> course) {
        this.course = course;
    }

    public void setCourses (byte idCourse, String nameCourse, short price, short term, String comment) {
        Course course = new Course();
        course.setCourse(idCourse, nameCourse, price, term, comment);
        this.course.add(course);
    }

    public boolean setCourses (String nameFile) throws IOException {
        String fullName = " ";
        String dirName = System.getProperty("user.dir");
        fullName = dirName + File.separator + nameFile;
        File file = new File(fullName);

        //file.createNewFile();

        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);

        byte idCourse;
        String nameCourse;
        short price;
        short term;
        String comment;

        String line = reader.readLine();

        while (line != null) {

            idCourse = Byte.parseByte(line);
            line = reader.readLine();
            nameCourse = line;
            line = reader.readLine();
            price = Short.parseShort(line);
            line = reader.readLine();
            term = Short.parseShort(line);
            line = reader.readLine();
            comment = line;
            line = reader.readLine();

            Course course = new Course();

            course.setCourse(idCourse, nameCourse, price, term, comment);
            this.course.add(course);
        }
        return true;
    }

    public void setNameSchool(String nameSchool) {
        this.nameSchool = nameSchool;
    }

    public void getCourse () {
        for (Course course1 : course) {
            System.out.print("\nid course " + course1.getIdCourse() + "\n"
                    + "name course: " +course1.getNameCourse() +"\n"
                    + "price: " + course1.getPrice() + "\n" + "term: " + course1.getTerm() + "\n"
                    + "comment: " + course1.getComment() + "\n");
        }
    }
}
