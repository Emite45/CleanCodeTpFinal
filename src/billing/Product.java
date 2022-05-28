package billing;

public class Product {

	private String name;
	private Double price;
	private boolean imported;
	private String category;

	public Product(String name, Double price, boolean imported, String category) {
		this.name = name;
		this.price = price;
		this.imported = imported;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}
	
	public Boolean getImported() {
		return imported;
	}
	
	public String getCategory() {
		return category;
	}
}
