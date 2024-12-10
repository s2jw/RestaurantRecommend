package term_project;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.Map;
import java.util.HashMap;
import java.io.*;

public class Store extends JFrame implements ActionListener{
	protected String Name;		  // 식당명
	protected String TradeName;   // 상호명
	protected String runningtime; // 운영시간
	protected String closedDay;   // 휴무일
	protected String PhoneNum;    // 전화번호
	protected String Location;    // 위치
	protected static int MenuNum; // 메뉴 개수
	protected String[] Menu;	  // 메뉴
	protected int StoreNum;       // 가게
	protected static int ReviewNum;// 리뷰 개수
	protected String[] wReview;   // 리뷰
	protected int[] Star;         // 리뷰 별 개수
	protected Double ReviewAvg = 0.0;// 평점 평균
	protected int ReviewStar = 1; // 선택한 평점
	protected String currentProjPath;
	
	private JPanel MainPanel;
	private	GridBagLayout gBag;
	private JList MenuList;
	private JList ReviewList;
	private JButton MenuBtn;
	private JButton InfoBtn;
	private JButton ReviewBtn;
	private JTextArea InfoLabel;
	private JScrollPane MenuScroll;
	private JScrollPane ReviewScroll;
	private JPanel StarAvg;
	private JPanel WriteReview;
	private JTextField Write;
	
	private static final int WIDTH = 1300; // windows창 크기
	private static final int HEIGHT = 1000;
	private static final int NAMEPANEL_X = 1200; // 식당이름패널 크기
	private static final int NAMEPANEL_Y = 100;
	private static final int BTN_X = 400; // 버튼 크기
	private static final int BTN_Y = 100;
	private static final int MAINPANEL_X = 1200; // 메인패널 크기
	private static final int MAINPANEL_Y = 600;
	private static final int MENUICON_X = 150; // 메뉴 아이콘 크기
	private static final int MENUICON_Y = 100;
	private static final int REVIEWSCROLL_X = 500; //리뷰 스크롤 크기
	private static final int REVIEWSCROLL_Y = 360;
	private static final int STARIMAGE_X = 150; // 별 이미지 크기
	private static final int STARIMAGE_Y = 50;
	private static final int WRITE_X = 1000; // 리뷰 적는 곳 크기
	private static final int WRITE_Y = 100;
	private static final int SAVE_X = 200; // 세이브 버튼 크기
	private static final int SAVE_Y = 100;
	
	private Map<String, ImageIcon> imageMap;
	private Map<String, ImageIcon> reviewMap;
	public static final String srcPath = "src";
	public static final String packageName = "term_project";
	
	public void paint(Graphics g){
		super.paint(g);
		Image background = new ImageIcon(currentProjPath + "/" + srcPath + 
									"/Background" +"/storebackground.jpg").getImage();
		g.drawImage(background, 0, 0, null);
	}
	
	public Store(int Num){
		super();
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBackground(Color.WHITE);
		gBag = new GridBagLayout();
		setLayout(gBag);
		setVisible(true);
		
		StoreNum = Num;
		
		getInfo();	//가게 정보 읽어오기
		setTitle(Name);
		
		//식당명 상단에 위치하기
		JLabel NameLabel = new JLabel(Name);
		Font Namefont = new Font("맑은 고딕", Font.BOLD, 50); //폰트
		NameLabel.setFont(Namefont);
		NameLabel.setVerticalAlignment(JLabel.CENTER);
		NameLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel NamePanel = new JPanel();
		NamePanel.setLayout(new BorderLayout());
		NamePanel.setBackground(Color.CYAN);
		NamePanel.setPreferredSize(new Dimension(NAMEPANEL_X, NAMEPANEL_Y));
		NamePanel.add(NameLabel, BorderLayout.CENTER);
		gbinsert(NamePanel, 0, 0, 3, 1);
		
		Font Btnfont = new Font("helvitica", Font.BOLD, 40);
		
		//메뉴 버튼 
		MenuBtn = new JButton("Menu");
		MenuBtn.setBackground(Color.GREEN);
		MenuBtn.setPreferredSize(new Dimension(BTN_X, BTN_Y));
		MenuBtn.setFont(Btnfont);
		MenuBtn.addActionListener(this);
		gbinsert(MenuBtn, 0, 1, 1, 1);
		
		//정보 버튼
		InfoBtn = new JButton("Information");
		InfoBtn.setBackground(Color.GREEN);
		InfoBtn.setPreferredSize(new Dimension(BTN_X, BTN_Y));
		InfoBtn.setFont(Btnfont);
		InfoBtn.addActionListener(this);
		gbinsert(InfoBtn, 1, 1, 1, 1);
		
		//리뷰 버튼
		ReviewBtn = new JButton("Review");
		ReviewBtn.setBackground(Color.GREEN);
		ReviewBtn.setPreferredSize(new Dimension(BTN_X, BTN_Y));
		ReviewBtn.setFont(Btnfont);
		ReviewBtn.addActionListener(this);
		gbinsert(ReviewBtn, 2, 1, 1, 1);
		
		//메인 패널
		MainPanel = new JPanel();
		MainPanel.setPreferredSize(new Dimension(MAINPANEL_X, MAINPANEL_Y));
		MenuClick();
		gbinsert(MainPanel, 0, 2, 3, 7);
	}
	
