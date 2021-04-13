/**
 * @(#)Bill.java
 *
 *
 * @author 
 * @version 1.00 2013/10/8
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;

public class Bill extends JFrame implements ActionListener
{
    	
    JButton bot1= new JButton ("Main");
	JButton bot2= new JButton ("Clear");
	JButton botP1= new JButton ("Price");
	JButton botP2= new JButton ("Price");
	JButton botTP= new JButton ("Total");
	JButton botC= new JButton ("Change");
	JButton botS= new JButton ("Search");
	JButton botOk= new JButton ("Ok");
	
	JLabel lab1= new JLabel ("Billings/Check-Out");
	JLabel labRN= new JLabel ("Room Number");
	JLabel labFN= new JLabel ("First Name");
	JLabel labMI= new JLabel ("MI");
	JLabel labLN= new JLabel ("Last Name: ");
	JLabel labDI= new JLabel ("Day-In:");
	JLabel labRS= new JLabel ("Room Size");
	JLabel labND= new JLabel ("No. of Days");
	JLabel labTP= new JLabel ("Total Price:");
	JLabel labYM= new JLabel ("Your Money:");
	JLabel labC= new JLabel ("Change:");
	JLabel labDn= new JLabel ("Down");
	
	JTextField txtRN= new JTextField ();
	JTextField txtLN= new JTextField ();
	JTextField txtMI= new JTextField ();
	JTextField txtFN= new JTextField ();	
	JTextField txtM= new JTextField ();
	JTextField txtD= new JTextField ();
	JTextField txtY= new JTextField ();
	JTextField txtRS= new JTextField ();
	JTextField txtND= new JTextField ();
	JTextField txtP1= new JTextField ();
	JTextField txtP2= new JTextField ();
	JTextField txtTP= new JTextField ();
	JTextField txtYM= new JTextField ();
	JTextField txtC= new JTextField ();
	JTextField txtDn= new JTextField ();

	
	String dialogmessage;
    String dialogs;
    int dialogtype = JOptionPane.PLAIN_MESSAGE;
    public static int record;
	static Connection cn;
	static Statement st;
	static PreparedStatement ps;
	static ResultSet rs;
	
	Container con = getContentPane();
	
	ImageIcon image = new ImageIcon("2.jpeg");
	JLabel back = new JLabel(image,JLabel.CENTER);
	
	Font font = new Font("Times new Roman", Font.BOLD, 20);

    public Bill() 
    {
    	con.setLayout(null);
    	con.add(botOk);
    	botOk.setBounds(100,600,100,30);
    	botOk.addActionListener(this);    	
    	con.add(bot2);
    	bot2.setBounds(200,600,100,30);
    	bot2.addActionListener(this);  
    	con.add(botP1);
    	botP1.setBounds(250,250,80,30);
    	botP1.addActionListener(this);    	
    	con.add(botP2);
    	botP2.setBounds(250,300,80,30);
    	botP2.addActionListener(this);
    	con.add(botTP);
    	botTP.setBounds(250,400,80,30);
    	botTP.addActionListener(this);    	
    	con.add(botC);
    	botC.setBounds(250,450,80,30);
    	botC.addActionListener(this); 
    	con.add(botS);
    	botS.setBounds(250,100,80,30);
    	botS.addActionListener(this);
    	con.add(bot1);
    	bot1.setBounds(120,630,150,30);
    	bot1.addActionListener(this); 
    	
    	con.add(lab1);
    	lab1.setBounds(50,50,300,30);
    	lab1.setFont(font);
    	con.add(labRN);
    	labRN.setBounds(50,100,300,30);
    	con.add(labFN);
    	labFN.setBounds(175,125,300,30);
    	con.add(labMI);
    	labMI.setBounds(325,125,300,30);
    	con.add(labLN);
    	labLN.setBounds(385,125,300,30);
   		con.add(labDI);
    	labDI.setBounds(50,200,150,30);
    	con.add(labRS);
    	labRS.setBounds(50,250,200,30);
   		con.add(labND);
    	labND.setBounds(50,300,150,30);
    	con.add(labDn);
    	labDn.setBounds(50,350,200,30);
    	con.add(labTP);
    	labTP.setBounds(50,400,150,30);
    	con.add(labYM);
    	labYM.setBounds(50,450,200,30);
   		con.add(labC);
    	labC.setBounds(50,500,150,30);
    	
    	con.add(txtRN);
    	txtRN.setBounds(150,100,100,30);
    	con.add(txtFN);
    	txtFN.setBounds(150,150,150,30);
    	txtFN.setEditable(false);
    	con.add(txtMI);
    	txtMI.setBounds(300,150,50,30);
    	txtMI.setEditable(false);
    	con.add(txtLN);
    	txtLN.setBounds(350,150,150,30);
    	txtLN.setEditable(false);
    	con.add(txtM);
    	txtM.setBounds(150,200,50,30);
    	txtM.setEditable(false);
    	con.add(txtD);
    	txtD.setBounds(200,200,50,30);
    	txtD.setEditable(false);
    	con.add(txtY);
    	txtY.setBounds(250,200,50,30);
    	txtY.setEditable(false);
    	con.add(txtRS);
    	txtRS.setBounds(150,250,80,30);
    	txtRS.setEditable(false);
    	con.add(txtND);
    	txtND.setBounds(150,300,50,30);
    	txtND.setEditable(false);
    	con.add(txtP1);
    	txtP1.setBounds(350,250,80,30);
    	txtP1.setEditable(false);
    	con.add(txtP2);
    	txtP2.setBounds(350,300,80,30);
    	txtP2.setEditable(false);
    	con.add(txtDn);
    	txtDn.setBounds(150,350,80,30);
    	txtDn.setEditable(false);
    	con.add(txtTP);
    	txtTP.setBounds(150,400,80,30);
    	txtTP.setEditable(false);
    	con.add(txtYM);
    	txtYM.setBounds(150,450,80,30);
    	con.add(txtC);
    	txtC.setBounds(150,500,80,30);
    	txtC.setEditable(false);
    	
    	con.add(back).setBounds(0,0,700,700);
    	
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
    public void actionPerformed (ActionEvent e)
    {
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
			String Down  = "";
			
			double single = 300;
			double duble = 500;
			double family = 1000;
    	Object source = e.getSource();
    	
//    	int Text8=(Integer.parseInt(txt8.getText()));
//    	int Text9=(Integer.parseInt(txt9.getText()));
		if(source == botS)
		{
		Room_No = txtRN.getText().trim();
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
     			String query = "SELECT * FROM Check_In WHERE Room_No='" + Room_No +"'";
                ResultSet rs = stmt.executeQuery(query);
                int foundrec = 0;
                while (rs.next())
                {
                	First_Name = rs.getString(4).trim();
					Middle_Initial = rs.getString(3).trim();
					Last_Name = rs.getString(2).trim();
					Month = rs.getString(7).trim();
					Day = rs.getString(8).trim();
					Year = rs.getString(9).trim();
					Room_Size = rs.getString(10).trim();
					No_Days = rs.getString(11).trim();
					Down = rs.getString(12).trim();
		
                	txtFN.setText(First_Name);               	
                	txtMI.setText(Middle_Initial);	
                	txtLN.setText(Last_Name);             	
                	txtM.setText(Month);
                	txtD.setText(Day);               	
                	txtY.setText(Year);	
                	txtRS.setText(Room_Size); 
                	txtND.setText(No_Days);            	
                	txtDn.setText(Down);
                	
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
		if(source == botP1)
		{
			if(txtRS.getText().equals("Single"))
			{
				txtP1.setText(""+single);
			}
			if(txtRS.getText().equals("Double"))
			{
				txtP1.setText(""+duble);
			}
			if(txtRS.getText().equals("Family"))
			{
				txtP1.setText(""+family);
			}
		}
		if(source == botP2)
    	{
    		double total=(Double.parseDouble(txtND.getText()));
    		
    		double totals = total * single;
    		double totald = total * duble;
    		double totalf = total * family;
    		if(txtRS.getText().equals("Single"))
			{
				txtP2.setText(""+totals);
			}
			if(txtRS.getText().equals("Double"))
			{
				txtP2.setText(""+totald);
			}
			if(txtRS.getText().equals("Family"))
			{
				txtP2.setText(""+totalf);
			}	
				
    	}
    	if(source == botTP)
    	{
    		double t2=(Double.parseDouble(txtP2.getText()));
    		double t1=(Double.parseDouble(txtDn.getText()));
    		
    		double total1=t2-t1;
    		txtTP.setText(""+total1);
    			
    	}
    	if(source == botC)
    	{
    		double t1=(Double.parseDouble(txtTP.getText()));
    		double t2=(Double.parseDouble(txtYM.getText()));
    		double total= t2-t1;
    		
    		if(t2<t1)
    		{
    			    dialogmessage = "Not Enough Money";          
                    dialogtype = JOptionPane.WARNING_MESSAGE;
                    JOptionPane.showMessageDialog((Component)null, dialogmessage, dialogs, dialogtype);
                    txtC.setText("");
    		}
    		else
    		{
    			txtC.setText(""+total);
    		}		
    	}
    	if(source == botOk)
    	{
    		int DResult = JOptionPane.showConfirmDialog((Component) null,"Are you sure you want to Check Out?");
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
			    	Room_No = txtRN.getText().trim();	              
                         if ( !Room_No.equals(""))
                         {
                                   	String query = "SELECT * FROM Check_In WHERE Room_No='" + Room_No+"'";
                					ResultSet rss = stmt.executeQuery(query);
                					rss.next();
                					String code=rss.getString(3);
	
               						String temp = "DELETE from Check_In" + " WHERE Room_No= '" + Room_No + "'";
                                   	int result = stmt.executeUpdate( temp );
                                   if ( result == 1)
                                 	{
                                 	dialogmessage = "Thank You Come Again..!";
                    				dialogtype = JOptionPane.WARNING_MESSAGE;
                    				JOptionPane.showMessageDialog((Component)null, dialogmessage, dialogs, dialogtype);
                    				clear();
                              		}
                        }  
                 stmt.close();       
            }

            catch(Exception ex)
           {
               System.out.println("\nUnknown Error" + ex);
               dialogmessage = "ERROR"; 
               dialogtype = JOptionPane.WARNING_MESSAGE;
               JOptionPane.showMessageDialog((Component)null, dialogmessage, dialogs, dialogtype);
           }
          
    	}				
    	}
	   	if(source == bot1)
    	{
    		TheMain main = new TheMain();
           	main.setMain();
           	dispose();
    		
    	}
    	if(source == bot2)
    	{
			clear();	
    	}
    	
    }
     private void clear()
    {
   	    txtRN.setText("");
	 	txtLN.setText("");
	 	txtMI.setText("");
	 	txtFN.setText("");	
	 	txtM.setText("");
	 	txtD.setText("");
	 	txtY.setText("");
		txtRS.setText("");
	 	txtND.setText("");
	 	txtP1.setText("");
	 	txtP2.setText(""); 
		txtTP.setText("");
		txtYM.setText("");
	 	txtC.setText("");
	 	txtDn.setText("");
    }
    public static void setBill()
    {
    	Bill frame = new Bill();
    	frame.setTitle("Hotel Reservation System");
    	frame.setVisible(true);
    	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    	frame.setLocation(100,5);
    	frame.setSize((screen.width-500),screen.height-50);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }  
    
    
}