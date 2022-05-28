package billing;

public class TaxCalculatorClient {

	public double calculateTax(Product product) {
		return getAppropriateTaxSystem(product).getTax(product);
	}
	
	TaxSystem getAppropriateTaxSystem(Product myProduct) {
		
		if (productHasNoVATAndIsImported(myProduct)) {
			return TaxSystem.IMPORTED_NON_TAXABLE;
		}
		if(productHasNoVAT(myProduct)) {
			return TaxSystem.NON_TAXABLE;			
		}
		if(productIsImported(myProduct)) {
			return TaxSystem.IMPORTED_TAXABLE;
		}
		return TaxSystem.TAXABLE;
	}

	private boolean productHasNoVATAndIsImported(Product myProduct) {
		return productHasNoVAT(myProduct) && productIsImported(myProduct);
	}

	private Boolean productIsImported(Product myProduct) {
		return myProduct.getImported();
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
