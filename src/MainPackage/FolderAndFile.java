package MainPackage;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.FileView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
public class FolderAndFile extends JPanel implements ActionListener
{
	String filepath=(new Method().getFilePath())+"data";
	File storage=new File(filepath);
	JTree tree;
	DefaultMutableTreeNode root;
	String st="";
	JPanel jtreePanel,buttonPanel;
	JButton importFile,exportFile;
	JFileChooser fc;
	JScrollPane js;
	File fileObj=new File(filepath);
	public FolderAndFile()
	{
		setSize(650,480);
		setLayout(new BorderLayout());
		jtreePanel=new JPanel();
		jtreePanel.setLayout(new BorderLayout());
		jtreePanel.setSize(640,300);
		
		buttonPanel=new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,20));
		buttonPanel.setSize(640,80);
		buttonPanel.setBackground(new Method().getColor());
		buttonPanel.setBorder(BorderFactory.createBevelBorder(0));
		
		importFile=new JButton("Import");
		exportFile=new JButton("Export");
		buttonPanel.add(importFile);
		buttonPanel.add(exportFile);
		importFile.addActionListener(this);
		exportFile.addActionListener(this);
		root=new DefaultMutableTreeNode("root",true);
		///////////////////////////////////////call DefaultMutableTreeNode function
		getList(root,storage);
		tree=new JTree(root);
		tree.setRootVisible(true);
		js=new JScrollPane((JTree)tree);
		js.setPreferredSize(new Dimension(640,300));
		jtreePanel.add(js,BorderLayout.CENTER);
		this.add(jtreePanel,BorderLayout.NORTH);
		this.add(buttonPanel,BorderLayout.CENTER);
		this.setVisible(true);
	}
	public void getList(DefaultMutableTreeNode node,File f)
	{
		if(!f.isDirectory())
		{
			String st=new String(f.getName());
			DefaultMutableTreeNode child=new DefaultMutableTreeNode(st);
			node.add(child);
		}
		else
		{
			String st=new String(f.getName());
			DefaultMutableTreeNode child=new DefaultMutableTreeNode(st);
			node.add(child);
			File flist[]=f.listFiles();
			for(int i=0;i<flist.length;i++)
			{
				getList(child,flist[i]);
			}
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==importFile)
		{
			fc=new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			fc.setFileHidingEnabled(false);
			fc.setDialogTitle("Import File And Folder");
			fc.setApproveButtonText("Import");
			int returnVal=fc.showOpenDialog(this);
			if(returnVal==JFileChooser.APPROVE_OPTION)
			{
				File file=fc.getSelectedFile().getAbsoluteFile();
				String st=file.getPath();
				if(!file.isDirectory()){
					st="\""+st+"\""+" "+filepath;
			try {
				  Runtime.getRuntime().exec("C:\\windows\\system32\\cmd.exe /c move "+st);
				} catch (IOException e1) {
				e1.printStackTrace();
			   }
			}else{
				File fileForDir=fc.getSelectedFile().getAbsoluteFile();
				String stForDir=file.getPath()+"\\*"+" ";
				///////////////////////////////////////////////for distination file path
				filepath=filepath+"\\"+file.getName();
				File makeDir=new File(filepath);
				makeDir.mkdir();
				filepath=filepath+"\\"+" ";
				stForDir=stForDir+filepath;
				stForDir="C:\\windows\\system32\\cmd.exe /c xcopy "+stForDir+"/h /i /c /k /e /r /y";
				try{
					Runtime.getRuntime().exec(stForDir);
					Runtime.getRuntime().exec("C:\\windows\\system32\\cmd.exe /c rd /s"+file.getPath());
					System.out.println("C:\\windows\\system32\\cmd.exe /c rd /s /q "+file.getPath());
				}catch(Exception ex){
					ex.printStackTrace();
				}
			  }
			}
		}
		else if(e.getSource()==exportFile)
		{
			fc=new JFileChooser();
			fc.setFileHidingEnabled(false);
			fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			fc.setDialogTitle("Export File And Folder");
			fc.setApproveButtonText("Export");
			int returnVal=fc.showOpenDialog(this);
			if(returnVal==JFileChooser.APPROVE_OPTION)
			{
				File file=fc.getSelectedFile().getAbsoluteFile();
				String st=file.getPath();
				st=filepath+" "+st;
			try {
				Runtime.getRuntime().exec("C:/windows/system32/cmd.exe /c move "+st);
				} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			   }
			}
		}
	}
}