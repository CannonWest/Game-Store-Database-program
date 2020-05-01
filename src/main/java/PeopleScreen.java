
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

public class PeopleScreen extends JPanel {
    private JLabel jcomp1;
    private JLabel jcomp2;
    private JList empList;
    private JList cusList;
    private JButton addPerson;
    private JButton refresh; 
    private JButton back;
    private DefaultListModel empLM;
    private DefaultListModel cusLM;

    private JFrame frame;

    public PeopleScreen(JFrame frameM) {
    	frame = frameM;
    	
		empLM = new DefaultListModel();	
		cusLM = new DefaultListModel();
        //construct components
        
        jcomp1 = new JLabel ("Employees");
        jcomp2 = new JLabel ("Customers");
        empList = new JList (empLM);
        cusList = new JList (cusLM);
        
        addPerson = new JButton("Add Person");
        addPerson.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFrame frame = new JFrame ("AddPersonScreen");
                frame.getContentPane().add (new AddPersonScreen(frame));
                frame.pack();
                frame.setVisible (true);
            }
        });
        
        refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	populateListsFromDB();
            }
        });
        
        back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frame.dispose();
            	JFrame frame = new JFrame ("MainScreen");
                frame.getContentPane().add (new MainScreen());
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
        add (refresh);
        add (back);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (25, 15, 100, 25);
        jcomp2.setBounds (25, 285, 100, 25);
        empScrollPane.setBounds (25, 90, 800, 150);
        cusScrollPane.setBounds (25, 350, 800, 150);
        addPerson.setBounds (100, 15, 200, 25);
        refresh.setBounds(400, 15, 200, 25);
        back.setBounds(700,15,200,25);
    }
    
    private void populateListsFromDB()	{
    	SQLConnection sqlCon = new SQLConnection();
    	sqlCon.openConnection();
    	empLM.clear();
    	ArrayList<String> empListSQL = sqlCon.selectEmployees();
    	for(String str : empListSQL)	{
    		empLM.addElement(str);
    	}
    	
    	cusLM.clear();
    	ArrayList<String> cusListSQL = sqlCon.selectCustomers();
    	for(String str : cusListSQL)	{
    		cusLM.addElement(str);
    	}
    	
    	sqlCon.closeConnection();
    }
    
    public static void main (String[] args) {
        JFrame frame = new JFrame ("PeopleScreen");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new PeopleScreen(frame));
        frame.pack();
        frame.setVisible (true);
    }
}
