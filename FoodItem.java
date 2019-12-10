
public class FoodItem {

	private  String name;
	private long catalogueNumber;
	private int quantity;
	Date productionDate;
	Date expiryDate;
	private  int minTemperature;
	private  int maxTemperature;
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
	



		System.out.println(this.expiryDate.toString());
		
		this.minTemperature = minTemperature;
		this.maxTemperature = maxTemperature;
		this.price = price;

	}
	
	
	/*
	 * 	 final String name;
	 long catalogueNumber
	Date productionDate
	 expiryDate
	  int minTemperature
	 maxTemperature
	 int price

	 */
	
	public boolean equals(FoodItem other) {
		return (this.catalogueNumber == other.catalogueNumber && 
				this.name.equals(other.name) &&
				this.productionDate.equals(other.productionDate)&&
				this.expiryDate.equals(other.expiryDate)&&
				this.minTemperature == other.minTemperature&&
				this.maxTemperature == other.maxTemperature&&
				this.price == other.price);
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
	

	public static void main(String args[]) {
		/*
		 * 	public FoodItem(String name, 
		 * long catalogueNumber, int quantity,
		 *  Date productionDate, Date expiryDate,
			int minTemperature, int maxTemperature, int price) {
		 */
		
		Date productionDate = new Date(8, 12, 2019);
		Date ExpieryDate = new Date(1, 2, 2020);
		Date CheckDate = new Date(1, 1, 2020);
		
		
		
		FoodItem foodItem =  new FoodItem("cocoPaps",1234,3,productionDate,
			ExpieryDate,-5,5,30);
		
		
		System.out.println(productionDate.toString());
	System.out.println(ExpieryDate.toString());
	System.out.println(CheckDate.toString());
		
		
	System.out.println(ExpieryDate.before(CheckDate));
		//System.out.print(foodItem.isFresh(CheckDate));
		
	
		

	}

}
