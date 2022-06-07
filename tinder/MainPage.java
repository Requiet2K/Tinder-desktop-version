package tinder;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.swing.JSeparator;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.AlphaComposite;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainPage extends JFrame {

	private JPanel contentPane;
	int mouseX, mouseY;
	boolean check = true;
	static ResultSet rs;
	Image randomImage;
	JLabel lblRandomPhoto;
	static dbConnection con;
	JLabel lblNameAge;
	static JLabel lblLoggedPhoto;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
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
	 * @throws IOException 
	 */
	public MainPage() throws SQLException, IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 1017, 597);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		
			
		con = new dbConnection();
		
		JButton btnChats = new JButton("Chats & Matches");
		btnChats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Chats chat;
				try {
					chat = new Chats();
					chat.setVisible(true);
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		
		JButton btnSettings = new JButton("Settings");
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Settings set;
				try {
					set = new Settings();
					set.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JButton btnSettings_1 = new JButton("Logout");
		btnSettings_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login login;
				try {
					login = new login();
					login.setVisible(true);
					dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSettings_1.setForeground(Color.WHITE);
		btnSettings_1.setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
		btnSettings_1.setFocusPainted(false);
		btnSettings_1.setBackground(Color.BLACK);
		btnSettings_1.setBounds(110, 507, 78, 23);
		contentPane.add(btnSettings_1);
		btnSettings.setForeground(Color.WHITE);
		btnSettings.setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
		btnSettings.setFocusPainted(false);
		btnSettings.setBackground(Color.BLACK);
		btnSettings.setBounds(88, 453, 121, 23);
		contentPane.add(btnSettings);
		btnChats.setHorizontalAlignment(SwingConstants.LEFT);
		btnChats.setForeground(Color.WHITE);
		btnChats.setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
		btnChats.setFocusPainted(false);
		btnChats.setBackground(Color.BLACK);
		btnChats.setBounds(88, 397, 121, 23);
		contentPane.add(btnChats);
		
		JLabel labelRegion = new JLabel(con.getValues(login.loggedUser.id).region);								
		labelRegion.setHorizontalAlignment(SwingConstants.LEFT);
		labelRegion.setForeground(Color.BLACK);
		labelRegion.setFont(new Font("Gill Sans MT", Font.PLAIN, 19));
		labelRegion.setBounds(134, 283, 78, 37);
		contentPane.add(labelRegion);
		
		
				
		JLabel lblLocationPng = new JLabel();
		lblLocationPng.setForeground(Color.BLACK);
		lblLocationPng.setFont(new Font("Gill Sans MT", Font.PLAIN, 18));
		lblLocationPng.setHorizontalAlignment(SwingConstants.LEFT);
		lblLocationPng.setBounds(88, 283, 41, 37);
		contentPane.add(lblLocationPng);
		
		File location = new File("C:\\\\Users\\\\90533\\\\eclipse-workspace\\\\afterVize\\\\images\\\\location.png");
		Image locationI = ImageIO.read(location);
		Image sizedLocation = locationI.getScaledInstance(lblLocationPng.getWidth(),lblLocationPng.getHeight(),Image.SCALE_SMOOTH);
		lblLocationPng.setIcon(new ImageIcon(sizedLocation));
		
		
		JButton btnNewButton = new JButton("Switch Location");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Location loc = new Location();
				loc.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setFont(new Font("Gill Sans MT", Font.PLAIN, 12));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setBounds(88, 341, 121, 23);
		contentPane.add(btnNewButton);
		btnNewButton.setFocusPainted(false);
		
		lblLoggedPhoto = new JLabel("");
		lblLoggedPhoto.setBounds(70, 48, 156, 156);
		contentPane.add(lblLoggedPhoto);
		
		Image loggedImage = con.getPhoto(login.loggedUser.id).getScaledInstance(lblLoggedPhoto.getWidth(),lblLoggedPhoto.getHeight(),Image.SCALE_SMOOTH);	
		
		ImageIcon loggedIcon = circularPhoto(loggedImage);
		
		lblLoggedPhoto.setIcon(loggedIcon);
		
		JLabel lblLoginInfos = new JLabel(login.loggedUser.name+" "+login.loggedUser.surname+"  |  "+login.loggedUser.age);
		lblLoginInfos.setForeground(Color.BLACK);
		lblLoginInfos.setFont(new Font("Gill Sans MT", Font.PLAIN, 18));
		lblLoginInfos.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginInfos.setBounds(72, 216, 156, 29);
		contentPane.add(lblLoginInfos);
		
		JLabel lblMinimize = new JLabel("-");
		lblMinimize.setBounds(962, 10, 21, 21);
		contentPane.add(lblMinimize);
		lblMinimize.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinimize.setForeground(Color.WHITE);
		lblMinimize.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		lblMinimize.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblMinimize.setForeground(Color.BLACK);
			
			}
			public void mouseExited(MouseEvent e) {
				lblMinimize.setForeground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
		
		JLabel lblClose = new JLabel("X");
		lblClose.setBounds(993, 11, 21, 21);
		contentPane.add(lblClose);
		lblClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblClose.setForeground(Color.BLACK);
			
			}
			public void mouseExited(MouseEvent e) {
				lblClose.setForeground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblClose.setForeground(Color.WHITE);
		lblClose.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblClose.setHorizontalAlignment(SwingConstants.CENTER);
		
	    lblRandomPhoto = new JLabel("");
	    lblRandomPhoto.setBounds(535, 111, 244, 244);
	    contentPane.add(lblRandomPhoto);

	    String randomPhotoQuery;
	    
	    if(!login.loggedUser.region.equals("Global")) {
			randomPhotoQuery = "select * from finder.user where id !="+login.loggedUser.id+" and gender !='"+login.loggedUser.gender+"' and region='"+login.loggedUser.region+"' order by RAND()";
	    }
	    else {
	        randomPhotoQuery = "select * from finder.user where id !="+login.loggedUser.id+" and gender !='"+login.loggedUser.gender+"' order by RAND()";
	    }

		Statement st = con.getConnection().createStatement();
	    rs = st.executeQuery(randomPhotoQuery);
	    
		lblNameAge = new JLabel("");
		lblNameAge.setForeground(Color.BLACK);
		lblNameAge.setHorizontalAlignment(SwingConstants.CENTER);
		lblNameAge.setFont(new Font("Gill Sans MT", Font.PLAIN, 24));
		lblNameAge.setBounds(545, 366, 234, 38);
		contentPane.add(lblNameAge);
	    
	    likeSkip();  
	    
	    ImageIcon heart1 = new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\blackHeart.png");
	    ImageIcon heart2 = new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\redHeart.png");		
	    
	    JLabel lblLike = new JLabel(heart1);
	    lblLike.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseEntered(MouseEvent e) {
	    		lblLike.setIcon(heart2);
	    	}
	    	@Override
	    	public void mouseExited(MouseEvent e) {
	    		lblLike.setIcon(heart1);
	    	}
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		
	    		String likedQuery = "insert into finder.liked (user_id,liked_id) values(?,?)";
	    		PreparedStatement psLike;
	    		
	    		String likedBefore = "select * from finder.liked";
	    		   		
				try {
					
					Statement stlikeCheck = con.getConnection().createStatement();
		    		ResultSet rslikeCheck = stlikeCheck.executeQuery(likedBefore);		    				
		    				    			
		    			if (check) {
						
						psLike = con.getConnection().prepareStatement(likedQuery);
						psLike.setInt(1, login.loggedUser.id);
						psLike.setInt(2, rs.getInt(1));
						psLike.executeUpdate();
						
						if(matchCheck()) {
							MatchScreen screen = new MatchScreen();
							screen.setVisible(true);
							chatRegister();
						}

						likeSkip();
					}		    					    										
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

				
	    		
	    	}
	    });
	    lblLike.setBounds(596, 409, 50, 50);
	    contentPane.add(lblLike);	    
	    
	    ImageIcon x1 = new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\next1.png");
	    ImageIcon x2 = new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\next2.png");	
	    
	    JLabel lblX = new JLabel(x1);
		lblX.setBounds(676, 406, 50, 61);
		contentPane.add(lblX);
		lblX.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseEntered(MouseEvent e) {
	    		lblX.setIcon(x2);
	    	}
	    	@Override
	    	public void mouseExited(MouseEvent e) {
	    		lblX.setIcon(x1);
	    	}
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					
					if(check) {
						likeSkip();
					}
					

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	    });

		
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
				setLocation(getX() + e.getX() - mouseX , getY() + e.getY() - mouseY);
			}
		});
		
		JLabel lblBack = new JLabel(new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\backgroundMain.png"));
		lblBack.setForeground(Color.WHITE);
		lblBack.setText("Switch Location");
		lblBack.setBounds(0, 0, 1024, 600);
		contentPane.add(lblBack);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 1024, 37);
		contentPane.add(panel);
		panel.setLayout(null);
		
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
	
	public void chatRegister() throws SQLException {
		String query = "insert into finder.messages (sender_id,receiver_id,messages) values (?,?,?)";
		PreparedStatement ps = con.getConnection().prepareStatement(query);
		ps.setInt(1, login.loggedUser.id);
		ps.setInt(2, rs.getInt(1));
		ps.setString(3, "");
		ps.executeUpdate();
		String query2 = "insert into finder.messages (sender_id,receiver_id,messages) values (?,?,?)";
		PreparedStatement ps2 = con.getConnection().prepareStatement(query);
		ps2.setInt(1, rs.getInt(1));
		ps2.setInt(2, login.loggedUser.id);
		ps2.setString(3, "");
		ps2.executeUpdate();
	}
	
	public void likeSkip() throws SQLException {
		
	    while(true) {
	    	
	    	if(check) {
	    		
		    	if(rs.next()) {
		    		
		    		if(!likeCheck(login.loggedUser.id,rs.getInt(1))) {
		    			
		    			randomImage = con.getPhoto(rs.getInt(1)).getScaledInstance(lblRandomPhoto.getWidth(),lblRandomPhoto.getHeight(),Image.SCALE_SMOOTH);		
		    			ImageIcon randomIcon = new ImageIcon(randomImage);
		    			lblRandomPhoto.setIcon(randomIcon);
		    			lblNameAge.setText(rs.getString(2)+" "+rs.getInt(4));
				 	    break;
				 	    
		    		}
		    		
			    }
			    else {

			    		lblRandomPhoto.setHorizontalAlignment(SwingConstants.CENTER);
			    		lblRandomPhoto.setText("There's no one left around you!");
			    		lblRandomPhoto.setForeground(Color.BLACK);
			    		lblRandomPhoto.setIcon(null);
			    		lblNameAge.setText("");
			    		check = false;
			    		break;
			    }
	    		
	    	}

	    	

	    }
	}
	
	public boolean likeCheck(int logged_id,int liked) throws SQLException {
		dbConnection con = new dbConnection();
		String likecheckQuery = "select * from finder.liked";
		Statement st = con.getConnection().createStatement();
		ResultSet rs = st.executeQuery(likecheckQuery);
		while(rs.next()) {
			if(rs.getInt(2) == logged_id && rs.getInt(3) == liked) {
				return true;
			}
		}
		return false;
	}
	
	public boolean matchCheck() throws SQLException {
		
		String matchCheck = "select count(*) from finder.liked where (user_id = "+login.loggedUser.id+" and liked_id = "+rs.getInt(1)+") or (user_id = "+rs.getInt(1)+" and liked_id = "+login.loggedUser.id+")";
		Statement stMatchCheck = con.getConnection().createStatement();
		ResultSet rsMatchCheck = stMatchCheck.executeQuery(matchCheck);
		rsMatchCheck.next();
		
		int res = rsMatchCheck.getInt(1);

		if(res==2) {
			return true;
		}
		
		return false;
		
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
