package billing;

public class TaxCalculatorClient {

	public TaxSystem getAppropriateTaxSystem(Product myProduct) {
		
		if(productIsImported(myProduct)) {
			return TaxSystem.IMPORTED_TAXABLE;
		}
		if(productHasNoVAT(myProduct)) {
			
			if(productIsImported(myProduct)) {
				return TaxSystem.IMPORTED_TAXABLE;
			}
			return TaxSystem.NON_TAXABLE;
		}
		return TaxSystem.TAXABLE;
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