	public void getInfo(){
		// 가게 정보 받아오기
		currentProjPath = "";
		try{
			currentProjPath = new File(".").getCanonicalPath();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		String StoreInformation = "StoreInfo.txt";
		String storeFilePath = currentProjPath+"/"+srcPath+"/store/"+
				StoreNum+"/"+StoreInformation;
		
		try{
			File file = null;
			BufferedReader br = null;
			
			file = new File(storeFilePath);
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
			
			Name = br.readLine();
			TradeName = br.readLine();
			runningtime = br.readLine();
			closedDay = br.readLine();
			PhoneNum = br.readLine();
			Location = br.readLine();
			MenuNum = Integer.parseInt(br.readLine());
			Menu = new String[MenuNum];
			for(int i = 0; i < MenuNum; i++){
				String menuName = br.readLine();
				String Price = br.readLine();
				Menu[i] = "<html>" + menuName + "<br/>" + Price + "<html>";
			}
			
			br.close();
		}catch(Exception e){
			System.out.println("File not found.");
			System.exit(0);
		}
		
		// 리뷰 정보 받아오기
		String review = "Review";
		String reviewPath = currentProjPath+"/"+srcPath+"/store/"+
				StoreNum+"/"+review;
				
		Review[] R = null;
		try{
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(reviewPath));
			R = (Review[])inputStream.readObject();

			inputStream.close();
			
		}catch(FileNotFoundException e){
			System.err.println("File not found.");
			System.exit(0);
		}catch(ClassNotFoundException e){
			System.err.println("Problems with class");
			System.exit(0);
		}catch(IOException e){
			System.err.println("IO Error.");
			System.exit(0);			
		}
		ReviewNum = R.length;
		wReview = new String[ReviewNum];
		Star = new int[ReviewNum];
		for(int i = 0; i < ReviewNum; i++){
			Star[i] = R[i].getStar();
			wReview[i] = R[i].getreview();
		}
		
		
	}
	
	//GridBagLayout에 component 넣기
	public void gbinsert(JComponent c, int x, int y, int w, int h){
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gBag.setConstraints(c, gbc);
		this.add(c);
	}
	
