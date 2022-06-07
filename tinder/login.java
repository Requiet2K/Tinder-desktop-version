package tinder;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.AttributeSet.ColorAttribute;

import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDesktopPane;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField txtMail;
	private JPasswordField txtPassword;
	private JSeparator separator;
	private JSeparator separator_1;
	int mouseX, mouseY;
	static User loggedUser = new User();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public login() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 250, 722, 475);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255,88,100));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		
		JLabel tinderLogo = new JLabel(new ImageIcon(""));
		tinderLogo.setBounds(452, 65, 245, 412);
		contentPane.add(tinderLogo);
		
		ImageIcon usernameIcon = new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\username.png");
		
		File fileP = new File("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\phone.png");
        Image imgP = ImageIO.read(fileP);
        Image newImageP = imgP.getScaledInstance(tinderLogo.getWidth(), tinderLogo.getHeight(), Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(newImageP);
        tinderLogo.setIcon(icon);
        

		
		JLabel lblName = new JLabel(usernameIcon);
		lblName.setBounds(102, 181, 57, 52);
		contentPane.add(lblName);
		 
		ImageIcon passwordIcon = new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\password.png");
		
		JLabel lblPassword = new JLabel(passwordIcon);
		lblPassword.setBounds(102, 258, 57, 52);
		contentPane.add(lblPassword);
		
		txtMail = new JTextField();
		txtMail.setText("Enter Mail-Adress");
		txtMail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtMail.getText().equals("Welcome!")) {
					txtMail.setText("");
					txtMail.setForeground(Color.WHITE);
				}
			}
		});
		txtMail.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		txtMail.setBackground(new Color(255,88,100));
		txtMail.setBorder(null);
		txtMail.setForeground(Color.DARK_GRAY);
		txtMail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Border border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
				lblName.setBorder(border);
				if(txtMail.getText().equals("Enter Mail-Adress")) {
					txtMail.setText("");
					txtMail.setForeground(Color.BLACK);
				}
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				lblName.setBorder(null);
				if(txtMail.getText().equals("")) {
					txtMail.setText("Enter Mail-Adress");
					txtMail.setForeground(Color.DARK_GRAY);
				}
			}
		});
		txtMail.setBounds(169, 203, 125, 20);
		contentPane.add(txtMail);
		txtMail.setColumns(10);
		
		ImageIcon visible = new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\view.png");
		ImageIcon invisible = new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\invisible.png");
		
		
		JLabel lblPassShow = new JLabel();
		lblPassShow.setIcon(visible);
		lblPassShow.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
		lblPassShow.setForeground(Color.BLACK);
		lblPassShow.setVisible(false);
		lblPassShow.setBorder(null);
		lblPassShow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(lblPassShow.getIcon().equals(visible)) {
					lblPassShow.setIcon(invisible);
					txtPassword.setEchoChar((char) 0);
				}
				else {
					lblPassShow.setIcon(visible);
					txtPassword.setEchoChar('*');
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblPassShow.setForeground(Color.DARK_GRAY);
			}
			public void mouseExited(MouseEvent e) {
				lblPassShow.setForeground(Color.BLACK);
			}
			
		});
		lblPassShow.setBounds(304, 283, 24, 20);
		contentPane.add(lblPassShow);

		txtPassword = new JPasswordField();
		txtPassword.setText("Enter Password");
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(txtPassword.getText().length()>0) {
					lblPassShow.setVisible(true);
				}
				else {
					lblPassShow.setVisible(false);
				}
			}
		});
		txtPassword.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		txtPassword.setBackground(new Color(255,88,100));
		txtPassword.setEchoChar('*');
		txtPassword.setBorder(null);
		txtPassword.setForeground(Color.DARK_GRAY);
		txtPassword.setToolTipText("");
		txtPassword.setBounds(169, 283, 125, 20);
		contentPane.add(txtPassword);
		txtPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				Border border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK);
				lblPassword.setBorder(border);
				
				
				if(txtPassword.getText().equals("Enter Password")) {
					txtPassword.setText("");
					txtPassword.setForeground(Color.BLACK);
				}			
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				lblPassword.setBorder(null);
				if(txtPassword.getText().equals("")) {
					txtPassword.setText("Enter Password");
					txtPassword.setForeground(Color.DARK_GRAY);
				}
			}
		});
		
		separator = new JSeparator();
		separator.setForeground(new Color(60,60,60));
		separator.setBackground(new Color(40,40,40));
		separator.setBounds(169, 224, 125, 9);
		contentPane.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setForeground(new Color(60,60,60));
		separator_1.setBackground(new Color(40,40,40));
		separator_1.setBounds(169, 304, 125, 14);
		contentPane.add(separator_1);
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(getX() + e.getX() - mouseX, getY() + e.getY() - mouseY);
			}
		});
		panel.setForeground(Color.BLACK);
		panel.setBackground(new Color(15,15,15));
		panel.setBounds(0, 0, 786, 31);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblClose = new JLabel("X");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblClose.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblClose.setForeground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblClose.setForeground(Color.WHITE);
		lblClose.setBackground(Color.BLACK);
		lblClose.setBounds(686, 0, 37, 32);
		panel.add(lblClose);
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		lblClose.setFont(new Font("Arial", Font.PLAIN, 14));
		
		JLabel lblDown = new JLabel("-");
		lblDown.setForeground(Color.WHITE);
		lblDown.setBackground(Color.BLACK);
		lblDown.setBounds(653, 0, 32, 32);
		panel.add(lblDown);
		lblDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblDown.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblDown.setForeground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED); 
			}
		});				

		lblDown.setHorizontalAlignment(SwingConstants.CENTER);
		lblDown.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
		
		Border border2 = BorderFactory.createLineBorder(Color.BLACK, 2);
        
        JLabel lblBorder2 = new JLabel("");
        lblBorder2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
        lblBorder2.setBounds(49, 403, 343, 38);
        contentPane.add(lblBorder2);
        lblBorder2.setBorder(border2);
		
		JButton btnSign = new JButton("LOGIN");
	 	btnSign.setFocusPainted(false);
		btnSign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		 		if(txtMail.getText().equals("admin") && txtPassword.getText().equals("1234")) {
					adminPanel admin = new adminPanel();
					admin.setVisible(true);
					return;
				}
				
				dbConnection con = new dbConnection();
				try {
					ResultSet rs = con.getResultSet();
					while(rs.next()) {
						if(rs.getString(5).equals(txtMail.getText()) && rs.getString(9).equals(txtPassword.getText())) {
							
							loggedUser = con.getValues(rs.getInt(1));
							
							JOptionPane.showMessageDialog(contentPane, "Welcome "+loggedUser.name+" "+loggedUser.surname);
							
							MainPage main = new MainPage();
							main.setVisible(true);
							txtMail.setText("Welcome!");
							txtMail.setForeground(Color.GRAY);
							txtPassword.setText("");
							dispose();
							return;
							
						}
					}
					JOptionPane.showMessageDialog(contentPane, "Account not found!");
					return;
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSign.setFont(new Font("Lucida Sans", Font.PLAIN, 13));
		btnSign.setForeground(Color.WHITE);
		btnSign.setBackground(Color.BLACK);
		btnSign.setBounds(169, 336, 125, 20);
		contentPane.add(btnSign);
		
		JLabel lblRegister = new JLabel("Don't have an account? Sign up here!");
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				register reg;
				try {
					reg = new register();
					reg.setVisible(true);
					dispose();
				} catch (IOException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblRegister.setForeground(Color.BLACK);
			}
			public void mouseExited(MouseEvent e) {
				lblRegister.setForeground(Color.WHITE);
			}
		});
		lblRegister.setFont(new Font("Lucida Sans", Font.PLAIN, 11));
		lblRegister.setForeground(Color.WHITE);
		lblRegister.setBounds(65, 416, 304, 14);
		contentPane.add(lblRegister);
		
		JLabel lblrightCorner = new JLabel("Copyright \u00A9 2019 all rights reserved.");
		lblrightCorner.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 11));
		lblrightCorner.setForeground(Color.WHITE);
		lblrightCorner.setBounds(527, 459, 205, 14);
		contentPane.add(lblrightCorner);
		
		JLabel lblTinderLogo = new JLabel(new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\tinerLogo1.png"));
		lblTinderLogo.setBounds(131, 92, 57, 53);
		contentPane.add(lblTinderLogo);
		
		JLabel lblTinder = new JLabel("finder");
		lblTinder.setForeground(Color.BLACK);
		lblTinder.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 34));
		lblTinder.setBounds(188, 92, 140, 50);
		contentPane.add(lblTinder);
		
		ImageIcon imagePInside = new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\phone.gif");
		JLabel lblGif = new JLabel("");
		lblGif.setBounds(484, 72, 170, 365);
		contentPane.add(lblGif);
		lblGif.setIcon(imagePInside);
		
		JLabel lblBorder1 = new JLabel("");
		lblBorder1.setBounds(49, 65, 343, 327);
		contentPane.add(lblBorder1);
		lblBorder1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 31, 722, 2);
		contentPane.add(separator_2);
	    
	}
}
