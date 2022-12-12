package board;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class BoardVO {
	
	private int boardNO;
	private String category;
	private String title;
	private String content;
	private String id;
	private int view;
	private Date writeDate;
	private String isExist;
	
	
	public BoardVO() {}


	public BoardVO(String category, String title, String content, String id) {
		super();
		this.category = category;
		this.title = title;
		this.content = content;
		this.id = id;
	}

	
	
	
	
}

