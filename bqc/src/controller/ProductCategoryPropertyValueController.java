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
import pojo.ProductCategoryPropertyValue;
import service.ProductCategoryPropertyValueService;
import service.ProductCategoryService;
import util.Page;

@Controller
@RequestMapping("")
public class ProductCategoryPropertyValueController {
	@Autowired
	ProductCategoryPropertyValueService service;
	
	@RequestMapping("admin/manageProductCategoryPropertyValue")
	public ModelAndView manage(HttpServletRequest request) {
		return service.manage(request);
	}
	
	@ResponseBody
	@RequestMapping("admin/listProductCategoryPropertyValue")
	public Map<String, Object> list(Page page,HttpServletRequest request) {	
		return service.list(page, request);
	}
	
	@ResponseBody
	@RequestMapping("admin/deleteProductCategoryPropertyValue")
	public void delete(HttpServletRequest request) {
		service.delete(request);
	}
	
	@ResponseBody
	@RequestMapping("admin/editProductCategoryPropertyValue")
	public ModelAndView edit(HttpServletRequest request) {
		return service.edit(request);
	}
	
	@ResponseBody
	@RequestMapping("admin/updateProductCategoryPropertyValue")
	public void update(HttpServletRequest request) {
		service.update(request);
	}
	
	@ResponseBody
	@RequestMapping("admin/addProductCategoryPropertyValue")
	public void addProductCategoryPropertyValue(HttpServletRequest request) {
		service.add(request);
	}
	
	@ResponseBody
	@RequestMapping("admin/productCategoryPropertyValue/addPage")
	public ModelAndView addPage(HttpServletRequest request) {
		return service.addPage(request);
	}
}
