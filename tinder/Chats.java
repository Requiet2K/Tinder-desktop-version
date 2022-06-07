package tinder;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.Image;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.UIManager;
public class Chats extends JFrame {

	private static final int TYPE_INT_RGB = 0;
	private JPanel contentPane;
	dbConnection con = new dbConnection();
	private JTextField textField;
	int clickedId, mouseX, mouseY;
	String messages="";
	boolean chatOpen=false;
	JTextArea textArea;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chats frame = new Chats();
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
	public Chats() throws SQLException, IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 200, 513, 581);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		
		ImageIcon send1 = new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\send1.png");
		ImageIcon send2 = new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\send2.png");
		ImageIcon send3 = new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\send3.png");
		
		JLabel lblExit = new JLabel("X");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				MainPage main;
				try {
					main = new MainPage();
					main.setVisible(true);
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblExit.setForeground(Color.GRAY);
			}
			public void mouseExited(MouseEvent e) {
				lblExit.setForeground(Color.BLACK);
			}
		});
		
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
			public void mouseExited(MouseEvent e) {
				lblMinimize.setForeground(Color.BLACK);
			}
		});
		lblMinimize.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinimize.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		lblMinimize.setBounds(456, 3, 26, 25);
		contentPane.add(lblMinimize);
		lblExit.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setBounds(487, 3, 26, 25);
		contentPane.add(lblExit);
		
		JLabel lblChattedName = new JLabel("");
		lblChattedName.setFont(new Font("Segoe UI Light", Font.BOLD, 12));
		lblChattedName.setForeground(new Color(101,181,216));
		lblChattedName.setBounds(38, 211, 78, 14);
		contentPane.add(lblChattedName);
		
		JLabel lblPicChat = new JLabel();
		lblPicChat.setBounds(6, 204, 26, 26);
		contentPane.add(lblPicChat);
		lblPicChat.setVisible(false);
		
		ImageIcon labelX = new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\labelX.png");
		ImageIcon labelX2 = new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\labelX2.png");
		
		JLabel lblCloseChat = new JLabel(labelX);
		lblCloseChat.setBounds(119, 204, 24, 27);
		contentPane.add(lblCloseChat);
		lblCloseChat.setVisible(false);
		
		
		JLabel lblChatInfo = new JLabel(new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\infoChat.png"));
		lblChatInfo.setBounds(0, 200, 143, 36);
		contentPane.add(lblChatInfo);
		lblChatInfo.setVisible(false);
		
		
		lblCloseChat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				lblChatInfo.setVisible(false);
				textField.setEnabled(false);
				lblCloseChat.setVisible(false);
				lblPicChat.setIcon(null);
				chatOpen = false;
				lblChattedName.setText("");
				textArea.setText("");
				messages="";
				textField.setText("Click the picture of the person you want to send a message to");
				textField.setForeground(Color.GRAY);
				textField.setHorizontalAlignment(SwingConstants.CENTER);
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblCloseChat.setIcon(labelX2);
			}
			public void mouseExited(MouseEvent e) {
				lblCloseChat.setIcon(labelX);
			}
		});
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if((textField.getText().equals("Click the picture of the person you want to send a message to") || textField.getText().equals("Type here!") ) && chatOpen==true) {
					textField.setText("");
					textField.setForeground(Color.WHITE);
					textField.setHorizontalAlignment(SwingConstants.LEADING);
					}
			}
		});
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if((textField.getText().equals("Click the picture of the person you want to send a message to") || textField.getText().equals("Type here!") ) && chatOpen==true) {
				textField.setText("");
				textField.setForeground(Color.WHITE);
				textField.setHorizontalAlignment(SwingConstants.LEADING);
				}
			}
			public void focusLost(FocusEvent e) {
				if((textField.getText().equals("") || textField.getText().equals("Click the picture of the person you want to send a message to"))) {
					if(!chatOpen) {
						textField.setText("Click the picture of the person you want to send a message to");
					}
					else {
						textField.setText("Type here!");
					}
					
					textField.setForeground(Color.GRAY);
					textField.setHorizontalAlignment(SwingConstants.CENTER);
				}
			}
		});
		textField.setBorder(null);
		textField.setBackground(new Color(27,28,32));

		JButton btnNewButton = new JButton(send1);

		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setIcon(send3);
			}
			public void mouseExited(MouseEvent e) {
				if(textField.getText().length()>0) btnNewButton.setIcon(send2);
				else btnNewButton.setIcon(send1);
			}
		});
		
				btnNewButton.setBounds(460, 528, 43, 40);
				contentPane.add(btnNewButton);
		textField.setText("Click the picture of the person you want to send a message to");
		textField.setForeground(Color.GRAY);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(10, 529, 440, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		

		
		JLabel lblBorder = new JLabel(new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\text.png"));
		lblBorder.setBounds(0, 518, 516, 63);
		contentPane.add(lblBorder);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 32, 516, 168);
		contentPane.add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(238,238,238));
		scrollPane.setViewportView(panel);
		
		Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
		


		
		ArrayList<Integer> matches = matchCount();
		
			for(int i = 0 ; i < matches.size() ; i++) {
				
				JPanel panel2 = new JPanel(new GridLayout(2, 1));
				JLabel Xbutton = new JLabel(new ImageIcon("C:\\Users\\90533\\eclipse-workspace\\afterVize\\images\\XIconn.png"));
				Xbutton.setVisible(false);
				
				Image newImage = con.getPhoto(matches.get(i)).getScaledInstance(75, 75, Image.SCALE_DEFAULT);
				JLabel tinderLogo = new JLabel(new ImageIcon(newImage));
				tinderLogo.setToolTipText(Integer.toString(matches.get(i)));
				
				tinderLogo.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
						Xbutton.setVisible(true);

						tinderLogo.setBorder(border);
					}
					public void mouseExited(MouseEvent e) {
						tinderLogo.setBorder(null);
						Xbutton.setVisible(false);
					}
					public void mouseClicked(MouseEvent e) {
						textField.setEnabled(true);
						chatOpen = true;
						textField.setText("Type here!");
						textField.setForeground(Color.GRAY);
						textField.setHorizontalAlignment(SwingConstants.CENTER);
						textArea.setText("");
						lblChatInfo.setVisible(true);
						messages="";
						lblPicChat.setVisible(true);
						lblCloseChat.setVisible(true);
						clickedId = Integer.parseInt(tinderLogo.getToolTipText());
						Image image;
						try {
							bringMessages();
							messages+=textArea.getText();
							lblChattedName.setText(con.getValues(clickedId).name+" "+con.getValues(clickedId).surname);
							image = con.getPhoto(clickedId).getScaledInstance(lblPicChat.getWidth(), lblPicChat.getHeight(), Image.SCALE_DEFAULT);
							lblPicChat.setIcon(new ImageIcon(image));
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					});
				
					Xbutton.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							Xbutton.setVisible(true);
							}
						public void mouseExited(MouseEvent e) {
							Xbutton.setVisible(false);
	                	
							}
						public void mouseClicked(MouseEvent e) {
							clickedId = Integer.parseInt(tinderLogo.getToolTipText());
							int decision = JOptionPane.showConfirmDialog(contentPane,"Remove from your matches?");
							if(decision == 1 && decision ==2) {
								return;
							}
							else {
								String query1 = "delete from finder.messages where sender_id = "+login.loggedUser.id+" and receiver_id ="+clickedId;
								String query2 = "delete from finder.messages where sender_id = "+clickedId+" and receiver_id ="+login.loggedUser.id;
								String query3 = "delete from finder.liked where user_id ="+login.loggedUser.id+" and liked_id ="+clickedId;
								Statement st1,st2,st3;
								try {
									st1 = con.getConnection().createStatement();
									st1.executeUpdate(query1);
									st2 = con.getConnection().createStatement();
									st2.executeUpdate(query2);
									st3 = con.getConnection().createStatement();
									st3.executeUpdate(query3);
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
							}
							panel.remove(panel2);
							panel.revalidate();
							panel.repaint();
							lblChatInfo.setVisible(false);
							textField.setEnabled(false);
							lblCloseChat.setVisible(false);
							lblPicChat.setIcon(null);
							chatOpen = false;
							lblChattedName.setText("");
							
							textArea.setText("");
							messages="";
							textField.setText("Click the picture of the person you want to send a message to");
							textField.setForeground(Color.GRAY);
							textField.setHorizontalAlignment(SwingConstants.CENTER);
							}
						
						});
					panel2.setBorder(null);
					
					panel2.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							
		                    Xbutton.setVisible(true);
		                	}
		                public void mouseExited(MouseEvent e) {
		                	
		                	Xbutton.setVisible(false);
		                	}	
		                public void mouseClicked(MouseEvent e) {
		                	
		                	}
		            	});
				
				panel2.add(tinderLogo);
				panel2.add(Xbutton);
				
				panel.add(panel2);

			}
		
			
			JPanel panel_1 = new JPanel();
			panel_1.setBackground(Color.LIGHT_GRAY);
			panel_1.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					setLocation(getX() + e.getX() - mouseX , getY() + e.getY() - mouseY);	
				}
			});
			panel_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					mouseX = e.getX();
					mouseY = e.getY();
				}
			});
			panel_1.setBounds(0, 0, 516, 30);
			contentPane.add(panel_1);
			panel_1.setLayout(null);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(0, 236, 513, 282);
			scrollPane_1.setBorder(border);
			contentPane.add(scrollPane_1);
			
			textArea = new JTextArea();
			scrollPane_1.setViewportView(textArea);
			textArea.setForeground(Color.WHITE);
			textArea.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
			textArea.setBackground(new Color(32,34,40));
			textArea.setEditable(false);
			
			textField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if(!textField.getText().equals("Click the picture of the person you want to send a message to")) {
						if(textField.getText().length()>0) {
							btnNewButton.setIcon(send2);
						}
						else {
							btnNewButton.setIcon(send1);
						}
					}
				}
				@Override
				public void keyPressed(KeyEvent e) {
					
					if(textField.getText().equals("Click the picture of the person you want to send a message to")) {
						textField.setText("");
					}
					if(textField.getText().equals("Type here!")) {
						textField.setForeground(Color.WHITE);
						textField.setHorizontalAlignment(SwingConstants.LEADING);
						textField.setText("");
					}
			
					if(e.getKeyCode() == KeyEvent.VK_ENTER) {
						if(textField.getText().length()==0) {
							return;
						}
						messages+=login.loggedUser.name+": "+textField.getText()+"\n";
						textArea.setText(messages);
						textField.setText("");
						try {
							clearBeforeMessages();
							updateChat();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					   }
				}
			});
			
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!chatOpen) {
						return;
					}
					if(textField.getText().equals("Type here!")) {
						return;
					}
					if(textField.getText().length()==0) {
						return;
					}
					messages+=login.loggedUser.name+": "+textField.getText()+"\n";
					textArea.setText(messages);
					textField.setText("");
					try {
						clearBeforeMessages();
						updateChat();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			
	}	
	
	public void bringMessages() throws SQLException {
		String query = "select * from finder.messages where sender_id ="+login.loggedUser.id+" and receiver_id="+clickedId;
		Statement st = con.getConnection().createStatement();
		ResultSet rs = st.executeQuery(query);
		rs.next();
		textArea.setText(rs.getString(4));
	}
	
	public void clearBeforeMessages() throws SQLException {
		String query = "update finder.messages set messages = ? where sender_id ="+login.loggedUser.id+" and receiver_id="+clickedId;
		PreparedStatement ps = con.getConnection().prepareStatement(query);
		ps.setString(1, "");
		ps.executeUpdate();
	}
	
	public void updateChat() throws SQLException {
		String query = "update finder.messages set messages = ? where sender_id="+login.loggedUser.id+" and receiver_id="+clickedId;
		PreparedStatement ps = con.getConnection().prepareStatement(query);
		ps.setString(1, textArea.getText());
		ps.executeUpdate();
		
		String query2 = "update finder.messages set messages = ? where sender_id="+clickedId+" and receiver_id="+login.loggedUser.id;
		PreparedStatement ps2 = con.getConnection().prepareStatement(query2);
		ps2.setString(1, textArea.getText());
		ps2.executeUpdate();
	}
	
	
	public ArrayList<Integer> matchCount() throws SQLException {
		
			ArrayList<Integer> matchers = new ArrayList<Integer>();

			Statement st1 = con.getConnection().createStatement();
			Statement st2 = con.getConnection().createStatement();

			
			ResultSet rs1 = st1.executeQuery("select * from finder.liked where user_id="+login.loggedUser.id);
			
			while(rs1.next()) {
				
				ResultSet rs2 = st2.executeQuery("select * from finder.liked where liked_id="+login.loggedUser.id);		
				
				while(rs2.next()) {
					
					
					if(rs1.getInt(3)==rs2.getInt(2)) {
						
						matchers.add(rs2.getInt(2));
						
					}
					
				}
				
			}		
			return matchers;
		}
}