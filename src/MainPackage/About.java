package MainPackage;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;
public class About extends JPanel implements ActionListener,ListSelectionListener{
	JPanel AboutP=new JPanel();
	JButton about=new JButton("About");
	JScrollPane scroll;
	JTextArea textaera=new JTextArea(18,43);
	JList l;
	String strabout="";
	String label=new String("\n   This software is developed by\n " +
			"\t1.Chit Nan Ko\n \t2.Nay Zin Htoo\n \t3.Ravi Kant\n \t4.Zin Mar Aung\n \t5.Myat Thae Nu Naing\n");
	
	String[] nam={"Autot Run","USB Port","CMD","Task  Manager","Group Policy","Control Panel","UAC","Run Box","Program Security"
			,"File Folder","General","Storage Security","Window Security","Internet Security","Setting"}; 
	
	private String filepath=(new Method().getFilePath()+"help/");
	BufferedReader br;
	
	public About(){
		this.setSize(650,300);
		this.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		this.setBorder(BorderFactory.createBevelBorder(0));
		this.setBackground(new Method().getColor());
		l=new JList(nam);
		scroll=new JScrollPane(l);
		l.addListSelectionListener(this);
		AboutP.setLayout(new FlowLayout(FlowLayout.CENTER,0,25));
		AboutP.setPreferredSize(new Dimension(150,300));
		AboutP.setBorder(BorderFactory.createLineBorder(new Method().getColor(),2));
		
		about.setPreferredSize(new Dimension(80,40));
		about.addActionListener(this);
		
		AboutP.add(about);
		AboutP.add(scroll);
		
		textaera.setLayout(new FlowLayout());
		textaera.setBorder(BorderFactory.createLineBorder(new Method().getColor(),2));
		textaera.setText(label);
		this.add(AboutP);
		this.add(textaera);
		
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==about){
			if(!textaera.equals("")){
				textaera.setText("");
				l.removeSelectionInterval(0,15);
			}
			 textaera.append(label);
			 textaera.setEditable(false);
		}
		
	}
	public void valueChanged(ListSelectionEvent le){
		textaera.setEditable(false);
		if(l.getSelectedIndex()==0){
			readFile(filepath+"auto.txt") ;
		}
		if(l.getSelectedIndex()==1){
			readFile(filepath+"usb.txt");
		}
		if(l.getSelectedIndex()==2){
			readFile(filepath+"cmd.txt");
			}
		if(l.getSelectedIndex()==3){
			readFile(filepath+"task.txt");
		}
		if(l.getSelectedIndex()==4){
			readFile(filepath+"group.txt");
		}
		if(l.getSelectedIndex()==5){
			readFile(filepath+"control.txt");
		}
		if(l.getSelectedIndex()==6){
			readFile(filepath+"uac.txt");
		}
		if(l.getSelectedIndex()==7){
			readFile(filepath+"run.txt");
		}
		if(l.getSelectedIndex()==8){
			readFile(filepath+"program.txt");
			}
		if(l.getSelectedIndex()==9){
			readFile(filepath+"file.txt");
		}
		if(l.getSelectedIndex()==10){
			readFile(filepath+"general.txt");
		}
		if(l.getSelectedIndex()==11){
			readFile(filepath+"storage.txt");
		}
		if(l.getSelectedIndex()==12){
			readFile(filepath+"window.txt");
		}
		if(l.getSelectedIndex()==13){
			readFile(filepath+"internet.txt");
		}
		if(l.getSelectedIndex()==14){
			readFile(filepath+"setting.txt");
		}
		if(l.getSelectedIndex()==15){
						readFile(filepath+"drive.txt");
		}
	}
	public void readFile(String st)
	{
		try{
			br=new BufferedReader(new FileReader(st) );
			textaera.setText("");
			while((strabout=br.readLine())!=null)
				textaera.append(strabout+"\n");		
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
	}
}