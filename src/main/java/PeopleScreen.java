
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class PeopleScreen extends JPanel {
    private JLabel jcomp1;
    private JLabel jcomp2;
    private JList empList;
    private JList cusList;
    private JButton addPerson;
    
    private DefaultListModel empLM;
    private DefaultListModel cusLM;


    public PeopleScreen(DefaultListModel contEmpLM, DefaultListModel contCusLM) {
    	if(contEmpLM == null)	{
    		empLM = new DefaultListModel();
    	}
    	else
    		empLM = contEmpLM;
    	if(contCusLM == null)	{
    		this.cusLM = new DefaultListModel();
    	}
    	else
    		cusLM = contCusLM;
        //construct components
        
        
        jcomp1 = new JLabel ("Employees");
        jcomp2 = new JLabel ("Customers");
        empList = new JList (empLM);
        cusList = new JList (cusLM);
        
        addPerson = new JButton("Add Person");
        addPerson.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            	JFrame frame = new JFrame ("AddPersonScreen");
                frame.getContentPane().add (new AddPersonScreen(empLM, cusLM));
                frame.pack();
                frame.setVisible (true);
            }
        });

        //adjust size and set layout
        setPreferredSize (new Dimension (944, 563));
        setLayout (null);

        JScrollPane empScrollPane = new JScrollPane();
        empScrollPane.setViewportView(empList);
        empList.setLayoutOrientation(JList.VERTICAL);
        
        JScrollPane cusScrollPane = new JScrollPane();
        cusScrollPane.setViewportView(cusList);
        cusList.setLayoutOrientation(JList.VERTICAL);
        
        //add components
        add (jcomp1);
        add (jcomp2);
        add (empScrollPane);
        add (cusScrollPane);
        add (addPerson);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (25, 15, 100, 25);
        jcomp2.setBounds (25, 285, 100, 25);
        empScrollPane.setBounds (25, 90, 800, 150);
        cusScrollPane.setBounds (25, 350, 800, 150);
        addPerson.setBounds (100, 15, 200, 25);

        
    }

//    public void addEmployee(Employee emp)	{
//    	empLM.addElement(emp);
//    	revalidate();
//    }

    public static void main (String[] args) {
        JFrame frame = new JFrame ("PeopleScreen");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new PeopleScreen(null, null));
        frame.pack();
        frame.setVisible (true);
    }
}
