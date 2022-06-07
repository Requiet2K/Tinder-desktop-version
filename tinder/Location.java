package tinder;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.MouseMotionAdapter;

public class Location extends JFrame {

	private JPanel contentPane;
	int mouseX;
	int mouseY;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Location frame = new Location();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Location() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 300, 700, 569);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_ = new JLabel("-");
		lbl_.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lbl_.setForeground(Color.GRAY);
			}
			public void mouseExited(MouseEvent e) {
				lbl_.setForeground(Color.BLACK);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		
		ImageIcon global1 = new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\global1.png");
		ImageIcon global2 = new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\global2.png");
		
		JLabel lblGlobal = new JLabel(global1);
		lblGlobal.setFont(new Font("Gill Sans MT", Font.BOLD | Font.ITALIC, 16));
		lblGlobal.setVerticalTextPosition(SwingConstants.BOTTOM);	
		lblGlobal.setHorizontalTextPosition(SwingConstants.CENTER); 
		lblGlobal.setText("GLOBAL");
		lblGlobal.setForeground(Color.BLACK);
		lblGlobal.setBounds(10, 102, 85, 85);
		contentPane.add(lblGlobal);
		lblGlobal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblGlobal.setIcon(global2);
				lblGlobal.setForeground(Color.DARK_GRAY);
			}
			public void mouseExited(MouseEvent e) {
				lblGlobal.setIcon(global1);
				lblGlobal.setForeground(Color.BLACK);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					newLocation("Global");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MainPage page;
				try {
					page = new MainPage();
					page.setVisible(true);
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		
		
		JLabel lblInfo = new JLabel("--> For now, we only serve in these countries.");
		lblInfo.setForeground(Color.BLACK);
		lblInfo.setBounds(10, 0, 281, 32);
		contentPane.add(lblInfo);
		lblInfo.setFont(new Font("Gill Sans MT", Font.BOLD | Font.ITALIC, 13));
		
		JLabel lblGermany = new JLabel("GERMANY");
		lblGermany.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblGermany.setForeground(Color.GRAY);
			}
			public void mouseExited(MouseEvent e) {
				lblGermany.setForeground(Color.BLACK);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					newLocation("Germany");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MainPage page;
				try {
					page = new MainPage();
					page.setVisible(true);
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		lblGermany.setFont(new Font("Gill Sans MT", Font.BOLD, 13));
		lblGermany.setBounds(224, 306, 116, 32);
		contentPane.add(lblGermany);
		
		JLabel lblFrance = new JLabel("FRANCE");
		lblFrance.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblFrance.setForeground(Color.GRAY);
			}
			public void mouseExited(MouseEvent e) {
				lblFrance.setForeground(Color.BLACK);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					newLocation("France");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MainPage page;
				try {
					page = new MainPage();
					page.setVisible(true);
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		lblFrance.setFont(new Font("Gill Sans MT", Font.BOLD, 18));
		lblFrance.setBounds(137, 349, 85, 47);
		contentPane.add(lblFrance);
		
		JLabel lblTurkey = new JLabel("TURKEY");
		lblTurkey.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblTurkey.setForeground(Color.GRAY);
			}
			public void mouseExited(MouseEvent e) {
				lblTurkey.setForeground(Color.BLACK);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					newLocation("Turkey");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MainPage page;
				try {
					page = new MainPage();
					page.setVisible(true);
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		lblTurkey.setHorizontalAlignment(SwingConstants.CENTER);
		lblTurkey.setFont(new Font("Gill Sans MT", Font.BOLD, 30));
		lblTurkey.setBounds(449, 438, 158, 67);
		contentPane.add(lblTurkey);
		lbl_.setBounds(630, 7, 21, 21);
		contentPane.add(lbl_);
		lbl_.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_.setForeground(Color.BLACK);
		lbl_.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		
		
		
		JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblX.setForeground(Color.GRAY);
			}
			public void mouseExited(MouseEvent e) {
				lblX.setForeground(Color.BLACK);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		lblX.setBounds(669, 7, 21, 21);
		contentPane.add(lblX);
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setForeground(Color.BLACK);
		lblX.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblBackground = new JLabel(new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\europe.png"));
		lblBackground.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblBackground.setBounds(0, 0, 700, 569);
		contentPane.add(lblBackground);
		
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
		panel.setBounds(0, 0, 700, 31);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(700, 0, 0, 569);
		contentPane.add(lblNewLabel);
		setUndecorated(true);
		
		
	}
	
	public void newLocation(String location) throws SQLException {
		dbConnection con = new dbConnection();
		login.loggedUser.region = location;
		String query = "update finder.user set region = ? where id = ?";
		PreparedStatement ps = con.getConnection().prepareStatement(query);
		ps.setString(1, location);
		ps.setInt(2, login.loggedUser.id);															
		ps.executeUpdate();
	}
}
