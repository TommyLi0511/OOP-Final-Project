package project;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class DropGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private Student MatchedStudent;
	private JPanel contentPane;
	private JLabel lblCourseID;
	private JLabel lblMsg;
	private JTextField textFieldCourseID;
	private JButton btnDrop;
	private JButton btnCancel;

	public void D_Show() {
		this.show();
	}
	
	public Course getCourse(String courseID) {
		List<Course> enrolledcourses = MatchedStudent.getEnrolledCourse();
		for(int i =0; i < enrolledcourses.size(); i++){
			Course cur_C = enrolledcourses.get(i);
			if(courseID.equals(cur_C.getID())) {
				return cur_C;
			}
		}
		return null;
	}
	
	public void Drop() {
		String courseID = textFieldCourseID.getText();
		if(courseID.isEmpty()) {
			lblMsg.setText("Please Enter A Course ID!!!");
		}else {
			Course matchedCourse = getCourse(courseID);
			if(MatchedStudent.Remove(matchedCourse)) {
				lblMsg.setText("Success!!!");
			}else {
				lblMsg.setText("You didn't enroll in this course!!!");
			}
		}
	}
	public void cancel() {
		dispose();
	}
	
	/**
	 * Create the frame.
	 */
	public DropGUI(Student matchedstudent) {
		MatchedStudent = matchedstudent;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblCourseID = new JLabel("Course ID:");
		lblCourseID.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourseID.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblCourseID.setBounds(90, 194, 200, 100);
		contentPane.add(lblCourseID);
		
		textFieldCourseID = new JTextField();
		textFieldCourseID.setFont(new Font("Tahoma", Font.PLAIN, 30));
		textFieldCourseID.setBounds(403, 194, 362, 100);
		contentPane.add(textFieldCourseID);
		textFieldCourseID.setColumns(10);
		
		lblMsg = new JLabel("");
		lblMsg.setHorizontalAlignment(SwingConstants.CENTER);
		lblMsg.setForeground(Color.RED);
		lblMsg.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblMsg.setBounds(276, 319, 343, 100);
		contentPane.add(lblMsg);
		
		btnDrop = new JButton("Drop");
		btnDrop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Drop();
			}
		});
		btnDrop.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnDrop.setBounds(128, 433, 269, 100);
		contentPane.add(btnDrop);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnCancel.setBounds(500, 430, 269, 100);
		contentPane.add(btnCancel);
	}
}
