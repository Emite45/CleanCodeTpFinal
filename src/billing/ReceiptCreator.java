package billing;

import java.util.List;

public class ReceiptCreator {

	public Receipt createReceipt(List<Product> listOfProducts) {
		double totalAmountOfTaxes = calculateTotalTaxes(listOfProducts);
		double totalPriceWithTaxes = getTotalPriceWithTaxes(listOfProducts);
		Receipt receipt = new Receipt(listOfProducts, totalAmountOfTaxes, totalPriceWithTaxes);
		return receipt;
	}
 
	public double getTotalPriceWithoutTaxes(List<Product> listOfProducts) {
		return RoundDoubleToTwoSignificantDigits(calculateTotalPriceWithoutTaxes(listOfProducts));
	}

	public double calculateTotalTaxes(List<Product> listOfProducts) {
		return RoundDoubleToTwoSignificantDigits(new TaxCalculatorClient().getTax(listOfProducts));
	}
	
	public double calculateTotalPriceWithoutTaxes(List<Product> listOfProducts) {
		double totalPriceWithoutTaxes = 0.;
		for (Product product : listOfProducts) {
			totalPriceWithoutTaxes += product.getPrice();
		}
		return totalPriceWithoutTaxes;
	}

	double RoundDoubleToTwoSignificantDigits(double doubleToModify) {
		return Double.parseDouble((String.format("%.2f", doubleToModify).replace(",", ".")));
	}

	public double getTotalPriceWithTaxes(List<Product> listOfProducts) {
		return RoundDoubleToTwoSignificantDigits(calculateTotalPriceWithTaxes(listOfProducts));
	}

	private double calculateTotalPriceWithTaxes(List<Product> listOfProducts) {
		double totalPriceWithTaxes = getTotalPriceWithoutTaxes(listOfProducts)
				+ new TaxCalculatorClient().getTax(listOfProducts);
		return totalPriceWithTaxes;
	}

}
