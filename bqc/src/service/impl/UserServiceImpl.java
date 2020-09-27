package service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import mapper.UserMapper;
import pojo.User;
import service.UserService;
import util.Page;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserMapper mapper;
	
	@Override
	public Map<String, Object> login(HttpServletRequest request,HttpSession session) {
		Map<String, Object> map= new HashMap<>();
		
		User user = new User();
		user.setName(request.getParameter("name"));
		user.setPassword(request.getParameter("password"));
		
		User pojo = mapper.login(user);
		
		//登录成功
		if(pojo!=null) {
			session.setAttribute("admin_user", pojo);
			map.put("code", 0);
		}
		//登录失败
		else {
			map.put("code", 1);
		}
		
		return map;
	}
	
	@Override
	public void add(User pojo) {
		mapper.add(pojo);
	}

	@Override
	public void delete(HttpServletRequest request) {
		String[] id = request.getParameterValues("id");
		
		for (String tmp : id) {
			mapper.delete(Integer.parseInt(tmp));
		}
	}

	@Override
	public void update(User pojo) {
		mapper.update(pojo);
	}

	@Override
	public User get(int id) {
		return mapper.get(id);
	}

	@Override
	public ModelAndView edit(int id) {
		ModelAndView mav = new ModelAndView("admin/user/edit");

		User pojo = mapper.get(id);
		mav.addObject("pojo", pojo);
		return mav;
	}
	
	@Override
	public Map<String, Object> list(Page page,HttpServletRequest request) {
		User pojo = new User();
		
		//搜索
		String search_name = request.getParameter("search_name");
		if(search_name!="") {
			pojo.setName(search_name);
		}
		
		Map<String, Object> map= new HashMap<>();
		PageHelper.offsetPage((page.getPage()-1)*page.getLimit(), page.getLimit());
		List<User> data = mapper.list(pojo);
		
		map.put("data", data);
		map.put("code", 0);
		map.put("count",(int)new PageInfo<>(data).getTotal());
		map.put("msg","");
		
		return map;
	}

	@Override
	public int total() {
		return mapper.total();
	}
}
