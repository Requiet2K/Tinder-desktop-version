package tinder;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.sql.SQLException;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MatchScreen extends JFrame {

	private JPanel contentPane;
	int mouseX, mouseY;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MatchScreen frame = new MatchScreen();
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
	public MatchScreen() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(750, 225, 510, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		dbConnection con = new dbConnection();
		
		JButton btnKeepswip = new JButton("Keep swiping");
		btnKeepswip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnKeepswip.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		btnKeepswip.setForeground(Color.BLACK);
		btnKeepswip.setBounds(125, 520, 243, 32);
		btnKeepswip.setBackground(new Color(255,88,100));
		contentPane.add(btnKeepswip);
		btnKeepswip.setFocusPainted(false);
		
		JLabel tinderLogo = new JLabel("finder");
		tinderLogo.setFont(new Font("Microsoft YaHei UI Light", Font.BOLD, 34));
		tinderLogo.setHorizontalAlignment(SwingConstants.CENTER);
		tinderLogo.setBounds(146, 44, 222, 48);
		tinderLogo.setForeground(new Color(255,88,100));
		contentPane.add(tinderLogo);
		tinderLogo.setIcon(new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\tinder48Logo.png"));
		
		JLabel lblNewLabel_1 = new JLabel("You and "+MainPage.rs.getString(2)+" have liked each other.");
		lblNewLabel_1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 11));
		lblNewLabel_1.setForeground(new Color(255,88,100));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(87, 261, 333, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblMatchLogged = new JLabel("");
		lblMatchLogged.setBounds(87, 310, 127, 130);
		contentPane.add(lblMatchLogged);
		
		Image matchLogged = con.getPhoto(login.loggedUser.id).getScaledInstance(lblMatchLogged.getWidth(),lblMatchLogged.getHeight(),Image.SCALE_SMOOTH);	
		
		ImageIcon matchedLogged = circularPicture(matchLogged);
		
		lblMatchLogged.setIcon(matchedLogged);
		
		
		
		JLabel lblMatched = new JLabel("");
		lblMatched.setBounds(293, 310, 127, 130);
		contentPane.add(lblMatched);
		
		Image matched = con.getPhoto(MainPage.rs.getInt(1)).getScaledInstance(lblMatched.getWidth(),lblMatched.getHeight(),Image.SCALE_SMOOTH);	
		
		ImageIcon matchedIcon = circularPicture(matched);
		
		lblMatched.setIcon(matchedIcon);
		
		JLabel lblBackground = new JLabel(new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\matchBack.png"));
		lblBackground.setBounds(0, 0, 510, 600);
		contentPane.add(lblBackground);
		panel.setBounds(0, 0, 510, 32);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 510, 552);
		contentPane.add(lblNewLabel);
		setUndecorated(true);
		
		
		
	}
	
	public ImageIcon circularPicture(Image image) {
		
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
		ImageIcon last = new ImageIcon(masked);
		
		return last;
		
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
