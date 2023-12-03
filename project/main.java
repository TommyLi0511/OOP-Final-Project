package project;

import java.util.ArrayList;

public class main {
	public static void main(String[] args) {
		ClassSchedulingSystem css = new ClassSchedulingSystem();
		css.loadDataFromFile("data.bin");
		if(css.getStudents()==null & css.getCourses()==null) {
			css = Enroll_tester();
//			css = Drop_tester();
		}
		MainGUI maingui = new MainGUI(css);
		maingui.M_Show();
	}
	
	public static ClassSchedulingSystem Enroll_tester() {
		Course OOP_L = new Course("CSCI-UA 470-001","Andy","SC Room 101A",10,10,new CourseTime("MoWe",2), 4);
		Course Jazz_L = new Course("CORE-UA 730-001","David","WS Room 101",7,10,new CourseTime("TuTh",3), 4);
		Course Jazz_R = new Course("CORE-UA 730-007","Ella","SC Room 318",2,5,new CourseTime("Fr",5), 0);
		Course Pro_Sta_L = new Course("MATH-UA 235-004","Ellis","Online",5,10,new CourseTime("TuTh",6), 4);
		Course Pro_Sta_R = new Course("MATH-UA 235-005","Tommy","WW Room 102",4,5,new CourseTime("Fr",4), 0);
		Course Num_Ana_L = new Course("MATH-UA 252-005","Jason","WW Room 201",3,10,new CourseTime("TuTh",4), 4);
		Course Num_Ana_R = new Course("MATH-UA 252-006","Tylor","WW Room 201",2,5,new CourseTime("Fr",2), 0);
		
		ArrayList<Course> courses = new ArrayList<Course>();
		
		courses.add(OOP_L);
		courses.add(Jazz_L);
		courses.add(Jazz_R);
		courses.add(Pro_Sta_L);
		courses.add(Pro_Sta_R);
		courses.add(Num_Ana_L);
		courses.add(Num_Ana_R);
		
		ArrayList<Student> studs = new ArrayList<Student>();
		Student stud1 = new Student("jl12727", "pxdi@VsK");
		stud1.Add(Num_Ana_R);
		studs.add(stud1);
		
		
		ClassSchedulingSystem css = new ClassSchedulingSystem(studs,courses);
		
		return css;
	}
	
	public static ClassSchedulingSystem Drop_tester() {
		Course OOP_L = new Course("CSCI-UA 470-001","Andy","SC Room 101A",0,10,new CourseTime("MoWe",2), 4);
		Course Jazz_L = new Course("CORE-UA 730-001","David","WS Room 101",0,10,new CourseTime("TuTh",3), 4);
		Course Jazz_R = new Course("CORE-UA 730-007","Ella","SC Room 318",0,5,new CourseTime("Fr",5), 0);
		Course Pro_Sta_L = new Course("MATH-UA 235-004","Ellis","Online",0,10,new CourseTime("TuTh",6), 4);
		Course Pro_Sta_R = new Course("MATH-UA 235-005","Tommy","WW Room 102",0,5,new CourseTime("Fr",4), 0);
		Course Num_Ana_L = new Course("MATH-UA 252-005","Jason","WW Room 201",0,10,new CourseTime("TuTh",4), 4);
		Course Num_Ana_R = new Course("MATH-UA 252-006","Tylor","WW Room 201",0,5,new CourseTime("Fr",2), 0);
		
		ArrayList<Course> courses = new ArrayList<Course>();
		
		courses.add(OOP_L);
		courses.add(Jazz_L);
		courses.add(Jazz_R);
		courses.add(Pro_Sta_L);
		courses.add(Pro_Sta_R);
		courses.add(Num_Ana_L);
		courses.add(Num_Ana_R);
		
		ArrayList<Student> studs = new ArrayList<Student>();
		Student stud1 = new Student("jl12727", "pxdi@VsK");
		stud1.Add(OOP_L);
		stud1.Add(Jazz_L);
		stud1.Add(Jazz_R);
		stud1.Add(Pro_Sta_L);
		stud1.Add(Pro_Sta_R);
		stud1.Add(Num_Ana_L);
		stud1.Add(Num_Ana_R);
		studs.add(stud1);
		
		ClassSchedulingSystem css = new ClassSchedulingSystem(studs,courses);
		
		return css;
	}
}
