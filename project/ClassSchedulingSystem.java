package project;

import java.io.*;
import java.util.ArrayList;

public class ClassSchedulingSystem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3783463832485344253L;
	private ArrayList<Student> Stds;
    private ArrayList<Course> Courses;
    public ClassSchedulingSystem() {
    	// default constructor
    }
    public ClassSchedulingSystem(ArrayList<Student> stds, ArrayList<Course> courses){
        Stds = stds;
        Courses = courses;
    }
	public void saveDataToFile(String fileName){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadDataFromFile(String fileName) {
    	File file = new File(fileName);
    	if(file.exists()) {
    		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            	ClassSchedulingSystem loadedCSS = (ClassSchedulingSystem) ois.readObject();
                this.Stds = loadedCSS.getStudents();
                this.Courses = loadedCSS.getCourses();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
    	}else {
    		try {
    			file.createNewFile();
    		}catch(IOException e) {
    			e.printStackTrace();
    		}
    		
    	}
        
    }
    public Student varify(String id){
        for(int i = 0; i< Stds.size(); i++){
            Student cur = Stds.get(i);
            if(id.equals(cur.getID())){
                return cur;
            }
        }
        return null; //fail
    }
    public void AddStudent(Student student){
        Stds.add(student);
    }
    public ArrayList<Course> getCourses() {
        return Courses;
    }
    public ArrayList<Student> getStudents(){
    	return Stds;
    }
}
