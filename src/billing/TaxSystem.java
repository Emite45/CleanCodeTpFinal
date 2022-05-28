package billing;

public enum TaxSystem {

	TAXABLE {},
	NON_TAXABLE {
		@Override
		public Double getTax(Product product) {
			return 0.0;
		}
	},
	IMPORTED_TAXABLE {
		@Override
		public Double getTax(Product product) {
			return 0.15 * product.getPrice();
		}
	},
	IMPORTED_NON_TAXABLE {
		@Override
		public Double getTax(Product product) {
			return 0.05 * product.getPrice();
		}
	};

	Double getTax(Product product) {
		return 0.1 * product.getPrice();
	}
}
