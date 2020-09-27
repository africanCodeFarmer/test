package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import pojo.User;
import service.UserService;
import util.Page;

@Controller
@RequestMapping("")
public class UserController {
	@Autowired
	UserService service;
	
	@RequestMapping("admin/manageUser")
	public ModelAndView manage() {
		ModelAndView mav = new ModelAndView("admin/user/index");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("admin/listUser")
	public Map<String, Object> list(Page page,HttpServletRequest request) {
		return service.list(page, request);
	}
	
	@ResponseBody
	@RequestMapping("admin/deleteUser")
	public void delete(HttpServletRequest request) {
		service.delete(request);
	}
	
	@ResponseBody
	@RequestMapping("admin/editUser")
	public ModelAndView edit(int id) {
		return service.edit(id);
	}
	
	@ResponseBody
	@RequestMapping("admin/updateUser")
	public void update(User pojo) {
		service.update(pojo);
	}
	
	@ResponseBody
	@RequestMapping("admin/user/addUser")
	public void add(User pojo) {
		service.add(pojo);
	}
	
	@ResponseBody
	@RequestMapping("admin/loginValidate")
	public Map<String, Object> login(HttpServletRequest request,HttpSession session) {
		return service.login(request,session);
	}
}
