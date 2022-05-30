package billing;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public class ReceiptDisplay {

	public String displayReceipt(Receipt myReceipt) {
		String fullReceiptDisplay = displayListOfProductNamesWithPrices(myReceipt)
								  + displayTotalAmountOfTaxes(myReceipt)
								  + displayTotalPriceWithTaxes(myReceipt);
		return fullReceiptDisplay;
	}

	public String displayListOfProductNamesWithPrices(Receipt receipt) {
		String listOfProductNamesWithPrices = "";
		List<Product> listOfProducts = receipt.getListOfProducts();
		for (Product product : listOfProducts.stream().distinct().collect(Collectors.toList())) {
			Long occurence = (receipt.getOccurencesOfProducts().get(product));
			listOfProductNamesWithPrices += (occurence + " " + product.getName() + " : " + keepTwoSignificantDigits(occurence * product.getPrice()) + "\n");
		}

		return listOfProductNamesWithPrices;
	}
	
	public String displayTotalAmountOfTaxes(Receipt receipt) {
		return "Montant des taxes : " + keepTwoSignificantDigits(receipt.getTotalAmountOfTaxes()) + "\n";
	}
		
	public String keepTwoSignificantDigits(double doubleToModify) {
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		return decimalFormat.format(doubleToModify).replace(",", ".");
	}

	public String displayTotalPriceWithTaxes(Receipt receipt) {
		return "Total : " + keepTwoSignificantDigits(receipt.getTotalPriceIncludingTaxes());
	}

}
