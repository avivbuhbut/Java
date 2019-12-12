
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

	/*
	 * creates a new FoodItem object
	 * 
	 * 
	 * 
	 * 
	 * @param name - name of food item
	 * 
	 * @param catalogueNumber - catalogue number of food item
	 * 
	 * @param quantity - quantity of food item
	 * 
	 * @param productionDate - production date
	 * 
	 * @param expiryDate - expiry date
	 * 
	 * @param minTemperature - minimum storage temperature
	 * 
	 * @param maxTemperature - maximum storage temperature
	 * 
	 * @param price - unit price
	 * 
	 */

	public FoodItem(String name, long catalogueNumber, int quantity, Date productionDate, Date expiryDate,
			int minTemperature, int maxTemperature, int price) {

		// if the product expirey date is before the production date than change it

		if (minTemperature > maxTemperature)
			maxTemperature = minTemperature;

		if (name == " ") {
			name = "item";
		}

		if ((catalogueNumber / 100) < 1000 & (catalogueNumber / 100) < 9999)
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
			this.expiryDate = this.productionDate.tomorrow();

		this.minTemperature = minTemperature;
		this.maxTemperature = maxTemperature;
		this.price = price;

	}

	/*
	 * check if 2 food items are the same (excluding the quantity values)
	 * 
	 * 
	 * @param other - the food item to compare this food item to
	 * 
	 * @returns true if the food items are the same
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
	 * 
	 * 
	 * @param other - the food item to be copied
	 * 
	 */

	public FoodItem(FoodItem other) {
		this.name = other.name;
		this.catalogueNumber = other.catalogueNumber;
		this.quantity = other.quantity;
		this.productionDate = other.productionDate;
		this.expiryDate = other.expiryDate;
		this.minTemperature = other.minTemperature;
		this.maxTemperature = other.maxTemperature;
		this.price = other.price;

	}

	public boolean isFresh(Date d) {
		return (this.expiryDate.after(d));
	}

	public String toString() {
		/*
		 * F date
		 */
		return "FoodItem: " + this.name + "\tCatalogueNumber: " + this.catalogueNumber + "\tProductionDate: "
				+ this.productionDate + "\tExpiryDate: " + this.expiryDate + "\tQuantity: " + this.quantity;
	}

	public boolean olderFoodItem(FoodItem other) {
		return !(this.productionDate.before(other.productionDate));
	}

	public int howManyItems(int amount) {

		int canBuy = amount / this.price;

		if (canBuy > this.quantity)
			return Math.abs((canBuy - this.quantity) - canBuy);
		else
			return canBuy;

	}

	public boolean isCheaper(FoodItem other) {
		return this.price < other.price;
	}

	/*
	 * gets the catalogue number
	 * 
	 * @returns the catalogue number
	 * 
	 */

	long getCatalogueNumber() {
		return this.catalogueNumber;
	}
	
	
	/*
	 * 
	 * 
	 *gets the name
	 * 
	 * @returns the name
	 * 
	 */

	public String getName() {
		return this.name;
	}

	public int getQuantity() {
		return this.quantity;
	}
	
	/*
	 * 
	 * 
	 *gets the production date
	 * 
	 * @returns the production date
	 * 
	 */
	

	Date getProductionDate() {
		return this.productionDate;
	}

	Date getExpiryDate() {
		return this.expiryDate;
	}

	
	/*
	 * 
	 * 
	 *gets the minimum storage temperature
	 * 
	 * @returns the minimum storage temperature
	 * 
	 */
	
	public int getMinTemperature() {
		return this.minTemperature;
	}

	/*
	 * 
	 * 
	 * gets the maximum storage temperature
	 * 
	 * @returns the maximum storage temperature
	 * 
	 */

	public int getMaxTemperature() {
		return this.maxTemperature;
	}

	/*
	 * 
	 * 
	 *gets the unit price
	 * 
	 * @returns the  units price
	 * 
	 */
	
	public int getPrice() {
		return this.price;
	}

	public void setQuantity(int n) {
		if (quantity < 0)
			quantity = defaultQuantity;

		this.quantity = n;
	}

	public void setProductionDate(Date d) {
		this.productionDate = d;
	}

	public void setExpiryDate(Date d) {
		this.expiryDate = new Date(d);

		// if (this.expiryDate.before(this.productionDate))
		// this.expiryDate = this.productionDate.tomorrow();
	}

	public void setPrice(int n) {
		if (price < 0)
			price = defaultPrice;

		this.price = n;
	}

}
