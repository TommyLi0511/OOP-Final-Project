package project;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;

public class ViewMyScheduleGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private Student MatchedStudent;
	private ClassSchedulingSystem CSS;
	private JPanel contentPane;
	private JTable tableCourses;
	private JLabel lblYourCourses;
	private JLabel lblRefresh;
	private JButton btnEnroll;
	private JButton btnDrop;
	private JButton btnLogout;
	private JButton btnRefresh;
	private DefaultTableModel tableModel;
	private String[] columnNames = {"Course ID", "Professor", "Location", "Current Student #", "Capacity", "Time", "Credit"};
	/**
	 * Launch the application.
	 */
	
	public void VMS_Show() {
		this.show();
	}
	
	public void Drop() {
		DropGUI dropgui = new DropGUI(MatchedStudent);
		dropgui.D_Show();
		lblRefresh.setText("Please Reflesh");
	}
	
	public void Enroll() {
		EnrollGUI enrollgui  = new EnrollGUI(MatchedStudent,CSS);
		enrollgui.E_Show();
		lblRefresh.setText("Please Reflesh");
	}
	
	public void DisplayMyCourses() {
		int numOfCourses = MatchedStudent.getEnrolledCourse().size();
        Object[][] data = new Object[numOfCourses][7];

        for (int i = 0; i < numOfCourses; i++) {
            Course enrolledcourses = MatchedStudent.getEnrolledCourse().get(i);
            data[i][0] = enrolledcourses.getID();
            data[i][1] = enrolledcourses.getProfessor();
            data[i][2] = enrolledcourses.getLocation();
            data[i][3] = enrolledcourses.getCurrentStudentNumber();
            data[i][4] = enrolledcourses.getMaxStudentNumber();
            CourseTime ct = enrolledcourses.getTime();
            data[i][5] = ct.timeconvert();
            data[i][6] = enrolledcourses.getCredit();
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
	
	public void Refresh() {
		int numOfCourses = MatchedStudent.getEnrolledCourse().size();
        Object[][] newData = new Object[numOfCourses][7];

        for (int i = 0; i < numOfCourses; i++) {
            Course enrolledcourses = MatchedStudent.getEnrolledCourse().get(i);
            newData[i][0] = enrolledcourses.getID();
            newData[i][1] = enrolledcourses.getProfessor();
            newData[i][2] = enrolledcourses.getLocation();
            newData[i][3] = enrolledcourses.getCurrentStudentNumber();
            newData[i][4] = enrolledcourses.getMaxStudentNumber();
            CourseTime ct = enrolledcourses.getTime();
            newData[i][5] = ct.timeconvert();
            newData[i][6] = enrolledcourses.getCredit();
        }

        tableModel.setDataVector(newData, columnNames);
        tableModel.fireTableDataChanged();
        int[] columnWidths = {200, 120, 180, 140, 120, 220, 50};
        for (int i = 0; i < columnWidths.length; i++) {
            TableColumn column = tableCourses.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
        }
	}
	
	public void Logout() {
		if(MatchedStudent.getCredit()<= 8) {
			lblRefresh.setText("Not Enough Credit");
		}else {
			CSS.saveDataToFile("data.bin");
			dispose();
		}
	}
	/**
	 * Create the frame.
	 */
	public ViewMyScheduleGUI(ClassSchedulingSystem css, Student matchedStudent) {
		CSS = css;
		
		MatchedStudent = matchedStudent;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblYourCourses = new JLabel("Your Courses:");
		lblYourCourses.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblYourCourses.setBounds(50, 11, 210, 38);
		contentPane.add(lblYourCourses);
		
		DisplayMyCourses();
		
		btnEnroll = new JButton("Enroll");
		btnEnroll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Enroll();
			}
		});
		btnEnroll.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnEnroll.setBounds(50, 471, 210, 82);
		contentPane.add(btnEnroll);
		
		btnDrop = new JButton("Drop");
		btnDrop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Drop();
			}
		});
		btnDrop.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnDrop.setBounds(345, 471, 210, 81);
		contentPane.add(btnDrop);
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Logout();
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnLogout.setBounds(640, 471, 210, 82);
		contentPane.add(btnLogout);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Refresh();
			}
		});
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnRefresh.setBounds(611, 11, 177, 38);
		contentPane.add(btnRefresh);
		
		lblRefresh = new JLabel("");
		lblRefresh.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRefresh.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblRefresh.setForeground(Color.RED);
		lblRefresh.setBounds(299, 11, 302, 38);
		contentPane.add(lblRefresh);
	}
}
