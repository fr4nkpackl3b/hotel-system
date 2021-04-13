/**
 * @(#)Watataps_Sample.java
 *
 *
 * @author 
 * @version 1.00 2013/10/1
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class WatatapsSample extends JFrame implements ActionListener
{
	public static void main(String[]args)
	{
		setWatatapsSample();
    }
    JFrame frame= new JFrame("Administrator Login");
    JLabel labuser = new JLabel("Username:");
	JLabel labpass = new JLabel("Password:");
	JTextField txtuser = new JTextField();
	JPasswordField txtpass = new JPasswordField();
	JButton bok = new JButton(new ImageIcon("ok.jpeg"));
	JButton bclose = new JButton(new ImageIcon("eks.jpeg"));
	public String loginname="";
   	public String loginpass="";
	Connection cn;
	Statement st;
	ResultSet rs;
	PreparedStatement ps;
	public String user="";
	public String pass="";
	public int cnt;
	int dialogtype = JOptionPane.PLAIN_MESSAGE;
   	String dialogmessage;
   	String dialogs;
   	
    public static void setWatatapsSample()
    {
		WatatapsSample p1= new WatatapsSample();
	    p1.setSize(250,140);
	    p1.setLocation(300,300);
	    p1.setVisible(true);
	    p1.setResizable(false);
	}

    public WatatapsSample() 
    {
    	super("Administrator Login");
    	JPanel pane= new JPanel();
    	pane.setLayout(null);
    	
    	pane.setBackground(Color.blue);
    	labuser.setForeground(Color.white);
    	labpass.setForeground(Color.white);
    	
    	pane.add(labuser);
    	labuser.setBounds(5,5,100,20);
    	pane.add(txtuser);
    	txtuser.setBounds(110,5,100,20);
    
    	pane.add(labpass);
    	labpass.setBounds(5,30,100,20);
    	pane.add(txtpass);
    	txtpass.setBounds(110,30,100,20);
    
    	bok.setBounds(110,65,40,35);
    	pane.add(bok);
    	bok.addActionListener(this);
    	
    	pane.add(bclose);
    	bclose.setBounds(155,65,40,35);
    	bclose.addActionListener(this);
    	
	    bok.setToolTipText("Log-in");
		bclose.setToolTipText("Exit");
		
    	setContentPane(pane);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	pane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), ""));
    	
    	try
    	{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			cn = DriverManager.getConnection("jdbc:odbc:Final");
		}
		catch(ClassNotFoundException e)  
		{
 			JOptionPane.showMessageDialog(null,"unable to load driver");
 			System.exit(0);
 		}
 		catch(SQLException e)
 		{
 			JOptionPane.showMessageDialog(null,"unable to connect");
 			System.exit(0);
		}
    }
    public void actionPerformed(ActionEvent e)
    {
    	
    	Object source=e.getSource();
    	 if(source==bok)
        {
    		try
    		{
    			String str1=txtuser.getText();
				String str2=txtpass.getText();
    				if((str1.length()==0 || str2.length()==0))
    				{
    					JOptionPane.showMessageDialog(null,"Some Fields are empty","WARNING",JOptionPane.WARNING_MESSAGE);
    				}
    				else
    				{
    				st=cn.createStatement();
    				rs=st.executeQuery("SELECT * from Login Where username like'" +txtuser.getText() + "'AND password='"+txtpass.getText()+"'");
    				
    				while(rs.next())
    				{
    					loginname=rs.getString("Username");
    					loginpass=rs.getString("Password");
    				}
    				if((loginname.equalsIgnoreCase(txtuser.getText()))&&(loginpass.equalsIgnoreCase(txtpass.getText())))
    				{	
    					TheMain main = new TheMain();
                  		main.setMain();
              				 
    					dialogmessage = "Welcome: "+loginname;
                	    dialogtype = JOptionPane.INFORMATION_MESSAGE;
                    	JOptionPane.showMessageDialog((Component)null, dialogmessage, dialogs, dialogtype);
                    	txtuser.setText("");
                    	txtpass.setText("");
            			frame.dispose();      
    				}
    		 		 else 
                	{
                    	JOptionPane.showMessageDialog(null, "INVALID ID OR PASSWORD!","WARNING!!",JOptionPane.WARNING_MESSAGE);
                    	cnt=cnt+1;
                   		txtuser.setText("");
                    	txtpass.setText("");
                	}		
    			
    				if(cnt==3)
    				{
    					frame.dispose();
    				}
    				}
    				frame.dispose();
    		}
    		catch(SQLException c)
    		{
    		    System.out.print(c.getMessage()); 
    		}    			   		
        }
        else
        {
       		System.exit(0);
        }
    	if(source==bclose)
    	{
    		frame.dispose();
    	}
    } 
}