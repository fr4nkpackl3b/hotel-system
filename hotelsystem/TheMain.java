/**
 * @(#)TheMain.java
 *
 *
 * @author 
 * @version 1.00 2013/10/5
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;

public class TheMain extends JFrame implements ActionListener
{
	JDesktopPane desktop = new JDesktopPane();
	JToolBar toolBar = new JToolBar(JToolBar.HORIZONTAL);
	JLabel lab1= new JLabel ("Welcome");
	JLabel lab2= new JLabel ("To");
	JLabel lab3= new JLabel ("Watataps Day-Night Hotel System");
	JLabel lab4 = new JLabel ("Log-out");
	
	JButton bcheck = new JButton("Check-in");
	JButton breserve = new JButton("Reservation");
	JButton bbill = new JButton("Billings");
	JButton out = new JButton(new ImageIcon("logout.png"));
	
	Container con = getContentPane();
	Font font1 = new Font("Castellar", Font.BOLD,50);
	Font font2 = new Font("Edwardian Script ITC", Font.BOLD, 30);
	
	ImageIcon image = new ImageIcon("main.jpg");
	JLabel back = new JLabel(image,JLabel.CENTER);

    public TheMain() 
    {
    	con.setLayout(null);
    	
    	con.add(lab1);
    	lab1.setBounds(100,5,300,150);
    	lab1.setFont(font1);
    	con.add(lab2);
    	lab2.setBounds(200,100,50,50);
    	con.add(lab3);
    	lab3.setBounds(50,150,1000,50);
    	lab3.setFont(font2);
    	con.add(lab4);
    	lab4.setBounds(50,400,300,30);
    	
    	con.add(bcheck);
    	bcheck.setBounds(100,300,100,30);
    	bcheck.addActionListener(this);
    	con.add(breserve);
    	breserve.setBounds(200,300,130,30);
    	breserve.addActionListener(this);
    	con.add(bbill);
    	bbill.setBounds(330,300,100,30);
    	bbill.addActionListener(this);
    	con.add(out);
    	out.setBounds(100,400,30,30);
    	out.addActionListener(this);
    	
    	con.add(back).setBounds(0,0,900,900);
    	
 //   	bcheck.setToolTipText("Check-in");
    	
        getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
     public void actionPerformed (ActionEvent e)
    {
    	WatatapsSample log= new WatatapsSample();
    	CheckIn in= new CheckIn();
    	Reserve rev = new Reserve();
    	Bill bill = new Bill();
    	Object source = e.getSource();
    	if(source == bcheck)
    	{
    		in.setCheckIn();
    		dispose();
    		
    	}
    	if(source == breserve)
    	{
    		rev.setReserve();
    		dispose();
    	}
    	if(source == bbill)
    	{
    		bill.setBill();
    		dispose();
    	}
    	if(source == out)
		{
			log.setWatatapsSample();
			dispose();
		}
    }
       
     public static void setMain ()
    {
    	TheMain frame = new TheMain();
    	frame.setTitle("Hotel Reservation System");
    	frame.setVisible(true);
    	Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    	frame.setLocation(100,5);
    	frame.setSize((screen.width-500),screen.height-50);
    }
    
    
}