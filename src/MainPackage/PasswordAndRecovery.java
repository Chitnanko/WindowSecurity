package MainPackage;
import javax.swing.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PasswordAndRecovery {
	private BufferedReader br;
	PrintWriter pw;
	 String oldPass,newPass,rePass;
	String filepath=new Method().getFilePath()+"res/";
	JPanel changePPassword=new JPanel(),
			changeWPassword=new JPanel(),
			createPassword=new JPanel(),
			recoverPassword=new JPanel();
	JTextField Name=new JTextField(25);
	JPasswordField changePOld=new JPasswordField(15),
				changePNew=new JPasswordField(15),
				changePReType=new JPasswordField(15);
	public PasswordAndRecovery()
	{
		changePPassword.setLayout(new GridLayout(3,2,0,5));
		changeWPassword.setLayout(new GridLayout(3,2,0,5));
		createPassword.setLayout(new GridLayout(2,2,0,5));
		recoverPassword.setLayout(new GridLayout(2,1,0,5));
		changePPassword.setPreferredSize(new Dimension(350,90));
		changeWPassword.setPreferredSize(new Dimension(350,90));
		createPassword.setPreferredSize(new Dimension(330,60));
		recoverPassword.setPreferredSize(new Dimension(200,50));
	}
	/////////////////////////////////////////////////////////when user forget password call this method
	public int RecoveryPassword(String question,String st)
	{
		int b=0;
		recoverPassword.add(new JLabel(question));
		recoverPassword.add(Name);
		int select=JOptionPane.showConfirmDialog(null, recoverPassword,"Recovery password",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
		if(select==JOptionPane.OK_OPTION){
			if(Name.getText().equals(st)){
				b=1;
			} else {
				JOptionPane.showMessageDialog(null, "Worng Answer!","Wrong",JOptionPane.WARNING_MESSAGE);
			}
		}else{
			b=2;
		}
	  return b;
	}
	///////////////////////////////////////////call this method from setting panel
	public int CratePassword()
	{
		int b=0;
		createPassword.add(new JLabel("Create Password"));
		createPassword.add(changePNew);
		createPassword.add(new JLabel("Retype Password"));
		createPassword.add(changePReType);
		int select=JOptionPane.showConfirmDialog(null,createPassword,"Create Password",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
		if(select==JOptionPane.OK_OPTION)
		{
			newPass=new String(changePNew.getPassword());
			rePass=new String(changePReType.getPassword());
			
		   if(newPass.equals(rePass) && !newPass.equals("") && !rePass.equals(""))
			 {
				try {
					pw=new PrintWriter(new FileWriter(filepath+"pwd.txt"));
					pw.println(newPass);
					pw.close();
					b=1;
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else 
				{
					JOptionPane.showMessageDialog(null,"Password did not match","Password did not match",JOptionPane.INFORMATION_MESSAGE);
					b=0;
				}
		}
		else {b=2;}
	 return b; //////////////////////return to Setting panel
   }
	/////////////////////////////////////////////// change program password
	public boolean ChangePPassword()
	{
		boolean b=false;
		changePPassword.add(new JLabel("Enter old Password"));
		changePPassword.add(changePOld);
		changePPassword.add(new JLabel("Enter new Password"));
		changePPassword.add(changePNew);
		changePPassword.add(new JLabel("Retype new Password"));
		changePPassword.add(changePReType);
		JFrame.setDefaultLookAndFeelDecorated(true);
		int select=JOptionPane.showConfirmDialog(null,changePPassword,"Change Password",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
		if(select==JOptionPane.OK_OPTION)
		{
			oldPass=new String(changePOld.getPassword());
			newPass=new String(changePNew.getPassword());
			rePass=new String(changePReType.getPassword());
			b=isCorrectAction(0);
		}
		else
			{b=true;}
		return b;
	}
	/////////////////////////////////////change password for window 
	public boolean ChangeWPassword()
	{
		boolean b=false;
		changeWPassword.add(new JLabel("Enter program Password"));
		changeWPassword.add(changePOld);
		changeWPassword.add(new JLabel("Enter new Password"));
		changeWPassword.add(changePNew);
		changeWPassword.add(new JLabel("Retype new Password"));
		changeWPassword.add(changePReType);
		JFrame.setDefaultLookAndFeelDecorated(true);
		int select=JOptionPane.showConfirmDialog(null,changeWPassword,"Change Password",JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
		if(select==JOptionPane.OK_OPTION)
		{
			oldPass=new String(changePOld.getPassword());
			newPass=new String(changePNew.getPassword());
			rePass=new String(changePReType.getPassword());
			b=isCorrectAction(1);
		}
		else
		{
			b=true;
		}
		return b;
	}
	
	 public boolean isCorrectAction(int isWindow)
	 {
		String isCorrect;
		boolean b=false;
		if(newPass.equals(rePass) && !newPass.equals(""))
		{
			try{
				br=new BufferedReader(new FileReader(filepath+"pwd.txt"));
				isCorrect=br.readLine();
				if(isCorrect.equals(oldPass))
				{
					if(isWindow!=1)
					{
					 pw=new PrintWriter(new FileWriter(filepath+"pwd.txt"));
					 pw.println(newPass);
					 b=true;
					 pw.close();
					 br.close();
					}
					else{
						Process process=new ProcessBuilder((new Method().getExePath()+"windowPass.exe"),newPass).start();
						b=true;
							if(process.waitFor()!=0)
						{
							JOptionPane.showMessageDialog(null,"unspecify error","Error",JOptionPane.ERROR_MESSAGE);
						}
						else
							JOptionPane.showMessageDialog(null,"succefull");
							b=true;
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Incorrect Password!Retype","Worng Password",JOptionPane.WARNING_MESSAGE);
				}
			}
				catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else
			JOptionPane.showMessageDialog(null, "Password did not match","Incorrect password",JOptionPane.WARNING_MESSAGE);
		return b;
	}
	 //////////////////////////////////////call from mainwindow.java 
	 public String readPwd(int PwdOrRecordPwd)
	 {
		 String returnString="";
		 try{
			 ///////////////////////////////////////////////to read pwd.txt current program password
			 if(PwdOrRecordPwd==1)
			   br=new BufferedReader(new FileReader(filepath+"pwd.txt"));
			 //////////////////////////////////////to read recordPwd.txt
			 else if(PwdOrRecordPwd==0)
				 br=new BufferedReader(new FileReader(filepath+"recordPwd.txt"));
			 returnString=br.readLine();
		 }catch (Exception e)
		 {
			 e.printStackTrace();
		 }
		 return returnString;
	 }
	 public void writePwd(int OpenOrClose){
		 
		 try {
			pw=new PrintWriter(new FileWriter(filepath+"recordPwd.txt"));
			////////////////////////////when user entered password is correct wirte 1 to file
			if(OpenOrClose==1)
				pw.println(""+OpenOrClose);
			////////////////////////////////////when user close program rewrite 1 to 0
			else if(OpenOrClose==0)
				pw.println(""+OpenOrClose);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
}