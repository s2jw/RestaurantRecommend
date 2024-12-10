package term_project;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

public class mainPage extends JFrame implements ActionListener {
	public static final int WIDTH = 900;
	public static final int WEIGHT = 700;
	
	public static final String srcPath = "src"; //src 파일 경로
	private static String currentProjPath = "";
	
	public mainPage() {
		setSize(WIDTH, WEIGHT);
		setTitle("경북대생을 위한 식당 정보 : 골 목 식 당");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		try{
			currentProjPath = new File(".").getCanonicalPath();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		String imageFilePath = currentProjPath+"/"+srcPath+"/mainPage_image/mainPage_image/";
		
		//String imageFilePath = currentProjPath+"/src/mainPage_image/"+"/";
		
		/* 타이틀 부분 */
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new GridLayout(2, 1));
		titlePanel.setBackground(Color.ORANGE);
		JLabel title = new JLabel("KNU 골 목 식 당");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font("DungGeunMo", Font.BOLD, 32)); 
		title.setForeground(Color.BLACK);
		JLabel intro = new JLabel("위 치 를 선 택 하 세 요!");
		intro.setHorizontalAlignment(JLabel.CENTER);
		intro.setFont(new Font("Monospaced", Font.BOLD, 20));
		intro.setForeground(new Color(200, 0, 0));
		titlePanel.add(title);
		titlePanel.add(intro);
		add(titlePanel, BorderLayout.NORTH);
		
		/* 위 치 */
		JPanel locPanel = new JPanel();
		locPanel.setBackground(Color.WHITE);
		locPanel.setLayout(new GridLayout(2, 3, 15, 15));
		
		//북문 
		ImageIcon northIcon = new ImageIcon(imageFilePath + "northGate2.png");
		JButton northBtn = new JButton("북 문");
		northBtn.addActionListener(this);
		northBtn.setIcon(northIcon);
		locPanel.add(northBtn, new GridLayout(1, 1));
		
		//정문 
		ImageIcon mgIcon = new ImageIcon(imageFilePath + "mainGate2.png");
		JButton mgBtn = new JButton("정 문");
		mgBtn.addActionListener(this);
		mgBtn.setIcon(mgIcon);
		locPanel.add(mgBtn, new GridLayout(1, 2));
		
		//쪽문 
		ImageIcon sideIcon = new ImageIcon(imageFilePath + "sideGate2.png");
		JButton sideBtn = new JButton("쪽 문");
		sideBtn.addActionListener(this);
		sideBtn.setIcon(sideIcon);
		locPanel.add(sideBtn, new GridLayout(1, 3));
		
		//동문 / 텍문 
		ImageIcon eastIcon = new ImageIcon(imageFilePath + "eastGate.png");
		JButton eastBtn = new JButton("동 / 텍문");
		eastBtn.addActionListener(this);
		eastBtn.setIcon(eastIcon);
		locPanel.add(eastBtn, new GridLayout(2, 1));
		
		//학식 
		ImageIcon schoolIcon = new ImageIcon(imageFilePath + "school.png");
		JButton schoolBtn = new JButton("학 식");
		schoolBtn.addActionListener(this);
		schoolBtn.setIcon(schoolIcon);
		locPanel.add(schoolBtn, new GridLayout(2, 2));
		
		//메뉴 추천 
		JButton recBtn = new JButton("?");
		ImageIcon choose = new ImageIcon(imageFilePath + "choose.png");
		recBtn.setIcon(choose);
		recBtn.addActionListener(this);
		locPanel.add(recBtn, new GridLayout(2, 3));
		
		add(locPanel, BorderLayout.CENTER);
		
		/* 조 이름 */
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new GridLayout(2, 1));
		namePanel.setBackground(Color.BLACK);
		JLabel name = new JLabel("자바프로그래밍 1조 - 김혁 X 정해수 X 최정원");
		name.setHorizontalAlignment(JLabel.CENTER);
		name.setFont(new Font("DungGeunMo", Font.BOLD, 16));
		name.setForeground(Color.WHITE);
		namePanel.add(name);
		add(namePanel, BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if (actionCommand.equals("북 문")) { 
			RestaurantChoice northR = new RestaurantChoice("북문");
			northR.setVisible(true);
		} else if (actionCommand.equals("정 문")) {
			RestaurantChoice mainR = new RestaurantChoice("정문");
			mainR.setVisible(true);
		} else if (actionCommand.equals("쪽 문")) {
			RestaurantChoice sideR = new RestaurantChoice("쪽문");
			sideR.setVisible(true);
		} else if (actionCommand.equals("동 / 텍문")) {
			RestaurantChoice eastR = new RestaurantChoice("동/텍문");
			eastR.setVisible(true);
		} else if (actionCommand.equals("학 식")) {
			RestaurantChoice schoolR = new RestaurantChoice("학식");
			schoolR.setVisible(true);
		} else if (actionCommand.equals("?")) {
			recommend site = new recommend();
			site.setVisible(true);
		}
	}
	
	public static void main(String[] args) {
		mainPage gui = new mainPage();
		gui.setVisible(true);
	}

}
