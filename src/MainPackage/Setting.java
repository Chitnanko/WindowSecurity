package MainPackage;
import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Setting extends JPanel implements ActionListener{
	int questionIndex;
	int timeIndex;
	int actionIndex;
	int specifyDisable=0;
	String filepath=((new PasswordAndRecovery().filepath));
	File file=new File(filepath+"setting.txt");
	String forRecovery="";
	//////////////////////////////////////////////////////////////////program and window password variable
	JLabel changePassword=new JLabel("Change Program Password",JLabel.CENTER),
			changeWPassword=new JLabel("Change Window Password",JLabel.CENTER),
			disableChangeWPassword=new JLabel("Disable change Window Password",JLabel.CENTER);		//disable changing window password from controlpanel
	////////////////////////////// password recovery option
	JLabel question=new JLabel("Question"),
			answer=new JLabel("Answer"),
			TimeLimitOption=new JLabel("Times Limit Option");
	String Question[]={"What is your Father's name?","What is your Mother's name?","What is your Teacher's name?","What is your  name?"};
	String Action[]={"Shutdown","Restart","Logoff"};
	String Time[]={"3","4","5","6","7"};
	JComboBox Questioncombo=new JComboBox(Question),
			TimeLimitCombo=new JComboBox(Time),
			actionCombo=new JComboBox(Action);
	JButton changePButton=new JButton("Change"),
			changeWButton=new JButton("Change"),
			DisableButton=new JButton("Disable"),
			AnssaveButton=new JButton("Save"),
			ActionsaveButton=new JButton("Save");
	JCheckBox timeCheckBox=new JCheckBox(),
			   actionCheckBox=new JCheckBox();
	JTextField answerTextField=new JTextField(20);
	JPanel changePanel=new JPanel(),
			recoveryPanel=new JPanel(),
			QSPanel=new JPanel(),
			TimeLimitPanel=new JPanel(),
			savePanel=new JPanel(),
			AnssavePanel=new JPanel(),
			ActionPanel=new JPanel(),
			TimeLimitGridPanel=new JPanel(),
			TimeLimitCheckPanel=new JPanel();
	
	public Setting(){
		this.setSize(650,380);
		this.setLayout(new FlowLayout());
		this.setBackground(new Method().getColor());
		this.setBorder(BorderFactory.createBevelBorder(0));
		//changePanel.setBorder(BorderFactory.createLineBorder(new Method().getColor()));
		changePanel.setLayout(new GridLayout(3,2,0,20));
		changePanel.setPreferredSize(new Dimension(500,130));
		changePanel.setBackground(new Method().getColor());
		changePanel.add(changePassword);
		changePanel.add(changePButton);
		changePButton.addActionListener(this);
		changePanel.add(changeWPassword);
		changePanel.add(changeWButton);
		changeWButton.addActionListener(this);
		changePanel.add(disableChangeWPassword);
		changePanel.add(DisableButton);
		DisableButton.addActionListener(this);
		////////////////////////////////////////////////
		recoveryPanel.setLayout(new BorderLayout(10,10));
		recoveryPanel.setPreferredSize(new Dimension(600,210));
		recoveryPanel.setBorder(BorderFactory.createTitledBorder("Recovery Option"));
		recoveryPanel.setBackground(new Method().getColor());
		
		QSPanel.setLayout(new GridLayout(2,2,30,0));
		QSPanel.setBackground(new Method().getColor());
		QSPanel.add(question);
		QSPanel.add(answer);
		QSPanel.add(Questioncombo);
		QSPanel.add(answerTextField);
		
		//recoveryPanel.add(AnssavePanel,BorderLayout.SOUTH);
		
		TimeLimitPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		TimeLimitPanel.setPreferredSize(new Dimension(600,110));
		TimeLimitPanel.setBorder(BorderFactory.createLineBorder(new Method().getColor()));
		TimeLimitPanel.setBackground(new Method().getColor());
		
		TimeLimitGridPanel.setLayout(new GridLayout(2,2,5,5));
		TimeLimitGridPanel.setPreferredSize(new Dimension(600,60));
		TimeLimitGridPanel.setBackground(new Method().getColor());
		//////////////////////////////////////////////////////for Time Limit Option checkbox panel
		TimeLimitCheckPanel.setLayout(new FlowLayout(FlowLayout.CENTER,5,10));
		TimeLimitCheckPanel.setBackground(new Method().getColor());
		TimeLimitCheckPanel.add(timeCheckBox);
		timeCheckBox.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					TimeLimitCombo.setEnabled(true);
				   actionCheckBox.setEnabled(true);
				}
				else 
					{
					TimeLimitCombo.setEnabled(false);
					actionCombo.setEnabled(false);
					actionCheckBox.setSelected(false);
					actionCheckBox.setEnabled(false);
					}
			}
			}
				);
		TimeLimitCheckPanel.add(new JLabel("Specify Time Limit Option"));
		TimeLimitCombo.setEnabled(false);
		TimeLimitGridPanel.add(TimeLimitCheckPanel);
		TimeLimitGridPanel.add(TimeLimitCombo);
		/////////////////////////////////////////////////for action checkbox panel
		ActionPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		ActionPanel.setBackground(new Method().getColor());
		ActionPanel.add(actionCheckBox);
		actionCheckBox.setEnabled(false);
		ActionPanel.add(new JLabel("Action when reached limit"));
		TimeLimitGridPanel.add(ActionPanel);
		TimeLimitGridPanel.add(actionCombo);
		actionCheckBox.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					actionCombo.setEnabled(true);
				}
				else 
					{
					actionCombo.setEnabled(false);
					}
			}
			}
				);
		actionCombo.setEnabled(false);
		if(file.exists())
		{
			recordSetting();
		}
		///////////////////////////////////////////////////save Panel
		savePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		savePanel.setPreferredSize(new Dimension(590,45));
		savePanel.setBorder(BorderFactory.createBevelBorder(0));
		savePanel.setBackground(new Method().getColor());
		savePanel.add(ActionsaveButton);
		ActionsaveButton.addActionListener(this);
		////////////////////////////////////////////////import TimeLimitGridPanel and save button
		TimeLimitPanel.add(TimeLimitGridPanel);
		TimeLimitPanel.add(savePanel);
		
		recoveryPanel.add(QSPanel,BorderLayout.NORTH);
		recoveryPanel.add(TimeLimitPanel,BorderLayout.CENTER);
		this.add(changePanel,BorderLayout.NORTH);
		this.add(recoveryPanel,BorderLayout.CENTER);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==changePButton)
		{
			while(true)
			{
			    if(new PasswordAndRecovery().ChangePPassword())
			    	break; 
			    ///////////////////////break when return value is true.
			}
		}
		else if(e.getSource()==changeWButton)
		{
			while(true)
			{
			    if(new PasswordAndRecovery().ChangeWPassword())
			    	break;
			}
		}
		else if(e.getSource()==DisableButton)
		{
			if(specifyDisable==0)
			{
				DisableButton.setText("Enable");
				callDisalbleExe();
				specifyDisable=1;
			}
			else if(specifyDisable==1)
			{
				DisableButton.setText("Disable");
				callDisalbleExe();
				specifyDisable=0;
			}
		}
		else if(e.getSource()==ActionsaveButton)
		{
			questionIndex=Questioncombo.getSelectedIndex();
			forRecovery="#"+questionIndex;
			if(answerTextField.getText().equals(""))
			{
				forRecovery+="#"+"111";
			}
			else
				forRecovery+="#"+answerTextField.getText();
			///////////////////////////////if check time limit checkbox
			if(timeCheckBox.isSelected())
			{
				timeIndex=TimeLimitCombo.getSelectedIndex();
				forRecovery+="#"+timeIndex;
			}
			else
				forRecovery+="#"+"111";
			/////////////////////////////////////////////for action such as restart logog shutdown
			if(actionCheckBox.isSelected() && timeCheckBox.isSelected())
			{
				actionIndex=actionCombo.getSelectedIndex();
				forRecovery+="#"+actionIndex;
			}
			else
			{
				forRecovery+="#"+"111";
			}
			///////////////////write forRecovery to file
			try{
				PrintWriter pw=new PrintWriter(new FileWriter(filepath+"setting.txt"));
				pw.println(forRecovery);
				pw.close();
			}catch(Exception e1)
			{
				e1.printStackTrace();
			}
		}
	}
	public void callDisalbleExe()
	{
		PrintWriter pw;
		String st;
		try
		{
			st="cmd.exe /c start "+new Method().getExePath()+"disWinPass.bat"+" "+specifyDisable;
			System.out.println(st);
			Runtime.getRuntime().exec(st);
			pw=new PrintWriter(new FileWriter(filepath+"disaWinPass.txt"));
			pw.println(specifyDisable);
			pw.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	///////////////////////////when load program call this method
	public void recordSetting()
	{
		String[] record=new String[4];
		int countSetting=0;
		/////////////////////////////read file
		record=returnRecord();
		//////////////////////////////////set setting last user action
		if(!record[countSetting].equals("111"))
		{
			Questioncombo.setSelectedIndex(Integer.parseInt(record[countSetting]));
			answerTextField.setText(record[++countSetting]);
		}
		if(!record[++countSetting].equals("111"))
		{
			timeCheckBox.setEnabled(true);
			timeCheckBox.setSelected(true);
		    TimeLimitCombo.setEnabled(true);
			actionCheckBox.setEnabled(true);
			actionCheckBox.setSelected(false);
			actionCombo.setEnabled(false);
			
			TimeLimitCombo.setSelectedIndex(Integer.parseInt(record[countSetting]));
		}
		if(!record[++countSetting].equals("111"))
		{
			actionCombo.setSelectedIndex(Integer.parseInt(record[countSetting]));
		}
	}
	///////////////////////////////////end recordSetting Method()
	@SuppressWarnings("resource")
	public String[] returnRecord()
	{
		String[] record=new String[4];
		BufferedReader br;
		String st;
		StringTokenizer sToken;
		int count=0;
		try
		{
	      br=new BufferedReader(new FileReader(filepath+"setting.txt"));
	      st=br.readLine();
	      sToken=new StringTokenizer(st,"#");
	      while(sToken.hasMoreTokens())
	      {
	    	  record[count++]=sToken.nextToken();
	      }
		}catch(Exception e)
		 {
			e.printStackTrace();
		 }
		return record;
	}
}
