package billing;

public class Product {

	private String name;
	private Double price;
	private boolean imported;

	public Product(String name, Double price, boolean imported) {
		this.name = name;
		this.price = price;
		this.imported = imported;
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
}
