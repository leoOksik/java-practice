public class Course {

    private byte idCourse;
    private String nameCourse;
    private short price;
    private short term;
    public String comment;

    public byte getIdCourse() {
        return idCourse;
    }
    public String getNameCourse() {
        return nameCourse;
    }

    public short getPrice() {
        return price;
    }

    public short getTerm() {
        return term;
    }

    public String getComment() {
        return comment;
    }

    public void setCourse (byte idCourse, String nameCourse, short price, short term, String comment) {
        this.idCourse = idCourse;
        this.nameCourse = nameCourse;
        this.price = price;
        this.term = term;
        this.comment = comment;
    }

}
