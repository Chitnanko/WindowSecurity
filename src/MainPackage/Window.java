package MainPackage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
public class Window extends JPanel implements ActionListener{
	private String filepath=(new PasswordAndRecovery().filepath+"windowSec.txt");
	private File conditionFile=new File(new Method().getFilePath()+"windowSec.txt");
	private int[] readRecord=(new Method().readRecord(filepath,6));
	private int[] actionNumber=new int[6];
	JButton[] jbuttonObj=new JButton[12];
	JLabel[] jlabelObj=new JLabel[6];
	String[] jlabelName={"CMD","TaskManager","GroupPolicy","ControlPanel","UAC","RunBox"};
	
	JLabel cmd=new JLabel("CMD",JLabel.CENTER),
			taskma=new JLabel("TaskManager",JLabel.CENTER),
			grouppolicy=new JLabel("GroupPolicy",JLabel.CENTER),
			controllPanel=new JLabel("ControlPanel",JLabel.CENTER),
			Uac=new JLabel("UAC",JLabel.CENTER),
			run=new JLabel("RunBox",JLabel.CENTER);
	JButton SaveChange=new JButton("SaveChange");
	JPanel SavePanel=new JPanel(),
			EnaDisPanel=new JPanel();
	public Window(){
		for(int i=0;i<jbuttonObj.length;i++)
		{
			if(i==0 || i%2==0)							//when i is 0 or even we create disable button
			{
				jbuttonObj[i]=new JButton("Disable");	//////////////////for Disable button
			}
			else {
				jbuttonObj[i]=new JButton("Enabel");		///////////////////create enable button when i is odd number				
			}
		}
		for(int i=0;i<jlabelObj.length;i++)
		{
			jlabelObj[i]=new JLabel(jlabelName[i],JLabel.CENTER);
			actionNumber[i]=0;										//this array is  for count user clicked enable or disable later
		}
		this.setLayout(new BorderLayout());
		this.setBounds(100,100,650,380);
		this.setBackground(new Color(153,153,204));
		EnaDisPanel.setLayout(new GridLayout(6,3,50,10));
		EnaDisPanel.setPreferredSize(new Dimension(650,280));
		EnaDisPanel.setBackground(new Color(153,153,153));
		EnaDisPanel.setBorder(BorderFactory.createBevelBorder(0));
		///////////////////add component with array using for loop
		for(int i=0;i<jlabelObj.length;i++)
		{
			EnaDisPanel.add(jlabelObj[i]);
			EnaDisPanel.add(jbuttonObj[i*2]);
			jbuttonObj[i*2].addActionListener(this);
			EnaDisPanel.add(jbuttonObj[i*2+1]);
			jbuttonObj[i*2+1].addActionListener(this);
			jbuttonObj[i*2+1].setEnabled(false);
		}
		///////////////Read Record when user change state again remain when program reopen
		if(conditionFile.exists())
		{
		 for(int i=0;i<readRecord.length;i++)
		 {
			if(readRecord[i]==1)
			{
				actionNumber[i]=readRecord[i];
				jbuttonObj[i*2].setEnabled(false);
				jbuttonObj[i*2+1].setEnabled(true);
			}else if(readRecord[i]==0)
			{
				actionNumber[i]=readRecord[i];
				jbuttonObj[i*2].setEnabled(true);
				jbuttonObj[i*2+1].setEnabled(false);
			}
		 }
		}
		///////////////////////////////////
		SavePanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
		SavePanel.setBackground(new Method().getColor());
		SavePanel.setPreferredSize(new Dimension(650,100));
		SavePanel.setBorder(BorderFactory.createBevelBorder(0));
		SaveChange.setPreferredSize(new Dimension(110,50));
		SavePanel.add(SaveChange);
		SaveChange.addActionListener(this);
		this.add(EnaDisPanel,BorderLayout.NORTH);
		this.add(SavePanel,BorderLayout.CENTER);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()!=SaveChange)
		{
		for(int i=0;i<actionNumber.length;i++)
		  { 
			if(e.getSource()==jbuttonObj[i*2])			//when actioncommand equal with jbuttonObj disable button
			{
				actionNumber[i]=1;
				jbuttonObj[i*2].setEnabled(false);
				jbuttonObj[i*2+1].setEnabled(true);
			}
			else if(e.getSource()==jbuttonObj[i*2+1])	 //action command equal with jbuttonObj enable button
			{
				actionNumber[i]=0;
				jbuttonObj[i*2+1].setEnabled(false);
				jbuttonObj[i*2].setEnabled(true);
			}
		   }
		 }
		/////////////////////clicked savechange button
	else
		{
			String forCallExe=(new Method().getExePath()+"windowSec.bat");
			String forArgument=""; 
			for(int i=0;i<actionNumber.length;i++)
			{
				forArgument+="#"+actionNumber[i];
				forCallExe+=" "+actionNumber[i];
			}
			System.out.println(forCallExe);
			new Method().recordAction(filepath,forArgument,forCallExe);
		}
	}
	
}
