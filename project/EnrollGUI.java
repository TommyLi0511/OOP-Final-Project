package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class EnrollGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private Student MatchedStudent;
	private ClassSchedulingSystem CSS;
	private JPanel contentPane;
	private JLabel lblCourseID;
	private JLabel lblMsg;
	private JLabel lblCourses;
	private JTable tableCourses;
	private JButton btnEnroll;
	private JButton btnCancel;
	private JTextField txtEnterCourseId;
	private String[] columnNames = {"Course ID", "Professor", "Location", "Current Student #", "Capacity", "Time", "Credit"};
	private DefaultTableModel tableModel;
	
	public void E_Show() {
		this.show();
	}
	public Course getCourse(String courseID) {
		ArrayList<Course> courses = CSS.getCourses();
		for(int i =0; i < courses.size(); i++){
			Course cur_C = courses.get(i);
			if(courseID.equals(cur_C.getID())) {
				return cur_C;
			}
		}
		return null;
	}
	
	public void Enroll() {
		String courseID = txtEnterCourseId.getText();
		if(courseID.isEmpty()) {
			lblMsg.setText("Please Enter A Course ID!!!");
		}else {
			Course matchedCourse = getCourse(courseID);
			if(matchedCourse == null) {
				lblMsg.setText("Invalid CourseID!!!");
			}else {
				int result = MatchedStudent.Add(matchedCourse);
				if(result == 3) {
					lblMsg.setText("Success!!!");
					txtEnterCourseId.setText("");
					Refresh();
				}else if(result == 0){
					lblMsg.setText("There is a Time Conflict!!!");
					txtEnterCourseId.setText("");
				}else if(result == 1){
					lblMsg.setText("This course is full!!!");
					txtEnterCourseId.setText("");
				}else {
					lblMsg.setText("Too many courses!!!");
					txtEnterCourseId.setText("");
				}
			}
		}
	}
	
	public void Refresh() {
		int numOfCourses = CSS.getCourses().size();
        Object[][] newData = new Object[numOfCourses][7];

        for (int i = 0; i < numOfCourses; i++) {
            Course courses = CSS.getCourses().get(i);
            newData[i][0] = courses.getID();
            newData[i][1] = courses.getProfessor();
            newData[i][2] = courses.getLocation();
            newData[i][3] = courses.getCurrentStudentNumber();
            newData[i][4] = courses.getMaxStudentNumber();
            CourseTime ct = courses.getTime();
            newData[i][5] = ct.timeconvert();
            newData[i][6] = courses.getCredit();
        }

        tableModel.setDataVector(newData, columnNames);
        tableModel.fireTableDataChanged();
        int[] columnWidths = {200, 120, 180, 140, 120, 220, 50};
        for (int i = 0; i < columnWidths.length; i++) {
            TableColumn column = tableCourses.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
        }
	}
	
	public void DisplayAllCourses(){
		int numOfCourses = CSS.getCourses().size();
        
        Object[][] data = new Object[numOfCourses][7];

        for (int i = 0; i < numOfCourses; i++) {
            Course course = CSS.getCourses().get(i);
            data[i][0] = course.getID();
            data[i][1] = course.getProfessor();
            data[i][2] = course.getLocation();
            data[i][3] = course.getCurrentStudentNumber();
            data[i][4] = course.getMaxStudentNumber();
            CourseTime ct = course.getTime();
            data[i][5] = ct.timeconvert();
            data[i][6] = course.getCredit();
        }

        tableModel = new DefaultTableModel(data, columnNames){
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableCourses = new JTable(tableModel);
        tableCourses.setFont(new Font("Tahoma", Font.PLAIN, 18));
        tableCourses.setRowHeight(30);
        
        int[] columnWidths = {200, 120, 180, 140, 120, 220, 50};
        for (int i = 0; i < columnWidths.length; i++) {
            TableColumn column = tableCourses.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
        }
        
        JScrollPane scrollPane = new JScrollPane(tableCourses);
        scrollPane.setBounds(20, 60, 856, 400);
        contentPane.add(scrollPane);
	}
	
	public EnrollGUI(Student matchedStudent, ClassSchedulingSystem css) {
		MatchedStudent = matchedStudent;
		CSS = css;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCourses = new JLabel("All Courses:");
		lblCourses.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblCourses.setBounds(50, 11, 210, 38);
		contentPane.add(lblCourses);
		
		DisplayAllCourses();
		
		btnEnroll = new JButton("Enrolll");
		btnEnroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Enroll();
			}
		});
		btnEnroll.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnEnroll.setBounds(430, 471, 210, 81);
		contentPane.add(btnEnroll);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnCancel.setBounds(666, 471, 210, 81);
		contentPane.add(btnCancel);
		
		txtEnterCourseId = new JTextField();
		txtEnterCourseId.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnterCourseId.setFont(new Font("Tahoma", Font.PLAIN, 30));
		txtEnterCourseId.setBounds(176, 471, 234, 81);
		contentPane.add(txtEnterCourseId);
		txtEnterCourseId.setColumns(10);
		
		lblCourseID = new JLabel("Course ID:");
		lblCourseID.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblCourseID.setBounds(20, 471, 146, 77);
		contentPane.add(lblCourseID);
		
		lblMsg = new JLabel("");
		lblMsg.setForeground(Color.RED);
		lblMsg.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblMsg.setBounds(342, 11, 504, 38);
		contentPane.add(lblMsg);
	}
}
