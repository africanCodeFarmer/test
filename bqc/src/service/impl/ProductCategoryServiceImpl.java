package service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import mapper.ProductCategoryMapper;
import pojo.ProductCategory;
import service.ProductCategoryService;
import util.Page;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{
	@Autowired
	ProductCategoryMapper mapper;
	
	@Override
	public void add(ProductCategory pojo) {
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
	public void update(ProductCategory pojo) {
		mapper.update(pojo);
	}

	@Override
	public ProductCategory get(int id) {
		return mapper.get(id);
	}

	@Override
	public ModelAndView edit(int id) {
		ModelAndView mav = new ModelAndView("admin/productCategory/edit");

		ProductCategory pojo = mapper.get(id);
		mav.addObject("pojo", pojo);
		return mav;
	}
	
	@Override
	public Map<String, Object> list(Page page,HttpServletRequest request) {
		ProductCategory pojo = new ProductCategory();
		
		//搜索
		String search_name = request.getParameter("search_name");
		if(search_name!="") {
			pojo.setName(search_name);
		}
		
		Map<String, Object> map= new HashMap<>();
		PageHelper.offsetPage((page.getPage()-1)*page.getLimit(), page.getLimit());
		List<ProductCategory> data = mapper.list(pojo);
		
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
