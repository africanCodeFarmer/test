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
import service.ProductCategoryService;
import util.Page;

@Controller
@RequestMapping("")
public class ProductCategoryController {
	@Autowired
	ProductCategoryService service;
	
	@RequestMapping("admin/manageProductCategory")
	public ModelAndView manage() {
		ModelAndView mav = new ModelAndView("admin/productCategory/index");
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("admin/listProductCategory")
	public Map<String, Object> list(Page page,HttpServletRequest request) {
		return service.list(page, request);
	}
	
	@ResponseBody
	@RequestMapping("admin/deleteProductCategory")
	public void delete(HttpServletRequest request) {
		service.delete(request);
	}
	
	@ResponseBody
	@RequestMapping("admin/editProductCategory")
	public ModelAndView edit(int id) {
		return service.edit(id);
	}
	
	@ResponseBody
	@RequestMapping("admin/updateProductCategory")
	public void update(ProductCategory pojo) {
		service.update(pojo);
	}
	
	@ResponseBody
	@RequestMapping("admin/productCategory/addProductCategory")
	public void add(ProductCategory pojo) {
		service.add(pojo);
	}
}
