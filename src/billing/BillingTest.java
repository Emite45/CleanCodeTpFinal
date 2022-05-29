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
		Product myProduct = new Product("boite de chocolats", 10.0, false, "Food");
		assertEquals(0, new TaxCalculatorClient().calculateTax(myProduct));
	}

	@Test
	void whenProductIsMedicineNotImportedThenTaxIsZero() {
		Product myProduct = new Product("boite de pilule contre la migraine", 10.0, false, "Medicine");
		assertEquals(0, new TaxCalculatorClient().calculateTax(myProduct));
	}

	@Test
	void whenProductIsBookNotImportedThenTaxIsZero() {
		Product myProduct = new Product("Le Clean Code pour les nuls", 10.0, false, "Book");
		assertEquals(0, new TaxCalculatorClient().calculateTax(myProduct));
	}

	@Test
	void whenProductIsImportedFoodThenTaxIsFivePercent() {
		Product myProduct = new Product("boite de chocolats", 10.0, true, "Food");
		assertEquals(0.05 * myProduct.getPrice(), new TaxCalculatorClient().calculateTax(myProduct));
	}

	@Test
	void whenProductIsImportedMedicineThenTaxIsFivePercent() {
		Product myProduct = new Product("boite de pilule contre la migraine", 10.0, true, "Medicine");
		assertEquals(0.05 * myProduct.getPrice(), new TaxCalculatorClient().calculateTax(myProduct));
	}

	@Test
	void whenProductIsImportedBookThenTaxIsFivePercent() {
		Product myProduct = new Product("Le Clean Code pour les nuls", 10.0, true, "Book");
		assertEquals(0.05 * myProduct.getPrice(), new TaxCalculatorClient().calculateTax(myProduct));
	}
	
	@Test
	void whenNotRoundedTaxIs4_1985ThenRoundedTaxIs4_2() {
		Product myProduct = new Product("flacon de parfum", 27.99, true, "Beauty");
		assertEquals(4.2, new TaxCalculatorClient().calculateRoundedTax(myProduct));
	}
	
	@Test
	void whenNotRoundedTaxIs5_555ThenRoundedTaxIs5_6() {
		Product myProduct = new Product("Le Clean Code pour les nuls vol.2", 11.11, true, "Book");
		assertEquals(0.6, new TaxCalculatorClient().calculateRoundedTax(myProduct));
	}
	
	@Test
	void whenNotRoundedTaxIs0_5ThenRoundedTaxIs0_5() {
		Product myProduct = new Product("Le Clean Code pour les nuls", 10.0, true, "Book");
		assertEquals(0.5, new TaxCalculatorClient().calculateRoundedTax(myProduct));
	}
	
	// 4.1985 + 1.899 + 0 + 0.5625
	@Test
	void whenProducsAreOneImportedPerfumeOnePerfumeOneHeachacheMedicineBoxOneImportedChocolateBoxThenTheTotalTaxIs6_70() {
		Product myProduct1 = new Product("flacon de parfum", 27.99, true, "Beauty");
		Product myProduct2 = new Product("flacon de parfum", 18.99, false, "Beauty");
		Product myProduct3 = new Product("boite de pilule contre la migraine", 9.75, false, "Medicine");
		Product myProduct4 = new Product("boite de chocolats", 11.25, true, "Food");
		assertEquals(6.70, new TaxCalculatorClient().calculateRoundedTax(myProduct1)
						 + new TaxCalculatorClient().calculateRoundedTax(myProduct2)
						 + new TaxCalculatorClient().calculateRoundedTax(myProduct3)
					     + new TaxCalculatorClient().calculateRoundedTax(myProduct4));
	} 
	

}
