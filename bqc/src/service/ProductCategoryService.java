package service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import pojo.ProductCategory;
import util.Page;

public interface ProductCategoryService {
	public void add(ProductCategory pojo);
	public void delete(HttpServletRequest request);
	public void update(ProductCategory pojo);
	public ProductCategory get(int id);
	
	public Map<String, Object> list(Page page,HttpServletRequest request);
	public int total();
	
	public ModelAndView edit(int id);
}
