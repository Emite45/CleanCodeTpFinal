package billing;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxCalculatorClient {

	public double calculateTax(Product product) {
		return getAppropriateTaxSystem(product).getTax(product);
	}

//	public double calculateRoundedTax(Product product) {
//		double initialTax = getAppropriateTaxSystem(product).getTax(product);
//		
//		BigDecimal initialTaxBigDecimal = new BigDecimal(String.valueOf(initialTax));
//		double truncatedTax = (initialTaxBigDecimal.setScale(2, RoundingMode.FLOOR).doubleValue());
//		String truncatedTaxString = String.valueOf(truncatedTax);
//		String decimalPartOfTaxString = truncatedTaxString.substring(truncatedTaxString.indexOf('.') + 1);
//		int decimalPartOfTax = Integer.valueOf(decimalPartOfTaxString);
//
//		if (!divisibleBy5(decimalPartOfTax)) {
//			int resultOfDivision = (decimalPartOfTax / 5);
//			double roundedTax = truncatedTax - (decimalPartOfTax * 0.01) + ((resultOfDivision + 1) * 0.05);
//			return roundedTax;	
//		}
//		
//		if (divisibleBy5(decimalPartOfTax) && truncatedTaxIsInferiorToInitialTax(truncatedTax, initialTax)) {
//			double roundedTax = truncatedTax + 0.05;
//			return roundedTax;
//		}
//		
//		
//		return initialTax;
//	}
	
	public double calculateRoundedTax(Product product) {
		double initialTax = getAppropriateTaxSystem(product).getTax(product);
		double roundedTax = Math.ceil(initialTax / 0.05) / 20;
		
		return roundedTax;
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

	Double getRoundedTax(Product myProduct) {
		Double tax = getAppropriateTaxSystem(myProduct).getTax(myProduct);
		return tax;
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

	private boolean divisibleBy5(int decimalPart) {
		return decimalPart % 5 == 0;
	}
	
	private boolean truncatedTaxIsInferiorToInitialTax(double truncatedTax, double initialTax) {
		return truncatedTax < initialTax;
	}
}
