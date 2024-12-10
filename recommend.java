package term_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class recommend extends JFrame implements ActionListener{
	public static final int WIDTH = 500;
	public static final int HEIGHT = 400;
	private JLabel recMenu;
	JPanel answer;
	
	public static final String srcPath = "src"; //src 파일 경로
	private static String currentProjPath = "";
	
	public recommend() {
		setTitle("메뉴를 추천해드려요");
		setSize(WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel menu = new JLabel("오늘 날씨가 어떤가요?");
		menu.setHorizontalAlignment(JLabel.CENTER);
		menu.setFont(new Font("DungGeunMo", Font.BOLD, 24));
		add(menu, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 3, 20, 20));
		
		try{
			currentProjPath = new File(".").getCanonicalPath();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		String imageFilePath = currentProjPath+"/"+srcPath+"/mainPage_image/mainPage_image/";
		
		JButton w1 = new JButton("1");
		ImageIcon i1 = new ImageIcon(imageFilePath + "1.png");
		w1.addActionListener(this);
		w1.setIcon(i1);
		panel.add(w1, new GridLayout(1, 1));
	
		JButton w2 = new JButton("2");
		ImageIcon i2 = new ImageIcon(imageFilePath + "2.png");
		w2.addActionListener(this);
		w2.setIcon(i2);
		panel.add(w2, new GridLayout(1, 2));
		
		JButton w3 = new JButton("3");
		ImageIcon i3 = new ImageIcon(imageFilePath + "3.png");
		w3.addActionListener(this);
		w3.setIcon(i3);
		panel.add(w3, new GridLayout(1, 3));
		
		JButton w4 = new JButton("4");
		ImageIcon i4 = new ImageIcon(imageFilePath + "4.png");
		w4.addActionListener(this);
		w4.setIcon(i4);
		panel.add(w4, new GridLayout(2, 1));
		
		JButton w5= new JButton("5");
		ImageIcon i5 = new ImageIcon(imageFilePath + "5.png");
		w5.addActionListener(this);
		w5.setIcon(i5);
		panel.add(w5, new GridLayout(2, 2));
		
		JButton w6 = new JButton("6");
		ImageIcon i6 = new ImageIcon(imageFilePath + "6.png");
		w6.addActionListener(this);
		w6.setIcon(i6);
		panel.add(w6, new GridLayout(2, 3));
		
		add(panel, BorderLayout.CENTER);
		
		answer = new JPanel();
		answer.setBackground(Color.DARK_GRAY);
		recMenu = new JLabel();
		recMenu.setHorizontalAlignment(JLabel.CENTER);
		recMenu.setFont(new Font("DungGeunMo", Font.BOLD, 20));
		recMenu.setForeground(Color.WHITE);
		answer.add(recMenu);
		add(answer, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) {
		recommend gui = new recommend();
		gui.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if (actionCommand.equals("1")) {
			recMenu.setText("시원한 모밀 추천해요 !");
		} else if (actionCommand.equals("2")) {
			recMenu.setText("파스타는 어때요? 양식을 추천해요 !");
		} else if (actionCommand.equals("3")) {
			recMenu.setText("초밥! 일식을 추천해요 !");
		} else if (actionCommand.equals("4")) {
			recMenu.setText("비 오는 날엔 한식을 추천해요 !");
		} else if (actionCommand.equals("5")) {
			recMenu.setText("따뜻한 국물 요리를 추천해요 !");
		} else if (actionCommand.equals("6")) {
			recMenu.setText("매콤한 음식을 추천해요 !");
		}
	}

}
