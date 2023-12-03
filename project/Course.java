package project;

import java.io.Serializable;

public class Course implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2144150016570742380L;
	private String ID;
    private String Professor;
    private String Location;
    private int CurrentStudentNumber;
    private int MaxStudentNumber;
    private CourseTime Time;
    private int Credit;
    public Course(String id, String prof, String loc, int csn, int msn, CourseTime time, int credit){
        ID = id;
        Professor = prof;
        Location = loc;
        CurrentStudentNumber = csn;
        MaxStudentNumber = msn;
        Time =  time;
        Credit = credit;
    }
    public void IncreCSNByOne(){
        CurrentStudentNumber++;
    }
    public void DecreCSNByOne(){
        CurrentStudentNumber--;
    }
    public String getID() {
        return ID;
    }
    public String getProfessor() {
        return Professor;
    }
    public String getLocation() {
        return Location;
    }
    public int getCurrentStudentNumber() {
        return CurrentStudentNumber;
    }
    public int getMaxStudentNumber() {
        return MaxStudentNumber;
    }
    public CourseTime getTime() {
        return Time;
    }
    public int getCredit() {
        return Credit;
    }
}

