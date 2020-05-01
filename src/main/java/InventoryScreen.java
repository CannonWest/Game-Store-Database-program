
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

public class InventoryScreen extends JPanel {
    private JLabel jcomp1;
    private JLabel jcomp2;
    private JList gameList;
    private JList otherList;
    private JButton addItem;
    private JButton refresh; 
    private JButton back;
    private DefaultListModel empLM;
    private DefaultListModel cusLM;

    private JFrame frame;

    public InventoryScreen(JFrame frameM) {
    	frame = frameM;
    	
		empLM = new DefaultListModel();	
		cusLM = new DefaultListModel();
        //construct components
        
        jcomp1 = new JLabel ("Employees");
        jcomp2 = new JLabel ("Customers");
        gameList = new JList (empLM);
        otherList = new JList (cusLM);
        
        addItem = new JButton("Add Person");
        addItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFrame frame = new JFrame ("addItemScreen");
                frame.getContentPane().add (new AddInventoryScreen(frame));
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

        JScrollPane gameScrollPane = new JScrollPane();
        gameScrollPane.setViewportView(gameList);
        gameList.setLayoutOrientation(JList.VERTICAL);
        
        JScrollPane otherScrollPane = new JScrollPane();
        otherScrollPane.setViewportView(otherList);
        otherList.setLayoutOrientation(JList.VERTICAL);
        
        //add components
        add (jcomp1);
        add (jcomp2);
        add (gameScrollPane);
        add (otherScrollPane);
        add (addItem);
        add (refresh);
        add (back);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (25, 15, 100, 25);
        jcomp2.setBounds (25, 285, 100, 25);
        gameScrollPane.setBounds (25, 90, 800, 150);
        otherScrollPane.setBounds (25, 350, 800, 150);
        addItem.setBounds (100, 15, 200, 25);
        refresh.setBounds(400, 15, 200, 25);
        back.setBounds(700,15,200,25);
    }
    
    private void populateListsFromDB()	{
    	SQLConnection sqlCon = new SQLConnection();
    	sqlCon.openConnection();
//    	empLM.clear();
//    	ArrayList<String> gameListSQL = sqlCon.selectEmployees();
//    	for(String str : gameListSQL)	{
//    		empLM.addElement(str);
//    	}
//    	
//    	cusLM.clear();
//    	ArrayList<String> otherListSQL = sqlCon.selectCustomers();
//    	for(String str : otherListSQL)	{
//    		cusLM.addElement(str);
//    	}
    	
    	sqlCon.closeConnection();
    }
    
    public static void main (String[] args) {
        JFrame frame = new JFrame ("TransactionScreen");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new InventoryScreen(frame));
        frame.pack();
        frame.setVisible (true);
    }
}
