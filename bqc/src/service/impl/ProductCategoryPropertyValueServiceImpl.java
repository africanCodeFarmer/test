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
import mapper.ProductCategoryPropertyValueMapper;
import mapper.ProductMapper;
import pojo.Product;
import pojo.ProductCategory;
import pojo.ProductCategoryProperty;
import pojo.ProductCategoryPropertyValue;
import service.ProductCategoryPropertyValueService;
import util.Page;

@Service
public class ProductCategoryPropertyValueServiceImpl implements ProductCategoryPropertyValueService{
	@Autowired
	ProductCategoryPropertyValueMapper mapper;
	@Autowired
	ProductMapper pMapper;
	@Autowired
	ProductCategoryPropertyMapper pcpMapper;
	@Autowired
	ProductCategoryMapper pcMapper;
	
	public void fillProductCategoryPropertyValue(Product product) {
		//获取该商品分类的所有属性
		ProductCategory productCategory = pcMapper.get(product.getProductCategory().getId());
		ProductCategoryProperty pojo = new ProductCategoryProperty();
		pojo.setProductCategory(productCategory);
		List<ProductCategoryProperty> productCategoryProperties = pcpMapper.list(pojo);
		
		//检测填充产品该有的所有属性
		for (ProductCategoryProperty pcp : productCategoryProperties) {
			ProductCategoryPropertyValue pcpv = new ProductCategoryPropertyValue();
			pcpv.setProduct(product);
			pcpv.setProductCategoryProperty(pcpMapper.get(pcp.getId()));
			
			//不存在则添加
			if(mapper.getByPidAndPcpId(pcpv)==null) {
				pcpv.setValue("");
				mapper.add(pcpv);
			}
		}
	}
	
	@Override
	public ModelAndView manage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/productCategoryPropertyValue/index");
		
		//传递父对象-产品
		Product father_pojo = pMapper.get(Integer.parseInt(request.getParameter("pid")));
		mav.addObject("father_pojo", father_pojo);
		
		//填充属性
		fillProductCategoryPropertyValue(father_pojo);
		return mav;
	}
	
	@Override
	public Map<String, Object> list(Page page,HttpServletRequest request) {
		ProductCategoryPropertyValue pojo = new ProductCategoryPropertyValue();
		
		//填充父对象-产品
		pojo.setProduct(pMapper.get(Integer.parseInt(request.getParameter("pid"))));
		
		//搜索
		String search_name = request.getParameter("search_name");
		if(search_name!="") {
			pojo.setValue(search_name);
		}
		
		Map<String, Object> map= new HashMap<>();
		PageHelper.offsetPage((page.getPage()-1)*page.getLimit(), page.getLimit());
		List<ProductCategoryPropertyValue> data = mapper.list(pojo);
		
		map.put("data", data);
		map.put("code", 0);
		map.put("count",(int)new PageInfo<>(data).getTotal());
		map.put("msg","");
		
		return map;
	}

	//暂不更改
	@Override
	public void add(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ProductCategoryPropertyValue get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView edit(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView addPage(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	@Override
//	public void add(HttpServletRequest request) {
//		ProductCategoryProperty pojo = new ProductCategoryProperty();
//		pojo.setName(request.getParameter("name"));
//		pojo.setProductCategory(pcMapper.get(Integer.parseInt(request.getParameter("pcid"))));
//		
//		mapper.add(pojo);
//	}
//
//	@Override
//	public void delete(HttpServletRequest request) {
//		String[] id = request.getParameterValues("id");
//		
//		for (String tmp : id) {
//			mapper.delete(Integer.parseInt(tmp));
//		}
//	}
//
//	@Override
//	public void update(HttpServletRequest request) {
//		ProductCategoryProperty pojo = new ProductCategoryProperty();
//		pojo.setId(Integer.parseInt(request.getParameter("id")));
//		pojo.setProductCategory(pcMapper.get(Integer.parseInt(request.getParameter("pcid"))));
//		pojo.setName(request.getParameter("name"));
//		
//		mapper.update(pojo);
//	}
//
//	@Override
//	public ProductCategoryProperty get(int id) {
//		return mapper.get(id);
//	}
//
//	@Override
//	public ModelAndView edit(HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView("admin/productCategoryPropertyValue/edit");
//
//		ProductCategoryProperty pojo = mapper.get(Integer.parseInt(request.getParameter("id")));
//		mav.addObject("pojo", pojo);
//		
//		//传递父对象
//		ProductCategory father_pojo = pcMapper.get(Integer.parseInt(request.getParameter("pcid")));
//		mav.addObject("father_pojo", father_pojo);
//		return mav;
//	}
//	
//	public ModelAndView addPage(HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView("admin/productCategoryPropertyValue/add");
//		
//		//传递父对象
//		ProductCategory father_pojo = pcMapper.get(Integer.parseInt(request.getParameter("pcid")));
//		mav.addObject("father_pojo", father_pojo);
//		return mav;
//	}
}
