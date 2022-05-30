package billing;

public class Product {

	private String name;
	private double price;
	private boolean isImported;
	private String category;

	public Product(String name, Double price, boolean imported, String category) {
		this.name = name;
		this.price = price;
		this.isImported = imported;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
	
	public Boolean getIsImported() {
		return isImported;
	}
	
	public String getCategory() {
		return category;
	}
}
