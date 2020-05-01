import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

public class AddInventoryScreen extends JPanel {
    private JRadioButton gameBut;
    private JRadioButton otherBut;
    private JRadioButton videoBut;
    private JRadioButton boardBut;
    private JLabel jcomp3;
    private JLabel jcomp4;
    private JLabel jcomp5;
    private JTextField jcompPublisher;
    private JTextField jcompID;
    private JTextField jcompName;
    private JTextField jcompPrice;
    private JLabel jcomp10;
    private JTextField jcompAgeRating;
    private JLabel jcomp12;
    private JTextField jcompConsole;
    private JLabel jcomp14;
    private JTextField jcompGenre;
    private JLabel jcomp16;
    
    private JButton addItem;
    
    private static JFrame frame;

    public AddInventoryScreen(JFrame frameM) {
        this.frame = frameM;
    	//construct components
        gameBut = new JRadioButton ("Game");
        otherBut = new JRadioButton ("Other");
        
        videoBut = new JRadioButton ("Video");
        boardBut = new JRadioButton ("Board");

        jcomp3 = new JLabel ("ID");
        jcomp4 = new JLabel ("Name");
        jcomp5 = new JLabel ("Price");
        jcompPublisher = new JTextField (5);
        jcompID = new JTextField (5);
        jcompName = new JTextField (5);
        jcompPrice = new JTextField (5);
        jcomp10 = new JLabel ("Publisher");
        jcompAgeRating = new JTextField (5);
        jcomp12 = new JLabel ("Age Rating");
        jcompConsole = new JTextField (5);
        jcomp14 = new JLabel ("Console");
        jcompGenre = new JTextField (5);
        jcomp16 = new JLabel ("Genre");
        addItem = new JButton ("Add Item");

        ButtonGroup buttonG = new ButtonGroup();
        buttonG.add(gameBut);
        buttonG.add(otherBut);
        
        ButtonGroup buttonG2 = new ButtonGroup();
        buttonG2.add(videoBut);
        buttonG2.add(boardBut);
        
        gameBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	jcompAgeRating.setEnabled(true);
            	jcomp12.setEnabled(true);
            	jcompPublisher.setEnabled(true);
            	jcomp10.setEnabled(true);
            	jcompConsole.setEnabled(true);
            	jcomp14.setEnabled(true);
            	jcompGenre.setEnabled(true);
            	jcomp16.setEnabled(true);
            	
            	videoBut.setEnabled(true);
            	boardBut.setEnabled(true);
            }
        });
        
        otherBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	jcompAgeRating.setEnabled(false);
            	jcomp12.setEnabled(false);
            	jcompPublisher.setEnabled(false);
            	jcomp10.setEnabled(false);
            	jcompConsole.setEnabled(false);
            	jcomp14.setEnabled(false);
            	jcompGenre.setEnabled(false);
            	jcomp16.setEnabled(false);
            	
            	videoBut.setEnabled(false);
            	boardBut.setEnabled(false);
            }
        });
        
        videoBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	jcompConsole.setEnabled(true);
            	jcomp14.setEnabled(true);
            }
        });
        
        boardBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	jcompConsole.setEnabled(false);
            	jcomp14.setEnabled(false);
            }
        });
        
        addItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	List<JTextField> list = new ArrayList<JTextField>();
        
            	list.add(jcompID);
            	list.add(jcompName);
            	list.add(jcompPrice);
            	
            	boolean cont = true;
            	for(JTextField tf : list)	{
            		if (tf.getText().equals(""))
            			cont = false;
            	}
            	if(!cont)	{}
            	else	{
	            	
	            	if (gameBut.isEnabled())	{
	            		list.add(jcompAgeRating);
	            		list.add(jcompPublisher);
	            		list.add(jcompGenre);
	            		for(JTextField tf : list)	{
	                		if (tf.getText().equals(""))
	                			cont = false;
	                	}
	            		if(!cont)	{}
	            		else	{
	            			if(videoBut.isEnabled())	{
	            				list.add(jcompConsole);
	            				addGameToDB(list,videoBut.isEnabled());
	            			}
	            			else
	            				addGameToDB(list,videoBut.isEnabled());
	            			frame.dispose();
	            		}
	            	}
	        
	            	if (otherBut.isEnabled())	{
	            		addOtherToDB(list);
	            		frame.dispose();
	            	}
            	}
            }
        });
        
        //adjust size and set layout
        setPreferredSize (new Dimension (944, 563));
        setLayout (null);

        //add components
        add (gameBut);
        add (otherBut);
        add (videoBut);
        add (boardBut);
        add (jcomp3);
        add (jcomp4);
        add (jcomp5);
        add (jcompPublisher);
        add (jcompID);
        add (jcompName);
        add (jcompPrice);
        add (jcomp10);
        add (jcompAgeRating);
        add (jcomp12);
        add (jcompConsole);
        add (jcomp14);
        add (jcompGenre);
        add (jcomp16);
        add (addItem);

        //set component bounds (only needed by Absolute Positioning)
        gameBut.setBounds (10, 10, 100, 25);
        otherBut.setBounds (10, 35, 100, 25);
        videoBut.setBounds (10, 100, 100, 25);
        boardBut.setBounds (10, 125, 100, 25);
        
        jcomp3.setBounds (150, 5, 100, 25);
        jcomp4.setBounds (280, 5, 100, 25);
        jcomp5.setBounds (410, 5, 100, 25);
        jcompPublisher.setBounds (565, 30, 130, 25);
        jcompID.setBounds (150, 30, 100, 25);
        jcompName.setBounds (280, 30, 100, 25);
        jcompPrice.setBounds (410, 30, 120, 25);
        jcomp10.setBounds (565, 5, 100, 25);
        jcompAgeRating.setBounds (725, 30, 130, 25);
        jcomp12.setBounds (725, 5, 100, 25);
        jcompConsole.setBounds (280, 125, 100, 25);
        jcomp14.setBounds (280, 100, 100, 25);
        jcompGenre.setBounds (150, 125, 100, 25);
        jcomp16.setBounds (150, 100, 100, 25);
        addItem.setBounds(10, 400, 100, 25);
    }

    private void addGameToDB(List<JTextField> list, boolean video)	{
    	SQLConnection sqlCon = new SQLConnection();
    	sqlCon.openConnection();

    	sqlCon.closeConnection();
    }
    
    private void addOtherToDB(List<JTextField> list)	{
    	SQLConnection sqlCon = new SQLConnection();
    	sqlCon.openConnection();

    	sqlCon.closeConnection();
    }

	public static void main (String[] args) {
        frame = new JFrame ("AddInventoryScreen");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new AddInventoryScreen(frame));
        frame.pack();
        frame.setVisible (true);
    }
}