	//MenuList를 위한 Map 만들기
	private Map<String, ImageIcon> createImageMap(String[] list){
		Map<String, ImageIcon> map = new HashMap<>();
		
		String storeFilePath = currentProjPath+"/"+srcPath+"/store/"+StoreNum+"/menu";
		
		try{
			for(int i = 1; i <= MenuNum; i++){
				ImageIcon image = new ImageIcon(storeFilePath + i + ".jpg");
				image = imageSetsize(image, MENUICON_X, MENUICON_Y);
				map.put(list[i - 1], image);
			}
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return map;
	}
	
	private void MenuClick(){
		if(MenuScroll == null){
			//메뉴 패널 만들기
			imageMap = createImageMap(Menu);
			MenuList = new JList(Menu);
			//MenuList.setVisibleRowCount(3);
			MenuList.setBackground(Color.WHITE);
			MenuList.setCellRenderer(new ListRenderer());
		
			MenuScroll = new JScrollPane(MenuList);
			MenuScroll.setPreferredSize(new Dimension(MAINPANEL_X, MAINPANEL_Y));
			MenuScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			MenuScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		}
		MainPanel.add(MenuScroll);
	}
	
	//MenuList 보일 때 설정하기
	public class ListRenderer extends DefaultListCellRenderer{
		Font font = new Font("monospaced", Font.BOLD, 24);
		
		public Component getListCellRendererComponent(
				JList list, Object value, int index, 
				boolean isSelected, boolean cellHasFocus){
			
			JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			label.setIcon(imageMap.get((String) value));
			label.setHorizontalTextPosition(JLabel.RIGHT);
			label.setFont(font);
			return label;
		}
	}
	
	private void InfoClick(){
		//정보 패널 만들기
		if(InfoLabel == null){
			Font font = new Font("monospaced", Font.PLAIN, 24);
			String info = "\n상호명 : " + TradeName + "\n\n운영시간 : " + runningtime +
					"\n\n휴무일 : " + closedDay + "\n\n전화번호 : " + PhoneNum + 
					"\n\n위치 : " + Location + "\n";
			InfoLabel = new JTextArea(info);
			InfoLabel.setBackground(Color.WHITE);
			InfoLabel.setFont(font);
			InfoLabel.setPreferredSize(new Dimension(MAINPANEL_X, MAINPANEL_Y));
			MainPanel.add(InfoLabel);
		}else{
			MainPanel.add(InfoLabel);
		}
	}
	
	private void ReviewClick(){
		if(ReviewScroll == null){
			//평점 평균 구하기
			for(int i = 0; i < ReviewNum; i++){
				ReviewAvg += Star[i];
			}
			ReviewAvg = ReviewAvg / ReviewNum;
			
			//리뷰 패널 만들기
			reviewMap = createReviewMap(wReview);
			ReviewList = new JList(wReview);
			//ReviewList.setVisibleRowCount(3);
			ReviewList.setBackground(Color.WHITE);
			ReviewList.setCellRenderer(new ReviewListRenderer());
		
			ReviewScroll = new JScrollPane(ReviewList);
			ReviewScroll.setPreferredSize(new Dimension(REVIEWSCROLL_X, REVIEWSCROLL_Y));
			ReviewScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			ReviewScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			
			//평점 평균 패널 만들기
			StarAvg = new JPanel();
			StarAvg.setLayout(new BorderLayout());
			//StarAvg.setBackground(Color.LIGHT_GRAY);
			
			JLabel average = new JLabel("       평점 평균");
			Font averagefont = new Font("맑은 고딕", Font.BOLD, 20);
			average.setFont(averagefont);
			StarAvg.add(average, BorderLayout.NORTH);
			
			JLabel Avg = new JLabel();
			Font Avgfont = new Font("helvitica",Font.PLAIN ,30);
			Avg.setText("  " + ReviewAvg.toString());
			Avg.setFont(Avgfont);
			StarAvg.add(Avg, BorderLayout.CENTER);
			
			String starPath = currentProjPath+"/"+srcPath+"/store/star";
			ImageIcon starIcon;
			
			if(ReviewAvg >= 4.5){
				starIcon = new ImageIcon(starPath + "5.jpg");
			}else if(ReviewAvg >= 3.5){
				starIcon = new ImageIcon(starPath + "4.jpg");
			}else if(ReviewAvg >= 2.5){
				starIcon = new ImageIcon(starPath + "3.jpg");
			}else if(ReviewAvg >= 1.5){
				starIcon = new ImageIcon(starPath + "2.jpg");
			}else{
				starIcon = new ImageIcon(starPath + "1.jpg");
			}
			JLabel iconLabel = new JLabel();
			iconLabel.setIcon(starIcon);
			StarAvg.add(iconLabel, BorderLayout.WEST);
			
			//리뷰 쓰는 패널 만들기
			WriteReview = new JPanel();
			WriteReview.setLayout(new BorderLayout());
			
			JPanel StarPanel = new JPanel();
			StarPanel.setBackground(Color.WHITE);
			StarPanel.setLayout(new FlowLayout());
			
			JButton OneStar = new JButton();
			OneStar.setActionCommand("1");
			ImageIcon oneS = new ImageIcon(starPath + "1.jpg");
			oneS = imageSetsize(oneS, STARIMAGE_X, STARIMAGE_Y);
			OneStar.setIcon(oneS);
			OneStar.setBackground(Color.WHITE);
			OneStar.addActionListener(this);
			StarPanel.add(OneStar);
			
			JButton TwoStar = new JButton();
			TwoStar.setActionCommand("2");
			ImageIcon twoS = new ImageIcon(starPath + "2.jpg");
			twoS = imageSetsize(twoS, STARIMAGE_X, STARIMAGE_Y);
			TwoStar.setIcon(twoS);
			TwoStar.setBackground(Color.WHITE);
			TwoStar.addActionListener(this);
			StarPanel.add(TwoStar);
			
			JButton ThreeStar = new JButton();
			ThreeStar.setActionCommand("3");
			ImageIcon threeS = new ImageIcon(starPath + "3.jpg");
			threeS =imageSetsize(threeS, STARIMAGE_X, STARIMAGE_Y);
			ThreeStar.setIcon(threeS);
			ThreeStar.setBackground(Color.WHITE);
			ThreeStar.addActionListener(this);
			StarPanel.add(ThreeStar);
			
			JButton FourStar = new JButton();
			FourStar.setActionCommand("4");
			ImageIcon fourS = new ImageIcon(starPath + "4.jpg");
			fourS = imageSetsize(fourS, STARIMAGE_X, STARIMAGE_Y);
			FourStar.setIcon(fourS);
			FourStar.setBackground(Color.WHITE);
			FourStar.addActionListener(this);
			StarPanel.add(FourStar);
			
			JButton FiveStar = new JButton();
			FiveStar.setActionCommand("5");
			ImageIcon fiveS = new ImageIcon(starPath + "5.jpg");
			fiveS = imageSetsize(fiveS, STARIMAGE_X, STARIMAGE_Y);
			FiveStar.setIcon(fiveS);
			FiveStar.setBackground(Color.WHITE);
			FiveStar.addActionListener(this);
			StarPanel.add(FiveStar);
			
			Write = new JTextField("평점을 선택하고 한줄평을 해주세요.", 30);
			Write.setBackground(Color.WHITE);
			Write.setPreferredSize(new Dimension(WRITE_X, WRITE_Y));
			Font writefont = new Font("helvitica", Font.BOLD, 25);
			Write.setFont(writefont);
			
			JButton Save = new JButton("SAVE");
			Save.setPreferredSize(new Dimension(SAVE_X, SAVE_Y));
			Font Savefont = new Font("Serif", Font.BOLD, 40);
			Save.setFont(Savefont);
			Save.addActionListener(this);
			Save.setBackground(Color.LIGHT_GRAY);
			
			WriteReview.add(StarPanel, BorderLayout.NORTH);
			WriteReview.add(Write, BorderLayout.CENTER);
			WriteReview.add(Save, BorderLayout.EAST);
		}
		
		MainPanel.setLayout(new BorderLayout());
		MainPanel.add(ReviewScroll, BorderLayout.CENTER);
		MainPanel.add(StarAvg, BorderLayout.NORTH);
		MainPanel.add(WriteReview, BorderLayout.SOUTH);
	
	}
	
	//리뷰 리스트를 만들기 위한 Map
	private Map<String, ImageIcon> createReviewMap(String[] list){
		Map<String, ImageIcon> map = new HashMap<>();
		
		String storeFilePath = currentProjPath+"/"+srcPath+"/store/star";
		
		try{
			for(int i = 0; i < ReviewNum; i++){
				ImageIcon image = new ImageIcon(storeFilePath + Star[i] + ".jpg");
				image = imageSetsize(image, STARIMAGE_X, STARIMAGE_Y);
				map.put(list[i], image);
			}
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return map;
	}
	
	public class ReviewListRenderer extends DefaultListCellRenderer{
		Font font = new Font("monospaced", Font.BOLD, 25);
		
		public Component getListCellRendererComponent(
				JList list, Object value, int index, 
				boolean isSelected, boolean cellHasFocus){
			
			JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			label.setIcon(reviewMap.get((String) value));
			label.setHorizontalTextPosition(JLabel.RIGHT);
			label.setFont(font);
			return label;
		}
	}
	
	private void SaveClick(){
		String writtenR = Write.getText();
		Write.setText("평점을 선택하고 한줄평을 해주세요.");
		
		ObjectOutputStream outputStream;
		try{
			String reviewPath = currentProjPath+"/"+srcPath+"/store/"+
					StoreNum+"/Review";
			outputStream = new ObjectOutputStream(new FileOutputStream(reviewPath));
			
			Review[] R = new Review[ReviewNum + 1];
			for(int i = 0; i < ReviewNum; i++){
				R[i] = new Review(Star[i], wReview[i]);
			}
			
			R[ReviewNum] = new Review(ReviewStar, writtenR);
			outputStream.writeObject(R);
			
			outputStream.close();
		} catch(IOException e){
			System.err.println("Error writing");
			System.exit(0);
		}
		
	}
	
	public ImageIcon imageSetsize(ImageIcon icon, int i, int j){
		Image ximg = icon.getImage();
		Image yimg = ximg.getScaledInstance(i, j, java.awt.Image.SCALE_SMOOTH);
		ImageIcon xyimg = new ImageIcon(yimg);
		return xyimg;
	}
	
	public void actionPerformed(ActionEvent e){
		String actionCmd = e.getActionCommand();
		
		if(actionCmd.equals("Menu")){
			MainPanel.removeAll();
			MenuClick();
			MainPanel.updateUI();
		}else if(actionCmd.equals("Information")){
			MainPanel.removeAll();
			InfoClick();
			MainPanel.updateUI();
		}else if(actionCmd.equals("Review")){
			MainPanel.removeAll();
			ReviewClick();
			MainPanel.updateUI();
		}else if(actionCmd.equals("1")){
			ReviewStar = 1;
		}else if(actionCmd.equals("2")){
			ReviewStar = 2;
		}else if(actionCmd.equals("3")){
			ReviewStar = 3;
		}else if(actionCmd.equals("4")){
			ReviewStar = 4;
		}else if(actionCmd.equals("5")){
			ReviewStar = 5;
		}else if(actionCmd.equals("SAVE")){
			SaveClick();
		}else{
			System.out.println("Unexpected error");
		}
		
	}
	
	/*public static void main(String[] args) {
		Store gui = new Store(2);
		gui.setVisible(true);
	}*/

}
