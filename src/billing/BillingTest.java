package billing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class BillingTest {

	@Test
	void whenProductIsNotImportedNorFoodNorMedicineNorBookThenTaxIsTenPercent() {
		Product myProduct = new Product("flacon de parfum", 10.0, false, "Beauty");
		assertEquals(0.1 * myProduct.getPrice(), new TaxCalculatorClient().getTax(Stream.of(myProduct).collect(Collectors.toList())));
	}

	@Test
	void whenProductIsImportedAndNotFoodNorMedicineNorBookThenTaxIsFifteenPercent() {
		Product myProduct = new Product("flacon de parfum importé", 10.0, true, "Beauty");
		assertEquals(0.15 * myProduct.getPrice(), new TaxCalculatorClient().getTax(Stream.of(myProduct).collect(Collectors.toList())));
	}

	@Test
	void whenProductIsFoodNotImportedThenTaxIsZero() {
		Product myProduct = new Product("boite de chocolats", 10.0, false, "Food");
		assertEquals(0, new TaxCalculatorClient().getTax(Stream.of(myProduct).collect(Collectors.toList())));
	}

	@Test
	void whenProductIsMedicineNotImportedThenTaxIsZero() {
		Product myProduct = new Product("boite de pilule contre la migraine", 10.0, false, "Medicine");
		assertEquals(0, new TaxCalculatorClient().getTax(Stream.of(myProduct).collect(Collectors.toList())));
	}

	@Test
	void whenProductIsBookNotImportedThenTaxIsZero() {
		Product myProduct = new Product("Le Clean Code pour les nuls", 10.0, false, "Book");
		assertEquals(0, new TaxCalculatorClient().getTax(Stream.of(myProduct).collect(Collectors.toList())));
	}

	@Test
	void whenProductIsImportedFoodThenTaxIsFivePercent() {
		Product myProduct = new Product("boite de chocolats importés", 10.0, true, "Food");
		assertEquals(0.05 * myProduct.getPrice(), new TaxCalculatorClient().getTax(Stream.of(myProduct).collect(Collectors.toList())));
	}

	@Test
	void whenProductIsImportedMedicineThenTaxIsFivePercent() {
		Product myProduct = new Product("boite de pilule contre la migraine importée", 10.0, true, "Medicine");
		assertEquals(0.05 * myProduct.getPrice(), new TaxCalculatorClient().getTax(Stream.of(myProduct).collect(Collectors.toList())));
	}

	@Test
	void whenProductIsImportedBookThenTaxIsFivePercent() {
		Product myProduct = new Product("Le Clean Code pour les nuls importé", 10.0, true, "Book");
		assertEquals(0.05 * myProduct.getPrice(), new TaxCalculatorClient().getTax(Stream.of(myProduct).collect(Collectors.toList())));
	}
	
	@Test
	void whenNotRoundedTaxIs4_1985ThenRoundedTaxIs4_2() {
		Product myProduct = new Product("flacon de parfum importé", 27.99, true, "Beauty");
		assertEquals(4.2, new TaxCalculatorClient().getTax(Stream.of(myProduct).collect(Collectors.toList())));
	}
	
	@Test
	void whenNotRoundedTaxIs5_555ThenRoundedTaxIs5_6() {
		Product myProduct = new Product("Le Clean Code pour les nuls vol.2 importé", 11.11, true, "Book");
		assertEquals(0.6, new TaxCalculatorClient().getTax(Stream.of(myProduct).collect(Collectors.toList())));
	}
	
	@Test
	void whenNotRoundedTaxIs0_5ThenRoundedTaxIs0_5() {
		Product myProduct = new Product("Le Clean Code pour les nuls importé", 10.0, true, "Book");
		assertEquals(0.5, new TaxCalculatorClient().getTax(Stream.of(myProduct).collect(Collectors.toList())));
	}
	
	@Test
	void whenProductsAreOneImportedPerfumeOnePerfumeOneHeachacheMedicineBoxOneImportedChocolateBoxThenTotalTaxIs6_70() {
		Product myProduct1 = new Product("flacon de parfum importé", 27.99, true, "Beauty");
		Product myProduct2 = new Product("flacon de parfum", 18.99, false, "Beauty");
		Product myProduct3 = new Product("boite de pilule contre la migraine", 9.75, false, "Medicine");
		Product myProduct4 = new Product("boite de chocolats importés", 11.25, true, "Food");
		assertEquals(6.70, new TaxCalculatorClient().getTax(Stream.of(myProduct1,myProduct2,myProduct3,myProduct4).collect(Collectors.toList())));
	} 
	
	@Test
	void whenProductsAreOneImportedPerfumeOnePerfumeOneHeachacheMedicineBoxOneImportedChocolateBoxThenTotalPriceWithoutTaxesIs67_98() {
		Product myProduct1 = new Product("flacon de parfum importé", 27.99, true, "Beauty");
		Product myProduct2 = new Product("flacon de parfum", 18.99, false, "Beauty");
		Product myProduct3 = new Product("boite de pilule contre la migraine", 9.75, false, "Medicine");
		Product myProduct4 = new Product("boite de chocolats importés", 11.25, true, "Food");
		List<Product> listOfProducts = Stream.of(myProduct1,myProduct2,myProduct3,myProduct4).collect(Collectors.toList());
		assertEquals(67.98, new ReceiptCreator().getTotalPriceWithoutTaxes(listOfProducts));
	} 
	
	@Test
	void whenProductsAreOneImportedPerfumeOnePerfumeOneHeachacheMedicineBoxOneImportedChocolateBoxThenTotalPriceWithTaxesIs74_68() {
		Product myProduct1 = new Product("flacon de parfum importé", 27.99, true, "Beauty");
		Product myProduct2 = new Product("flacon de parfum", 18.99, false, "Beauty");
		Product myProduct3 = new Product("boite de pilule contre la migraine", 9.75, false, "Medicine");
		Product myProduct4 = new Product("boite de chocolats importés", 11.25, true, "Food");
		List<Product> listOfProducts = Stream.of(myProduct1,myProduct2,myProduct3,myProduct4).collect(Collectors.toList());
		assertEquals(74.68, new ReceiptCreator().getTotalPriceWithTaxes(listOfProducts));
	} 
	
	@Test
	void whenProductsAreOneImportedPerfumeOnePerfumeOneHeachacheMedicineBoxOneImportedChocolateBoxThenDisplayListOfProductsWithPrices() {
		Product myProduct1 = new Product("flacon de parfum importé", 27.99, true, "Beauty");
		Product myProduct2 = new Product("flacon de parfum", 18.99, false, "Beauty");
		Product myProduct3 = new Product("boite de pilule contre la migraine", 9.75, false, "Medicine");
		Product myProduct4 = new Product("boite de chocolats importés", 11.25, true, "Food");
		List<Product> listOfProducts = Stream.of(myProduct1,myProduct2,myProduct3,myProduct4).collect(Collectors.toList());
		Receipt myReceipt = new ReceiptCreator().createReceipt(listOfProducts);
		assertEquals("1 flacon de parfum importé : 27.99\n"
				   + "1 flacon de parfum : 18.99\n"
				   + "1 boite de pilule contre la migraine : 9.75\n"
				   + "1 boite de chocolats importés : 11.25\n",
				new ReceiptDisplay().displayListOfProductNamesWithPrices(myReceipt));
	}
	
	@Test
	void whenProductsAreOneImportedPerfumeOnePerfumeOneHeachacheMedicineBoxOneImportedChocolateBoxThenDisplayTotalAmountOfTaxes() {
		Product myProduct1 = new Product("flacon de parfum importé", 27.99, true, "Beauty");
		Product myProduct2 = new Product("flacon de parfum", 18.99, false, "Beauty");
		Product myProduct3 = new Product("boite de pilule contre la migraine", 9.75, false, "Medicine");
		Product myProduct4 = new Product("boite de chocolats importés", 11.25, true, "Food");
		List<Product> listOfProducts = Stream.of(myProduct1,myProduct2,myProduct3,myProduct4).collect(Collectors.toList());
		Receipt myReceipt = new ReceiptCreator().createReceipt(listOfProducts);
		assertEquals("Montant des taxes : 6.70\n",
				new ReceiptDisplay().displayTotalAmountOfTaxes(myReceipt));
	}
	
	@Test
	void whenProductsAreOneImportedPerfumeOnePerfumeOneHeachacheMedicineBoxOneImportedChocolateBoxThenDisplayTotalPriceWithTaxes() {
		Product myProduct1 = new Product("flacon de parfum importé", 27.99, true, "Beauty");
		Product myProduct2 = new Product("flacon de parfum", 18.99, false, "Beauty");
		Product myProduct3 = new Product("boite de pilule contre la migraine", 9.75, false, "Medicine");
		Product myProduct4 = new Product("boite de chocolats importés", 11.25, true, "Food");
		List<Product> listOfProducts = Stream.of(myProduct1,myProduct2,myProduct3,myProduct4).collect(Collectors.toList());
		Receipt myReceipt = new ReceiptCreator().createReceipt(listOfProducts);
		assertEquals("Total : 74.68",
				new ReceiptDisplay().displayTotalPriceWithTaxes(myReceipt));
	}
	
	@Test
	void whenProductsAreOneImportedPerfumeOnePerfumeOneHeachacheMedicineBoxOneImportedChocolateBoxThenDisplayReceipt() {
		Product myProduct1 = new Product("flacon de parfum importé", 27.99, true, "Beauty");
		Product myProduct2 = new Product("flacon de parfum", 18.99, false, "Beauty");
		Product myProduct3 = new Product("boite de pilule contre la migraine", 9.75, false, "Medicine");
		Product myProduct4 = new Product("boite de chocolats importés", 11.25, true, "Food");
		List<Product> listOfProducts = Stream.of(myProduct1,myProduct2,myProduct3,myProduct4).collect(Collectors.toList());
		Receipt myReceipt = new ReceiptCreator().createReceipt(listOfProducts);
		assertEquals("1 flacon de parfum importé : 27.99\n"
				   + "1 flacon de parfum : 18.99\n"
				   + "1 boite de pilule contre la migraine : 9.75\n"
				   + "1 boite de chocolats importés : 11.25\n"
				   + "Montant des taxes : 6.70\n"
				   + "Total : 74.68",
				new ReceiptDisplay().displayReceipt(myReceipt));
	} 
	
	
	
	
}
