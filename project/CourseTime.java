package project;

import java.io.Serializable;

/**
 * CourseTime
 *      In this design, there are 8 possible values of "Section": 
 *          1->8:00~9:15
 *          2->9:30~10:45
 *          3->11:00~12:15
 *          4->12:30~13:45
 *          5->14:00~15:15
 *          6->15:30~16:45
 *          7->17:00~18:15
 *          8->18:30~19:45
 *      And there are Three possible value of "Day":
 *          MoWe->Monday and Wednesday
 *          TuTh->Tuesday and Thursday
 *          Fr->Friday
 *      
 */
public class CourseTime implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8044606100796513220L;
	private String Day;
    private int Section;
    public CourseTime(String day, int section){
        Day = day;
        Section = section;
    }
    public boolean Compare(CourseTime ct){
        if(this.Day.equals(ct.getDay()) && this.Section== ct.getSection()){
            return false;//time conflict
        }
        return true;
    }
    public String getDay(){
        return Day;
    }
    public int getSection(){
        return Section;
    } 
    public String timeconvert() {
    	String result = Day;
    	if(Section == 1) {
    		result += " 8:00~9:15";
    	}else if(Section == 2){
    		result += " 9:30~10:45";
    	}else if(Section == 3){
    		result += " 11:00~12:15";
    	}else if(Section == 4){
    		result += " 12:30~13:45";
    	}else if(Section == 5){
    		result += " 14:00~15:15";
    	}else if(Section == 6){
    		result += " 15:30~16:45";
    	}else if(Section == 7){
    		result += " 17:00~18:15";
    	}else if(Section == 8){
    		result += " 18:30~19:45";
    	}
    	return result;
    }
}