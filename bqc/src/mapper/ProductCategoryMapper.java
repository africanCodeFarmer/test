package mapper;

import java.util.List;
import pojo.ProductCategory;

public interface ProductCategoryMapper {
	public void add(ProductCategory pojo);
	public void delete(int id);
	public void update(ProductCategory pojo);
	public ProductCategory get(int id);
	
	public List<ProductCategory> list(ProductCategory pojo);
	public int total();
}
