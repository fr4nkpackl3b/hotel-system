/**
 * @(#)CheckIn.java
 *
 *
 * @author 
 * @version 1.00 2013/10/4
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;

public class CheckIn extends JFrame implements ActionListener
{

	String month[] = {"","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	String day[] = {"","1","2","3","4","5","6","7","8","9","10",
					"11","12","13","14","15","16","17","18","19","20",
					"21","22","23","24","25","26","27","28","29","30","31"};
	String size[] = {"","Single","Double","Family"};
	
	JComboBox cm = new JComboBox(month);
	JComboBox cd = new JComboBox(day);
	JComboBox cs = new JComboBox(size);
	
	JLabel lab1= new JLabel ("Room Number");
	JLabel lab2= new JLabel ("Last Name: ");
	JLabel lab3= new JLabel ("Middle Initial:");
	JLabel lab4= new JLabel ("First Name");
	JLabel lab5= new JLabel ("Contact No.");
	JLabel lab6= new JLabel ("Address:");
	JLabel lab7= new JLabel ("Day-In:");
	JLabel lab8= new JLabel ("Month:");
	JLabel lab9= new JLabel ("Day:");
	JLabel lab10= new JLabel ("Year");
	JLabel lab11= new JLabel ("Room Size");
	JLabel lab12= new JLabel ("No. of Days");
	JLabel lab13= new JLabel ("Check-In");
	JLabel lab14= new JLabel ("Down_Pay");
	
	JButton bot1= new JButton ("Ok");
	JButton bot2= new JButton ("Clear");
	JButton bot3= new JButton ("Main");
	JButton botS= new JButton ("Search");
	JButton botDel= new JButton ("Delete");
		
	JTextField txt1= new JTextField ();
	JTextField txt2= new JTextField ();
	JTextField txt3= new JTextField ();
	JTextField txt4= new JTextField ();
	JTextField txt5= new JTextField ();
	JTextField txt6= new JTextField ();
	JTextField txt7= new JTextField ();
	JTextField txt8= new JTextField ();	
	JTextField txt9= new JTextField ("0");	
//	JTextField txt10= new JTextField ();
	
	String Room_No = "";
	String Last_Name = "";
	String Middle_Initial = "";
	String First_Name = "";
	String Contact_No  = "";
	String Address   = ""; 
	String Month = "";
	String Day = "";
	String Year = "";
	String Room_Size = "";
	String No_Days  = "";
	String Down = "";
	
	String dialogmessage;
    String dialogs;
    int dialogtype = JOptionPane.PLAIN_MESSAGE;
    public static int record;
	static Connection cn;
	static Statement st;
	static PreparedStatement ps;
	static ResultSet rs;
	Container con = getContentPane();
	
	ImageIcon image = new ImageIcon("check.jpg");
	JLabel back = new JLabel(image,JLabel.CENTER);
	
	Font font = new Font("Times new Roman", Font.BOLD, 20);

    public CheckIn() 
    {
    	con.setLayout(null);
    	con.add(lab13);
    	lab13.setBounds(50,50,300,30);
    	lab13.setFont(font);
    	con.add(lab1);
    	lab1.setBounds(50,100,300,30);
    	con.add(lab2);
    	lab2.setBounds(50,150,300,30);
    	con.add(lab3);
    	lab3.setBounds(50,200,300,30);
    	con.add(lab4);
    	lab4.setBounds(50,250,300,30);
    	con.add(lab5);
    	lab5.setBounds(50,300,300,30);
    	con.add(lab6);
    	lab6.setBounds(50,350,300,30);
    	con.add(lab7);
    	lab7.setBounds(50,400,300,30);
    	con.add(lab8);
    	lab8.setBounds(150,450,300,30);
    	con.add(lab9);
    	lab9.setBounds(200,450,300,30);    	
    	con.add(lab10);
    	lab10.setBounds(250,450,300,30);
    	con.add(lab11);
    	lab11.setBounds(50,500,300,30);    	
    	con.add(lab12);
    	lab12.setBounds(50,550,300,30);
    	con.add(lab14);
    	lab14.setBounds(230,550,300,30);     	 
    	
    	con.add(txt1);
    	txt1.setBounds(150,100,100,30);
    	con.add(txt2);
    	txt2.setBounds(150,150,300,30);
    	con.add(txt3);
    	txt3.setBounds(150,200,50,30);
    	con.add(txt4);
    	txt4.setBounds(150,250,300,30);
    	con.add(txt5);
    	txt5.setBounds(150,300,300,30);
    	con.add(txt6);
    	txt6.setBounds(150,350,300,30);
    	con.add(cm);
    	cm.setBounds(150,400,50,30);
    	cm.addActionListener(this);
    	con.add(cd);
    	cd.setBounds(200,400,50,30);
    	cd.addActionListener(this);
    	con.add(txt7);
    	txt7.setBounds(250,400,50,30);
    	con.add(cs);
    	cs.setBounds(150,500,100,30);
    	cs.addActionListener(this);
    	con.add(txt8);
    	txt8.setBounds(150,550,50,30);
    	con.add(txt9);
    	txt9.setBounds(300,550,50,30);
    	txt9.setEditable(false);
    	
    	con.add(bot1);
    	bot1.setBounds(100,600,100,30);
    	bot1.addActionListener(this);    	
    	con.add(botDel);
    	botDel.setBounds(200,600,100,30);
    	botDel.addActionListener(this);
    	con.add(bot3);
    	bot3.setBounds(100,630,100,30);
    	bot3.addActionListener(this);
    	con.add(bot2);
    	bot2.setBounds(200,630,100,30);
    	bot2.addActionListener(this);
    	con.add(botS);
    	botS.setBounds(250,100,80,30);
    	botS.addActionListener(this); 
    	
    	con.add(back).setBounds(0,0,900,900);
    	
    	try
			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				cn = DriverManager.getConnection("jdbc:odbc:Final");
			}
			catch(ClassNotFoundException e)  
			{
 				System.err.println("Failed to load driver");
 				e.printStackTrace();
 			}
 			catch(SQLException e)
 			{
 				System.err.println("Unable to connect");
 				e.printStackTrace();
 			}   


    }
    public void actionPerformed (ActionEvent event)
    {			
    	Object source = event.getSource();
    	
    	if(source == bot1)
    	{
    		String Room_No = "";
    		try
    		{
				st=cn.createStatement();
				rs=st.executeQuery("SELECT * FROM Check_In WHERE Room_No ='"+txt1.getText()+"'");
				while(rs.next())
				{
					Room_No = rs.getString(1);
				}
				st.close();
			}
			catch(SQLException sqlEx)
			{
				System.out.println(sqlEx.getMessage());
			}
			
			if(txt1.getText().equals("") || txt2.getText().equals("")|| txt3.getText().equals("")  || txt4.getText().equals("") || txt5.getText().equals("") || txt6.getText().equals("")|| cm.getSelectedItem().equals("") || cd.getSelectedItem().equals("") || txt7.getText().equals("")|| cs.getSelectedItem().equals("")|| txt8.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Please fill the remaining spaces!","Message",JOptionPane.INFORMATION_MESSAGE);	
			}		
			else
			{ 
    		//==compare data===				 
    		if(Room_No.equals(txt1.getText()))
    		{
    			JOptionPane.showMessageDialog(null,"The Room is already Occupied!","Message",JOptionPane.INFORMATION_MESSAGE);
				 clear();
    		}
    		try
    		{
    			st = cn.createStatement();
				ps = cn.prepareStatement("INSERT INTO Check_In (Room_No,Last_Name,Middle_Initial,First_Name,Contact_No,Address,Month,Day,Year,Room_Size,No_Days,Down) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
				ps.setString(1,txt1.getText());
				ps.setString(2,txt2.getText());
				ps.setString(3,txt3.getText());
				ps.setString(4,txt4.getText());
				ps.setString(5,txt5.getText());
				ps.setString(6,txt6.getText());
				ps.setString(7,(String)cm.getSelectedItem());
				ps.setString(8,(String)cd.getSelectedItem());
				ps.setString(9,txt7.getText());
				ps.setString(10,(String)cs.getSelectedItem());
				ps.setString(11,txt8.getText());
				ps.setString(12,txt9.getText());
				ps.executeUpdate();
				
				JOptionPane.showMessageDialog(null,"New record has been successfully added!","Message",JOptionPane.INFORMATION_MESSAGE);
				txt1.requestFocus(true);
				st.close();
				}
				catch(SQLException se)
				{
					System.out.println(se.getMessage());
				}
		   }
		   
    	}
    	if(source == botS)
    	{
		Room_No = txt1.getText().trim();
				try
					{
						Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
						cn = DriverManager.getConnection("jdbc:odbc:Final");
					}
				catch(ClassNotFoundException ex)  
					{
 						System.err.println("Failed to load driver");
 						ex.printStackTrace();
 					}
 				catch(SQLException ex)
 					{
 						System.err.println("Unable to connect");
 						ex.printStackTrace();
 					}	
		 try 
	     {		
		 	Statement stmt = cn.createStatement();          
    		
         if (!Room_No.equals(""))
         {	
     			String query = "SELECT * FROM Reservation WHERE Room_No='" + Room_No +"'";
                ResultSet rs = stmt.executeQuery(query);
                int foundrec = 0;
                while (rs.next())
                {
                	Last_Name = rs.getString(2).trim();
					Middle_Initial = rs.getString(3).trim();
					First_Name = rs.getString(4).trim();
					Contact_No = rs.getString(5).trim();
					Address = rs.getString(6).trim();
					Month = rs.getString(7).trim();
					Day = rs.getString(8).trim();
					Year = rs.getString(9).trim();
					Room_Size = rs.getString(10).trim();
					No_Days = rs.getString(11).trim();
					Down = rs.getString(12).trim();
		
                	txt4.setText(First_Name);               	
                	txt3.setText(Middle_Initial);	
                	txt2.setText(Last_Name);  
                	txt5.setText(Contact_No);	
                	txt6.setText(Address);            	
                	cm.setSelectedItem(Month);
                	cd.setSelectedItem(Day);               	
                	txt7.setText(Year);	
                	cs.setSelectedItem(Room_Size);             	
                	txt8.setText(No_Days);
                	txt9.setText(Down);
                	
                    foundrec = 1;
                    
                } 
     		if (foundrec == 0)
                {
                    dialogmessage = "No Such Room";               
                    dialogtype = JOptionPane.WARNING_MESSAGE;
                    JOptionPane.showMessageDialog((Component)null, dialogmessage, dialogs, dialogtype);
                   	clear();
                }
                
         }
         else
         {
         			dialogmessage = "No Blank Field Allowed";          
                    dialogtype = JOptionPane.WARNING_MESSAGE;
                    JOptionPane.showMessageDialog((Component)null, dialogmessage, dialogs, dialogtype);
                    clear();
                   
         }    
		  stmt.close();
          
     		
		 }
			catch(Exception ex)
            {
                System.out.println("\nUnknown Error");
            }
    		
    	}
		if(source == bot2)
    	{
			clear();
    		
    	}
	   	if(source == bot3)
    	{
    		TheMain main = new TheMain();
           	main.setMain();
           	dispose();
    		
    	}
    	if(source == botDel)
    	{
    		int DResult = JOptionPane.showConfirmDialog((Component) null,"Are you sure you want to delete Record?");
			if (DResult == JOptionPane.NO_OPTION) 
			{
			clear();
			} 		 
	 		if (DResult == JOptionPane.YES_OPTION)
         	{
         	try
         	{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				cn = DriverManager.getConnection("jdbc:odbc:Final");
			}
			catch(ClassNotFoundException e)  
			{
 				System.err.println("Failed to load driver");
 				e.printStackTrace();
 			}
 			catch(SQLException e)
 			{
 				System.err.println("Unable to connect");
 				e.printStackTrace();
 			}
            try
              {
                  	Statement stmt = cn.createStatement();   
			    	Room_No = txt1.getText().trim();	              
                         if ( !Room_No.equals(""))
                         {
                                   	String query = "SELECT * FROM Reservation WHERE Room_No='" + Room_No+"'";
                					ResultSet rss = stmt.executeQuery(query);
                					rss.next();
                					String code=rss.getString(3);
	
               						String temp = "DELETE from Reservation" + " WHERE Room_No= '" + Room_No + "'";
                                   	int result = stmt.executeUpdate( temp );
                                   if ( result == 1)
                                 	{
                                 	dialogmessage = "Reserve Guest Record Deleted!!!";
                    				dialogtype = JOptionPane.WARNING_MESSAGE;
                    				JOptionPane.showMessageDialog((Component)null, dialogmessage, dialogs, dialogtype);
                    				clear();
                              		}
                        }  
                 stmt.close();       
            }

            catch(Exception e)
           {
               System.out.println("\nUnknown Error" + e);
               dialogmessage = "ERROR"; 
               dialogtype = JOptionPane.WARNING_MESSAGE;
               JOptionPane.showMessageDialog((Component)null, dialogmessage, dialogs, dialogtype);
           }
          
    	}
    	}
    	
    }
    private void clear()
    {
				
			txt1.setText("");
	    	txt2.setText(""); 
	    	txt3.setText(""); 
	    	txt4.setText(""); 
	    	txt5.setText(""); 
	    	txt6.setText("");
	    	txt7.setText(""); 
	    	txt8.setText(""); 
	    	cm.setSelectedItem(month[0]);
	    	cd.setSelectedItem(day[0]);
	    	cs.setSelectedItem(size[0]);
	    	txt9.setText("0");
    }
     public static void setCheckIn()
    {
    	CheckIn frame = new CheckIn();
    	frame.setTitle("Hotel Reservation System");
    	frame.setVisible(true);
    	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    	frame.setLocation(100,5);
    	frame.setSize((screen.width-500),screen.height-50);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }  
}