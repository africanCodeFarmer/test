package mapper;

import java.util.List;
import pojo.Product;

public interface ProductMapper {
	public void add(Product pojo);
	public void delete(int id);
	public void update(Product pojo);
	public Product get(int id);
	
	public List<Product> list(Product pojo);
}
