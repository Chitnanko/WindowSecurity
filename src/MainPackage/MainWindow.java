package MainPackage;
import MainPackage.MainPanelLayout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
public class MainWindow extends JFrame implements ActionListener{
	int isCancel=0;
	int isPassed=0;
	String[] shutdownOrRestart={" /s /t 0"," /r /t 0"," /l"};
	/////////////////////////////////////////////for recovery
	Setting settingObj=new Setting();
	int count=0;
	String[] recordSetting=new String[4];
	PasswordAndRecovery PwdAndRecovery;
	MainPanelLayout stPanel=new MainPanelLayout(this);							//MainPanelLayout panel
    String path=(new Method().getFilePath())+"image/";									//image path
    File pwdFile=new File(new PasswordAndRecovery().filepath+"pwd.txt");
     JButton storage,window,internet,program,
	 		 general,folder,setting,about;
	 JPanel  HPanel=new JPanel(),												//import button panel
			 GPanel=new JPanel();												//import hpanel in this panel
    Container MainCt;															//main container(cardLayout)
    CardLayout cl=new CardLayout();												//for cardLayout (to change panel)
	public MainWindow(){
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.setTitle("Security");
		this.setResizable(false);
		this.setBounds(100,100,720,580);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    MainCt=this.getContentPane();
	    MainCt.setLayout(cl);
	    GPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,30));
		GPanel.setBackground(new Method().getColor());
		//GPanel.setBorder(BorderFactory.createBevelBorder(0));
		HPanel.setLayout(new GridLayout(4,2,50,10));
		HPanel.setBackground(new Method().getColor());
		HPanel.setPreferredSize(new Dimension(650,480));
		storage=createButton(storage,path+"storage.jpg");						//combine string var and image name
		window=createButton(window,path+"window.jpg");
		internet=createButton(window,path+"internet.jpg");
		program=createButton(program,path+"program.jpg");
		general=createButton(general,path+"general.jpg");
		folder=createButton(folder,path+"folder.jpg");
		setting=createButton(setting,path+"setting.jpg");
		about=createButton(about,path+"help.jpg");
		HPanel.add(storage);
		HPanel.add(window);
		HPanel.add(internet);
		HPanel.add(program);
		HPanel.add(general);
		HPanel.add(folder);
		HPanel.add(setting);
		HPanel.add(about);
		GPanel.add(HPanel);
		MainCt.add(GPanel,"1");
		MainCt.add(stPanel,"2");
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(isPassed==1)////////////////////////////////////////////////////this place is rewrite 0 to 1
	  {
		if(e.getSource()==storage)
		{
			ChangeCardPanel(1);
		}
		else if(e.getSource()==folder)
		{
			ChangeCardPanel(6);
		}
		else if(e.getSource()==setting)
		{
			ChangeCardPanel(7);
		}
		else if(e.getSource()==general)
		{
			ChangeCardPanel(5);
		}
		else if(e.getSource()==window)
		{
			ChangeCardPanel(2);
		}
		else if(e.getSource()==program)
		{
			ChangeCardPanel(4);
		}
		else if(e.getSource()==internet)
		{
			ChangeCardPanel(3);
		}
		else if(e.getSource()==about)
		{
			ChangeCardPanel(8);
		}
	  }
		else IsExitFile();
	}
	
	public static void main(String args[]){
	
			/*String[] names={UIManager.getSystemLookAndFeelClassName(),UIManager.getCrossPlatformLookAndFeelClassName()};
			for(String name:names)
			{
				try{
					UIManager.setLookAndFeel(name);
					System.out.println(name);
				}catch (Exception e)
				{
					e.printStackTrace();
				}
			}*/
		/*try{
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch (Exception e)
		{
			e.printStackTrace();
		}*/
		JFrame.setDefaultLookAndFeelDecorated(true);
		new MainWindow();
	}
		public JButton createButton(JButton button,String path)
		{
			button=new JButton();
			button.setBackground(new Method().getColor());
			ImageIcon icon=new ImageIcon(path);
			Image img=icon.getImage();
			Image newImg=img.getScaledInstance(293,110, Image.SCALE_SMOOTH);
			ImageIcon newIcon=new ImageIcon(newImg);
			button.setIcon(newIcon);
			button.addActionListener(this);
			return button;
	}
		public void ChangeCardPanel(int current){
			cl.last(MainCt);
			stPanel.setCurrentCard(current);
			stPanel.setMainPanelColor(new Method().getColor());
			this.setTitle(new Method().getFrameTitle(current));
		}
		public void ModifyContainer()
		{
			cl.first(MainCt);
			this.setTitle("Security");
			
		}
	
		public void IsExitFile()
		{
			if(pwdFile.exists())
			{
				recordSetting=settingObj.returnRecord();
				int Index=Integer.parseInt(recordSetting[3]);
				int answerAns=Integer.parseInt(recordSetting[0]);
				int conditionNum=0;
				conditionNum=Integer.parseInt(settingObj.Time[Integer.parseInt(recordSetting[2])]);
				if(isPwd())
				{
					JOptionPane.showMessageDialog(null,"Login succefull","Successfull",JOptionPane.INFORMATION_MESSAGE);
					//IsExitFile
				}
				else if(isCancel!=1){
					count++;
					//////////////////////////////////////////////////////////let user to recovery password if user specify recovery
					
					 if((count==conditionNum) && conditionNum!=111)///////////////////////////carefull this place
						{
							////////////////////////////////this two statment is important to count user worng
						 	count=0;
							while(true){
								PwdAndRecovery=new PasswordAndRecovery();
								int isTrue=PwdAndRecovery.RecoveryPassword(settingObj.Question[answerAns],recordSetting[1]);
								///////////////////////////////////return true
								if(isTrue==1)
								{
									 while(true)
									   {
										   PwdAndRecovery=new PasswordAndRecovery();
										   int isTrueFor=PwdAndRecovery.CratePassword();
										   if(isTrueFor==1){
											 JOptionPane.showMessageDialog(null,"succefull","Successfull",JOptionPane.INFORMATION_MESSAGE);
											   break;
										   } else if(isTrueFor==2){
											   break;
										   }
									   }
										 IsExitFile();
									break;
								}
								///////////////////////////////////return cancel
								else if(isTrue==2)
								{
									/////////////////////////////////////////when user chance is gone will shutdown or logof or reastart prespecify
											try {
												String st="C:/windows/system32/cmd.exe /c shutdown"+shutdownOrRestart[Index];
												Runtime.getRuntime().exec(st);
												System.out.
												println(st);
											} catch (IOException e) {
												e.printStackTrace();
											}
										}
								  }
							}else{
									IsExitFile();
						}
					}else{
						//////////////////////////////////reset isCancel that user can reload 3 time or 2 time after click cancel
						isCancel=0;
					}
				}
			//////////////////////////////////////if file pwd file not exit
		   else{
			   while(true)
			   {
				   PwdAndRecovery=new PasswordAndRecovery();
				   int isTrue=PwdAndRecovery.CratePassword();
				   if(isTrue==1 || isTrue==2){
					   break;
				   }
			   }
				 IsExitFile();
		    }
	  }
		public boolean isPwd()
		{
			String  st;
			String pwd;
			boolean b=false;
			JPasswordField pf=new JPasswordField(15);
			int select=JOptionPane.showConfirmDialog(this,pf,"Enter Password",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(select==JOptionPane.OK_OPTION)
		  {
			st=new String(pf.getPassword());
			/////////////////////////////////////////read window password file
			pwd=(new PasswordAndRecovery().readPwd(1));
			if(pwd.equals(st))
			 {
				b=true;
				isPassed=1;
			 }
			else{
				b=false;
			}
		  }
			else
				isCancel=1;
			return b;
		
		}
}
