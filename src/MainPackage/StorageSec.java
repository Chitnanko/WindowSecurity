package MainPackage;
import java.awt.*;
import java.util.List;
import java.awt.event.*;
import javax.swing.*;
public class StorageSec extends JPanel implements ActionListener{
	Method methodObj=new Method();
	PasswordAndRecovery pwdAndRecovery=new PasswordAndRecovery();
	///////////////////////////////////////////////////////
	private int cDrive=0,
				dDrive=0,
			    cdDrive=0,
				ddDrive=0;
	private	int autoRun=0,
				usbPort=0;
	String filepath=pwdAndRecovery.filepath+"storageSec.txt";
	String callForExe=new Method().getExePath()+"storageSec.txt";
	JPanel LeftPanel=new JPanel(),
			BackgroudnP=new JPanel(),
			ButtonPanel=new JPanel(),
			RightPanel=new JPanel(),
			UnHidePanel=new JPanel(),
			HidePanel=new JPanel(),
			AllDrive1=new JPanel(),
			AllDrive2=new JPanel(),
			Drive3=new JPanel(),
			Drive4=new JPanel();
	private JButton[] ButtonNumber=new JButton[16];
	private String[] jButtonName={"Unhide","Hide","Disable","Enable","Disable","Enable","Disable","Enable","Unhide",
									"Hide","Disable","Enable","Disable","Enable","Disable","Enable"};
	JButton saveChange=new JButton("SaveChange");
			
