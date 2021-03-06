package fixed.assets;

import java.awt.EventQueue;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

public class EditFixedAssets1 {

	JFrame frame;
	private JTable tbpat;
	String head2[] ={ "Id ","Description","Serial Number","Catagory","Acquisition Date", "Purchase Order No ","Depreciation %","Cost","Accumulated Depreciation","Net Book Value" ,"Date last calculated"," Location ", "Manager", "Guarantee Expiry Date","Disposed" };
	DefaultTableModel dtm;
	JScrollPane jsp;
	ResultSet rs1, rs2;
	Connection con;
	private JButton edit;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
   		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditFixedAssets1 window = new EditFixedAssets1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public void createCon()
	{
		try
		{
		    Class.forName("com.mysql.jdbc.Driver");	
		    con =DriverManager.getConnection("jdbc:mysql://localhost:3306/fixed_assest_db","root","");       
		    Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY );
   		                                          
    		}
		catch (Exception e)
		{ System.out.println("Error in Con :" + e); }
	}

	public void showRecords()
	{
		dtm.setRowCount(0);
		System.gc();
		try
		{
			createCon();
                 
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
			ResultSet.CONCUR_UPDATABLE);
			ResultSet r1;
			r1 = st.executeQuery("select * from fixed_asset_details");
			String[] t = new String[20];
			//System.out.println("hi- "+r1.getString("id"));
			//System.out.println("hi1- "+r1.getString(0));
			
		
				while (r1.next())
				{
					t[0] = r1.getString(1);
	                t[1]=  r1.getString(2);
					t[2] = r1.getString(3);
					t[3] = r1.getString(4);
					t[4] = r1.getString(5);			
					t[5] = r1.getString(6);			
					t[6] = r1.getString(7);
					t[7] = r1.getString(8);
					t[8] = r1.getString(9);
					t[9] = r1.getString(10);
					t[10] = r1.getString(11);
					t[11] = r1.getString(12);
					t[12] = r1.getString(13);
					t[13] = r1.getString(14);
					t[14] = r1.getString(15);					
					
					dtm.addRow(t);
				}
			}
		catch (Exception e)
		{
			System.out.println("Error :" + e); 
			e.printStackTrace();
		}
	}

	public EditFixedAssets1() {
		initialize();
		createCon();
		dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(head2);
		tbpat = new JTable(dtm);
		jsp = new JScrollPane(tbpat);
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jsp.setBounds(21, 26, 666, 334);
		frame.getContentPane().add(jsp);
		tbpat.setRowHeight(20);
		
		JButton Cancel = new JButton("Cancel");
		Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{	
				HomeWindow h=new HomeWindow();
				h.frame.setVisible(true);
				frame.hide(); 
			}
		});
		Cancel.setBounds(385, 381, 89, 23);
		frame.getContentPane().add(Cancel);
		
		edit = new JButton("Edit");
		edit.setBounds(231, 381, 89, 23);
		frame.getContentPane().add(edit);
		showRecords();
		
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
						
						try
						{
		                    String id=(String)dtm.getValueAt(tbpat.getSelectedRow(), 0);
							
		                    EditFixedAssets2 editWindow2 = new EditFixedAssets2();
		                    editWindow2.frame.setVisible(true);
							
		                    editWindow2.Assign(id);
		                    frame.hide(); 
						}
						catch(Exception ea)
						{
							JOptionPane.showMessageDialog(null, "Select Valid Record to Update!","No Record Selected!", JOptionPane.ERROR_MESSAGE);
							ea.printStackTrace();
						}
			}
		});


	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(150, 130, 724, 453);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}
}
