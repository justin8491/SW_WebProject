package board;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FileVO implements Serializable {
	private static final long serialVersionUID = 5967150043358108618L;
	private int f_id;
	private int boardNO;
	private String org_name;
	private String real_name;
	private String content_type;
	private long length;
}