	private JLabel[] jLabelNumber=new JLabel[8];
	private String[] jLabelName={"AutoRun","USB Port","Drive","Hide Drive","AutoRun","USB Port",
								 "This changes will effect only current User","This changes will effect all user"};
	JComboBox cb1,cb2,cb3,cb4;
	String[] scb1={"C drive","D drive"};
	String[] scb2={"C drive","D drive"};
	String[] scb3={"C drive","D drive"};
	String[] scb4={"C drive","D drive"};
	public StorageSec(){
		this.setSize(650, 300);
		//this.setPreferredSize(new Dimension(650,300));
		this.setBackground(new Color(153,153,204));
		this.setLayout(new BorderLayout());
		///////////////////////////////////////
		BackgroudnP.setLayout(new GridLayout(1,2,50,10));
		BackgroudnP.setBackground(new Color(153,153,153));
		BackgroudnP.setBorder(BorderFactory.createBevelBorder(1));
		////////////////////////////////////////////////////////////////
		LeftPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		LeftPanel.setBorder(BorderFactory.createTitledBorder("Current User"));
		LeftPanel.setPreferredSize(new Dimension(300,290));
		LeftPanel.setBackground(new Color(153,153,153));
		LeftPanel.setBorder(BorderFactory.createBevelBorder(0));
		cb1=new JComboBox(scb1);
		cb2=new JComboBox(scb2);
		UnHidePanel.setLayout(new GridLayout(1,3,10,20));
		UnHidePanel.setPreferredSize(new Dimension(300,100));
		UnHidePanel.setBackground(new Color(153,153,153));
		UnHidePanel.setBorder(BorderFactory.createTitledBorder("Hide Drive"));
		
		for(int i=0; i<ButtonNumber.length; i++)
		{
			ButtonNumber[i]=new JButton(jButtonName[i]);
			ButtonNumber[i].addActionListener(this);
		}
		for(int i=0;i<jLabelNumber.length;i++)
		{
			jLabelNumber[i]=new JLabel(jLabelName[i]);
		}
		
		
		UnHidePanel.add(cb1);
		//Un/HidePanel.add(Disable5);
		//UnHidePanel.add(Enable5);
		UnHidePanel.add(ButtonNumber[8]);
		UnHidePanel.add(ButtonNumber[9]);
		HidePanel.setLayout(new GridLayout(1,3,10,20));
		HidePanel.setPreferredSize(new Dimension(300,100));
		HidePanel.setBackground(new Color(153,153,153));
		HidePanel.setBorder(BorderFactory.createTitledBorder("Disable Drive"));
		HidePanel.add(cb2);
		//HidePanel.add(Disable6);
		//HidePanel.add(Enable6);
		HidePanel.add(ButtonNumber[10]);
		HidePanel.add(ButtonNumber[11]);
		UnHidePanel.setPreferredSize(new Dimension(260,60));
		HidePanel.setPreferredSize(new Dimension(260,60));
		AllDrive1.setPreferredSize(new Dimension(260,80));
		AllDrive1.setBackground(new Color(153,153,153));
		AllDrive1.setLayout(new GridLayout(2,3,10,20));
		/*AllDrive1.add(autoRun1);
		AllDrive1.add(Disable7);
		AllDrive1.add(Enable7);
		AllDrive1.add(usbPort1);
		AllDrive1.add(Disable8);
		AllDrive1.add(Enable8);*/
		AllDrive1.add(jLabelNumber[4]);
		AllDrive1.add(ButtonNumber[12]);
		AllDrive1.add(ButtonNumber[13]);
		AllDrive1.add(jLabelNumber[5]);
		AllDrive1.add(ButtonNumber[14]);
		AllDrive1.add(ButtonNumber[15]);
		LeftPanel.add(UnHidePanel);
		LeftPanel.add(HidePanel);
		LeftPanel.add(AllDrive1);
		//LeftPanel.add(currentUser);
		LeftPanel.add(jLabelNumber[6]);
		///////////////////////////////////////////////////////////////////////
		RightPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		RightPanel.setPreferredSize(new Dimension(300,290));
		RightPanel.setBackground(new Color(153,153,153));
		RightPanel.setBorder(BorderFactory.createTitledBorder("All User"));
		RightPanel.setBorder(BorderFactory.createBevelBorder(0));
		Drive3.setPreferredSize(new Dimension(260,60));
		Drive3.setBackground(new Color(153,153,153));
		Drive4.setPreferredSize(new Dimension(260,60));
		Drive4.setBackground(new Color(153,153,153));
		cb3=new JComboBox(scb3);
		cb4=new JComboBox(scb4);
		AllDrive2.setPreferredSize(new Dimension(300,94));
		AllDrive2.setBackground(new Color(153,153,153));
		AllDrive2.setPreferredSize(new Dimension(260,80));
		AllDrive2.setLayout(new GridLayout(2,3,10,20));
		//AllDrive2.add(autoRun);
		/*AllDrive2.add(Disable3);
		AllDrive2.add(Enable3);
		AllDrive2.add(usbPort);
		AllDrive2.add(Disable4);
		AllDrive2.add(Enable4);*/
		AllDrive2.add(jLabelNumber[0]);
		AllDrive2.add(ButtonNumber[4]);
		AllDrive2.add(ButtonNumber[5]);
		AllDrive2.add(jLabelNumber[1]);
		AllDrive2.add(ButtonNumber[6]);
		AllDrive2.add(ButtonNumber[7]);
		RightPanel.add(Drive3);
		RightPanel.add(Drive4);
		RightPanel.add(AllDrive2);
		//RightPanel.add(allUser);
		RightPanel.add(jLabelNumber[7]);
		/////////////////////////////////////////////////////////////////////////
		ButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));
		ButtonPanel.setPreferredSize(new Dimension(650,100));
		ButtonPanel.setBackground(new Color(153,153,153));
		ButtonPanel.setBorder(BorderFactory.createBevelBorder(0));
		ButtonPanel.add(saveChange);
		saveChange.setPreferredSize(new Dimension(110,50));
		//ButtonPanel.add(jLabelNumber[0]);
		//jLabelNumber[0].setPreferredSize(new Dimension(110,50));
		//////////////////////////////////////////
		Drive3.setLayout(new GridLayout(1,3,10,10));
		Drive3.setBorder(BorderFactory.createTitledBorder("Hide Drive"));
		Drive3.add(cb3);
		//Drive3.add(Disable1);
		//Drive3.add(Enable1);
		//Drive3.add(ButtonNumber[0]);
		Drive3.add(ButtonNumber[1]);
		Drive4.setLayout(new GridLayout(1,3,10,10));
		Drive4.setBorder(BorderFactory.createTitledBorder("Disable Drive"));
		Drive4.add(cb4);
		//Drive4.add(Disable2);
		//Drive4.add(Enable2);
		Drive4.add(ButtonNumber[2]);
		Drive4.add(ButtonNumber[3]);
		//////////////////////////////////////////////
		BackgroudnP.add(LeftPanel);
		BackgroudnP.add(RightPanel);
		/////////////////////////////////////
		this.add(BackgroudnP,BorderLayout.NORTH);
		//this.add(ButtonPanel,BorderLayout.SOUTH);
		this.add(ButtonPanel,BorderLayout.CENTER);
		this.setVisible(true);
		
	}
	public void actionPerformed(ActionEvent e)
	{
		/*if(e.getSource()==)
		{
			//int CorD=
		}*/
	}	
}