package billing;

import java.util.function.Function;

public enum TaxSystem {
	TAXABLE(product -> 0.1 * product.getPrice()), 
	NON_TAXABLE(product -> 0.),
	IMPORTED_TAXABLE(product -> 0.15 * product.getPrice()), 
	IMPORTED_NON_TAXABLE(product -> 0.05 * product.getPrice());

	private Function<Product, Double> tax;

	public Function<Product, Double> getTax() {
		return tax;
	}

	private TaxSystem(Function<Product, Double> tax) {
		this.tax = tax;
	}
}
