import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class KioskAdmin {
	int tableCount = 0;
    JFrame frame = null;
    Panel pNorth = new Panel();
    // 메인
    public static void main(String[] args) {
        new KioskAdmin();
    }
    public KioskAdmin() {
    	String tmp = JOptionPane.showInputDialog("테이블 갯수를 입력하시오.");
    	tableCount = Integer.parseInt(tmp);
    	initFrame();
    }
	void initFrame() {
    	if(frame == null) {    		
    		frame = new JFrame("카운터");
    	}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	pNorth.removeAll();
        // 프레임 설정단
    	int tmpsizeX ;
    	if(tableCount <= 4)
    		tmpsizeX = tableCount * 150 + 25;
    	else{
    		tmpsizeX = 5 * 150 + 25;
    	}
    	int tmpsizeY = ((int)(tableCount/6)+1) * 185 + 50;
        frame.setBounds(0, 0, tmpsizeX, tmpsizeY);
        frame.setBackground(Color.black);
        // 폰트
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
        JButton bt[] = new JButton[tableCount+1]; 
        JButton sBt = new JButton("Setting");
        sBt.setBounds(0,0, 100, 20);
        sBt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Setting();
			}			
		});
        
        pNorth.add(sBt);
        int tmpX = 0;
        int tmpY = 0;
        for (int i = 1; i <= tableCount; i++) {
            // 햄버거 버튼
            bt[i] = new JButton(i+"번 테이블");
            bt[i].setActionCommand(String.valueOf(i));
            if((int)(i % 5)!= 0) {
            	tmpX = ((int)(i % 5)-1) * 150;
                bt[i].setBounds(25 + tmpX, 50+tmpY, 100, 100);
            }
            if((int)(i % 5) == 0) {
            	tmpX = 4 * 150;
            	bt[i].setBounds(25 + tmpX, 50+tmpY, 100, 100);
            	tmpY += 150;
            	tmpX = 0;
            	System.out.println(i);
            }
            pNorth.add(bt[i]);
            
            bt[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new tableInfo(Integer.parseInt(e.getActionCommand()));
				}
			});
        }
        // 북쪽
        pNorth.setBackground(new Color(255, 255, 215));
        pNorth.setLayout(null);
        pNorth.setSize(tmpsizeX, tmpsizeY);
        pNorth.setFont(font);
        
        frame.add(pNorth, BorderLayout.NORTH);
        frame.setVisible(true);
    }
}