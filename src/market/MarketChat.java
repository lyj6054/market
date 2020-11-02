package market;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MarketChat {	
	//Field
	JPanel chatPane , chatMainPane;
	JLabel jl_chat_select;
	JTextField jt_chat_select;
	JButton btnChat_select, btn_chatjoin, send;
	MarketMgmUI main;
	MemberVO mvo = new MemberVO();
	JTextField input;
	JScrollPane scrollPane;
	JTextArea content;
	JList list_chatlist;
	
	ObjectInputStream ois;
	ObjectOutputStream oos;
	
	
	
//Constructor
public MarketChat() {}
public MarketChat(MarketMgmUI  main) {
	this.main = main;
	this.chatPane = main.chatPane;
	this.mvo=main.vo;
	this.content =main.content;
	this.input = main.input;
	this.ois=main.ois;
	this.oos=main.oos;
}	

//Method
/**
 * @wbp.parser.entryPoint
*/
public void chat() {
	main.switchPane(MarketMgmUI.CHAT);
	chatPane.setLayout(null);
//	JPanel chatPane = new JPanel();
	
	JLabel title_label = new JLabel("※  채팅을 원하는 게시물 번호 입력  ※");
	title_label.setFont(new Font("제주고딕", Font.PLAIN, 13));
	title_label.setBounds(397, 52, 245, 32);
	chatPane.add(title_label);
	
	JLabel id_label = new JLabel("[  게시물 번호  ]");
	id_label.setFont(new Font("제주고딕", Font.PLAIN, 20));
	id_label.setBounds(151, 22, 146, 40);
	chatPane.add(id_label);
	
	jt_chat_select = new JTextField();
	jt_chat_select.setBounds(337, 29, 365, 25);
	jt_chat_select.setColumns(10);
	chatPane.add(jt_chat_select);
	
	btnChat_select = new JButton("\uAC80  \uC0C9");
	btnChat_select.setBackground(Color.DARK_GRAY);
	btnChat_select.setForeground(new Color(153, 204, 255));
	btnChat_select.setFont(new Font("제주고딕", Font.PLAIN, 16));
	btnChat_select.setBounds(737, 27, 79, 32);
	chatPane.add(btnChat_select);
		
	main.getContentPane().add(chatPane, BorderLayout.CENTER);
	
	JPanel panel = new JPanel();
	panel.setBackground(SystemColor.activeCaption);
	panel.setBounds(120, 90, 743, 485);
	chatPane.add(panel);
	panel.setLayout(null);
	
	JLabel lblNull = new JLabel(mvo.id+"님의 채팅방");
	lblNull.setHorizontalAlignment(SwingConstants.CENTER);
	lblNull.setFont(new Font("제주고딕", Font.PLAIN, 15));
	lblNull.setBounds(23, 19, 112, 19);
	panel.add(lblNull);
	
	
	btn_chatjoin = new JButton("\uCC44\uD305\uBC29\uCC38\uC5EC");
	btn_chatjoin.setBackground(Color.DARK_GRAY);
	btn_chatjoin.setForeground(Color.WHITE);
	btn_chatjoin.setFont(new Font("제주고딕", Font.PLAIN, 15));
	btn_chatjoin.setBounds(23, 419, 112, 54);
	panel.add(btn_chatjoin);
	
	DefaultListModel model = new DefaultListModel();
	list_chatlist = new JList(model);
	list_chatlist.setFont(new Font("제주고딕", Font.PLAIN, 13));
	for(String id:main.system.chat_list(mvo.id)) model.addElement(id);
	list_chatlist.setBounds(23, 48, 112, 360);
	panel.add(list_chatlist);
	
	
	
//	input = new JTextField();
	input.setBounds(147, 445, 481, 30);
	panel.add(input);
	input.setColumns(10);
	
	send = new JButton("\uC804  \uC1A1");
	send.setBackground(Color.DARK_GRAY);
	send.setForeground(Color.WHITE);
	send.setFont(new Font("제주고딕", Font.PLAIN, 15));
	send.setBounds(634, 445, 80, 28);
	panel.add(send);
	
	scrollPane = new JScrollPane();
	scrollPane.setBounds(147, 19, 566, 417);
	panel.add(scrollPane);
	
	content.setFont(new Font("제주고딕", Font.PLAIN, 15));
	scrollPane.setViewportView(content);
	main.setVisible(true);
		
	//리스너
	MemberChatEvent chatEvent = new MemberChatEvent();
	list_chatlist.addListSelectionListener(chatEvent);
	input.addActionListener(chatEvent);
	send.addActionListener(chatEvent);

	
	}//chat method

	
//chatFormCheck
public boolean chatFormCheck() {
	boolean result = false;
	if(input.getText().equals("")) {
		result=true;
	}
	return result;
}



public void chatProc() {

	
}

//이벤트 처리 클래스
class MemberChatEvent implements ActionListener, ListSelectionListener{
	
	public void valueChanged(ListSelectionEvent e) {
		//클릭된 번호 갖어오기
		String name = (String) list_chatlist.getSelectedValue();
		System.out.println(name);
		//arraylist에 채팅방 별로 내용 저장하기
		//채팅방 내용 textarea에 보여주기
		
		
		}
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(send == obj || input == obj) {
				try {
					String msg = input.getText().trim();
					if(msg.equals("")) {
						JOptionPane.showMessageDialog(null,"메시지를 입력해주세요");
						input.requestFocus();
					}else {
						//대화중~
						MessageVO msgVO= new MessageVO();
						msgVO.setName(mvo.getId());
						msgVO.setMsg(msg);
						msgVO.setStatus(MarketMgmUI.TALKING);
						oos.writeObject(msgVO);
						
					
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		}else if(btn_chatjoin ==obj) {
			//채팅하기를 눌럿을때
			
		}
	}
	
	
}//event class
}
