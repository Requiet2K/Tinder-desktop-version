package tinder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class adminPanel extends JFrame {

	private JPanel contentPane;
	int mouseX,mouseY;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminPanel frame = new adminPanel();
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
	public adminPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 414);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true);
		
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
		panel.setBackground(new Color(50,50,50));
		panel.setBounds(0, 0, 693, 39);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel.setForeground(Color.BLACK);
			}
			public void mouseExited(MouseEvent e) {
				lblNewLabel.setForeground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel.setBounds(660, 8, 23, 25);
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("-");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(627, 11, 23, 25);
		panel.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 673, 287);
		contentPane.add(scrollPane);
		
		DefaultTableModel tableModel = new DefaultTableModel();				
		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Get Users List");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tableModel.setRowCount(0);
				
				try {
					dbConnection con = new dbConnection();
					ResultSet rs = con.getResultSet();

					int columnCount = rs.getMetaData().getColumnCount();
					String[] column_names = new String[columnCount];
					
					for(int i = 1 ; i <= columnCount ; i++) {
						column_names[i-1] = rs.getMetaData().getColumnName(i);
					}
					
					tableModel.setColumnIdentifiers(column_names);
					
					while(rs.next()) {
						Object[] row = new Object[columnCount];
						for(int i = 1 ; i <= columnCount ; i++) {
							row[i-1] = rs.getObject(i);
						}
						tableModel.addRow(row);
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Lucida Sans", Font.BOLD, 11));
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setBounds(10, 348, 139, 23);
		contentPane.add(btnNewButton);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					int dialogResult = JOptionPane.showConfirmDialog (contentPane, "Are you sure?");
					if(dialogResult != JOptionPane.YES_OPTION){
						return;
					}
				
					dbConnection con = new dbConnection();
					int[] deleteId = table.getSelectedRows();
					
					for(int i = 0 ; i < deleteId.length ; i++) {
						
						int remove = (int) table.getValueAt(deleteId[i]-i, 0);
						
						
						try {
							
						
							String query1 = "delete from finder.messages where sender_id = "+remove+" or receiver_id ="+remove;
							String query2 = "delete from finder.liked where user_id ="+remove+" or liked_id ="+remove;
							Statement st1,st2;
							
							st1 = con.getConnection().createStatement();
							st1.executeUpdate(query1);
							st2 = con.getConnection().createStatement();
							st2.executeUpdate(query2);
						
							con.deleteUser(remove);
							tableModel.removeRow(deleteId[i]-i);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
					
					
					}
					
				
				
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		btnDelete.setBackground(Color.DARK_GRAY);
		btnDelete.setBounds(573, 348, 110, 23);
		contentPane.add(btnDelete);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		btnNewButton_1.setBackground(Color.DARK_GRAY);
		btnNewButton_1.setBounds(573, 382, 110, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				User user = new User();
				
				if(table.getSelectedRowCount() == 1) {
					
					user.id = Integer.parseInt(tableModel.getValueAt(table.getSelectedRow(), 0).toString());
					user.name = tableModel.getValueAt(table.getSelectedRow(), 1).toString();
					user.surname = tableModel.getValueAt(table.getSelectedRow(), 2).toString();
					user.age = Integer.parseInt(tableModel.getValueAt(table.getSelectedRow(), 3).toString());
					user.mail = tableModel.getValueAt(table.getSelectedRow(), 4).toString();
					user.phone = tableModel.getValueAt(table.getSelectedRow(), 5).toString();
					user.region = tableModel.getValueAt(table.getSelectedRow(), 6).toString();
					user.gender = tableModel.getValueAt(table.getSelectedRow(), 7).toString();
					user.password = tableModel.getValueAt(table.getSelectedRow(), 8).toString();
					
				}
				try {
					
					dbConnection con = new dbConnection();
					con.Update(user);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("Lucida Sans", Font.BOLD, 12));
		btnUpdate.setBackground(Color.DARK_GRAY);
		btnUpdate.setBounds(450, 348, 110, 23);
		contentPane.add(btnUpdate);

		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_1.setForeground(Color.BLACK);
			}
			public void mouseExited(MouseEvent e) {
				lblNewLabel_1.setForeground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);
			}
		});
	}
}
