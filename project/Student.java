package project;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1061452410888052444L;
	private String ID;
    private String Password;
    private ArrayList<Course> EnrolledCourse;
    private int Credit;
    public Student(String id, String pwd){
        ID = id;
        Password = pwd;
        EnrolledCourse = new ArrayList<Course>();
        Credit = 0;
    }
    public boolean Remove(Course course){
        boolean result = EnrolledCourse.remove(course);
        if(result){
            Credit-=course.getCredit();
            course.DecreCSNByOne();
        }
        return result;
    }
    public int Add(Course course){
        for(int i = 0; i< EnrolledCourse.size();i++){
            CourseTime time1 = EnrolledCourse.get(i).getTime();
            if(!time1.Compare(course.getTime())){
                return 0; //time conflict
            }
        }
        if(course.getMaxStudentNumber()==course.getCurrentStudentNumber()){
            return 1; // Course is full
        }
        if(Credit+course.getCredit()>16) {
        	return 2;
        }
        Credit += course.getCredit();
        course.IncreCSNByOne();
        EnrolledCourse.add(course);
        return 3;
    }
    public int getCredit() {
        return Credit;
    }
    public String getID() {
        return ID;
    }
    public String getPassword() {
        return Password;
    }
    public ArrayList<Course> getEnrolledCourse() {
        return EnrolledCourse;
    }
}
