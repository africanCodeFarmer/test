package service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import pojo.ProductCategoryProperty;
import util.Page;

public interface ProductCategoryPropertyService {
	public void add(HttpServletRequest request);
	public void delete(HttpServletRequest request);
	public void update(HttpServletRequest request);
	public ProductCategoryProperty get(int id);
	
	public Map<String, Object> list(Page page,HttpServletRequest request);
	
	public ModelAndView edit(HttpServletRequest request);
	
	public ModelAndView manage(HttpServletRequest request);
	public ModelAndView addPage(HttpServletRequest request);
}
