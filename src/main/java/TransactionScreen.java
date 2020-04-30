
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class TransactionScreen extends JPanel {
    private JLabel jcomp1;
    private JLabel jcomp2;
    private JList jcomp3;
    private JList jcomp4;

    public TransactionScreen() {
        //construct preComponents
        String[] jcomp3Items = {"Item 1", "Item 2", "Item 3"};

        //construct components
        jcomp1 = new JLabel ("Transactions");
        jcomp3 = new JList (jcomp3Items);

        //adjust size and set layout
        setPreferredSize (new Dimension (944, 563));
        setLayout (null);

        JScrollPane gameScrollPane = new JScrollPane();
        gameScrollPane.setViewportView(jcomp3);
        jcomp3.setLayoutOrientation(JList.VERTICAL);
        
        //add components
        add (jcomp1);
        add (gameScrollPane);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (25, 15, 100, 25);
        gameScrollPane.setBounds (25, 90, 500, 40);
    }


    public static void main (String[] args) {
        JFrame frame = new JFrame ("TransactionScreen");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new TransactionScreen());
        frame.pack();
        frame.setVisible (true);
    }
}
