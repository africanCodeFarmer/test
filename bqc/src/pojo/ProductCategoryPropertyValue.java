package pojo;

public class ProductCategoryPropertyValue {
	int id;
	Product product;
	ProductCategoryProperty productCategoryProperty;
	String value;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public ProductCategoryProperty getProductCategoryProperty() {
		return productCategoryProperty;
	}
	public void setProductCategoryProperty(ProductCategoryProperty productCategoryProperty) {
		this.productCategoryProperty = productCategoryProperty;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
