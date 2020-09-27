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
import pojo.Product;
import service.ProductService;
import service.ProductCategoryService;
import util.Page;

@Controller
@RequestMapping("")
public class ProductController {
	@Autowired
	ProductService service;
	@Autowired
	ProductCategoryService pcService;
	
	@RequestMapping("admin/manageProduct")
	public ModelAndView manage(HttpServletRequest request) {
		return service.manage(request);
	}
	
	@ResponseBody
	@RequestMapping("admin/listProduct")
	public Map<String, Object> list(Page page,HttpServletRequest request) {	
		return service.list(page, request);
	}
	
	@ResponseBody
	@RequestMapping("admin/deleteProduct")
	public void delete(HttpServletRequest request) {
		service.delete(request);
	}
	
	@ResponseBody
	@RequestMapping("admin/editProduct")
	public ModelAndView edit(HttpServletRequest request) {
		return service.edit(request);
	}
	
	@ResponseBody
	@RequestMapping("admin/updateProduct")
	public void update(HttpServletRequest request) {
		service.update(request);
	}
	
	@ResponseBody
	@RequestMapping("admin/addProduct")
	public void addProduct(HttpServletRequest request) {
		service.add(request);
	}
	
	@ResponseBody
	@RequestMapping("admin/product/addPage")
	public ModelAndView addPage(HttpServletRequest request) {
		return service.addPage(request);
	}
}
