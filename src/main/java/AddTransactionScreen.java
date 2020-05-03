import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

public class AddTransactionScreen extends JPanel {
    private JLabel jcomp3;
    private JLabel jcomp4;
    private JLabel jcomp5;
    private JTextField jcompID;
    private JTextField jcompPrice;
    private JTextField jcompCustomer;

    
    private JButton addTransaction;
    
    private static JFrame frame;

    public AddTransactionScreen(JFrame frameM) {
        this.frame = frameM;
    	//construct components
        jcomp3 = new JLabel ("Transaction ID");
        jcomp4 = new JLabel ("Price");
        jcomp5 = new JLabel ("Customer ID");
        jcompID = new JTextField (5);
        jcompPrice = new JTextField (5);
        jcompCustomer = new JTextField (5);
        addTransaction = new JButton ("Add Transaction");

        addTransaction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	List<JTextField> list = new ArrayList<JTextField>();
        
            	list.add(jcompID);
            	list.add(jcompPrice);
            	list.add(jcompCustomer);
            	
            	boolean cont = true;
            	for(JTextField tf : list)	{
            		if (tf.getText().equals(""))
            			cont = false;
            	}
            	if(!cont)	{}
            	else	{
            		addTransactionToDB(list);
        			frame.dispose();
            	}
            }
        });
        
        //adjust size and set layout
        setPreferredSize (new Dimension (944, 563));
        setLayout (null);

        //add components
        add (jcomp3);
        add (jcomp4);
        add (jcomp5);
        add (jcompID);
        add (jcompPrice);
        add (jcompCustomer);
        add (addTransaction);

        //set component bounds (only needed by Absolute Positioning)
        jcomp3.setBounds (20, 5, 100, 25);
        jcomp4.setBounds (150, 5, 100, 25);
        jcomp5.setBounds (300, 5, 100, 25);
        jcompID.setBounds (20, 30, 100, 25);
        jcompPrice.setBounds (150, 30, 100, 25);
        jcompCustomer.setBounds (300, 30, 120, 25);
        addTransaction.setBounds(20, 70, 200, 25);
    }

    private void addTransactionToDB(List<JTextField> list)	{
    	SQLConnection sqlCon = new SQLConnection();
    	sqlCon.openConnection();
    	ArrayList<String> arrList= new ArrayList<String>();
    	for(JTextField jtf : list)	{
    		arrList.add(jtf.getText());
    	}
    	sqlCon.insertTransaction(arrList);
    	
    	sqlCon.closeConnection();
    }
    
	public static void main (String[] args) {
        frame = new JFrame ("addTransactionScreen");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new AddTransactionScreen(frame));
        frame.pack();
        frame.setVisible (true);
    }
}
