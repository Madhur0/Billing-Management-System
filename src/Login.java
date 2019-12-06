import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.EventListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Login extends JFrame implements ActionListener, EventListener {

	JTextField uId;
	JLabel msg;
	JPasswordField jpswd;
	Connection con;
	Statement st;
	
	public Login() {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		this.setMaximizedBounds(env.getMaximumWindowBounds());
		this.setExtendedState(this.getExtendedState()|this.MAXIMIZED_BOTH);
		
		URL url1=Login.class.getResource("resources/background6.jpg");
		ImageIcon ico1 = new ImageIcon(url1);
		JLabel icon1 = new JLabel(ico1);
		setContentPane(icon1);
		setLayout(null);
		//setResizable(false);
		setTitle("Billing Management Software");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel header = new JPanel();
		header.setLayout(null);
		header.setBackground(Color.WHITE);
 		header.setBorder(BorderFactory.createLoweredBevelBorder());
 		header.setSize(1366,110);
		URL url=Login.class.getResource("resources/icon2.png");
		ImageIcon ico = new ImageIcon(url);
		JLabel icon = new JLabel(ico);
 		JLabel heading = new JLabel("Billing Management");
		heading.setForeground(Color.GRAY);
		heading.setFont(new Font("Serif",Font.BOLD,80));
		icon.setBounds(10,5, 100, 100);
		heading.setBounds(120, 5, 870,100);
		
		header.add(heading);
		add(header,BorderLayout.NORTH);
		
		JLabel note = new JLabel("WELCOME");
		note.setForeground(Color.WHITE);
		note.setFont(new Font("Times New Roman",Font.BOLD,50));
		note.setBounds(615,190,500,40);
		add(note);
		
		JPanel box = new JPanel(new GridLayout(4,1));
		box.setBorder(BorderFactory.createRaisedBevelBorder());
		box.setBounds(600,280,0,0);
		box.setSize(300,250);
		
		JPanel boxa = new JPanel(new GridLayout(2,1));
		boxa.setBackground(Color.GRAY);
		JLabel userId = new JLabel("USER ID",JLabel.CENTER);
		userId.setFont(new Font("Tahoma",Font.BOLD,15));
		userId.setForeground(Color.WHITE);
		boxa.add(userId);
		uId = new JTextField(20);
		boxa.add(uId);
		
		JPanel box1 = new JPanel(new GridLayout(2,1));
		box1.setBackground(Color.GRAY);
		JLabel password = new JLabel("PASSWORD",JLabel.CENTER);
		password.setFont(new Font("Tahoma",Font.BOLD,15));
		password.setForeground(Color.WHITE);
		box1.add(password);
		jpswd = new JPasswordField();
		box1.add(jpswd);
		
		JPanel box2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		box2.setBackground(Color.GRAY);
		msg = new JLabel();
		msg.setFont(new Font("Tahoma",Font.BOLD,15));
		msg.setForeground(Color.yellow);
		box2.add(msg);
		
		JPanel boxb = new JPanel(new FlowLayout());
		boxb.setBackground(Color.GRAY);	
		JButton login = new JButton("LOG IN");	
		boxb.add(login);
		JButton reset = new JButton("RESET");
		boxb.add(reset);
		box.add(boxa);
		box.add(box1);
		box.add(box2);
		box.add(boxb);
		login.addActionListener(this);
		reset.addActionListener(this);
		add(box);
		header.add(icon);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Login();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if((e.getActionCommand()=="LOG IN")) {
			
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost/bms","root","");
					st = con.createStatement();
				} catch (SQLException | ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(null,"Server is not connected.");
				}
				try {
					ResultSet rs = st.executeQuery("SELECT * FROM admin WHERE UserId='"+uId.getText()+"' AND Password='"+jpswd.getText()+"'");
					if(rs.next()) {
						this.setVisible(false);
						new MainView(rs.getString(1)).setVisible(true);
					}
					else {
						msg.setText("* Id/Passwrod not matched");
					}
				con.close();				
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null,ex.getMessage());
			}	
		}
		if((e.getActionCommand()=="RESET")) {
			uId.setText("");
			jpswd.setText("");
			msg.setText("");
		}
	}
}
