package service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.lang.Console;
import mapper.ProductCategoryMapper;
import mapper.ProductMapper;
import pojo.ProductCategory;
import pojo.Product;
import service.ProductService;
import util.Page;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductMapper mapper;
	@Autowired
	ProductCategoryMapper pcMapper;
	
	@Override
	public void add(HttpServletRequest request) {
		Product pojo = new Product();
		pojo.setName(request.getParameter("name"));
		
		pojo.setSubTitle(request.getParameter("subTitle"));
		if(request.getParameter("orignalPrice")!="")
			pojo.setOrignalPrice(Float.parseFloat(request.getParameter("orignalPrice")));
		if(request.getParameter("promotePrice")!="")
			pojo.setPromotePrice(Float.parseFloat(request.getParameter("promotePrice")));
		if(request.getParameter("stock")!="")
			pojo.setStock(Integer.parseInt(request.getParameter("stock")));
		
		pojo.setProductCategory(pcMapper.get(Integer.parseInt(request.getParameter("pcid"))));
		pojo.setCreateTime(new Date());
		
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
		Product pojo = new Product();
		
		pojo.setId(Integer.parseInt(request.getParameter("id")));
		pojo.setName(request.getParameter("name"));
		
		pojo.setSubTitle(request.getParameter("subTitle"));
		if(request.getParameter("orignalPrice")!="")
			pojo.setOrignalPrice(Float.parseFloat(request.getParameter("orignalPrice")));
		if(request.getParameter("promotePrice")!="")
			pojo.setPromotePrice(Float.parseFloat(request.getParameter("promotePrice")));
		if(request.getParameter("stock")!="")
			pojo.setStock(Integer.parseInt(request.getParameter("stock")));
		
		pojo.setProductCategory(pcMapper.get(Integer.parseInt(request.getParameter("pcid"))));
		pojo.setCreateTime(new Date());
		
		mapper.update(pojo);
	}

	@Override
	public Product get(int id) {
		return mapper.get(id);
	}

	@Override
	public Map<String, Object> list(Page page,HttpServletRequest request) {
		Product pojo = new Product();
		
		//填充父对象-商品分类
		if(request.getParameter("pcid")!=null) {
			int pcid = Integer.parseInt(request.getParameter("pcid"));
			pojo.setProductCategory(pcMapper.get(pcid));
		}
		
		//搜索
		String search_name = request.getParameter("search_name");
		String search_productCategory = request.getParameter("search_productCategory");
		if(search_name!="") {
			pojo.setName(search_name);
		}
		if(search_productCategory!=null && search_productCategory!="") {
			pojo.setProductCategory(pcMapper.get(Integer.parseInt(search_productCategory)));
		}
		
		Map<String, Object> map= new HashMap<>();
		PageHelper.offsetPage((page.getPage()-1)*page.getLimit(), page.getLimit());
		List<Product> data = mapper.list(pojo);
		
		map.put("data", data);
		map.put("code", 0);
		map.put("count",(int)new PageInfo<>(data).getTotal());
		map.put("msg","");
		
		return map;
	}

	@Override
	public ModelAndView edit(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/product/edit");

		Product pojo = mapper.get(Integer.parseInt(request.getParameter("id")));
		mav.addObject("pojo", pojo);
		
		//传递父对象
		if(request.getParameter("pcid")!=null) {
			ProductCategory father_pojo = pcMapper.get(Integer.parseInt(request.getParameter("pcid")));
			mav.addObject("father_pojo", father_pojo);
		}
		
		fillSelect(mav);
		return mav;
	}

	@Override
	public ModelAndView manage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/product/index");
		
		//传递父对象
		if(request.getParameter("pcid")!=null) {
			int pcid = Integer.parseInt(request.getParameter("pcid"));
			ProductCategory father_pojo = pcMapper.get(pcid);
			mav.addObject("father_pojo", father_pojo);
		}
		
		fillSelect(mav);
		return mav;
	}
	
	public ModelAndView addPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/product/add");
		
		//传递父对象
		if(request.getParameter("pcid")!=null) {
			ProductCategory father_pojo = pcMapper.get(Integer.parseInt(request.getParameter("pcid")));
			mav.addObject("father_pojo", father_pojo);
		}
		
		fillSelect(mav);
		return mav;
	}
	
	//填充下拉框-商品分类
	public void fillSelect(ModelAndView mav) {
		List<ProductCategory> pcs = pcMapper.list(new ProductCategory());
		
		mav.addObject("pcs", pcs);
	}
}
