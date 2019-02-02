package MainPackage;
import java.awt.*;
import MainPackage.StorageSec;
import java.awt.event.*;
import javax.swing.*;
public class MainPanelLayout extends JPanel implements ActionListener{
	private int currentCard=1;
	JPanel CardPanel,ButtonPanel,MainPanel;									//MainPanel contain CardPanel and ButtonPanel
	JButton home,next,previous;
	StorageSec StorageSecec=new StorageSec();									//from StorageSececPanel cardPanel 1
	GeneralSetting generalSetting=new GeneralSetting();
	ProgramSec programSec=new ProgramSec();	
	Setting setting=new Setting();
	Window window=new Window();
	Internet internet=new Internet();
	About about=new About();
	FolderAndFile folderAndFile=new FolderAndFile();						//from FolderAndFile cardpanel 6
	MainWindow mainWindowObj;
	CardLayout cl=new CardLayout();
	private String path="C:/Users/chit/desktop/image/";
	public MainPanelLayout(MainWindow mw)
	{
		mainWindowObj=mw;
		this.setLayout(new FlowLayout(FlowLayout.CENTER,0,30));			//outermost JPanel after container
		this.setBackground(new Color(153,153,204));
		MainPanel=new JPanel();
		MainPanel.setLayout(new BorderLayout(0,40));
		MainPanel.setPreferredSize(new Dimension(650,480));
		MainPanel.setBackground(new Color(153,153,204));
		CardPanel=new JPanel();											//create cardlayout panel make operation in this area
		CardPanel.setLayout(cl);										//create cardLayout
		CardPanel.setPreferredSize(new Dimension(650,380));
		CardPanel.add(StorageSecec,"1");									//add cardPanel 1 StorageSecec
		CardPanel.add(window,"2");
		CardPanel.add(internet,"3");
		CardPanel.add(programSec,"4");
		CardPanel.add(generalSetting,"5");
		CardPanel.add(folderAndFile,"6");								//add cardPanel 6 FolderAndFile
		CardPanel.add(setting,"7");
		CardPanel.add(about,"8");
		ButtonPanel=new JPanel();
		ButtonPanel.setLayout(new FlowLayout());
		ButtonPanel.setPreferredSize(new Dimension(650,110));
		ButtonPanel.setBackground(new Color(153,153,24));
		home=createButton(home,path+"home.jpg");
		next=createButton(home,path+"next.jpg");
		previous=createButton(home,path+"previous.jpg");
		ButtonPanel.add(previous);
		ButtonPanel.add(home);
		ButtonPanel.add(next);
		MainPanel.add(CardPanel,BorderLayout.NORTH);
		MainPanel.add(ButtonPanel,BorderLayout.CENTER);
		this.add(MainPanel);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==home)
		{mainWindowObj.ModifyContainer();
		setMainPanelColor(new Method().getColor());
		}
		if(e.getSource()==next)
		{
			if(currentCard<9)
			{
				if(currentCard!=8)
				currentCard=currentCard+1;
				else currentCard=1;
				cl.show(CardPanel, ""+(currentCard));
				mainWindowObj.setTitle(new Method().getFrameTitle(currentCard));
				
			}
		}
		else
		{
			if(e.getSource()==previous)
			{
				if(currentCard>0)
				{
					if(currentCard!=1)
					currentCard=currentCard-1;
					else currentCard=8;
					cl.show(CardPanel, ""+(currentCard));
					mainWindowObj.setTitle(new Method().getFrameTitle(currentCard));
				}
			}
		}
	}
	private JButton createButton(JButton button,String path)
	{
		button=new JButton();
		button.setBackground(new Color(153,153,204));
		ImageIcon icon=new ImageIcon(path);
		Image img=icon.getImage();
		Image newImg=img.getScaledInstance(100,50, Image.SCALE_SMOOTH);
		ImageIcon newIcon=new ImageIcon(newImg);
		button.setIcon(newIcon);
		button.setPreferredSize(new Dimension(100,50));
		button.addActionListener(this);
		return button;
}
	public void setCurrentCard(int current)
	{
		currentCard=current;
		cl.show(CardPanel, ""+(currentCard));
	}
	public void setMainPanelColor(Color color)
	{
		MainPanel.setBackground(color);
		this.setBackground(color);
	}
	
}
	


