package project;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	private ClassSchedulingSystem CSS;
	private JPanel contentPane;
	private JTextField textFieldID;
	private JTextField textFieldPassword;
	private JLabel lblMsg;
	private JLabel lblID;
	private JLabel lblPassword;
	private JButton btnLogin;
	private JButton btnSignUp;
	private JButton btnExit;

	/**
	 * Create the frame.
	 */
	
	public void SignUp() {
		String id = textFieldID.getText();
		String pwd = textFieldPassword.getText();
		if(id.isEmpty()||pwd.isEmpty()){
			lblMsg.setText("Warning: Please type in your ID and Password!!!");
		}else {
			Student matchedStudent = CSS.varify(id);
			if(matchedStudent == null) {
				Student new_S = new Student(id,pwd);
				CSS.AddStudent(new_S);
				lblMsg.setText("You have successfully sign up!!!");
				textFieldID.setText("");
				textFieldPassword.setText("");
			}else {
				lblMsg.setText("This ID has an account already!!!");
				textFieldID.setText("");
				textFieldPassword.setText("");
			}	
		}
	}
	
	public void M_Show() {
		this.show();
	}
	
	public void Login() {
		String id = textFieldID.getText();
		String pwd = textFieldPassword.getText();
		if(id.isEmpty()||pwd.isEmpty()){
			lblMsg.setText("Warning: Please type in your ID and Password!!!");
		}else {
			Student matchedStudent = CSS.varify(id);
			if(matchedStudent==null) {
				lblMsg.setText("Warning: Invalid ID or Password!!!");
				textFieldID.setText("");
				textFieldPassword.setText("");
			}else {
				if(!(matchedStudent.getPassword().equals(pwd))) {
					lblMsg.setText("Warning: Invalid ID or Password!!!");
					textFieldID.setText("");
					textFieldPassword.setText("");
				}else {
					ViewMyScheduleGUI VMSgui = new ViewMyScheduleGUI(CSS, matchedStudent);
					VMSgui.VMS_Show();
					textFieldID.setText("");
					textFieldPassword.setText("");
				}
			}
		}
	}
	public void Exit() {
		System.exit(ABORT);
	}
	
	public MainGUI(ClassSchedulingSystem css) {
		CSS = css;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblMsg = new JLabel("Welcome!!!");
		lblMsg.setForeground(Color.RED);
		lblMsg.setHorizontalAlignment(SwingConstants.CENTER);
		lblMsg.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblMsg.setBounds(60, 50, 780, 100);
		contentPane.add(lblMsg);
		
		lblID = new JLabel("ID:");
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblID.setBounds(138, 161, 200, 100);
		contentPane.add(lblID);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(138, 287, 200, 100);
		contentPane.add(lblPassword);
		
		textFieldID = new JTextField();
		textFieldID.setFont(new Font("Tahoma", Font.PLAIN, 30));
		textFieldID.setBounds(348, 161, 352, 100);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 30));
		textFieldPassword.setBounds(348, 287, 352, 100);
		contentPane.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login();
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnLogin.setBounds(60, 422, 220, 100);
		contentPane.add(btnLogin);
		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUp();
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnSignUp.setBounds(340, 422, 220, 100);
		contentPane.add(btnSignUp);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Exit();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnExit.setBounds(620, 422, 220, 100);
		contentPane.add(btnExit);
	}
}
