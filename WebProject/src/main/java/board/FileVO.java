package board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FileVO {
	private int f_id;
	private int boardNO;
	private String org_name;
	private String real_name;
	private String content_type;
	private long length;
}
