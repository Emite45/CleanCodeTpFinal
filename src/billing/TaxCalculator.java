package billing;

import java.util.List;

public class TaxCalculator {

	public double getTax(List<Product> listOfProducts) {
		return calculateRoundedTaxForAListOfProducts(listOfProducts);
	}
	
	public double calculateNotRoundedTax(Product product) {
		return getAppropriateTaxSystem(product).getTax().apply(product);
	}

	public double calculateRoundedTaxForAListOfProducts(List<Product> listOfProducts) {
		double totalTax = 0;
		for (Product product : listOfProducts) {
			totalTax = totalTax + calculateNotRoundedTax(product);
		}
		double roundedTotalTax = Math.ceil(totalTax / 0.05) / 20;
		return roundedTotalTax;
		
	}

	TaxSystem getAppropriateTaxSystem(Product myProduct) {

		if (productHasNoVATAndIsImported(myProduct)) {
			return TaxSystem.IMPORTED_NON_TAXABLE;
		}
		if (productHasNoVAT(myProduct)) {
			return TaxSystem.NON_TAXABLE;
		}
		if (productIsImported(myProduct)) {
			return TaxSystem.IMPORTED_TAXABLE;
		}
		return TaxSystem.TAXABLE;
	}

	private boolean productHasNoVATAndIsImported(Product myProduct) {
		return productHasNoVAT(myProduct) && productIsImported(myProduct);
	}

	private Boolean productIsImported(Product myProduct) {
		return myProduct.getIsImported();
	}

	private boolean productHasNoVAT(Product myProduct) {
		return productIsFood(myProduct) || productIsMedicine(myProduct) || productIsBook(myProduct);
	}

	private boolean productIsBook(Product myProduct) {
		return myProduct.getCategory() == "Book";
	}

	private boolean productIsMedicine(Product myProduct) {
		return myProduct.getCategory() == "Medicine";
	}

	private boolean productIsFood(Product myProduct) {
		return myProduct.getCategory() == "Food";
	}
}
