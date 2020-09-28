package mapper;

import java.util.List;
import pojo.ProductCategoryPropertyValue;

public interface ProductCategoryPropertyValueMapper {
	public void add(ProductCategoryPropertyValue pojo);
	public void delete(int id);
	public void update(ProductCategoryPropertyValue pojo);
	public ProductCategoryPropertyValue get(int id);
	
	public List<ProductCategoryPropertyValue> list(ProductCategoryPropertyValue pojo);
	
	public ProductCategoryPropertyValue getByPidAndPcpId(ProductCategoryPropertyValue pojo);
}
