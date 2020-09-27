package service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import pojo.User;
import util.Page;

public interface UserService {
	public void add(User pojo);
	public void delete(HttpServletRequest request);
	public void update(User pojo);
	public User get(int id);
	
	public Map<String, Object> list(Page page,HttpServletRequest request);
	public int total();
	
	public ModelAndView edit(int id);
	
	public Map<String, Object> login(HttpServletRequest request,HttpSession session);
}
