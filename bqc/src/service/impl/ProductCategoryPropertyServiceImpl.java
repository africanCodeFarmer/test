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
import mapper.ProductCategoryPropertyMapper;
import pojo.ProductCategory;
import pojo.ProductCategoryProperty;
import service.ProductCategoryPropertyService;
import util.Page;

@Service
public class ProductCategoryPropertyServiceImpl implements ProductCategoryPropertyService{
	@Autowired
	ProductCategoryPropertyMapper mapper;
	@Autowired
	ProductCategoryMapper pcMapper;
	
	@Override
	public void add(HttpServletRequest request) {
		ProductCategoryProperty pojo = new ProductCategoryProperty();
		pojo.setName(request.getParameter("name"));
		pojo.setProductCategory(pcMapper.get(Integer.parseInt(request.getParameter("pcid"))));
		
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
	public void update(HttpServletRequest request) {
		ProductCategoryProperty pojo = new ProductCategoryProperty();
		pojo.setId(Integer.parseInt(request.getParameter("id")));
		pojo.setProductCategory(pcMapper.get(Integer.parseInt(request.getParameter("pcid"))));
		pojo.setName(request.getParameter("name"));
		
		mapper.update(pojo);
	}

	@Override
	public ProductCategoryProperty get(int id) {
		return mapper.get(id);
	}

	@Override
	public Map<String, Object> list(Page page,HttpServletRequest request) {
		ProductCategoryProperty pojo = new ProductCategoryProperty();
		
		//填充父对象-商品分类
		int pcid = Integer.parseInt(request.getParameter("pcid"));
		pojo.setProductCategory(pcMapper.get(pcid));
		
		//搜索
		String search_name = request.getParameter("search_name");
		if(search_name!="") {
			pojo.setName(search_name);
		}
		
		Map<String, Object> map= new HashMap<>();
		PageHelper.offsetPage((page.getPage()-1)*page.getLimit(), page.getLimit());
		List<ProductCategoryProperty> data = mapper.list(pojo);
		
		map.put("data", data);
		map.put("code", 0);
		map.put("count",(int)new PageInfo<>(data).getTotal());
		map.put("msg","");
		
		return map;
	}

	@Override
	public ModelAndView edit(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/productCategoryProperty/edit");

		ProductCategoryProperty pojo = mapper.get(Integer.parseInt(request.getParameter("id")));
		mav.addObject("pojo", pojo);
		
		//传递父对象
		ProductCategory father_pojo = pcMapper.get(Integer.parseInt(request.getParameter("pcid")));
		mav.addObject("father_pojo", father_pojo);
		return mav;
	}

	@Override
	public ModelAndView manage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/productCategoryProperty/index");
		
		//传递父对象
		int pcid = Integer.parseInt(request.getParameter("pcid"));
		ProductCategory father_pojo = pcMapper.get(pcid);
		mav.addObject("father_pojo", father_pojo);
		return mav;
	}
	
	public ModelAndView addPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/productCategoryProperty/add");
		
		//传递父对象
		ProductCategory father_pojo = pcMapper.get(Integer.parseInt(request.getParameter("pcid")));
		mav.addObject("father_pojo", father_pojo);
		return mav;
	}
}
