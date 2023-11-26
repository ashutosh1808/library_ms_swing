import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MainFrame extends JFrame
{
Container c;
JButton btnAdd,btnView,btnEdit,btnDelete;

MainFrame()
{
	c=getContentPane();
	c.setLayout(null);
	Font f=new Font("Arial",Font.BOLD,30);

	btnAdd=new JButton("Add");
	btnAdd.setFont(f);
	btnAdd.setBounds(300,100,150,40);
	c.add(btnAdd);

	btnView=new JButton("View");
	btnView.setFont(f);
	btnView.setBounds(300,200,150,40);
	c.add(btnView);

	btnEdit=new JButton("Edit");
	btnEdit.setFont(f);
	btnEdit.setBounds(300,300,150,40);
	c.add(btnEdit);

	btnDelete=new JButton("Delete");
	btnDelete.setFont(f);
	btnDelete.setBounds(300,400,150,40);
	c.add(btnDelete);
	
	ActionListener a1=(ae)->{
		Create c=new Create();
		dispose();
	};
	btnAdd.addActionListener(a1);

	ActionListener a2=(ae)->{
		View c=new View();
		dispose();
	};
	btnView.addActionListener(a2);

	ActionListener a3=(ae)->{
		Update c=new Update();
		dispose();
	};
	btnEdit.addActionListener(a3);

	ActionListener a4=(ae)->{
		Delete c=new Delete();
		dispose();
	};
	btnDelete.addActionListener(a4);

	setVisible(true);
	setSize(800,600);
	setTitle("LMS");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocationRelativeTo(null);

}
}