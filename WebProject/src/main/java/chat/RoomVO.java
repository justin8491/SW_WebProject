package chat;

import java.util.HashSet;
import java.util.Set;

import jakarta.websocket.Session;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomVO {
	
	private String id;
	private String name;
	private Set<Session> sessions = new HashSet<>();
	
}
