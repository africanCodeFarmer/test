package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;

import pojo.ProductCategory;
import pojo.ProductCategoryProperty;
import service.ProductCategoryPropertyService;
import service.ProductCategoryService;
import util.Page;

@Controller
@RequestMapping("")
public class ProductCategoryPropertyController {
	@Autowired
	ProductCategoryPropertyService service;
	@Autowired
	ProductCategoryService pcService;
	
	@RequestMapping("admin/manageProductCategoryProperty")
	public ModelAndView manage(HttpServletRequest request) {
		return service.manage(request);
	}
	
	@ResponseBody
	@RequestMapping("admin/listProductCategoryProperty")
	public Map<String, Object> list(Page page,HttpServletRequest request) {	
		return service.list(page, request);
	}
	
	@ResponseBody
	@RequestMapping("admin/deleteProductCategoryProperty")
	public void delete(HttpServletRequest request) {
		service.delete(request);
	}
	
	@ResponseBody
	@RequestMapping("admin/editProductCategoryProperty")
	public ModelAndView edit(HttpServletRequest request) {
		return service.edit(request);
	}
	
	@ResponseBody
	@RequestMapping("admin/updateProductCategoryProperty")
	public void update(HttpServletRequest request) {
		service.update(request);
	}
	
	@ResponseBody
	@RequestMapping("admin/addProductCategoryProperty")
	public void addProductCategoryProperty(HttpServletRequest request) {
		service.add(request);
	}
	
	@ResponseBody
	@RequestMapping("admin/addPage")
	public ModelAndView addPage(HttpServletRequest request) {
		return service.addPage(request);
	}
}
