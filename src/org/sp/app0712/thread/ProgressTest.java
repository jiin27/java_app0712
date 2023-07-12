package org.sp.app0712.thread;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

//진행상황을 직관적으로 표현해주는 컴포넌트인 JProgressbar를 사용해보되, thread를 이용하여 서로 다른 속도와 크기로 동작하도록 해보자.
public class ProgressTest extends JFrame{
	JProgressBar bar1;
	JProgressBar bar2;
	JProgressBar bar3;
	JButton bt;
	MyThread t1, t2, t3;
	
	public ProgressTest() {
		bar1=new JProgressBar();
		bar2=new JProgressBar();
		bar3=new JProgressBar();
		bt=new JButton("시작");
				
		//스타일
		bar1.setPreferredSize(new Dimension(480, 50));
		bar2.setPreferredSize(new Dimension(480, 50));
		bar3.setPreferredSize(new Dimension(480, 50));
		
		setLayout(new FlowLayout());
		
		add(bar1);
		add(bar2);
		add(bar3);
		add(bt);
	
		setSize(500, 250);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//버튼에 리스너 연결
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("나눌렀어?");
				t1 = new MyThread(500, bar1);
				t2 = new MyThread(100, bar2);
				t3 = new MyThread(10, bar3);
				
				t1.start();
				t2.start();
				t3.start();
			}
		});
	}
		
	public static void main(String[] args) {
		new ProgressTest();
	}
}
