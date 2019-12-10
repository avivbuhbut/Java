
public class FoodItem {

	private String name;
	private long catalogueNumber;
	private int quantity;
	Date productionDate;
	Date expiryDate;
	private int minTemperature;
	private int maxTemperature;
	private int price;

	private final int defaultPrice = 1;
	private final int defaultQuantity = 1;

	public FoodItem(String name, long catalogueNumber, int quantity, Date productionDate, Date expiryDate,
			int minTemperature, int maxTemperature, int price) {

		// if the product expirey date is before the production date than change it

		if (minTemperature > maxTemperature)
			maxTemperature = minTemperature;

		if (name == " ") {
			name = "item";
		}

		if ((catalogueNumber / 100) > 100)
			catalogueNumber = 9999;

		if (quantity < 0)
			quantity = defaultQuantity;

		if (price < 0)
			price = defaultPrice;

		this.name = name;
		this.catalogueNumber = catalogueNumber;
		this.quantity = quantity;

		this.productionDate = new Date(productionDate);

		this.expiryDate = new Date(expiryDate);

		if (this.expiryDate.before(this.productionDate))
			this.expiryDate = this.productionDate.tommarow();

		this.minTemperature = minTemperature;
		this.maxTemperature = maxTemperature;
		this.price = price;

	}

	/*
	 * final String name; long catalogueNumber Date productionDate expiryDate int
	 * minTemperature maxTemperature int price
	 * 
	 */

	public boolean equals(FoodItem other) {
		return (this.catalogueNumber == other.catalogueNumber && this.name.equals(other.name)
				&& this.productionDate.equals(other.productionDate) && this.expiryDate.equals(other.expiryDate)
				&& this.minTemperature == other.minTemperature && this.maxTemperature == other.maxTemperature
				&& this.price == other.price);
	}

	/*
	 * copy contractor
	 */

	public FoodItem(FoodItem other) {
		other.name = this.name;
		other.catalogueNumber = this.catalogueNumber;
		other.quantity = this.quantity;
		other.productionDate = this.productionDate;
		other.expiryDate = this.expiryDate;
		other.minTemperature = this.minTemperature;
		other.maxTemperature = this.maxTemperature;
		other.price = this.price;

	}

	public boolean isFresh(Date d) {
		return (this.expiryDate.after(d));
	}

	public String toString() {
		/*
		 * F date
		 */
		return "FoodItem: " + this.name + " CatalogueNumber: " + this.catalogueNumber + " ProductionDate: "
				+ this.productionDate + " ExpiryDate: " + this.expiryDate + " Quantity:" + this.quantity;
	}

	public boolean olderFoodItem(FoodItem other) {
		return this.productionDate.before(other.productionDate);
	}

	public int howManyItems(int amount) {

		int canBuy = amount / this.price;
		if (canBuy > this.quantity)
			return 0;
		else
			return canBuy;

	}

	public boolean isCheaper(FoodItem other) {
		return this.price < other.price;
	}

	long getCatalogueNumber() {
		return this.catalogueNumber;
	}

	public String getName() {
		return this.name;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	Date getProductionDate() {
		return this.productionDate;
	}
	
	Date getExpiryDate() {
		return this.expiryDate;
	}
	
	public int getMinTemperature() {
		return this.minTemperature;	
	}
	
	public int getMaxTemperature() {
		return this.maxTemperature;
	}
	
	public int getPrice() {
		return this.price;
	}
	

	public static void main(String args[]) {
		/*
		 * public FoodItem(String name, long catalogueNumber, int quantity, Date
		 * productionDate, Date expiryDate, int minTemperature, int maxTemperature, int
		 * price) {
		 */

		Date productionDate = new Date(8, 12, 2019);
		Date ExpieryDate = new Date(1, 2, 2020);

		FoodItem foodItem = new FoodItem("cocoPaps", 1234, 3, productionDate, ExpieryDate, -5, 5, 30);

		Date productionDate2 = new Date(8, 10, 2019);
		Date ExpieryDate2 = new Date(1, 5, 2020);

		FoodItem foodItem2 = new FoodItem("chicken", 4321, 5, productionDate, ExpieryDate, -10, 10, 2);

		System.out.println(foodItem2.isCheaper(foodItem));

	}

}
