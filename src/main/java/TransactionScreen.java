
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

public class TransactionScreen extends JPanel {
    private JLabel jcomp1;
    private JList transList;
    private JButton addPerson;
    private JButton refresh; 
    private JButton back;
    private DefaultListModel transLM;

    private JFrame frame;

    public TransactionScreen(JFrame frameM) {
    	frame = frameM;
    	
		transLM = new DefaultListModel();
        //construct components
        
        jcomp1 = new JLabel ("Transactions");
        transList = new JList (transLM);
        
        addPerson = new JButton("Add Transaction");
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
                frame.getContentPane().add (new MainScreen(frame));
                frame.pack();
                frame.setVisible (true);
            }
        });

        //adjust size and set layout
        setPreferredSize (new Dimension (944, 563));
        setLayout (null);

        JScrollPane transScrollPane = new JScrollPane();
        transScrollPane.setViewportView(transList);
        transList.setLayoutOrientation(JList.VERTICAL);
        
        
        //add components
        add (jcomp1);
        add (transScrollPane);
        add (addPerson);
        add (refresh);
        add (back);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (25, 15, 100, 25);
        transScrollPane.setBounds (25, 90, 800, 150);
        addPerson.setBounds (100, 15, 200, 25);
        refresh.setBounds(400, 15, 200, 25);
        back.setBounds(700,15,200,25);
    }
    
    private void populateListsFromDB()	{
    	SQLConnection sqlCon = new SQLConnection();
    	sqlCon.openConnection();
    	transLM.clear();
    	ArrayList<String> transListSQL = sqlCon.selectEmployees();
    	for(String str : transListSQL)	{
    		transLM.addElement(str);
    	}
    	
    	sqlCon.closeConnection();
    }
    
    public static void main (String[] args) {
        JFrame frame = new JFrame ("TransactionScreen");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new TransactionScreen(frame));
        frame.pack();
        frame.setVisible (true);
    }
}
