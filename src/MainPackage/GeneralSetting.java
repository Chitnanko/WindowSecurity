package MainPackage;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
public class GeneralSetting extends JPanel implements ActionListener{
	private String filepath=(new PasswordAndRecovery().filepath+"generalSetting.txt");
	private File conditionFile=new File(new PasswordAndRecovery().filepath+"generalSetting.txt");
	private int[] readRecord=(new Method().readRecord(filepath,18));
	private int[] actionNumber=new int[18];
	private JPanel checkBoxP=new JPanel(),
			buttonP=new JPanel();
	private JButton selectAll=new JButton("SelectAll"),
			deSelectAll=new JButton("DeSelectAll"),
			saveChange=new JButton("SaveChange");
	private JCheckBox[] CheckBoxNumber=new JCheckBox[18];
	private String[] CheckBoxName={"Disable FolderOption","Disable RightClickContentMenu","Disable WindowKey","Hide MyComputer",
			"Hide ToolTip","Hide All DesktopIcon","DisableComputerManagment","Hide All Program","Hide Recycle bin","Disable Desktop(Background)",
			"Hide LogOf","Hide Shutdown","Hide Search","Disable TaskBoxPoup-up","HideComputerManage","ChangeMouse","Hide File Menu","No CD-ROM AutoRun"};
	public GeneralSetting(){
		this.setSize(650,380);
		this.setLayout(new BorderLayout());
		for(int i=0;i<CheckBoxNumber.length;i++){
			CheckBoxNumber[i]=new JCheckBox(CheckBoxName[i]);
			checkBoxP.add(CheckBoxNumber[i]);
			/////////////////////assign all actionNumber with 0
			actionNumber[i]=0;
		}
		checkBoxP.setLayout(new GridLayout(6,3,20,30));
		checkBoxP.setBackground(new Method().getColor());
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
		////////////////////////////////////////////////////////
		this.add(checkBoxP,BorderLayout.NORTH);
		buttonP.setBackground(new Method().getColor());
		buttonP.setLayout(new FlowLayout(FlowLayout.CENTER,0,25));
		buttonP.setBorder(BorderFactory.createBevelBorder(0));
		buttonP.add(selectAll);
		buttonP.add(deSelectAll);
		buttonP.add(saveChange);
		selectAll.addActionListener(this);
		deSelectAll.addActionListener(this);
		saveChange.addActionListener(this);
		this.add(buttonP,BorderLayout.CENTER);
		this.setVisible(true);
	}
	public static void main(String args[]){
		new GeneralSetting();
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==selectAll){
			selectAndDeselect(true);
		}
		else if(e.getSource()==deSelectAll){
			selectAndDeselect(false);
		}
		//////////////clicked saveChangebutton
		else
		{
			String forCallExe=(new Method().getExePath()+"general.bat");
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
			forCallExe+=forArgument;
			System.out.println(forCallExe);
			new Method().recordAction(filepath,forArgument,forCallExe);
		}
	}
	private void selectAndDeselect(boolean b)
	{
		for(int i=0;i<CheckBoxNumber.length;i++)
		{
			CheckBoxNumber[i].setSelected(b);
			//////////////assign actionNumber with 1 if selected else assign 0
			if(b)	
				actionNumber[i]=1;
			else
				actionNumber[i]=0;
		}
	}
}
