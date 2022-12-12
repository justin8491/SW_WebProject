package chat;

import java.awt.TrayIcon.MessageType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatVO {
	
	//클라이언트 아이디 값 
	private String id;
	private String chatRoomId;
	private String sender;
	private String message;
	private MessageType messageType;
	
	
	public ChatVO() {}
	
	
	
	
	
}
