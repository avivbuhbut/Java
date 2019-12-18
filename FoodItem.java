
public class FoodItem {
	
	//Created by: Aviv Buhbut id: 204445084

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

	/**
	 * creates a new FoodItem object
	 * 
	 * @param name
	 *            - name of food item
	 * @param catalogueNumber
	 *            - catalogue number of food item
	 * @param quantity
	 *            - quantity food item
	 * @param productionDate
	 *            - production date
	 * @param expiryDate
	 *            - expiry date
	 * @param minTemperature
	 *            - minimum storage temperature
	 * @param maxTemperature
	 *            - maximum storage temperature
	 * @param price
	 *            - unit price
	 * 
	 * 
	 */

	public FoodItem(String name, long catalogueNumber, int quantity, Date productionDate, Date expiryDate,
			int minTemperature, int maxTemperature, int price) {

		if (minTemperature > maxTemperature)
			maxTemperature = minTemperature;

		if (name == " ") {
			name = "item";
		}

		if ((catalogueNumber / 100) > 1000 & (catalogueNumber / 100) < 9999)
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

	/**
	 * check if 2 food items are the same (excluding the quantity values)
	 * 
	 * @param other
	 *            - the food item to compare this food item to
	 * @return true if the food items are the same
	 */

	public boolean equals(FoodItem other) {
		return (this.catalogueNumber == other.catalogueNumber && this.name.equals(other.name)
				&& this.productionDate.equals(other.productionDate) && this.expiryDate.equals(other.expiryDate)
				&& this.minTemperature == other.minTemperature && this.maxTemperature == other.maxTemperature
				&& this.price == other.price);
	}

	/**
	 * copy contractor
	 * 
	 * @param other
	 *            - the food item to be copied
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

	/**
	 * check if this food item is older than other food item
	 * 
	 * @param other
	 *            - food item to compare this food item to
	 * @return true if this food item is older than other date
	 */

	public boolean isFresh(Date d) {
		return (this.expiryDate.after(d));
	}

	/**
	 * @Overrides toString in class java.lang.Object
	 * 
	 * @returns String that represents this food item in the following format:
	 *          FoodItem: milk CatalogueNumber: 1234 ProductionDate: 14/12/2019
	 *          ExpiryDate: 21/12/2019 Quantity: 3
	 * 
	 */

	public String toString() {

		return "FoodItem: " + this.name + "\tCatalogueNumber: " + this.catalogueNumber + "\tProductionDate: "
				+ this.productionDate + "\tExpiryDate: " + this.expiryDate + "\tQuantity: " + this.quantity;
	}

	/**
	 * check if this food item is older than other food item
	 * 
	 * @param other
	 *            - food item to compare this food item to
	 * @return true if this food item is older than other date
	 */

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

	/**
	 * check if this food item is cheaper than other food item
	 * 
	 * @param other
	 *            - food item to compare this food item to
	 * @return true if this food item is cheaper than other date
	 */
	public boolean isCheaper(FoodItem other) {
		return this.price < other.price;
	}

	/**
	 * gets the catalogue number
	 * 
	 * @return the catalogue number
	 */

	long getCatalogueNumber() {
		return this.catalogueNumber;
	}

	/**
	 * gets the name
	 * 
	 * @return the name
	 */

	public String getName() {
		return this.name;
	}

	/**
	 * gets the quantity
	 * 
	 * @return the quantity
	 * 
	 */

	public int getQuantity() {
		return this.quantity;
	}

	/**
	 * gets the production date
	 * 
	 * @return the production date
	 * 
	 */

	Date getProductionDate() {
		return this.productionDate;
	}

	/**
	 * gets the expiry date
	 * 
	 * @return the expiry date
	 * 
	 */

	Date getExpiryDate() {
		return this.expiryDate;
	}

	/**
	 * gets the minimum storage temperature
	 * 
	 * @return the minimum storage temperature
	 * 
	 */

	public int getMinTemperature() {
		return this.minTemperature;
	}

	/**
	 * gets the maximum storage temperature
	 * 
	 * @return the maximum storage temperature
	 * 
	 */

	public int getMaxTemperature() {
		return this.maxTemperature;
	}

	/**
	 * gets the unit price
	 * 
	 * @return the units price
	 * 
	 */

	public int getPrice() {
		return this.price;
	}

	/**
	 * set the quantity (only if not negative)
	 * 
	 * @param n
	 *            - the quantity value to be set
	 * 
	 */

	public void setQuantity(int n) {
		if (quantity < 0)
			quantity = defaultQuantity;

		this.quantity = n;
	}

	/**
	 * set the production date (only if not after expiry date )
	 * 
	 * @param d
	 *            - production date value to be set
	 * 
	 */
	public void setProductionDate(Date d) {
		this.productionDate = d;
	}

	/**
	 * set the expiry date (only if not before production date )
	 * s
	 * @param d
	 *            - expiry date value to be set
	 * 
	 * 
	 */

	public void setExpiryDate(Date d) {
		this.expiryDate = new Date(d);

		// if (this.expiryDate.before(this.productionDate))
		// this.expiryDate = this.productionDate.tomorrow();
	}

	/**
	 * set the price (only if positive)
	 * 
	 * @param n
	 *            - price value to be set
	 * 
	 * 
	 */

	public void setPrice(int n) {
		if (price < 0)
			price = defaultPrice;

		this.price = n;
	}

}
