package pojo;

import java.util.HashMap;
import java.util.Map;

public class User {
	int id;
	String name;
	String password;
	int type;
	
	String typeText;
	HashMap<Integer, String> map = new HashMap<Integer, String>() {
	    {
	        put(0, "普通用户");
	        put(1, "商家");
	        put(2, "管理员");
	    }
	};
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
		this.setTypeText(map.containsKey(this.type)?map.get(this.type):"");
	}
	public String getTypeText() {
		return typeText;
	}
	public void setTypeText(String typeText) {
		this.typeText = typeText;
	}
}
