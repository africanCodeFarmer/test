package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class AdminController {
	@RequestMapping("admin/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("admin/index");
		return mav;
	}
	
	@RequestMapping("admin/login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("admin/login");
		return mav;
	}
}
