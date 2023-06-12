import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class tableInfo {
	JFrame frame = null;
    JPanel Pane = new JPanel();
    int tableNo = 0;
    void initSetting(int tableNo) {
    	if(frame == null) {    		
    		frame = new JFrame("Settings");
    	}
    	Pane.removeAll();
    	Pane.setLayout(null);
		
		JLabel l1 = new JLabel("이름");
		l1.setBounds(10,0,120,20);
		JLabel l2 = new JLabel("가격");
		l2.setBounds(150,0,80,20);
		JLabel l3 = new JLabel("개수");
		l3.setBounds(240,0,80,20);
		JLabel l4 = new JLabel("합");
		l4.setBounds(330,0,80,20);
		Pane.add(l1);
		Pane.add(l2);
		Pane.add(l4);
		Pane.add(l3);
		
		ArrayList tmpArr = null;
		try {
        	tmpArr = SQLC.GetOrders(tableNo);    
        }catch(Exception e) {
        }
		String menu[] = new String[tmpArr.size()];		
        int price[] = new int[tmpArr.size()];
        int count[] = new int[tmpArr.size()];
        int total[] = new int[tmpArr.size()];
    	for(int i = 0; i < tmpArr.size(); i++) {
    		if(tmpArr.size() != 0 && tmpArr.get(i) != null &&tmpArr.get(i).toString().split(":")[0] != null) {
    			menu[i] = tmpArr.get(i).toString().split(":")[0];
    			price[i] = Integer.parseInt(tmpArr.get(i).toString().split(":")[1]);
    			count[i] = Integer.parseInt(tmpArr.get(i).toString().split(":")[2]);
    			total[i] = price[i] * count[i];
    			System.out.println(count[i]);
    		}
    	}
    	
    	JLabel tfcount[] = new JLabel[menu.length];
    	JLabel tfname[] = new JLabel[menu.length];
    	JLabel tfprice[] = new JLabel[menu.length];
    	JLabel tftotal[] = new JLabel[menu.length];
		frame.setBounds(0, 0, 500, 80 + ((menu.length+1)*30));
    	for(int i = 0; i < menu.length; i++) { 		
    		tfname[i] = new JLabel();
    		tfname[i].setBounds(10,30 + (i*25),120,20);
    		tfname[i].setText(menu[i]);
    		
    		tfprice[i] = new JLabel();
    		tfprice[i].setBounds(150,30 + (i*25),80,20);
    		tfprice[i].setText(String.valueOf(price[i]));
    		
    		tfcount[i] = new JLabel();
    		tfcount[i].setBounds(240,30 + (i*25),80,20);
    		tfcount[i].setText(String.valueOf(count[i]));
    		
    		tftotal[i] = new JLabel();
    		tftotal[i].setBounds(330,30 + (i*25),80,20);
    		tftotal[i].setText(String.valueOf(total[i]));
    		
    		Pane.add(tftotal[i]);
    		Pane.add(tfprice[i]);
    		Pane.add(tfcount[i]);
    		Pane.add(tfname[i]);
    		
    	}
    	int tmpTotal = 0;
    	for(int i : total) {
    		tmpTotal += i;
    	}
    	JLabel l5 = new JLabel("총 합 : " + tmpTotal);
		l5.setBounds(130,30 + ((menu.length)*25),200,20);
		Pane.add(l5);
    	JButton bt = new JButton("계산하기");
    	bt.setBounds(330,30 + ((menu.length)*25),120,20);
		Pane.add(bt);
		frame.add(Pane);
		frame.setVisible(true);
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SQLC.DoPayment(String.valueOf(tableNo));
				frame.dispose();
			}
		});
    }
	public tableInfo(int tableNo) {
		this.tableNo = tableNo;
		initSetting(tableNo);
	}
}
