
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
			this.expiryDate = this.productionDate.tomorrow();

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
	
	
	public void setQuantity(int n) {
		if (quantity < 0)
			quantity = defaultQuantity;
		
		this.quantity = n;
	}
	
	public void setProductionDate(Date d) {
		this.productionDate = d;
	}
	
	
	public void setExpiryDate (Date d){
		this.expiryDate = new Date(d);

		if (this.expiryDate.before(this.productionDate))
			this.expiryDate = this.productionDate.tomorrow();
	}
	
	public void setPrice (int n) {
		if (price < 0)
		price = defaultPrice;
		
		
		this.price = n;
	}
	

	public static void main(String args[]) {
	
	      System.out.println("********** Test FoodItem - Started **********");
	        System.out.println("\n1. Testing Constructors and toString:");
	        Date d1=new Date(14,12,2019);
	        Date d2=new Date(21,12,2019);
	        // test concstructor which takes 8 parameters
	        FoodItem fi1 = new FoodItem(new String("milk"),111111,6,d1,d2, 10, 25,5);
	        System.out.println("\tFoodItem 1:\n\t" + fi1);
	        // test copy concstructor
	        FoodItem fi2 = new FoodItem(fi1);
	        System.out.println("\tFoodItem 2:\n\t" + fi2);
	        System.out.println("\n2. Testing accessors and mutators:");
	        // test getters
	        System.out.println("\tFoodItem 1 name: " + fi1.getName());
	        System.out.println("\tFoodItem 1 catalogue number: " + fi1.getCatalogueNumber());
	        System.out.println("\tFoodItem 1 quantity: " + fi1.getQuantity());
	        System.out.println("\tFoodItem 1 production date: " + fi1.getProductionDate());
	        System.out.println("\tFoodItem 1 expiry date: " + fi1.getExpiryDate());
	        System.out.println("\tFoodItem 1 min temperature: " + fi1.getMinTemperature());
	        System.out.println("\tFoodItem 1 max temperature: " + fi1.getMaxTemperature());
	        System.out.println("\tFoodItem 1 price: " + fi1.getPrice());

	        // test setters   
	        fi2.setQuantity(4);
	        Date d3=new Date(18,12,2019);
	        Date d4=new Date(28,12,2019);
	        fi2.setProductionDate(d3);
	        fi2.setExpiryDate(d4);
	        fi2.setPrice(2);
	        System.out.println("\tFoodItem 2:\n\t" + fi2);

	        System.out.println("\n3. Testing comparison methods:");
	        FoodItem fi3 = new FoodItem(new String("milk"),111111,6,d1,d2, 10, 25,5);
	        System.out.println("\tFoodItem 1 and FoodItem 3 are equal: " + fi1.equals(fi3));
	        System.out.println("\tFoodItem 1 is older than FoodItem 2: " + fi1.olderFoodItem(fi2));
	        System.out.println("\tFoodItem 1 is fresh at "+d3+" : " + fi1.isFresh(d3)); 
	        System.out.println("\tFoodItem 1 is cheaper than FoodItem 2: " + fi1.isCheaper(fi2));
	        System.out.println("\n4. Testing howManyItems method :");
	        int amount=10;
	        System.out.println("\t"+ amount+" sheqels can buy "+ fi1.howManyItems(amount)+" of FoodItem 1");
	        System.out.println("\t"+ amount+" sheqels can buy "+ fi2.howManyItems(amount)+" of FoodItem 2");

	        

	}

}
