package billing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BillingTest {

	@Test
	void whenProductIsNotImportedNorFoodNorMedicineNorBookThenTaxIsTenPercent() {
		Product myProduct = new Product("flacon de parfum", 10.0, false);
		assertEquals(0.1 * myProduct.getPrice(), new TaxCalculator().calculateTax(myProduct));
	}

}
