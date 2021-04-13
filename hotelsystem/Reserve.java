/**
 * @(#)reserve.java
 *
 *
 * @author 
 * @version 1.00 2013/10/6
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Reserve extends JFrame implements ActionListener
public static void main(String[]Args){
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
	JLabel lab13= new JLabel ("Reserve Guest");
	JLabel lab14= new JLabel ("Down_Pay");
	
	JButton bot1= new JButton ("Ok");
	JButton bot2= new JButton ("Clear");
	JButton bot3= new JButton ("Back To Main");
		
	JTextField txtRn= new JTextField ();
	JTextField txtLn= new JTextField ();
	JTextField txtMi= new JTextField ();
	JTextField txtFn= new JTextField ();
	JTextField txtCo= new JTextField ();
	JTextField txtAd= new JTextField ();
	JTextField txtYr= new JTextField ();
	JTextField txtNd= new JTextField ();	
	JTextField txt9= new JTextField ();
		
	static Connection cn;
	static Statement st;
	static PreparedStatement ps;
	static ResultSet rs;
	Container con = getContentPane();
	
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
	
	ImageIcon image = new ImageIcon("1.jpeg");
	JLabel back = new JLabel(image,JLabel.CENTER);
	
	Font font = new Font("Times new Roman", Font.BOLD, 20);

    public Reserve() 
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
    	
    	con.add(txtRn);
    	txtRn.setBounds(150,100,100,30);
    	con.add(txtLn);
    	txtLn.setBounds(150,150,300,30);
    	con.add(txtMi);
    	txtMi.setBounds(150,200,50,30);
    	con.add(txtFn);
    	txtFn.setBounds(150,250,300,30);
    	con.add(txtCo);
    	txtCo.setBounds(150,300,300,30);
    	con.add(txtAd);
    	txtAd.setBounds(150,350,300,30);
    	con.add(cm);
    	cm.setBounds(150,400,50,30);
    	cm.addActionListener(this);
    	con.add(cd);
    	cd.setBounds(200,400,50,30);
    	cd.addActionListener(this);
    	con.add(txtYr);
    	txtYr.setBounds(250,400,50,30);
    	con.add(cs);
    	cs.setBounds(150,500,100,30);
    	cs.addActionListener(this);
    	con.add(txtNd);
    	txtNd.setBounds(150,550,50,30);
    	con.add(txt9);
    	txt9.setBounds(300,550,50,30);
    	
    	con.add(bot1);
    	bot1.setBounds(100,600,100,30);
    	bot1.addActionListener(this);    	
    	con.add(bot2);
    	bot2.setBounds(200,600,100,30);
    	bot2.addActionListener(this);
    	con.add(bot3);
    	bot3.setBounds(120,630,150,30);
    	bot3.addActionListener(this);
    	
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
     public void actionPerformed (ActionEvent e)
    {
    	Object source = e.getSource();
    	
    	if(source == bot1)
    	{
    		String Room_No = "";
    		try
    		{
				st=cn.createStatement();
				rs=st.executeQuery("SELECT * FROM Reservation WHERE Room_No ='"+txtRn.getText()+"'");
				rs=st.executeQuery("SELECT * FROM Check_In WHERE Room_No ='"+txtRn.getText()+"'");
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
			
			if(txtRn.getText().equals("") || txtLn.getText().equals("")|| txtMi.getText().equals("")  || txtFn.getText().equals("") || txtCo.getText().equals("") || txtAd.getText().equals("")|| cm.getSelectedItem().equals("") || cd.getSelectedItem().equals("") || txtYr.getText().equals("")|| cs.getSelectedItem().equals("")|| txtNd.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Please fill the remaining spaces!","Message",JOptionPane.INFORMATION_MESSAGE);	
			}		
			else
			{ 
    		//==compare data===				 
    		if(Room_No.equals(txtRn.getText()))
    		{
    			JOptionPane.showMessageDialog(null,"The Room has already exist!","Message",JOptionPane.INFORMATION_MESSAGE);
				 clear();
    		}
    		try
    		{
    			st = cn.createStatement();
				ps = cn.prepareStatement("INSERT INTO Reservation (Room_No,Last_Name,Middle_Initial,First_Name,Contact_No,Address,Month,Day,Year,Room_Size,No_Days,Down) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
				ps.setString(1,txtRn.getText());
				ps.setString(2,txtLn.getText());
				ps.setString(3,txtMi.getText());
				ps.setString(4,txtFn.getText());
				ps.setString(5,txtCo.getText());
				ps.setString(6,txtAd.getText());
				ps.setString(7,(String)cm.getSelectedItem());
				ps.setString(8,(String)cd.getSelectedItem());
				ps.setString(9,txtYr.getText());
				ps.setString(10,(String)cs.getSelectedItem());
				ps.setString(11,txtNd.getText());
				ps.setString(12,txt9.getText());
				ps.executeUpdate();
				
				JOptionPane.showMessageDialog(null,"New record has been successfully added!","Message",JOptionPane.INFORMATION_MESSAGE);
				txtRn.requestFocus(true);
				st.close();
				clear();
				}
				catch(SQLException se)
				{
					System.out.println(se.getMessage());
				}
    	   	  
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
    	
    }
    private void clear()
    {
				
			txtRn.setText("");
	    	txtLn.setText(""); 
	    	txtMi.setText(""); 
	    	txtFn.setText(""); 
	    	txtCo.setText(""); 
	    	txtAd.setText("");
	    	txtYr.setText(""); 
	    	txtNd.setText(""); 
	    	cm.setSelectedItem(month[0]);
	    	cd.setSelectedItem(day[0]);
	    	cs.setSelectedItem(size[0]);
	    	txt9.setText(""); 
    }
    public static void setReserve ()
    {
    	Reserve frame = new Reserve();
    	frame.setTitle("Hotel Reservation System");
    	frame.setVisible(true);
    	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    	frame.setLocation(100,5);
    	frame.setSize((screen.width-500),screen.height-50);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 
    
    
}