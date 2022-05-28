package billing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BillingTest {

	@Test
	void whenProductIsNotImportedNorFoodNorMedicineNorBookThenTaxIsTenPercent() {
		Product myProduct = new Product("flacon de parfum", 10.0, false, "Beauty");
		assertEquals(0.1 * myProduct.getPrice(), new TaxCalculatorClient().calculateTax(myProduct));
	}
	
	@Test
	void whenProductIsImportedAndNotFoodNorMedicineNorBookThenTaxIsFifteenPercent() {
		Product myProduct = new Product("flacon de parfum", 10.0, true, "Beauty");
		assertEquals(0.15 * myProduct.getPrice(), new TaxCalculatorClient().calculateTax(myProduct));
	}
	
	@Test
	void whenProductIsFoodNotImportedThenTaxIsZero() {
		Product myProduct = new Product("pizza", 10.0, false, "Food");
		assertEquals(0, new TaxCalculatorClient().calculateTax(myProduct));
	}
	
	@Test
	void whenProductIsMedicineNotImportedThenTaxIsZero() {
		Product myProduct = new Product("doliprane", 10.0, false, "Medicine");
		assertEquals(0, new TaxCalculatorClient().calculateTax(myProduct));
	}
	
	@Test
	void whenProductIsBookNotImportedThenTaxIsZero() {
		Product myProduct = new Product("Le Clean Code pour les nuls", 10.0, false, "Book");
		assertEquals(0, new TaxCalculatorClient().calculateTax(myProduct));
	}
	
	@Test
	void whenProductIsImportedFoodThenTaxIsFivePercent() {
		Product myProduct = new Product("pizza", 10.0, true, "Food");
		assertEquals(0.05 * myProduct.getPrice(), new TaxCalculatorClient().calculateTax(myProduct));
	}
	
	@Test
	void whenProductIsImportedMedicineThenTaxIsFivePercent() {
		Product myProduct = new Product("doliprane", 10.0, true, "Medicine");
		assertEquals(0.05 * myProduct.getPrice(), new TaxCalculatorClient().calculateTax(myProduct));
	}
	
	@Test
	void whenProductIsImportedBookThenTaxIsFivePercent() {
		Product myProduct = new Product("Le Clean Code pour les nuls", 10.0, true, "Book");
		assertEquals(0.05 * myProduct.getPrice(), new TaxCalculatorClient().calculateTax(myProduct));
	}

}
