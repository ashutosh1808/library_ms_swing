import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class View extends JFrame
{
Container c;
TextArea taData;
JButton btnBack;

View()
{
	c=getContentPane();
	c.setLayout(null);
	Font f=new Font("Arial",Font.BOLD,26);
		
	taData=new TextArea(20,20);
	taData.setEditable(false);
	taData.setFont(f);
	taData.setBounds(10,10,750,490);
	c.add(taData);	
	
	btnBack=new JButton("Back");
	btnBack.setFont(f);
	btnBack.setBounds(300,520,100,40);
	c.add(btnBack);

	try{
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String url="jdbc:mysql://localhost:3306/lib_24nov23";
			String un="root",pw="abc456";
			Connection con=DriverManager.getConnection(url,un,pw);
			String sql="select * from book";
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			String data="";
			while(rs.next())
			{
				int bid=rs.getInt(1);
				String bname=rs.getString(2);
				data+=bid+" "+bname+"\n";
			}
			taData.setText(data);
		}catch(SQLException e){
			JOptionPane.showMessageDialog(c,"Issue: "+e);
			return;
		}

	ActionListener a1=(ae)->{
		MainFrame m1=new MainFrame();
		dispose();
	};
	btnBack.addActionListener(a1);
	setVisible(true);
	setSize(800,600);
	setTitle("View Orders");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocationRelativeTo(null);
}
}