package MainPackage;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
public class Internet extends JPanel implements ActionListener{
	private String filepath=(new PasswordAndRecovery().filepath+"internetSec.txt");
	private File conditionFile=new File(new Method().getFilePath()+"internetSec.txt");
	private int[] readRecord=(new Method().readRecord(filepath,7));
	private int[] actionNumber=new int[7];
	JButton buttons[]=new JButton[14];
	String[] buttonNames={"Disable","Enable","Disable","Enable","Disable","Enable","Disable",
			"Enable","Disable","Enable","Disable","Enable","Close","Open"};
	String[] name={"DisableInternetOption","Wifi Internet",
			"Dial_Up_Prefernce","TCP/IP Configuration","Lan_Card","Device Manager","FireWall"};
	JLabel label[]=new JLabel[7];	
	JButton save=new JButton("Save Change");
	JPanel UpPanel=new JPanel(),
		   ButtonPanel=new JPanel();
	public Internet(){
		this.setSize(650,300);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createBevelBorder(0));
		UpPanel.setLayout(new GridLayout(7,3,10,20));
		UpPanel.setBackground(new Method().getColor());
		for(int i=0;i<label.length;i++){
			label[i]=new JLabel(name[i],JLabel.CENTER);
		}
		for(int i=0;i<buttonNames.length;i++){
			buttons[i]=new JButton(buttonNames[i]);
		}
	/////////////////add components on UpPanel using for loop and array
		for(int i=0;i<label.length;i++)
		{
			UpPanel.add(label[i]);
			UpPanel.add(buttons[i*2]);
			buttons[i*2].addActionListener(this);
			UpPanel.add(buttons[i*2+1]);
			buttons[i*2+1].addActionListener(this);
			buttons[i*2+1].setEnabled(false);
		}
///////////////Read Record when user change state again remain when program reopen
	if(conditionFile.exists())
	{
	 for(int i=0;i<readRecord.length;i++)
	 {
		if(readRecord[i]==1)
		{
			actionNumber[i]=readRecord[i];
			buttons[i*2].setEnabled(false);
			buttons[i*2+1].setEnabled(true);
		}else if(readRecord[i]==0)
		{
			actionNumber[i]=readRecord[i];
			buttons[i*2].setEnabled(true);
			buttons[i*2+1].setEnabled(false);
		}
	 }
	}
	///////////////////////////////////
		this.add(UpPanel,BorderLayout.NORTH);
		ButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
		ButtonPanel.setBorder(BorderFactory.createBevelBorder(0));

		ButtonPanel.setBackground(new Method().getColor());
		ButtonPanel.add(save);
		save.addActionListener(this);
		this.add(ButtonPanel,BorderLayout.CENTER);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()!=save)
		{
		for(int i=0;i<actionNumber.length;i++)
		  { 
			if(e.getSource()==buttons[i*2])			//when actioncommand equal with jbuttonObj disable button
			{
				actionNumber[i]=1;
				buttons[i*2].setEnabled(false);
				buttons[i*2+1].setEnabled(true);
			}
			else if(e.getSource()==buttons[i*2+1])	 //action command equal with jbuttonObj enable button
			{
				actionNumber[i]=0;
				buttons[i*2+1].setEnabled(false);
				buttons[i*2].setEnabled(true);
			}
		   }
		 }
	else
		{
			String forCallExe=(new Method().getExePath()+"internetSec.bat");
			String forArgument=""; 
			for(int i=0;i<actionNumber.length;i++)
			{
				forArgument+="#"+actionNumber[i];
				forCallExe+=" "+actionNumber[i];
			}
			forCallExe+=forArgument;
			System.out.println(forCallExe);
			new Method().recordAction(filepath,forArgument,forCallExe);
		}
	}
}
