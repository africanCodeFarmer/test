package mapper;

import java.util.List;
import pojo.ProductCategoryProperty;

public interface ProductCategoryPropertyMapper {
	public void add(ProductCategoryProperty pojo);
	public void delete(int id);
	public void update(ProductCategoryProperty pojo);
	public ProductCategoryProperty get(int id);
	
	public List<ProductCategoryProperty> list(ProductCategoryProperty pojo);
}
