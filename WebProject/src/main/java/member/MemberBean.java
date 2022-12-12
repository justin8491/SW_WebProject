package member;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MemberBean {
	
	private String id;
	private String pwd;
	private String name;
	private String phone;
	private String email;
	private String accept;
	private String isexist;
	private Date createDate;
	
	
	public MemberBean() {}


	public MemberBean(String id, String pwd, String name, String phone, String email, String accept) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.accept = accept;
	}


	public MemberBean(String id, String pwd, String accept) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.accept = accept;
	}


	
	
	
	
	
	
	
}

