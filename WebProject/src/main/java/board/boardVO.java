package board;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class boardVO {
	
	private String boardNO;
	private String category;
	private String title;
	private String content;
	private Date writeDate;
	
	
	public boardVO() {}


	public boardVO(String category, String title, String content) {
		super();
		this.category = category;
		this.title = title;
		this.content = content;
	}

	
	
	
	
}

