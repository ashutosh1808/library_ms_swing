import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Create extends JFrame
{
Container c;
JLabel lblId,lblTitle,lblAuthor,lblPrice,lblIsbn,lblCopies;
JTextField txtId,txtTitle,txtAuthor,txtPrice,txtIsbn,txtCopies;
JButton btnSave,btnBack;


Create()
{
	c=getContentPane();	
	c.setLayout(null);
	Font f=new Font("Arial",Font.BOLD,26);
	
	lblId=new JLabel("Enter Book ID");
	lblId.setFont(f);
	lblId.setBounds(10,10,250,30);
	c.add(lblId);

	lblTitle=new JLabel("Enter Book Title");
	lblTitle.setFont(f);
	lblTitle.setBounds(10,80,250,30);
	c.add(lblTitle);

	lblAuthor=new JLabel("Enter Author Name");
	lblAuthor.setFont(f);
	lblAuthor.setBounds(10,150,250,30);
	c.add(lblAuthor);

	lblPrice=new JLabel("Enter Price");
	lblPrice.setFont(f);
	lblPrice.setBounds(10,220,250,30);
	c.add(lblPrice);

	lblIsbn=new JLabel("Enter ISBN");
	lblIsbn.setFont(f);
	lblIsbn.setBounds(10,290,250,30);
	c.add(lblIsbn);

	lblCopies=new JLabel("Enter Copies");
	lblCopies.setFont(f);
	lblCopies.setBounds(10,360,250,30);
	c.add(lblCopies);

	txtId=new JTextField(28);
	txtId.setFont(f);
	txtId.setBounds(310,10,350,40);
	c.add(txtId);

	txtTitle=new JTextField(28);
	txtTitle.setFont(f);
	txtTitle.setBounds(310,80,350,40);
	c.add(txtTitle);

	txtAuthor=new JTextField(28);
	txtAuthor.setFont(f);
	txtAuthor.setBounds(310,150,350,40);
	c.add(txtAuthor);

	txtPrice=new JTextField(28);
	txtPrice.setFont(f);
	txtPrice.setBounds(310,220,350,40);
	c.add(txtPrice);

	txtIsbn=new JTextField(28);
	txtIsbn.setFont(f);
	txtIsbn.setBounds(310,290,350,40);
	c.add(txtIsbn);

	txtCopies=new JTextField(28);
	txtCopies.setFont(f);
	txtCopies.setBounds(310,360,350,40);
	c.add(txtCopies);

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
			String sql="insert into book values(?,?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);

			int bid=Integer.parseInt(txtId.getText());
			if(bid<=0)
			{
				JOptionPane.showMessageDialog(c,"Book ID should be +ve");
				txtId.setText("");
				txtId.requestFocus();
				return;
			}

			String bname=txtTitle.getText();
			if(!(bname.matches("[a-zA-Z ]+")) || bname.length()<2)
			{
				JOptionPane.showMessageDialog(c,"Book name should contain letters only");
				txtTitle.setText("");
				txtTitle.requestFocus();
				return;	
			}

			String author=txtAuthor.getText();
			if(!(author.matches("[a-zA-Z ]+")) || author.length()<2)
			{
				JOptionPane.showMessageDialog(c,"Author name should contain letters only");
				txtAuthor.setText("");
				txtAuthor.requestFocus();
				return;	
			}
	
			double price=Double.parseDouble(txtPrice.getText());
			if(price<=0.0)
			{
				JOptionPane.showMessageDialog(c,"Price should be +ve");
				txtPrice.setText("");
				txtPrice.requestFocus();
				return;	
			}

			int isbn=Integer.parseInt(txtIsbn.getText());
			if(isbn<=0)
			{
				JOptionPane.showMessageDialog(c,"ISBN should be +ve");
				txtIsbn.setText("");
				txtIsbn.requestFocus();
				return;
			}

			int copies=Integer.parseInt(txtCopies.getText());
			if(copies<=0)
			{
				JOptionPane.showMessageDialog(c,"No. of copies should be +ve");
				txtIsbn.setText("");
				txtIsbn.requestFocus();
				return;
			}
	
			ps.setInt(1,bid);
			ps.setString(2,bname);
			ps.setString(3,author);
			ps.setDouble(4,price);
			ps.setInt(5,isbn);
			ps.setInt(6,copies);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(c,"Thank you!");
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(c,"Enter numbers in bid/price/isbn/copies!");
			return;
		}catch(SQLException e){
			JOptionPane.showMessageDialog(c,"Issue: "+e);
			return;
		}
	};
	btnSave.addActionListener(a2);
	
	setVisible(true);
	setSize(800,600);
	setTitle("Order Books");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocationRelativeTo(null);
}
}