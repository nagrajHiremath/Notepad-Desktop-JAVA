package src;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.desktop.AboutEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;

public class JavaPad extends JFrame implements ActionListener {

    JTextArea area;
    JScrollPane pane;
    String text;

    JavaPad() {

        //to set jframe size
        setBounds(300, 200, 600, 400);

        //MenuBar object to creating a Menubar
        JMenuBar menubar = new JMenuBar();

//***************************************************************************************************************
        //to creating a menu
        JMenu file = new JMenu("File");

        //creating menuItems
        JMenuItem newDoc = new JMenuItem("New");
        //setting shortcuts
        newDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        newDoc.addActionListener(this);

        JMenuItem open = new JMenuItem("Open");
        //setting shortcuts
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        open.addActionListener(this);

        JMenuItem save = new JMenuItem("Save");
        //setting shortcuts
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        save.addActionListener(this);

        JMenuItem print = new JMenuItem("Print");
        //setting shortcuts
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        print.addActionListener(this);

        JMenuItem exit = new JMenuItem("Exit");
        //setting shortcuts
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        exit.addActionListener(this);

        //add menuitems in menu
        file.add(newDoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);

//******************************************************************************************************************

        JMenu edit = new JMenu("Edit");

        JMenuItem copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        copy.addActionListener(this);

        JMenuItem cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        cut.addActionListener(this);

        JMenuItem selectAll = new JMenuItem("Select All");
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        selectAll.addActionListener(this);

        JMenuItem paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
        paste.addActionListener(this);

        edit.add(copy);
        edit.add(cut);
        edit.add(selectAll);
        edit.add(paste);

        //******************************************************************************************************

        JMenu help = new JMenu("Help");

        JMenuItem about = new JMenuItem("About JavaPad");
        about.addActionListener(this);

        help.add(about);

        //***********************************************************************************************************

        //add menu's on Menubar
        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);

        // adding menubar on Jframe
        setJMenuBar(menubar);

        area = new JTextArea();
        area.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        pane = new JScrollPane(area);
        pane.setBorder(BorderFactory.createEmptyBorder());

        add(pane, BorderLayout.CENTER);

    }
    @Override
    public void actionPerformed (ActionEvent ae){

        if(ae.getActionCommand().equals("New")){
            area.setText("");
        }
        else if(ae.getActionCommand().equals("Open")){
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter rest = new FileNameExtensionFilter("text files only", "txt");
            chooser.addChoosableFileFilter(rest);

            int action = chooser.showOpenDialog(this);
            if(action != JFileChooser.APPROVE_OPTION){
                return;
            }
            File file = chooser.getSelectedFile();

            try{
                BufferedReader reader = new BufferedReader(new FileReader(file));
                area.read(reader,null);
            }catch(Exception e){

            }

        }
        else if(ae.getActionCommand().equals("Save")){
            JFileChooser saveas = new JFileChooser();
            saveas.setApproveButtonText("Save");
            int action = saveas.showOpenDialog(this);
            if(action != JFileChooser.APPROVE_OPTION){
                return;
            }
            File filename = new File(saveas.getSelectedFile() + ".txt");
            BufferedWriter outfile = null;
            try {
                outfile = new BufferedWriter(new FileWriter(filename));
                area.write(outfile);
            }catch (Exception e){}

        }
        else if(ae.getActionCommand().equals("Print")){
            try{
                area.print();
            }catch(Exception e){}
        }
        else if(ae.getActionCommand().equals("Exit")){
            System.exit(0);
        }
        else if(ae.getActionCommand().equals("Copy")){
            text = area.getSelectedText();
        }
        else if(ae.getActionCommand().equals("Paste")){
            area.insert(text, area.getCaretPosition());
        }
        else if(ae.getActionCommand().equals("Cut")){
            text = area.getSelectedText();
            area.replaceRange("",area.getSelectionStart(), area.getSelectionEnd());
        }
        else if(ae.getActionCommand().equals("Paste")){
            area.selectAll();
        }
        else if(ae.getActionCommand().equals("About JavaPad")){
            new About().setVisible(true);
        }


    }
    public static void main(String[] args) {

        new JavaPad().setVisible(true);

    }
}
