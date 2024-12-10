package term_project;
/***
 * JavaPrograming Project
 * 
 * Project: Golmoksikdang
 * Class: Restaurant_Choice
 * Last Modified Date: 2022.06.04
 * 
 * @author JHS
 *
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class RestaurantChoice extends JFrame implements ActionListener{
	public static final int WIDTH = 1050; //window 출력 화면 크기
	public static final int HEIGHT = 680;
	
	public static final int MainPanel_WIDTH = 1050; //메인 패널 크기
	public static final int MainPanel_HEIGHT = 680;
	
	public static final int Menu_Panel_Position_X = 25; //메뉴 패널 위치
	public static final int Menu_Panel_Position_Y = 150;
	
	public static final int Menu_Panel_Size_W = 970; //메뉴 패널 크기
	public static final int Menu_Panel_Size_H = 470;
	
	public static final int Menu_Button_Position_X = 25; //메뉴 버튼 위치
	public static final int Menu_Button_Position_Y = 25;
	
	public static final int Menu_Button_Size_W = 130; //메뉴 버튼 크기
	public static final int Menu_Button_Size_H = 100;
	
	public static final int Restaurant_Button_Position_X = 20; //식당 버튼 위치
	public static final int Restaurant_Button_Position_Y = 15;
	
	public static final int Restaurant_Button_Size_W = 450; //식당 버튼 크기
	public static final int Restaurant_Button_Size_H = 125;
	
	public static final String srcPath = "src"; //src 파일 경로
	
	private int Temp_X = Menu_Button_Position_X; //버튼 위치 임시 저장
	
	private Store store; //식당 저장 객체
	private Random ran = new Random(); //랜덤 식당 추천 객체
	private int ranNum; //난수 생성
	
	private String Moon; //장소
	
	private JPanel MainPanel;
	
	private static ImageIcon HanIcon;
	private static ImageIcon HanClickIcon;
	private static ImageIcon ElIcon;
	private static ImageIcon ElClickIcon;
	private static ImageIcon JungIcon;
	private static ImageIcon JungClickIcon;
	private static ImageIcon YangIcon;
	private static ImageIcon YangClickIcon;
	private static ImageIcon FastIcon;
	private static ImageIcon FastClickIcon;
	private static ImageIcon GitaIcon;
	private static ImageIcon GitaClickIcon;
	private static ImageIcon RandomIcon;
	private static ImageIcon RandomClickIcon;
	
	private static ImageIcon HanBack1;
	private static ImageIcon HanBack2;
	private static ImageIcon ElBack1;
	private static ImageIcon ElBack2;
	private static ImageIcon JungBack1;
	private static ImageIcon JungBack2;
	private static ImageIcon YangBack1;
	private static ImageIcon YangBack2;
	private static ImageIcon FastBack1;
	private static ImageIcon FastBack2;
	private static ImageIcon GitaBack1;
	private static ImageIcon GitaBack2;
	
	private JLabel MainLB;
	private JLabel HB1;
	private JLabel HB2;
	private JLabel EB1;
	private JLabel EB2;
	private JLabel JB1;
	private JLabel JB2;
	private JLabel YB1;
	private JLabel YB2;
	private JLabel FB1;
	private JLabel FB2;
	private JLabel GB1;
	private JLabel GB2;
	
	private JLabel tempBG;
	
	
	private JPanel HanPanel = new JPanel(); //식당 패널
	private JPanel ElPanel = new JPanel();
	private JPanel JungPanel = new JPanel();
	private JPanel YangPanel = new JPanel();
	private JPanel FastPanel = new JPanel();
	private JPanel GitaPanel = new JPanel();
	private JPanel temp;
	
	private JButton HanBtn; //식당 버튼
	private JButton ElBtn;
	private JButton JungBtn;
	private JButton YangBtn;
	private JButton FastBtn;
	private JButton GitaBtn;
	private JButton RandomBtn;
	private static String currentProjPath = "";
	
	private int HanRes = 1;
	private int ElRes = 1;
	private int JungRes = 1;
	private int YangRes = 1;
	private int FastRes = 1;
	private int GitaRes = 1;
	
	private JButton[] HanRes1Btn = new JButton[13];
	private JButton[] ElRes1Btn = new JButton[13];
	private JButton[] JungRes1Btn = new JButton[13];
	private JButton[] YangRes1Btn = new JButton[13];
	private JButton[] FastRes1Btn = new JButton[13];
	private JButton[] GitaRes1Btn = new JButton[13];
	
	public void MakeResBtn(JButton ResBtn, JPanel Panel, String name, int Res, int num, ImageIcon menu1) {
		ResBtn = new JButton();
		ResBtn.setLayout(new BorderLayout());
		String temper = "";
		int len = 18 - name.length();
		if(len > 0)
			for(int i = 0; i < len; i++)
				temper += " ";
		temper += name;
		name = temper;
		JLabel Image = new JLabel();
		JLabel Name = new JLabel(name);
		ResBtn.setLayout(new BorderLayout());
		Name.setFont(new Font("SansSerif", Font.PLAIN, 20));
		Image.setIcon(menu1);
		ResBtn.add(Image, BorderLayout.WEST);
		ResBtn.add(Name, BorderLayout.CENTER);
		ResBtn.setBackground(Color.WHITE);
		
		int x = Res % 2;
		if(x == 0) x = 2;
		int X = Restaurant_Button_Position_X + (475 * (x - 1));
		int Y = Restaurant_Button_Position_Y + (150 * ((Res - 1) / 2));
		
		ResBtn.setBounds(X, Y, Restaurant_Button_Size_W, Restaurant_Button_Size_H);
		ResBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				temp.setVisible(true);
				store = new Store(num);
			}
		});
		Panel.add(ResBtn);
	}
	
	public ImageIcon imageSetsize(ImageIcon icon, int i, int j){
		Image ximg = icon.getImage();
		Image yimg = ximg.getScaledInstance(i, j, java.awt.Image.SCALE_SMOOTH);
		ImageIcon xyimg = new ImageIcon(yimg);
		return xyimg;
	}
	
	public void MakePanel(JPanel Panel) {
		Panel.setLayout(null);
		Panel.setBounds(Menu_Panel_Position_X, Menu_Panel_Position_Y, Menu_Panel_Size_W, Menu_Panel_Size_H);
		Panel.setVisible(false);
	}
	
	public void setBackground(ImageIcon back1, ImageIcon back2, JLabel b1, JLabel b2) {
		back1 = imageSetsize(back1, Menu_Panel_Size_W, Menu_Panel_Size_H);
		back2 = imageSetsize(back2, MainPanel_WIDTH, MainPanel_HEIGHT);
		b1.setBounds(0, 0, Menu_Panel_Size_W, Menu_Panel_Size_H);
		b2.setBounds(0, 0, MainPanel_WIDTH, MainPanel_HEIGHT);
	}	
	
	public void MakeMenuBtn(JButton Btn, ImageIcon Image) {
		Btn.setRolloverIcon(Image);
		Btn.setBorderPainted(false);
		Btn.setBounds(Temp_X, Menu_Button_Position_Y, Menu_Button_Size_W, Menu_Button_Size_H);
		Btn.addActionListener(this);
		Temp_X += 140;
	}
	
	public void RestaurantButton(String M) {
		 
			String currentProjPath = "";
			try{
				currentProjPath = new File(".").getCanonicalPath();
			} catch (IOException e){
				e.printStackTrace();
			}
			
			String StoreInformation = "StoreInfo.txt";
			String menu1 = "menu1.jpg";
			String storeFilePath;
			ImageIcon menu1Icon;
			
			File file = null;
			BufferedReader br = null;
			String Name;
			
			if(M.equals("북문")) {
				do {
					ranNum = ran.nextInt(20);
				} while(ranNum <= 1);
				for(int i = 2; i <= 19; i++) {
					storeFilePath = currentProjPath+"/"+srcPath+"/store/"+
							i+"/";
				
					try{
						menu1Icon = new ImageIcon(storeFilePath + menu1);
						menu1Icon = imageSetsize(menu1Icon, 150, 100);
						file = new File(storeFilePath+StoreInformation);
						br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
					
						Name = br.readLine();
						if(i >= 2 && i <= 4)
							MakeResBtn(HanRes1Btn[HanRes], HanPanel, Name, HanRes++, i, menu1Icon);
						else if(i >= 5 && i <= 7)
							MakeResBtn(YangRes1Btn[YangRes], YangPanel, Name, YangRes++, i, menu1Icon);
						else if(i >= 8 && i <= 10)
							MakeResBtn(JungRes1Btn[JungRes], JungPanel, Name, JungRes++, i, menu1Icon);
						else if(i >= 11 && i <= 13)
							MakeResBtn(ElRes1Btn[ElRes], ElPanel, Name, ElRes++, i, menu1Icon);
						else if(i >= 14 && i <= 16)
							MakeResBtn(FastRes1Btn[FastRes], FastPanel, Name, FastRes++, i, menu1Icon);
						else
							MakeResBtn(GitaRes1Btn[GitaRes], GitaPanel, Name, GitaRes++, i, menu1Icon);
						
						br.close();
					}catch(Exception e){
						System.err.println("File not found.");
						System.exit(0);
					}
				}
			} else if(M.equals("정문")) {
				do {
					ranNum = ran.nextInt(32);
				} while(ranNum <= 19);
				for(int i = 20; i <= 31; i++) {
					storeFilePath = currentProjPath+"/"+srcPath+"/store/"+
							i+"/";
				
					try{
						menu1Icon = new ImageIcon(storeFilePath + menu1);
						menu1Icon = imageSetsize(menu1Icon, 150, 100);
						file = new File(storeFilePath+StoreInformation);
						br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
						
						Name = br.readLine();
						if(i >= 20 && i <= 22)
							MakeResBtn(HanRes1Btn[HanRes], HanPanel, Name, HanRes++, i, menu1Icon);
						else if(i >= 23 && i <= 23)
							MakeResBtn(YangRes1Btn[YangRes], YangPanel, Name, YangRes++, i, menu1Icon);
						else if(i >= 24 && i <= 24)
							MakeResBtn(ElRes1Btn[ElRes], ElPanel, Name, ElRes++, i, menu1Icon);
						else if(i >= 25 && i <= 27)
							MakeResBtn(JungRes1Btn[JungRes], JungPanel, Name, JungRes++, i, menu1Icon);
						else if(i >= 28 && i <= 30)
							MakeResBtn(FastRes1Btn[FastRes], FastPanel, Name, FastRes++, i, menu1Icon);
						else
							MakeResBtn(GitaRes1Btn[GitaRes], GitaPanel, Name, GitaRes++, i, menu1Icon);
						
						br.close();
					}catch(Exception e){
						System.err.println("File not found.");
						System.exit(0);
					}
				}
			} else if(M.equals("쪽문")) {
				do {
					ranNum = ran.nextInt(46);
				} while(ranNum <= 31);
				for(int i = 32; i <= 45; i++) {
					storeFilePath = currentProjPath+"/"+srcPath+"/store/"+
							i+"/";
				
					try{
						menu1Icon = new ImageIcon(storeFilePath + menu1);
						menu1Icon = imageSetsize(menu1Icon, 150, 100);
						file = new File(storeFilePath+StoreInformation);
						br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
						
						Name = br.readLine();
						if(i >= 32 && i <= 34)
							MakeResBtn(HanRes1Btn[HanRes], HanPanel, Name, HanRes++, i, menu1Icon);
						else if(i >= 35 && i <= 37)
							MakeResBtn(ElRes1Btn[ElRes], ElPanel, Name, ElRes++, i, menu1Icon);
						else if(i >= 38 && i <= 39)
							MakeResBtn(JungRes1Btn[JungRes], JungPanel, Name, JungRes++, i, menu1Icon);
						else if(i >= 40 && i <= 42)
							MakeResBtn(FastRes1Btn[FastRes], FastPanel, Name, FastRes++, i, menu1Icon);
						else
							MakeResBtn(GitaRes1Btn[GitaRes], GitaPanel, Name, GitaRes++, i, menu1Icon);
						
						br.close();
					}catch(Exception e){
						System.err.println("File not found.");
						System.exit(0);
					}
				}
			} else if(M.equals("동/텍문")) {
				do {
					ranNum = ran.nextInt(58);
				} while(ranNum <= 45);
				for(int i = 46; i <= 57; i++) {
					storeFilePath = currentProjPath+"/"+srcPath+"/store/"+
							i+"/";
				
					try{
						menu1Icon = new ImageIcon(storeFilePath + menu1);
						menu1Icon = imageSetsize(menu1Icon, 150, 100);
						file = new File(storeFilePath+StoreInformation);
						br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
						
						Name = br.readLine();
						if(i >= 46 && i <= 49)
							MakeResBtn(HanRes1Btn[HanRes], HanPanel, Name, HanRes++, i, menu1Icon);
						else if(i >= 50 && i <= 50)
							MakeResBtn(ElRes1Btn[ElRes], ElPanel, Name, ElRes++, i, menu1Icon);
						else if(i >= 51 && i <= 54)
							MakeResBtn(JungRes1Btn[JungRes], JungPanel, Name, JungRes++, i, menu1Icon);
						else if(i >= 55 && i <= 55)
							MakeResBtn(FastRes1Btn[FastRes], FastPanel, Name, FastRes++, i, menu1Icon);
						else
							MakeResBtn(GitaRes1Btn[GitaRes], GitaPanel, Name, GitaRes++, i, menu1Icon);
						
						br.close();
					}catch(Exception e){
						System.err.println("File not found.");
						System.exit(0);
					}
				}
			}else if(M.equals("학식")) {
				do {
					ranNum = ran.nextInt(61);
				} while(ranNum <= 57);
				for(int i = 58; i <= 60; i++) {
					storeFilePath = currentProjPath+"/"+srcPath+"/store/"+
							i+"/";
				
					try{
						menu1Icon = new ImageIcon(storeFilePath + menu1);
						menu1Icon = imageSetsize(menu1Icon, 150, 100);
						file = new File(storeFilePath+StoreInformation);
						br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
						
						Name = br.readLine();
						MakeResBtn(HanRes1Btn[HanRes], HanPanel, Name, HanRes++, i, menu1Icon);
						
						HanPanel.setVisible(true);
						temp = HanPanel;
						HanBtn.setVisible(false);
						ElBtn.setVisible(false);
						YangBtn.setVisible(false);
						JungBtn.setVisible(false);
						FastBtn.setVisible(false);
						GitaBtn.setVisible(false);
						
						br.close();
					}catch(Exception e){
						System.err.println("File not found.");
						System.exit(0);
					}
				}
			}
	}
	
	public RestaurantChoice(String Moon) {
		super(Moon);
		this.Moon = Moon;
		setSize(WIDTH, HEIGHT);
		
		try{
			currentProjPath = new File(".").getCanonicalPath();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		String Backpath = currentProjPath + "\\" + srcPath + "\\Background\\";
		
		ImageIcon MainBG = new ImageIcon(Backpath + "MainBack.jpg");
		MainBG = imageSetsize(MainBG, MainPanel_WIDTH, MainPanel_HEIGHT);
		MainLB = new JLabel(MainBG);
		MainLB.setBounds(0, 0, MainPanel_WIDTH, MainPanel_HEIGHT);
		MainPanel = new JPanel();
		MainPanel.setLayout(null);
		MainPanel.setPreferredSize(new Dimension(MainPanel_WIDTH, MainPanel_HEIGHT));
		
		MakePanel(HanPanel);
		MakePanel(ElPanel);
		MakePanel(JungPanel);
		MakePanel(YangPanel);
		MakePanel(FastPanel);
		MakePanel(GitaPanel);
		
		HanBack1 = new ImageIcon(Backpath + "HanBack1.jpg");
		HanBack2 = new ImageIcon(Backpath + "HanBack2.jpg");
		HB1 = new JLabel(HanBack1);
		HB2 = new JLabel(HanBack2);
		setBackground(HanBack1, HanBack2, HB1, HB2);
		
		ElBack1 = new ImageIcon(Backpath + "ElBack1.jpg");
		ElBack2 = new ImageIcon(Backpath + "ElBack2.jpg");
		EB1 = new JLabel(ElBack1);
		EB2 = new JLabel(ElBack2);
		setBackground(ElBack1, ElBack2, EB1, EB2);
		
		JungBack1 = new ImageIcon(Backpath + "JungBack1.jpg");
		JungBack2 = new ImageIcon(Backpath + "JungBack2.jpg");
		JB1 = new JLabel(JungBack1);
		JB2 = new JLabel(JungBack2);
		setBackground(JungBack1, JungBack2, JB1, JB2);
		
		YangBack1 = new ImageIcon(Backpath + "YangBack1.jpg");
		YangBack2 = new ImageIcon(Backpath + "YangBack2.jpg");
		YB1 = new JLabel(YangBack1);
		YB2 = new JLabel(YangBack2);
		setBackground(YangBack1, YangBack2, YB1, YB2);
		
		FastBack1 = new ImageIcon(Backpath + "FastBack1.jpg");
		FastBack2 = new ImageIcon(Backpath + "FastBack2.jpg");
		FB1 = new JLabel(FastBack1);
		FB2 = new JLabel(FastBack2);
		setBackground(FastBack1, FastBack2, FB1, FB2);
		
		GitaBack1 = new ImageIcon(Backpath + "GitaBack1.jpg");
		GitaBack2 = new ImageIcon(Backpath + "GitaBack2.jpg");
		GB1 = new JLabel(GitaBack1);
		GB2 = new JLabel(GitaBack2);
		setBackground(GitaBack1, GitaBack2, GB1, GB2);
		
		String path = currentProjPath + "\\" + srcPath + "\\button_image\\";
		
		HanIcon = new ImageIcon(path + "Han.jpg"); //버튼 이미지
		HanClickIcon = new ImageIcon(path +"Han_click.jpg");
		ElIcon = new ImageIcon(path + "El.jpg");
		ElClickIcon = new ImageIcon(path + "El_click.jpg");
		JungIcon = new ImageIcon(path + "Jung.jpg");
		JungClickIcon = new ImageIcon(path + "Jung_click.jpg");
		YangIcon = new ImageIcon(path + "Yang.jpg");
		YangClickIcon = new ImageIcon(path + "Yang_click.jpg");
		FastIcon = new ImageIcon(path + "Fast.png");
		FastClickIcon = new ImageIcon(path + "Fast_click.png");
		GitaIcon = new ImageIcon(path + "Gita.jpg");
		GitaClickIcon = new ImageIcon(path + "Gita_click.jpg");
		RandomIcon = new ImageIcon(path + "Random.png");
		RandomClickIcon = new ImageIcon(path + "Random_click.png");
		
		HanBtn = new JButton(HanIcon);
		ElBtn = new JButton(ElIcon);
		JungBtn = new JButton(JungIcon);
		YangBtn = new JButton(YangIcon);
		FastBtn = new JButton(FastIcon);
		GitaBtn = new JButton(GitaIcon);
		RandomBtn = new JButton(RandomIcon);
		
		MakeMenuBtn(HanBtn, HanClickIcon);
		MakeMenuBtn(ElBtn, ElClickIcon);
		MakeMenuBtn(JungBtn, JungClickIcon);
		MakeMenuBtn(YangBtn, YangClickIcon);
		MakeMenuBtn(FastBtn, FastClickIcon);
		MakeMenuBtn(GitaBtn, GitaClickIcon);
		MakeMenuBtn(RandomBtn, RandomClickIcon);
		
		RestaurantButton(this.Moon);
		
		MainPanel.add(HanBtn);
		MainPanel.add(ElBtn);
		MainPanel.add(JungBtn);
		MainPanel.add(YangBtn);
		MainPanel.add(FastBtn);
		MainPanel.add(GitaBtn);
		MainPanel.add(RandomBtn);
		
		MainPanel.add(HanPanel);
		MainPanel.add(ElPanel);
		MainPanel.add(JungPanel);
		MainPanel.add(YangPanel);
		MainPanel.add(FastPanel);
		MainPanel.add(GitaPanel);
		
		HanPanel.add(HB1);
		ElPanel.add(EB1);
		JungPanel.add(JB1);
		YangPanel.add(YB1);
		FastPanel.add(FB1);
		GitaPanel.add(GB1);
		
		MainPanel.add(MainLB);
		MainPanel.add(HB2);
		MainPanel.add(EB2);
		MainPanel.add(JB2);
		MainPanel.add(YB2);
		MainPanel.add(FB2);
		MainPanel.add(GB2);
		//MainPanel.setVisible(false);
		HB2.setVisible(false);
		EB2.setVisible(false);
		JB2.setVisible(false);
		YB2.setVisible(false);
		FB2.setVisible(false);
		GB2.setVisible(false);
		
		add(MainPanel);
		
		addWindowListener(new CheckOnExit());
		
		setVisible(true);
	}
	
	private class CheckOnExit implements WindowListener {
		public void windowOpened(WindowEvent e) {}
		public void windowClosing(WindowEvent e) {
			dispose();
		}
		public void windowClosed(WindowEvent e) {}
		public void windowIconified(WindowEvent e) {}
		public void windowDeiconified(WindowEvent e) {}
		public void windowActivated(WindowEvent e) {}
		public void windowDeactivated(WindowEvent e) {}
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(temp != null) {
			temp.setVisible(false);
			tempBG.setVisible(false);
		}
		else MainLB.setVisible(false);
		
		if(HanBtn.equals(e.getSource())) {
			HanPanel.setVisible(true);
			HB2.setVisible(true);
			temp = HanPanel;
			tempBG = HB2;
		} else if(ElBtn.equals(e.getSource())) {
			ElPanel.setVisible(true);
			EB2.setVisible(true);
			temp = ElPanel;
			tempBG = EB2;
		} else if(JungBtn.equals(e.getSource())) {
			JungPanel.setVisible(true);
			JB2.setVisible(true);
			temp = JungPanel;
			tempBG = JB2;
		} else if(YangBtn.equals(e.getSource())) {
			YangPanel.setVisible(true);
			YB2.setVisible(true);
			temp = YangPanel;
			tempBG = YB2;
		} else if(FastBtn.equals(e.getSource())) {
			FastPanel.setVisible(true);
			FB2.setVisible(true);
			temp = FastPanel;
			tempBG = FB2;
		} else if(GitaBtn.equals(e.getSource())) {
			GitaPanel.setVisible(true);
			GB2.setVisible(true);
			temp = GitaPanel;
			tempBG = GB2;
		} else if(RandomBtn.equals(e.getSource())) {
			store = new Store(ranNum);
			if(temp != null) {
				temp.setVisible(true);
				tempBG.setVisible(true);
			}
			switch(Moon) {
			case "북문": do {
				ranNum = ran.nextInt(20);
			} while(ranNum <= 1); break;
			
			case "정문": do {
				ranNum = ran.nextInt(32);
			} while(ranNum <= 19); break;
			
			case "쪽문": do {
				ranNum = ran.nextInt(46);
			} while(ranNum <= 31); break;
			
			case "동/텍문": do {
				ranNum = ran.nextInt(58);
			} while(ranNum <= 45); break;
			
			case "학식": do {
				ranNum = ran.nextInt(61);
			} while(ranNum <= 57);
			}
		} else {
			System.err.println("Unanticipated Error");
		}
	}
	
	public static void main(String[] args) {
		RestaurantChoice gui = new RestaurantChoice("북문");
	}
	
}
