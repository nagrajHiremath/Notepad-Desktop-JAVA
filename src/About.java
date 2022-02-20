package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class About extends JFrame implements ActionListener {

    JButton b1;

    About(){
        setBounds(600, 200, 600, 400);
        setLayout(null);

        ImageIcon I = new ImageIcon(ClassLoader.getSystemResource("com/company/icons/windows.png"));
        Image I2 = I.getImage().getScaledInstance(400, 80, Image.SCALE_DEFAULT);
        ImageIcon I3 = new ImageIcon(I2);
        JLabel JL = new JLabel(I3);
        JL.setBounds(150,40,400,80);
        add(JL);

        ImageIcon I1 = new ImageIcon(ClassLoader.getSystemResource("com/company/icons/notepad.png"));
        Image I4 = I1.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
        ImageIcon I5 = new ImageIcon(I4);
        JLabel JL1 = new JLabel(I5);
        JL1.setBounds(50,180,70,70);
        add(JL1);

        JLabel l3 = new JLabel("<html> Microsoft windows <br> Version 6.1 <br> " +
                "Copyright All right Reserved 2009 Microsoft Corporation <br> " +
                "Notepad is word processing application <br>" +
                "This product is liscned by Microsoft Corporation to <br>-Nagraj Hiremath </html>");
        l3.setBounds(150,130,500,300);
        l3.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,15));
        add(l3);

        b1 = new JButton("OK");
        b1.setBounds(580,500,80,25);
        b1.addActionListener(this);
        add(b1);
    }
    public void actionPerformed(ActionEvent ae){
        this.setVisible(false);
    }

    public static void main(String[] args) {

        new About().setVisible(true);
    }

}
