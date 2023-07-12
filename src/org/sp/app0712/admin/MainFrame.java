package org.sp.app0712.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.sp.app0712.admin.page.BoardPage;
import org.sp.app0712.admin.page.CompanyPage;
import org.sp.app0712.admin.page.MemberPage;
import org.sp.app0712.admin.page.Page;
import org.sp.app0712.admin.page.ProductPage;

public class MainFrame extends JFrame implements ActionListener{
	ArrayList<JButton> menu; ///어플리케이션의 네비게이션 메뉴
	ImageIcon[] icon;
	JPanel p_north; //네비게이션들이 올라갈 컨테이너
	JPanel p_center; //모든 컨텐츠의 페이지가 붙여질 컨테이너
	JScrollPane scroll;
	
	Page[] pages; //모든 페이지들을 담을 수 있는 페이지 배열
	
	public MainFrame() {
		menu=new ArrayList<JButton>();
		icon=new ImageIcon[4];
		pages=new Page[4];
		p_north=new JPanel();
		
		createIcon();
		
		//버튼 4개 생성
		for(int i=0; i<4; i++) {
			JButton bt=new JButton(icon[i]);
			menu.add(bt); //컬렉션에 추가
			p_north.add(bt);
			
			//생성된 각 버튼과 리스너 연결
			bt.addActionListener(this);
		}
		
		p_center=new JPanel();
		scroll=new JScrollPane(p_center);
		p_center.setBackground(Color.YELLOW);
		
		//네비를 포함하고 있는 패널을 북쪽에 부착
		add(p_north, BorderLayout.NORTH);
		add(scroll);
		
		createPage();
		
		setSize(1000, 700);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public void createPage() {
		pages[0]=new ProductPage();
		pages[1]=new MemberPage();
		pages[2]=new CompanyPage();
		pages[3]=new BoardPage();
		
		for(int i=0; i<pages.length; i++) {
			p_center.add(pages[i]);
		}	
	}
	
	public void createIcon() {
		String[] path= {"product.png", "member.png", "company.png", "board.png"};
		for(int i=0; i<path.length; i++) {
			try {
				Image image=ImageIO.read(ClassLoader.getSystemResource("res/icon/"+path[i]));
				icon[i]=new ImageIcon(image); //반복문으로 생성된 이미지들을 icon[] 배열에 0번째부터 채워넣기
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
	}
	
	//페이지를 보이기/감추기 처리를 하는 메서드 정의
	//index 값은 보여주고 싶은 페이지의 index를 넘기면 됨. -> 버튼의 순번과 일치
	public void showHide(int index) {
		for(int i=0; i<pages.length; i++) {
			if(i==index) {
				pages[i].setVisible(true);
			}else {
				pages[i].setVisible(false);			
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource(); //이벤트를 일으킨 컴포넌트 얻기
		
		//버튼 4개 중에 내가 누른 버튼의 index 알아맞히기
		int index=menu.indexOf(obj);
		//System.out.println(index+"번째 버튼 눌림");
		showHide(index);
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}

	
}
