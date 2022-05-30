package billing;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Receipt {
	Map<Product, Long> occurencesOfProducts;
	double totalAmountOfTaxes;
	double totalPriceIncludingTaxes;
	List<Product> listOfProducts;
	
	public List<Product> getListOfProducts() {
		return listOfProducts;
	}
	public Map<Product, Long>getOccurencesOfProducts() {
		return occurencesOfProducts;
	}
	public void setOccurencesOfProducts(Map<Product, Long> listOfOccurencesAndPricesByProduct) {
		this.occurencesOfProducts = listOfOccurencesAndPricesByProduct;
	}
	public double getTotalAmountOfTaxes() {
		return totalAmountOfTaxes;
	}
	public void setTotalAmountOfTaxes(double totalAmountOfTaxes) {
		this.totalAmountOfTaxes = totalAmountOfTaxes;
	}
	public double getTotalPriceIncludingTaxes() {
		return totalPriceIncludingTaxes;
	}
	public void setTotalPriceIncludingTaxes(double totalPriceIncludingTaxes) {
		this.totalPriceIncludingTaxes = totalPriceIncludingTaxes;
	}
	
	public Map<Product, Long> getOccurencesOfProducts(List<Product> listOfProducts) {
		Map<Product, Long> occurencesOfProducts = listOfProducts.stream()
				.collect(Collectors.groupingBy(e -> e, Collectors.counting()));
		return occurencesOfProducts;
	}
		
	public Receipt(List<Product> listOfProducts, double totalAmountOfTaxes, double totalPriceIncludingTaxes) {
		this.setListOfProducts(listOfProducts);
		this.setOccurencesOfProducts(getOccurencesOfProducts(listOfProducts));
		this.setTotalAmountOfTaxes(totalAmountOfTaxes);
		this.setTotalPriceIncludingTaxes(totalPriceIncludingTaxes);
	}
	private void setListOfProducts(List<Product> listOfProducts) {
		this.listOfProducts = listOfProducts;
	}
}
