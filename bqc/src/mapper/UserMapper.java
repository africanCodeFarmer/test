package mapper;

import java.util.List;
import pojo.User;

public interface UserMapper {
	public void add(User pojo);
	public void delete(int id);
	public void update(User pojo);
	public User get(int id);
	
	public List<User> list(User pojo);
	public int total();
	
	public User login(User user);
}
