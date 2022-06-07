package tinder;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JSeparator;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

public class register extends JFrame {

	private JPanel contentPane;
	JLabel gorsel = new JLabel("");
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtMailadress;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	int mouseX,mouseY;
	private JTextField txtPhone;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					register frame = new register();
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
	 * @throws MalformedURLException 
	 * @throws ParseException 
	 */
	public register() throws MalformedURLException, IOException, ParseException {
		setBackground(new Color(95, 158, 160));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 250, 950, 469);
		contentPane = new JPanel();
		contentPane.setForeground(Color.DARK_GRAY);
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		
		JLabel lblNewLabel_2 = new JLabel("X");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_2.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_2.setForeground(Color.WHITE);
			}
		});
		
		JLabel lblNewLabel_2_1 = new JLabel("-");
		lblNewLabel_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_2_1.setForeground(Color.BLACK);
			}
			public void mouseExited(MouseEvent e) {
				lblNewLabel_2_1.setForeground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		
		JLabel lblNewLabel_3 = new JLabel("Back");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					login log = new login();
					log.setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_3.setForeground(Color.BLACK);
				
			}
			public void mouseExited(MouseEvent e) {
				lblNewLabel_3.setForeground(Color.WHITE);
			}
		});
			
		lblNewLabel_3.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		lblNewLabel_3.setBounds(0, 0, 64, 36);
		contentPane.add(lblNewLabel_3);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 24));
		lblNewLabel_2_1.setBounds(877, 11, 30, 14);
		contentPane.add(lblNewLabel_2_1);
		lblNewLabel_2.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(907, 11, 37, 14);
		contentPane.add(lblNewLabel_2);
		
		JSeparator separator_3_1_1_1 = new JSeparator();
		separator_3_1_1_1.setForeground(new Color(60, 60, 60));
		separator_3_1_1_1.setBackground(new Color(40, 40, 40));
		separator_3_1_1_1.setBounds(762, 44, 161, 2);
		contentPane.add(separator_3_1_1_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(new Color(40, 40, 40));
		separator_1.setBackground(new Color(60, 60, 60));
		separator_1.setBounds(922, 44, 1, 195);
		contentPane.add(separator_1);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(40,40,40));
		separator.setBackground(new Color(60,60,60));
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(762, 44, 1, 195);
		contentPane.add(separator);
		
		JSeparator separator_3_1_1 = new JSeparator();
		separator_3_1_1.setForeground(new Color(60, 60, 60));
		separator_3_1_1.setBackground(new Color(40, 40, 40));
		separator_3_1_1.setBounds(762, 238, 161, 2);
		contentPane.add(separator_3_1_1);
		
		JLabel lblNewLabel = new JLabel("Socialize with People!");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(80, 36, 252, 44);
		contentPane.add(lblNewLabel);
		
		
		gorsel.setBounds(768, 51, 149, 183);
		contentPane.add(gorsel);
		
		ImageIcon icon = new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\date.jpg");
		
		JFileChooser file = new JFileChooser();
		
		ImageIcon SelectPhotoIcon = new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\gallery.png");
		
		JButton btnPhoto = new JButton("Select a Photo");
		btnPhoto.setBorder(null);
		btnPhoto.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		btnPhoto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnPhoto.setBackground(Color.GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnPhoto.setBackground(Color.DARK_GRAY);
			}
		});
		btnPhoto.setBackground(Color.DARK_GRAY);
		btnPhoto.setForeground(Color.WHITE);
		btnPhoto.setIcon(SelectPhotoIcon);
		btnPhoto.setVerticalTextPosition(SwingConstants.BOTTOM);	
		btnPhoto.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				file.setCurrentDirectory(new File("user.home"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images","png","jpg");
				file.addChoosableFileFilter(filter);
				
				if(file.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					try {
						
						Image img = ImageIO.read(file.getSelectedFile());
						Image img2 = img.getScaledInstance(gorsel.getWidth(),gorsel.getHeight(),Image.SCALE_SMOOTH);
						gorsel.setIcon(new ImageIcon(img2));
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "No picture selected!");
				}
				
			}
		});
		btnPhoto.setBounds(783, 246, 123, 77);
		contentPane.add(btnPhoto);
		
		
		ImageIcon button = new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\buton.png");
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(545, 13, 53, 36);
		contentPane.add(lblNewLabel_1);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		txtName.setBackground(Color.DARK_GRAY);
		txtName.setForeground(Color.GRAY);
		txtName.setBorder(null);
		txtName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtName.getText().equals("Enter Name")) {
					txtName.setText("");
					txtName.setForeground(Color.WHITE);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtName.getText().equals("") || txtName.getText().equals("Enter Name")) {
					txtName.setText("Enter Name");
					txtName.setForeground(Color.GRAY);
				}
			}
		});
		txtName.setText("Enter Name");
		txtName.setBounds(545, 44, 168, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtSurname = new JTextField();
		txtSurname.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		txtSurname.setBorder(null);
		txtSurname.setBackground(Color.DARK_GRAY);
		txtSurname.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtSurname.getText().equals("Enter Surname")) {
					txtSurname.setText("");
					txtSurname.setForeground(Color.WHITE);
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(txtSurname.getText().equals("") || txtSurname.getText().equals("Enter Surname")) {
					txtSurname.setText("Enter Surname");
					txtSurname.setForeground(Color.GRAY);
				}
			}
		});
		txtSurname.setForeground(Color.GRAY);
		txtSurname.setText("Enter Surname");
		txtSurname.setColumns(10);
		txtSurname.setBounds(545, 107, 168, 20);
		contentPane.add(txtSurname);
		
		txtMailadress = new JTextField();
		txtMailadress.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		txtMailadress.setBorder(null);
		txtMailadress.setBackground(Color.DARK_GRAY);
		txtMailadress.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtMailadress.getText().equals("Enter Mail-Adress")) {
					txtMailadress.setText("");
					txtMailadress.setForeground(Color.WHITE);
				}
			}
			public void focusLost(FocusEvent e) {
				if(txtMailadress.getText().equals("") || txtMailadress.getText().equals("Enter Mail-Adress")) {
					txtMailadress.setText("Enter Mail-Adress");
					txtMailadress.setForeground(Color.GRAY);
				}
			}
		});
		txtMailadress.setForeground(Color.GRAY);
		txtMailadress.setText("Enter Mail-Adress");
		txtMailadress.setColumns(10);
		txtMailadress.setBounds(545, 169, 168, 20);
		contentPane.add(txtMailadress);
		

		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		passwordField.setBorder(null);
		passwordField.setBackground(Color.DARK_GRAY);
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(passwordField.getText().equals("Password")) {
					passwordField.setText("");
					passwordField.setForeground(Color.WHITE);
				}
			}
			public void focusLost(FocusEvent e) {
				if(passwordField.getText().equals("") || passwordField.getText().equals("Password")) {
					passwordField.setText("Password");
					passwordField.setForeground(Color.GRAY);
				}
			}
		});
		passwordField.setText("Password");
		passwordField.setForeground(Color.GRAY);
		passwordField.setEchoChar('*');
		passwordField.setToolTipText("Password");
		passwordField.setBounds(545, 301, 168, 20);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		passwordField_1.setBorder(null);
		passwordField_1.setBackground(Color.DARK_GRAY);
		passwordField_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(passwordField_1.getText().equals("Password")) {
					passwordField_1.setText("");
					passwordField_1.setForeground(Color.WHITE);
				}
			}
			public void focusLost(FocusEvent e) {
				if(passwordField_1.getText().equals("") || passwordField_1.getText().equals("Password")) {
					passwordField_1.setText("Password");
					passwordField_1.setForeground(Color.GRAY);
				}
			}
		});
		passwordField_1.setText("Password");
		passwordField_1.setForeground(Color.GRAY);
		passwordField_1.setEchoChar('*');
		passwordField_1.setToolTipText("Password Again");
		passwordField_1.setBounds(545, 369, 168, 20);
		contentPane.add(passwordField_1);
		
		DefaultComboBoxModel cbLang = new DefaultComboBoxModel(new String[] {"(+90) TR","(+49) DE","(+33) FR"});
		JComboBox comboBox = new JComboBox(cbLang);
		comboBox.setForeground(Color.WHITE);
		comboBox.setBackground(Color.DARK_GRAY);
		comboBox.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		comboBox.setBounds(635, 231, 78, 23);
		contentPane.add(comboBox);
		

		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		rdbtnMale.setForeground(Color.WHITE);
		rdbtnMale.setBackground(Color.DARK_GRAY);
		rdbtnMale.setBounds(597, 439, 64, 23);
		contentPane.add(rdbtnMale);
		rdbtnMale.setActionCommand("Male");
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		rdbtnFemale.setForeground(Color.WHITE);
		rdbtnFemale.setBackground(Color.DARK_GRAY);
		rdbtnFemale.setBounds(659, 439, 67, 23);
		contentPane.add(rdbtnFemale);
		rdbtnFemale.setActionCommand("Female");
		
		ButtonGroup genders = new ButtonGroup();
		genders.add(rdbtnMale);
		genders.add(rdbtnFemale);
		
		ImageIcon visible = new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\view.png");
		ImageIcon invisible = new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\invisible.png");
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(lblNewLabel_8.getIcon().equals(visible)) {
					passwordField.setEchoChar((char) 0);
					passwordField_1.setEchoChar((char) 0);
					lblNewLabel_8.setIcon(invisible);
				}
				else {
					passwordField.setEchoChar('*');
					passwordField_1.setEchoChar('*');
					lblNewLabel_8.setIcon(visible);
				}
			}
		});
		lblNewLabel_8.setBounds(716, 294, 37, 23);
		contentPane.add(lblNewLabel_8);
		lblNewLabel_8.setIcon(visible);
		
		txtPhone = new JTextField();
		txtPhone.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(txtPhone.getText().equals("Enter Phone")) {
					txtPhone.setText("");
					txtPhone.setForeground(Color.WHITE);
				}
			}
			public void focusLost(FocusEvent e) {
				if(txtPhone.getText().equals("")) {
					txtPhone.setText("Enter Phone");
					txtPhone.setForeground(Color.GRAY);
				}
			}
			
		});
		txtPhone.setForeground(Color.GRAY);
		txtPhone.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		txtPhone.setText("Enter Phone");
		txtPhone.setBorder(null);
		txtPhone.setBackground(Color.DARK_GRAY);
		txtPhone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
					boolean dot = false;                              
				    char vChar = e.getKeyChar();
				    if (txtPhone.getText().equals(""))
				        dot = false;
				    if (dot == false){
				        if (vChar == '.') dot = true;
				        else if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE)  || (vChar == KeyEvent.VK_DELETE))) {
				                e.consume();
				        }
				    } else {
				        if (!(Character.isDigit(vChar) || (vChar == KeyEvent.VK_BACK_SPACE) || (vChar == KeyEvent.VK_DELETE))) {
				                e.consume();
				        }
				    }
				    if (txtPhone.getText().length() >= 10 ) {
		                e.consume();
		        }
				}
			
		});
		txtPhone.setBounds(545, 232, 86, 20);
		contentPane.add(txtPhone);
		txtPhone.setColumns(10);
		
		JLabel lblNewLabel_1_7 = new JLabel("Surname");
		lblNewLabel_1_7.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_7.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		lblNewLabel_1_7.setBounds(545, 75, 53, 36);
		contentPane.add(lblNewLabel_1_7);
		
		JLabel lblNewLabel_1_8 = new JLabel("Mail Address");
		lblNewLabel_1_8.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_8.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		lblNewLabel_1_8.setBounds(545, 138, 86, 36);
		contentPane.add(lblNewLabel_1_8);
		
		JLabel lblNewLabel_1_1 = new JLabel("Phone");
		lblNewLabel_1_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(546, 200, 53, 36);
		contentPane.add(lblNewLabel_1_1);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(new Color(60, 60, 60));
		separator_3.setBackground(new Color(40, 40, 40));
		separator_3.setBounds(545, 254, 168, 2);
		contentPane.add(separator_3);
		
		JSeparator separator_3_1 = new JSeparator();
		separator_3_1.setForeground(new Color(60, 60, 60));
		separator_3_1.setBackground(new Color(40, 40, 40));
		separator_3_1.setBounds(545, 193, 168, 2);
		contentPane.add(separator_3_1);
		
		JSeparator separator_3_2 = new JSeparator();
		separator_3_2.setForeground(new Color(60, 60, 60));
		separator_3_2.setBackground(new Color(40, 40, 40));
		separator_3_2.setBounds(545, 134, 168, 2);
		contentPane.add(separator_3_2);
		
		JSeparator separator_3_3 = new JSeparator();
		separator_3_3.setForeground(new Color(60, 60, 60));
		separator_3_3.setBackground(new Color(40, 40, 40));
		separator_3_3.setBounds(545, 71, 168, 2);
		contentPane.add(separator_3_3);
		
		JLabel lblNewLabel_1_2 = new JLabel("Password");
		lblNewLabel_1_2.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(545, 265, 86, 36);
		contentPane.add(lblNewLabel_1_2);
		
		JSeparator separator_3_3_1 = new JSeparator();
		separator_3_3_1.setForeground(new Color(60, 60, 60));
		separator_3_3_1.setBackground(new Color(40, 40, 40));
		separator_3_3_1.setBounds(545, 325, 168, 2);
		contentPane.add(separator_3_3_1);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Password Again");
		lblNewLabel_1_2_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_2_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		lblNewLabel_1_2_1.setBounds(545, 333, 102, 36);
		contentPane.add(lblNewLabel_1_2_1);
		
		JSeparator separator_3_3_1_1 = new JSeparator();
		separator_3_3_1_1.setForeground(new Color(60, 60, 60));
		separator_3_3_1_1.setBackground(new Color(40, 40, 40));
		separator_3_3_1_1.setBounds(545, 393, 168, 2);
		contentPane.add(separator_3_3_1_1);
		
		JLabel lblNewLabel_4 = new JLabel("Photo");
		lblNewLabel_4.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(768, 23, 107, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Gender");
		lblNewLabel_5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		lblNewLabel_5.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_5.setBounds(545, 443, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Age");
		lblNewLabel_6.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_6.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(545, 413, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		JComboBox cbAge = new JComboBox();
		cbAge.setForeground(Color.WHITE);
		cbAge.setBackground(Color.DARK_GRAY);
		cbAge.setBounds(601, 410, 49, 22);
		contentPane.add(cbAge);
		for(int i = 18 ; i<60 ; i++) cbAge.addItem(i);
		
	
		
				
				JButton btnRegister = new JButton();
				btnRegister.setText("SIGN UP");
				btnRegister.setHorizontalTextPosition(SwingConstants.CENTER);
				btnRegister.setIcon(button);
				btnRegister.setBorder(null);
				btnRegister.setForeground(Color.WHITE);
				btnRegister.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
				btnRegister.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						btnRegister.setForeground(Color.LIGHT_GRAY);
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						btnRegister.setForeground(Color.WHITE);
					}
				});
				btnRegister.setBackground(Color.DARK_GRAY);
				btnRegister.setBounds(760, 413, 168, 44);
				contentPane.add(btnRegister);
				
						
						
						btnRegister.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
				                        "[a-zA-Z0-9_+&*-]+)*@" +
				                        "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
				                        "A-Z]{2,7}$"; 
								
								Pattern pat = Pattern.compile(emailRegex);
								
								
								if(txtName.getText().length()==0 || txtName.getText().equals("Enter Name")) {
									JOptionPane.showMessageDialog(contentPane, "Type a Name!");
									return;
								}
								if(txtSurname.getText().length()==0 || txtSurname.getText().equals("Enter Surname")) {
									JOptionPane.showMessageDialog(contentPane, "Type a Surname!");
									return;
								}
								if(!pat.matcher(txtMailadress.getText()).matches() || txtMailadress.getText().toString().length()==0) {
									JOptionPane.showMessageDialog(contentPane, "Incorrect Mail!");
									return;
								}
								if(txtPhone.getText().length()<10) {
									JOptionPane.showMessageDialog(contentPane, "Wrong Phone Number!");
									return;
								}
								if(genders.isSelected(null)) {
									JOptionPane.showMessageDialog(contentPane, "Select Gender!");
									return;
								}
								if(!passwordField.getText().toString().equals(passwordField_1.getText().toString())){
									JOptionPane.showMessageDialog(contentPane, "Passwords are not equal!");
									return;
								}
								if((passwordField.getText().toString().length())+(passwordField_1.getText().toString().length())==0 || passwordField.getText().equals("Password") || passwordField_1.getText().equals("Password")) {
									JOptionPane.showMessageDialog(contentPane, "Enter a password!");
									return;
								}
								if(gorsel.getIcon() == null) {
									JOptionPane.showMessageDialog(contentPane, "Select a Picture!");
									return;
								}
								
								/*if(passwordField.getText().toString().length()<8) {
									JOptionPane.showMessageDialog(contentPane, "Create more secure password!");
									return;
								}*/
								
								String region;
								
								switch(comboBox.getSelectedIndex()) {
								case 0:
									region = "Turkey";
									break;
								case 1:
									region = "Germany";
									break;
								case 2:
									region = "France";
									break;
								default:
									region = "wtf";
									break;
								}
								
								InputStream in = null;
								try {
									in = new FileInputStream(file.getSelectedFile().getAbsolutePath());
								} catch (FileNotFoundException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								}
								
								
								dbConnection connect = new dbConnection();
								ResultSet rs;
								try {
									rs = connect.getResultSet();
									while(rs.next()) {
										if(rs.getString(5).toLowerCase().equals(txtMailadress.getText().toLowerCase())) {
											JOptionPane.showMessageDialog(contentPane, "This mail is using.");
											return;
										}
										if(rs.getString(6).equals(txtPhone.getText())) {
											JOptionPane.showMessageDialog(contentPane, "This phone number is using.");
											return;
										}
										
										
									}
								} catch (SQLException e2) {
									// TODO Auto-generated catch block
				 					e2.printStackTrace();
								}
								
															    			    
								User user = new User();
								user.name = txtName.getText();
								user.surname = txtSurname.getText();
								user.age = Integer.parseInt(cbAge.getSelectedItem().toString());
								user.mail = txtMailadress.getText();
								user.phone = txtPhone.getText();
								user.region = region;
								user.gender = genders.getSelection().getActionCommand();
								user.password = passwordField.getText().toString();
								user.picture = in;
								
								try {
													
									connect.newUser(user);
									
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								JOptionPane.showMessageDialog(contentPane, "Welcome to Tinder World!");
							}
						});
				
						JLabel lblNewLabel_7 = new JLabel(new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\backgroundregister.png"));
						lblNewLabel_7.setBounds(426, 0, 597, 475);
						contentPane.add(lblNewLabel_7);
				
						
						JLabel tinderlogo = new JLabel(icon);
						tinderlogo.setFont(new Font("Tahoma", Font.PLAIN, 18));
						tinderlogo.setBackground(Color.BLACK);
						tinderlogo.setBounds(0, 0, 521, 475);
						contentPane.add(tinderlogo);
						tinderlogo.setIcon(icon);
						
						JPanel panel = new JPanel();
						panel.addMouseMotionListener(new MouseMotionAdapter() {
							@Override
							public void mouseDragged(MouseEvent e) {			
								setLocation(getX() + e.getX() - mouseX , getY() + e.getY() - mouseY);
							}
						});
						panel.addMouseListener(new MouseAdapter() {
							@Override
							public void mousePressed(MouseEvent e) {
								mouseX = e.getX();
								mouseY = e.getY();
							}
						});
						panel.setBounds(0, 0, 954, 36);
						panel.setBackground(new Color(40,40,40));
						contentPane.add(panel);
						panel.setLayout(null);

		
		
		
	}
}