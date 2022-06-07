package tinder;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;

public class Settings extends JFrame {

	private JPanel contentPane;
	int mouseX, mouseY;
	dbConnection con = new dbConnection();
	private JPasswordField pass1;
	private JPasswordField pass2;
	private JPasswordField pass3;
	boolean photoChanged = false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings frame = new Settings();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Settings() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(825, 175, 300, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
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
		
		ImageIcon visible = new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\view.png");
		ImageIcon invisible = new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\invisible.png");
		
		JLabel lblNewLabel_8 = new JLabel(visible);
		lblNewLabel_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(lblNewLabel_8.getIcon().equals(visible)) {
					pass1.setEchoChar((char) 0);
					pass2.setEchoChar((char) 0);
					pass3.setEchoChar((char) 0);
					lblNewLabel_8.setIcon(invisible);
				}
				else {
					pass1.setEchoChar('*');
					pass2.setEchoChar('*');
					pass3.setEchoChar('*');
					lblNewLabel_8.setIcon(visible);
				}
			}
		});
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(240, 358, 37, 23);
		contentPane.add(lblNewLabel_8);
		panel.setBounds(0, 0, 300, 33);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblClose = new JLabel("X");
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblClose.setForeground(Color.GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblClose.setForeground(Color.BLACK);
			}
		});
		lblClose.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		lblClose.setBounds(267, 0, 33, 33);
		panel.add(lblClose);
		
		JLabel lblMinimize = new JLabel("-");
		lblMinimize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblMinimize.setForeground(Color.GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblMinimize.setForeground(Color.BLACK);
			}
		});
		lblMinimize.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinimize.setFont(new Font("Yu Gothic UI Light", Font.BOLD, 18));
		lblMinimize.setBounds(231, 0, 33, 33);
		panel.add(lblMinimize);
		
		JLabel lblPhoto = new JLabel("");
		lblPhoto.setBounds(73, 45, 154, 167);
		contentPane.add(lblPhoto);
		setUndecorated(true);
		
		Image image = con.getPhoto(login.loggedUser.id).getScaledInstance(lblPhoto.getWidth(),lblPhoto.getHeight(),Image.SCALE_SMOOTH);
		lblPhoto.setIcon(new ImageIcon(image));
		
		JFileChooser file = new JFileChooser();
		
		JLabel lblUploadPhoto = new JLabel("Upload Photo");
		lblUploadPhoto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblUploadPhoto.setForeground(Color.GRAY);
			}
			public void mouseExited(MouseEvent e) {
				lblUploadPhoto.setForeground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
				file.setCurrentDirectory(new File("user.home"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images","png","jpg");
				file.addChoosableFileFilter(filter);
				
				if(file.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					try {
						
						Image img = ImageIO.read(file.getSelectedFile());
						Image img2 = img.getScaledInstance(lblPhoto.getWidth(),lblPhoto.getHeight(),Image.SCALE_SMOOTH);
						lblPhoto.setIcon(new ImageIcon(img2));
						photoChanged = true;
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "Resim Seçilmedi");
				}
				

				
			}
		});
		lblUploadPhoto.setForeground(Color.WHITE);
		lblUploadPhoto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUploadPhoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblUploadPhoto.setBounds(83, 228, 144, 14);
		contentPane.add(lblUploadPhoto);
		
		JLabel lblNewLabel = new JLabel("I want to change my password");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel.setBounds(73, 287, 169, 21);
		contentPane.add(lblNewLabel);
		
		JSeparator separator_3_1 = new JSeparator();
		separator_3_1.setForeground(new Color(60, 60, 60));
		separator_3_1.setBackground(new Color(40, 40, 40));
		separator_3_1.setBounds(73, 385, 154, 2);
		contentPane.add(separator_3_1);
		
		JLabel lblNewLabel_1_8 = new JLabel("Password");
		lblNewLabel_1_8.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_8.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		lblNewLabel_1_8.setBounds(73, 330, 86, 36);
		contentPane.add(lblNewLabel_1_8);
		
		JSeparator separator_3_1_1 = new JSeparator();
		separator_3_1_1.setForeground(new Color(60, 60, 60));
		separator_3_1_1.setBackground(new Color(40, 40, 40));
		separator_3_1_1.setBounds(73, 453, 154, 2);
		contentPane.add(separator_3_1_1);
		
		JLabel lblNewLabel_1_8_1 = new JLabel("New Password");
		lblNewLabel_1_8_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_8_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		lblNewLabel_1_8_1.setBounds(73, 398, 109, 36);
		contentPane.add(lblNewLabel_1_8_1);
		
		JLabel lblNewLabel_1_8_1_1 = new JLabel("New Password");
		lblNewLabel_1_8_1_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1_8_1_1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 12));
		lblNewLabel_1_8_1_1.setBounds(73, 466, 86, 36);
		contentPane.add(lblNewLabel_1_8_1_1);
		
		JSeparator separator_3_1_1_1 = new JSeparator();
		separator_3_1_1_1.setForeground(new Color(60, 60, 60));
		separator_3_1_1_1.setBackground(new Color(40, 40, 40));
		separator_3_1_1_1.setBounds(73, 521, 154, 2);
		contentPane.add(separator_3_1_1_1);
		
		JCheckBox chckPass = new JCheckBox("");
		chckPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckPass.isSelected()) {
					pass1.setEnabled(true);
					pass2.setEnabled(true);
					pass3.setEnabled(true);
				}
				else{
					pass1.setText("Password");
					pass1.setForeground(Color.GRAY);
					pass2.setText("Password");
					pass2.setForeground(Color.GRAY);
					pass3.setText("Password");
					pass3.setForeground(Color.GRAY);
					pass1.setEnabled(false);
					pass2.setEnabled(false);
					pass3.setEnabled(false);
				}
				
			}
		});
		

		chckPass.setBackground(Color.DARK_GRAY);
		chckPass.setBounds(240, 287, 21, 23);
		contentPane.add(chckPass);
		
		pass1 = new JPasswordField();
		pass1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(pass1.getText().equals("Password")) {
					pass1.setText("");
				}
			}
			public void focusLost(FocusEvent e) {
				if(pass1.getText().equals("")) {
					pass1.setText("Password");
				}
			}
		});
		pass1.setEnabled(false);
		pass1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
		pass1.setEchoChar('*');
		pass1.setForeground(Color.GRAY);
		pass1.setText("Password");
		pass1.setToolTipText("");
		pass1.setBorder(null);
		pass1.setBackground(Color.DARK_GRAY);
		pass1.setBounds(73, 361, 154, 20);
		contentPane.add(pass1);
		
		pass2 = new JPasswordField();
		pass2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(pass2.getText().equals("Password")) {
					pass2.setText("");
				}
			}
			public void focusLost(FocusEvent e) {
				if(pass2.getText().equals("")) {
					pass2.setText("Password");
				}
			}
		});
		pass2.setEnabled(false);
		pass2.setToolTipText("");
		pass2.setText("Password");
		pass2.setForeground(Color.GRAY);
		pass2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
		pass2.setEchoChar('*');
		pass2.setBorder(null);
		pass2.setBackground(Color.DARK_GRAY);
		pass2.setBounds(73, 430, 154, 20);
		contentPane.add(pass2);
		
		pass3 = new JPasswordField();
		pass3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(pass3.getText().equals("Password")) {
					pass3.setText("");
				}
			}
			public void focusLost(FocusEvent e) {
				if(pass3.getText().equals("")) {
					pass3.setText("Password");
				}
			}
		});
		pass3.setEnabled(false);
		pass3.setToolTipText("");
		pass3.setText("Password");
		pass3.setForeground(Color.GRAY);
		pass3.setFont(new Font("Microsoft YaHei", Font.PLAIN, 11));
		pass3.setEchoChar('*');
		pass3.setBorder(null);
		pass3.setBackground(Color.DARK_GRAY);
		pass3.setBounds(73, 497, 154, 20);
		contentPane.add(pass3);
		
		JButton btnChange = new JButton("Apply Settings");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!photoChanged && !chckPass.isSelected()) {
					JOptionPane.showMessageDialog(contentPane, "Nothing changed!");
					dispose();
					return;
				}
				
				if(!(pass2.getText().equals(pass3.getText()))) {
					JOptionPane.showMessageDialog(contentPane, "Password must be same!");
					return;
				}
				if(pass1.getText().length()==0) {
					JOptionPane.showMessageDialog(contentPane, "Type a Password!");
					return;
				}
				
				if(chckPass.isSelected() && !pass2.getText().equals("Password")) {
					if(!pass1.getText().equals(login.loggedUser.password)) {
						JOptionPane.showMessageDialog(contentPane, "Password wrong!");
						return;
					}
					String query = "update finder.user set password = ? where id ="+login.loggedUser.id;
					PreparedStatement ps;
					try {
						ps = con.getConnection().prepareStatement(query);
						ps.setString(1, pass2.getText());
						ps.executeUpdate();
						login.loggedUser.password = pass2.getText();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
				
				if(photoChanged) {
					
					ImageIcon mainPageIcon = (ImageIcon) lblPhoto.getIcon();
					ImageIcon circularMainPageIcon = circularPhoto(mainPageIcon.getImage());
					MainPage.lblLoggedPhoto.setIcon(circularMainPageIcon);
					
					InputStream in = null;
					try {
						in = new FileInputStream(file.getSelectedFile().getAbsolutePath());
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					String query = "update finder.user set picture = ? where id ="+login.loggedUser.id;
					
					PreparedStatement ps;
					try {
						ps = con.getConnection().prepareStatement(query);
						ps.setBinaryStream(1, in);
						ps.executeUpdate();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
				if(photoChanged && chckPass.isSelected()) {
					JOptionPane.showMessageDialog(contentPane, "Changes approved!");
				}
				if(photoChanged && !chckPass.isSelected()) {
					JOptionPane.showMessageDialog(contentPane, "Photo changed!");
				}
				if(!photoChanged && chckPass.isSelected()) {
					JOptionPane.showMessageDialog(contentPane, "Password changed!");
				}

				dispose();
				
				
				
			}
		});
		btnChange.setForeground(Color.WHITE);
		btnChange.setFont(new Font("Lucida Sans", Font.PLAIN, 13));
		btnChange.setFocusPainted(false);
		btnChange.setBackground(Color.BLACK);
		btnChange.setBounds(87, 548, 125, 20);
		contentPane.add(btnChange);
		
		JSeparator separator_3_1_2 = new JSeparator();
		separator_3_1_2.setForeground(new Color(60, 60, 60));
		separator_3_1_2.setBackground(new Color(40, 40, 40));
		separator_3_1_2.setBounds(0, 274, 300, 2);
		contentPane.add(separator_3_1_2);
		

		
		
	}
	
	public ImageIcon circularPhoto(Image image) {
		
	    ImageIcon loggedIcon = new ImageIcon(image);
	    
	    
	    BufferedImage master = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
	    
	    Graphics2D bGr = master.createGraphics();
	    bGr.drawImage(image, 0, 0, null);
	    bGr.dispose();
	    
	    int diameter = Math.min(master.getWidth(), master.getHeight());
	    BufferedImage mask = new BufferedImage(master.getWidth(), master.getHeight(), BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = mask.createGraphics();
	    applyQualityRenderingHints(g2d);
	    g2d.fillOval(0, 0, diameter - 1, diameter - 1);
	    g2d.dispose();

	    BufferedImage masked = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
	    g2d = masked.createGraphics();
	    applyQualityRenderingHints(g2d);
	    int x = (diameter - master.getWidth()) / 2;
	    int y = (diameter - master.getHeight()) / 2;
	    g2d.drawImage(master, x, y, null);
	    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
	    g2d.drawImage(mask, 0, 0, null);
	    g2d.dispose();
	    
	    ImageIcon finalIcon = new ImageIcon(masked);
	    
	    return finalIcon;
	    
	}

	public static void applyQualityRenderingHints(Graphics2D g2d) {

    g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
    g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
    g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

	}
}
