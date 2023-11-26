import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class Delete extends JFrame
{
Container c;
JLabel lblId;
JTextField txtId;
JButton btnSave,btnBack;

Delete()
{
	c=getContentPane();
	c.setLayout(null);
	Font f=new Font("Arial",Font.BOLD,26);

	lblId=new JLabel("Enter Book ID");
	lblId.setFont(f);
	lblId.setBounds(10,10,250,30);
	c.add(lblId);

	txtId=new JTextField(28);
	txtId.setFont(f);
	txtId.setBounds(310,10,350,40);
	c.add(txtId);

	btnSave=new JButton("Save");
	btnSave.setFont(f);
	btnSave.setBounds(200,420,250,35);
	c.add(btnSave);

	btnBack=new JButton("Back");
	btnBack.setFont(f);
	btnBack.setBounds(200,490,250,35);
	c.add(btnBack);
	
	ActionListener a1=(ae)->{
		MainFrame a=new MainFrame();
		dispose();		
	};
	btnBack.addActionListener(a1);

	ActionListener a2=(ae)->{	
		try{
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String url="jdbc:mysql://localhost:3306/lib_24nov23";
			String un="root",pw="abc456";
			Connection con=DriverManager.getConnection(url,un,pw);
			String sql="delete from book where bid=?";
			PreparedStatement ps=con.prepareStatement(sql);
			int bid=Integer.parseInt(txtId.getText());
			if(bid<=0)
			{
				JOptionPane.showMessageDialog(c,"Book ID should be +ve");
				txtId.setText("");
				txtId.requestFocus();
				return;
			}
			ps.setInt(1,bid);
			int r=ps.executeUpdate();
			if(r==1)	
				JOptionPane.showMessageDialog(c,"Thanks for deleting");
			else
			{
				JOptionPane.showMessageDialog(c,"Book "+bid+" absent");
				return;
			}
		}catch(NumberFormatException nfe){
			JOptionPane.showMessageDialog(c,"Book ID should have +ve numbers");
			return;
		}catch(SQLException e){
			JOptionPane.showMessageDialog(c,"Issue: "+e);
			return;
		}
	};
	btnSave.addActionListener(a2);
	setVisible(true);
	setSize(800,600);
	setTitle("Delete Orders");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocationRelativeTo(null);
}
}