package MainPackage;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.swing.*;
public class ProgramSec extends JPanel implements ActionListener {
	private String disablePfile=(new PasswordAndRecovery().filepath)+"disableCount.txt";
	private File disableFile=new File((new PasswordAndRecovery().filepath)+"disableCount.txt");
	private String programNamFile=(new PasswordAndRecovery().filepath)+"programNames.txt";
	private File programFile=new File((new PasswordAndRecovery().filepath)+"programNames.txt");
	private String filepath=(new PasswordAndRecovery().filepath+"programSec.txt");
	private File conditionFile=new File(new Method().getFilePath()+"programSec.txt");
	private String forCallExe=(new Method().getExePath()+"programSec.bat");
	private int[] readRecord=(new Method().readRecord(filepath,8));
	private int[] actionNumber=new int[8];
	private JCheckBox[] CheckBoxNumber=new JCheckBox[8];
	private String[] jCheckBoxName={"Disable NotePad","Disable Wmplayer","Disable Add or remove program",
									"Disable Internet Explorer","Disable Paint","Disable Firefox","Disable Viscual Studio",
									"Disable Office"};
	////////////////////////////////////////////////
	private JComboBox userSelect;
	private JButton selectAll=new JButton("SelectAll"),
			deSelectAll=new JButton("DeselectAll"),
			saveChange=new JButton("SaveChange"),
			addProgram=new JButton("addProgram"),
			removeProgram=new JButton("removeProgram"),
			disableProgram=new JButton("Disable");
	private JPanel Disp=new JPanel(),
			UserSelection=new JPanel(),
			buttonP=new JPanel();
	/////////////////////////////////////////////////for add program panel
	String[] programNames=new String[26];
	String[] accessProgram;
	int count=0;
	String[] disableCount=new String[26];
	int countProgram=0;
	//////////////////////////////////////////////Construtor for this class
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ProgramSec(){
		this.setSize(650,385);
		this.setLayout(new BorderLayout());
		this.setBackground(new Method().getColor());
		this.setBorder(BorderFactory.createBevelBorder(0));
		////////////////////////////////////////////////////JCheckBox Panel
		Disp.setLayout(new GridLayout(4,2,50,45));
		Disp.setBackground(new Method().getColor());
		Disp.setBorder(BorderFactory.createBevelBorder(1));
		//////////////////////////////////////////assign array disableCount with 0
		for(int i=0;i<disableCount.length;i++)
		{
			disableCount[i]="0";
		}
		////////add JCheckBox component using for loop
		for(int i=0;i<CheckBoxNumber.length;i++)
		{
			CheckBoxNumber[i]=new JCheckBox(jCheckBoxName[i]);
			Disp.add(CheckBoxNumber[i]);
			actionNumber[i]=0;
		}
		  ///////////////Read Record when user change state again remain when program reopen
				if(conditionFile.exists())
				{
				 for(int i=0;i<readRecord.length;i++)
				 {
					if(readRecord[i]==1)
					{
						actionNumber[i]=readRecord[i];
						CheckBoxNumber[i].setSelected(true);
					}else if(readRecord[i]==0)
					{
						actionNumber[i]=readRecord[i];
						CheckBoxNumber[i].setSelected(false);
					}
				 }
				}
				///////////////////////////////for user selection section
				if(programFile.exists() && disableFile.exists())
				{
					accessProgram=new Method().ReadDisProgram(programNamFile);
					disableCount=new Method().ReadDisProgram(disablePfile);
					userSelect=new JComboBox(accessProgram);
				} else
					userSelect.addItem("Program");

		/////////////////////////////////////UserSelectionPanel
		UserSelection.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));
		UserSelection.setBackground(new Method().getColor());
		UserSelection.setBorder(BorderFactory.createBevelBorder(0));
		UserSelection.add(userSelect);
		////////////////////////////////action for userSelect combo box
		userSelect.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				Object itemIndex=userSelect.getSelectedIndex();
				if(disableCount[((int) itemIndex)+1].equals("1"))
				{
					disableProgram.setText("Enable");
				}
				else 
					disableProgram.setText("Disable");
			}
		});
		
		UserSelection.add(addProgram);
		addProgram.addActionListener(this);
		
		UserSelection.add(removeProgram);
		removeProgram.addActionListener(this);
		
		UserSelection.add(disableProgram);
		disableProgram.addActionListener(this);
		//////////////////////////////////////////////buttonPanel
		buttonP.setLayout(new FlowLayout(FlowLayout.CENTER,0,25));
		buttonP.setBackground(new Method().getColor());
		buttonP.setBorder(BorderFactory.createBevelBorder(0));
		buttonP.add(selectAll);
		buttonP.add(deSelectAll);
		buttonP.add(saveChange);
		saveChange.addActionListener(this);
		selectAll.addActionListener(this);
		deSelectAll.addActionListener(this);
		this.add(Disp,BorderLayout.NORTH);
		this.add(buttonP,BorderLayout.CENTER);
		this.add(UserSelection,BorderLayout.SOUTH);
		this.setVisible(true);
	}
	/////////////////////////////////end Constructor
	public static void main(String args[]){
		new ProgramSec();
	}
	////////////////////////////////////////////////////////action performed 
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==selectAll){
			selectedMethod(true);
		}
		////////////////////////////////////for deselectAll button from button panel
		else if(e.getSource()==deSelectAll){
			selectedMethod(false);
		}
		//////////////////////////////////////////for saveChange button from button panel
		else if(e.getSource()==saveChange)
		{
			String forArgument=""; 
			for(int i=0;i<CheckBoxNumber.length;i++)
			{
				if(CheckBoxNumber[i].isSelected())
					actionNumber[i]=1;
				else actionNumber[i]=0;
			}
			for(int i=0;i<actionNumber.length;i++)
			{
				forArgument+="#"+actionNumber[i];
				forCallExe+=" "+actionNumber[i];
			}
			//System.out.println(forCallExe);
			new Method().recordAction(filepath,forArgument,forCallExe);
		}
		//////////////////////////////////////////for addProgram button from userSelection panel
		else if(e.getSource()==addProgram)
		{
			String st;
			while(true)
		   {
			st=JOptionPane.showInputDialog(null,"Enter Program Name like notepad.exe","Enter Program Name"
					,JOptionPane.QUESTION_MESSAGE);
			if(!st.isEmpty())
		   {
			programNames[countProgram]=st;
			userSelect.addItem(programNames[countProgram++]);
			//new Method().userSelection(programNames, programNamFile);
			break;
		   }
			else
			{
				JOptionPane.showMessageDialog(null,"Program name is empty");
				continue;
			}
		  }
		}
		//////////////////////////////////////for program remove button
		else if(e.getSource()==removeProgram)
		{
			//String removeProgramName=new String((String) userSelect.getSelectedItem());
			userSelect.removeItem(userSelect.getSelectedItem());
			//disableCount[userSelect.getSelectedIndex()]
			//System.out.println(removeProgramName);
			//new Method().removeUserSelection(removeProgramName,programNamFile);
		}
		//////////////////////////disable program button
		if(e.getSource()==disableProgram)
		{
			if(disableCount[userSelect.getSelectedIndex()].equals("0"))
			{
			 disableCount[userSelect.getSelectedIndex()]="1";
			 disableProgram.setText("Enable");
			 new Method().DisableProgram(programNames,programNamFile);
			 new Method().DisableProgram(disableCount,disablePfile);
			 
			}
			else{
				 disableCount[userSelect.getSelectedIndex()]="0";
				 disableProgram.setText("Disable");
				 new Method().DisableProgram(programNames,programNamFile);
				 new Method().DisableProgram(disableCount,disablePfile);
			}
		}

	}
	///////////////////////////////////////////////////////////////////end action performed
	private void selectedMethod(boolean b)
	{
		for(int i=0;i<CheckBoxNumber.length;i++)
		{
			CheckBoxNumber[i].setSelected(b);
		}
	}
}
	

