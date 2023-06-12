import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
 
public class Kiosk {

	int tableNo = 0;
    int count = 0;
    String show = "";
    String prev = "   상품명   :    단가   :    수량   :    합계\n\n";
    JFrame frame = null;
    Panel pNorth = new Panel();
    Panel pSouth = new Panel();
    TextArea ta = new TextArea("", 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
    void initFrame(String Category) {
    	if(frame == null) {    		
    		frame = new JFrame("햄버거 자동 판매기");
    		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	}
    	pNorth.removeAll();
    	pSouth.removeAll();
    	// 디자인단
        // 프레임 설정단
        frame.setBounds(0, 0, 780, 1000);
        frame.setBackground(Color.black);
        
        // 폰트
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
        Font font1 = new Font(Font.MONOSPACED, Font.BOLD, 22);
 
        // 북쪽
        pNorth.setBackground(new Color(255, 255, 215));
        pNorth.setLayout(null);
        pNorth.setSize(0, 500);
        pNorth.setFont(font);
        
        ArrayList tmpArr = null;
        ArrayList tmpArrC = null;
        try {
        	tmpArr = SQLC.GetMenus(Category);    
        	tmpArrC = SQLC.GetCategories();
        }catch(Exception e) {
        }
        
        // 배열 설정 부분
        String menu[] = new String[tmpArr.size()];
        JButton categories[] = new JButton[tmpArrC.size()];
        int price[] = new int[tmpArr.size()];

    	for(int i = 0; i < tmpArr.size(); i++) {
    		menu[i] = tmpArr.get(i).toString().split(":")[0];
    		price[i] = Integer.parseInt(tmpArr.get(i).toString().split(":")[1]);
    	}
    	for(int i = 0; i < tmpArrC.size(); i++) {
    		String tmp = tmpArrC.get(i).toString();
    		categories[i] = new JButton(tmp);
    		categories[i].setBounds(i*100,0, 100, 20);    		
    		categories[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	prev = ta.getText();
                	initFrame(tmp);
                    }
                }
            );
    		pNorth.add(categories[i]);
    	}
    	
        JButton bt[] = new JButton[menu.length];
        TextField suja[] = new TextField[menu.length];
        Button minus[] = new Button[menu.length];
        Button plus[] = new Button[menu.length];
        JButton ok[] = new JButton[menu.length];
        JLabel N[] = new JLabel[menu.length];
        JLabel l[] = new JLabel[menu.length];
 
        // 버튼 설정 부분
        for (int i = 0; i < menu.length; i++) {
 
            // 햄버거 버튼
            bt[i] = new JButton(menu[i]);
            if (i < 5) {
                bt[i].setBounds(25 + i * 150, 50, 100, 100);
            } else {
                bt[i].setBounds(25 + (i - 5) * 150, 300, 100, 100);
            }       
            //bt[i].setEnabled(false);
 
            // 숫자 txt 버튼
            suja[i] = new TextField("0");
            suja[i].setBackground(Color.white);
            suja[i].setEditable(false);
            suja[i].setBounds(bt[i].getX() + 30, bt[i].getY() + 130, 40, 20);
 
            // "-" 버튼
            minus[i] = new Button("-");
            minus[i].setBounds(bt[i].getX(), suja[i].getY(), 20, 20);
            //minus[i].setEnabled(false);
 
            // "+" 버튼
            plus[i] = new Button("+");
            plus[i].setBounds(bt[i].getX() + (100 - 20), suja[i].getY(), 20, 20);
            //plus[i].setEnabled(false);
 
            // 제품명
            N[i] = new JLabel(menu[i]);
            System.out.println();
            N[i].setBounds(bt[i].getX()+(bt[i].getWidth()/2)-(N[i].getText().length()*5), suja[i].getY() - 32, 100, 20);
            
            // 가격
            l[i] = new JLabel(price[i] + "원");
            l[i].setBounds(bt[i].getX()+(bt[i].getWidth()/2)-(l[i].getText().length()*5), suja[i].getY() - 20, 100, 20);
 
            // 확인 버튼
            ok[i] = new JButton("추가");
            ok[i].setBounds(bt[i].getX(), suja[i].getY() + 30, 100, 20);
            ok[i].setEnabled(false);
 
            pNorth.add(bt[i]);
            pNorth.add(suja[i]);
            pNorth.add(minus[i]);
            pNorth.add(plus[i]);
            pNorth.add(l[i]);
            pNorth.add(N[i]);
            pNorth.add(ok[i]);
        }
 
        // 중앙
        ta.setText(prev);       
        ta.setBackground(Color.white);
        ta.setEditable(false);
        ta.setFont(font1);
 
