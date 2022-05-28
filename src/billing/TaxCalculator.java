package billing;

public class TaxCalculator {

	public Double calculateTax(Product myProduct) {
		double tax = 0.1 * myProduct.getPrice();
		return tax;
	}
	
}
