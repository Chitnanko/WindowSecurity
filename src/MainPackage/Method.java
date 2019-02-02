package MainPackage;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.StringTokenizer;

import javax.swing.JButton;

public class Method {
	private String[] windowTitle={"StorageSecurity","WindowSecurity","InternetSecurity","ProgramSecurity",
			"GeneralSetting","FolderAndFileSecurity","Setting","Help"};
	private PrintWriter pw;
	private BufferedReader br;
	private StringTokenizer stToken;
	public Method()
	{
		
	}
	/////////////////////////////for color
	public Color getColor()
	{
		return (new Color(153,153,153));
	}
	////////////////////////////////////////////////////for file path
	public String getFilePath()
	{
		return ("C:\\Users\\chit\\desktop\\");
	}
	//////////////////////////////////for window title
	public String getFrameTitle(int current)
	{
		if(current>0 && current<9)
		{
			return windowTitle[current-1];
		}
		return null;
	}
	/////////////////////////////////////////////////read program name file call this method from programSec
	/////////////////////call from ProgramSec.java GeneralSetting.java
	public void recordAction(String filepath,String st,String callExe)
	{
		String callExeFile="cmd.exe /c start "+callExe;
		
		try
		{
			pw=new PrintWriter(new FileWriter(filepath));
			pw.println(st);
			pw.close();
			Runtime.getRuntime().exec(callExeFile);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	///////////////////////////////call from program.java and GeneralSetting.java
	public int[] readRecord(String filepath,int arrNum)
	{
		File file=new File(filepath);
		int[] returnValue=new int[arrNum];
		int count=0;
		for(int i=0;i<returnValue.length;i++)
			returnValue[i]=0;
		System.out.println(file);
		if(file.exists()){
		try
		{
			br=new BufferedReader(new FileReader(filepath));
			String st=br.readLine();
			stToken=new StringTokenizer(st,"#");
			while(stToken.hasMoreTokens())
			{
				returnValue[count++]=Integer.parseInt(stToken.nextToken());
				
			}
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
		return returnValue;
	}
	/////////////////////call from ProgramSec.java
	public void DisableProgram(String[] st,String filepath)
	{
	     String programName="";
		try{
		pw=new PrintWriter(new FileWriter(filepath));
		for(int i=0;i<st.length;i++)
		{
		  programName+="#"+st[i];	
		}
		pw.print(programName);
		pw.close();
	  }catch(Exception e)
		{
		  e.printStackTrace();
		}
	}
	public String[] ReadDisProgram(String filepath)
	{
		int count=0;
		String[] programOrDisable=new String[26];
		String[] returnString;
		String tem;
		try{
			br=new BufferedReader(new FileReader(filepath));
			tem=br.readLine();
			stToken=new StringTokenizer(tem,"#");
			while(stToken.hasMoreTokens())
			{
				programOrDisable[count++]=stToken.nextToken();
			}
			br.close();
			
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		returnString=new String[count];
		System.arraycopy(programOrDisable, 0, returnString, 0, count);
		return returnString;
	}
	//////////////////////////////////////call from ProgramSec.java
	/*public void removeUserSelection(String st,String filepath)
	{
		BufferedReader br;
		String rewrite="";
		String temp="";
		String temp1="";
		StringTokenizer Token;
		if(st!="")
		{
		 try{
			br=new BufferedReader(new FileReader(filepath));
			temp1=br.readLine();
			Token=new StringTokenizer(temp1,"#");
			while(Token.hasMoreTokens())
			{
				 temp=Token.nextToken();
				// System.out.println(temp);
				if(!temp.equals(st))
				{
					rewrite+="#"+temp;
					temp="";
				}
				temp="";
			}
		pw=new PrintWriter(new FileWriter(filepath));
		pw.println(rewrite);
		br.close();
		pw.close();
	}catch(Exception e)
		{
		 e.printStackTrace();
		}
}
  }*/
	public String getExePath()
	{
		return (getFilePath()+"bin/");
	}
}

