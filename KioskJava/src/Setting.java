import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Setting {
    JFrame frame = null;
    JPanel Pane = new JPanel();
    void initSetting() {
    	if(frame == null) {    		
    		frame = new JFrame("Settings");
    	}
    	Pane.removeAll();
    	Pane.setLayout(null);
		
		JLabel l1 = new JLabel("카테고리");
		l1.setBounds(10,0,60,20);
		JLabel l2 = new JLabel("이름");
		l2.setBounds(90,0,60,20);
		JLabel l3 = new JLabel("가격");
		l3.setBounds(230,0,60,20);
		Pane.add(l1);
		Pane.add(l2);
		Pane.add(l3);
		
		ArrayList tmpArr = null;
		try {
        	tmpArr = SQLC.GetMenusAll();    
        }catch(Exception e) {
        }
		String menu[] = new String[tmpArr.size()];		
        int price[] = new int[tmpArr.size()];
        String categories[] = new String[tmpArr.size()];
    	for(int i = 0; i < tmpArr.size(); i++) {
    		if(tmpArr.size() != 0 && tmpArr.get(i) != null &&tmpArr.get(i).toString().split(":")[0] != null) {
    			menu[i] = tmpArr.get(i).toString().split(":")[0];
    			price[i] = Integer.parseInt(tmpArr.get(i).toString().split(":")[1]);
    			categories[i] = tmpArr.get(i).toString().split(":")[2];
    		}
    	}
    	
    	JTextField tfcategory[] = new JTextField[menu.length+1];
    	JTextField tfname[] = new JTextField[menu.length+1];
    	JTextField tfprice[] = new JTextField[menu.length+1];
    	JButton bt[] = new JButton[menu.length+1];
    	JButton bt2[] = new JButton[menu.length];
		frame.setBounds(0, 0, 500, 80 + ((menu.length+1)*30));
    	for(int i = 0; i <= menu.length; i++) {
    		if(i == menu.length) {
    			tfcategory[i] = new JTextField();
        		tfcategory[i].setBounds(10,30 + (i*25),60,20);
        		
        		tfname[i] = new JTextField();
        		tfname[i].setBounds(90,30 + (i*25),120,20);
        		
        		tfprice[i] = new JTextField();
        		tfprice[i].setBounds(230,30 + (i*25),120,20);
        		
        		bt[i] = new JButton("추가");        	
        		bt[i].setBounds(355, 30 + (i*25), 60, 20);
        		Pane.add(bt[i]);
        		Pane.add(tfprice[i]);
        		Pane.add(tfcategory[i]);
        		Pane.add(tfname[i]);
    			break;
    		}
    		tfcategory[i] = new JTextField();
    		tfcategory[i].setBounds(10,30 + (i*25),60,20);
    		tfcategory[i].setText(categories[i]);
    		
    		tfname[i] = new JTextField();
    		tfname[i].setBounds(90,30 + (i*25),120,20);
    		tfname[i].setText(menu[i]);
    		tfname[i].setEnabled(false);
    		
    		tfprice[i] = new JTextField();
    		tfprice[i].setBounds(230,30 + (i*25),120,20);
    		tfprice[i].setText(String.valueOf(price[i]));
    		
    		bt[i] = new JButton("수정");
    		bt[i].setActionCommand(menu[i]);
    		bt[i].setBounds(355, 30 + (i*25), 60, 20);
    		
    		bt2[i] = new JButton("삭제");
    		bt2[i].setActionCommand(menu[i]);
    		bt2[i].setBounds(420, 30 + (i*25), 60, 20);
    		Pane.add(bt[i]);
    		Pane.add(bt2[i]);
    		Pane.add(tfprice[i]);
    		Pane.add(tfcategory[i]);
    		Pane.add(tfname[i]);
    		
    	}
		frame.add(Pane);
		frame.setVisible(true);
		
		for(int i = 0; i <= menu.length; i++) {
    		if(i == menu.length) {
        		bt[i].addActionListener(new ActionListener() {					
					@Override
					public void actionPerformed(ActionEvent e) {
						String tmpC = tfcategory[menu.length].getText();
						String tmpN = tfname[menu.length].getText();
						String tmpP = tfprice[menu.length].getText();
						if(tmpC.equals("") || tmpN.equals("") || tmpP.equals("")) {
							return;
						}
						SQLC.AddMenu(tmpC, tmpN, tmpP);
						initSetting();
					}
				});        
    			break;
    		}
    		bt[i].addActionListener(new ActionListener() {					
				@Override
				public void actionPerformed(ActionEvent e) {
					for(int k = 0; k < tfname.length; k++) {
						if(tfname[k].getText().equals(e.getActionCommand())) {
							String tmpC = tfcategory[k].getText();
							String tmpN = tfname[k].getText();
							String tmpP = tfprice[k].getText();
							if(tmpC.equals("") || tmpN.equals("") || tmpP.equals("")) {
								return;
							}
							SQLC.ModiMenu(tmpC, tmpN, tmpP);
							initSetting();
						}
					}
					System.out.println(e.getActionCommand());
				}
			});
    		bt2[i].addActionListener(new ActionListener() {					
				@Override
				public void actionPerformed(ActionEvent e) {
					SQLC.DelMenu(e.getActionCommand());
					initSetting();
				}
			});    		
    	}
    }
	public Setting() {
		initSetting();
	}
}