        // 남쪽
        pSouth.setFont(font);
        pSouth.setBackground(new Color(255, 255, 215));
 
        JButton bt1 = new JButton("주문");
        JButton bt2 = new JButton("초기화");
        pSouth.add(bt1);
        pSouth.add(bt2);
 
        // 주문버튼
        bt1.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e) {
            	String[] arr = ta.getText().split("\n");
            	ArrayList orderList = new ArrayList();
            	for(int i = 1; i < arr.length;i++) {
            		if(arr[i].split(":")[0]!="") {
            			System.out.println(arr[i].split(":")[0].trim()+":"+arr[i].split(":")[2].trim()+":"+arr[i].split(":")[1].trim());
            			orderList.add(arr[i].split(":")[0].trim()+":"+arr[i].split(":")[2].trim()+":"+arr[i].split(":")[1].trim());
            		}
            	}
            	SQLC.DoOrder(orderList, tableNo);
                JOptionPane.showMessageDialog(null, ta.getText() + " 주문되었습니다. \n이용해주셔서 감사합니다.");
                for (int i = 0; i < menu.length; i++) {
                    //bt[i].setEnabled(true);
                    //minus[i].setEnabled(false);
                    //plus[i].setEnabled(false);
                    suja[i].setText("0");
                    ta.setText("   상품명   :    단가   :    수량   :    합계\n\n");                    
                }
            }
        });
 
        // 초기화 버튼
        bt2.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < menu.length; i++) {
                    bt[i].setEnabled(true);
//                    minus[i].setEnabled(false);
//                    plus[i].setEnabled(false);
                    suja[i].setText("0");
                    prev = "   상품명   :    단가   :    수량   :    합계\n\n";
                    ta.setText(prev);
 
                }
            }
        });
 
        // 컴포넌트
        frame.add(pNorth, BorderLayout.NORTH);
        frame.add(ta, BorderLayout.CENTER);
        frame.add(pSouth, BorderLayout.SOUTH);
        frame.setVisible(true);
 
        // 이벤트단
        for (int i = 0; i < menu.length; i++) {
            int j = i;
 
            // 햄버그 버튼 이벤트
//            bt[i].addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    minus[j].setEnabled(true);
//                    plus[j].setEnabled(true);
//                    bt[j].setEnabled(false);
//                    ok[j].setEnabled(true);
// 
//                    count = 0;
//                }
//            });
 
            // "-" 버튼 이벤트
            minus[i].addActionListener(new ActionListener() {
 
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (count > 0) {
                        count = count - 1;
                        suja[j].setText(count + "");
                        ok[j].setEnabled(true);
                    } else {
                        minus[j].setEnabled(false);
                    }
                }
            });
            
            // "+" 버튼 이벤트
            plus[i].addActionListener(new ActionListener() {
 
                @Override
                public void actionPerformed(ActionEvent e) {
                    count = count + 1;
                    suja[j].setText(count + "");
                    ok[j].setEnabled(true);
                    if (count > 0) {
                        minus[j].setEnabled(true);
                    }
                }
            });
            
            //확인 버튼 이벤트
            ok[i].addActionListener(new ActionListener() {
 
                @Override
                public void actionPerformed(ActionEvent e) {
                	show = bt[j].getActionCommand();
                	String[] tmp = ta.getText().split("\n");
                	boolean isthere = false;
                	for(int k = 0; k<tmp.length; k++) {
                		System.out.println(tmp[k].split(":")[0].trim()+","+show);
                		if(tmp[k].split(":")[0].trim().equals(show)) {
                			count = count + Integer.parseInt(tmp[k].split(":")[2].trim());
                			tmp[k] ="   " + show + "   :   " + price[j] + "   :   " + count + "    :   " + (price[j]*count) + "원";
                			isthere = true;
                			break;
                		}
                	}
                	if(!isthere) {                  
                		ta.append("   " + show + "   :   " + price[j] + "   :   " + count + "    :   " + (price[j]*count) + "원" + "\n");
                	}else {
                		String NxtTxt = "";
                		for(int k = 0; k<tmp.length; k++) {
                    			NxtTxt += tmp[k]+"\n";
                    	}
                		ta.setText(NxtTxt);
                	}
                    ok[j].setEnabled(false);
                    count = 0;
                    suja[j].setText(count + "");
                }
            });
 
        }
 
        // 끄기
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    public Kiosk() {
    	tableNo = Integer.parseInt(JOptionPane.showInputDialog("테이블 번호를 입력하시오."));
    	initFrame("Main");
    }
 
    // 메인
    public static void main(String[] args) {
        new Kiosk();
    }
 
}