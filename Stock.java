

public class Stock {

	FoodItem[] _stock;
	int _noOfItems;
	final int MAX_ARRAY_lENGTH = 100;

	public Stock() {
		_stock = new FoodItem[MAX_ARRAY_lENGTH];
	}

	public int getNumOfItems() {

		// System.out.println(_stock.length);
		for (int i = 0; i < this._stock.length; i++) {
			if (_stock[i] != null)
				_noOfItems++;
			// System.out.println(_noOfItems);
		}

		return _noOfItems;

	}

	/**
	 * 
	 * @return 1 for same name,barcode,expiery date, production date
	 * @return 0 for same name,barcode but different expirey date and production
	 *         date
	 */
	private boolean checkIfItemExist(FoodItem newitem, int i) {
		// System.out.println(_stock[i].getName());
		if (_stock[i].getName().equals(newitem.getName())
				&& _stock[i].getCatalogueNumber() == newitem.getCatalogueNumber()
				&& _stock[i].getExpiryDate().equals(newitem.getExpiryDate())
				&& _stock[i].getProductionDate().equals(newitem.getProductionDate()))
			return true;

		else
			return false;

	}

	public boolean addItem(FoodItem newitem) {
		// System.out.println("getNum: " +getNumOfItems());

		for (int i = 0; i < _stock.length; i++) {
			// System.out.println(i);
			
			if(_stock.length > 1) {
				 if(checkIfItemExist(newitem, i)) {
					newitem.setQuantity(_stock[i].getQuantity() + newitem.getQuantity()); 
				 }
				 
			}
			
			if (_stock[i] == null) {			
				_stock[i] = new FoodItem(newitem);
				System.out.println(_stock[i].toString());
					return true;

				
			}
			
			
		}

		return false;

	}
	/*
	 * if non of the above happend than the for loope ended - it means that there
	 * isnt an item line the parameter newitem in the array and that there was no
	 * free space in the array to put the newitem than the method additem didnt work
	 * and it will return false
	 */
	
	/*
	private boolean checkIfItemExist(FoodItem newitem) {

		int k = 0;
		if (_stock.length > 1) {
			for (int i = 0; i < _stock.length; i++) {
				System.out.println(i);

				if (checkProductsValues(newitem, i) == 1) {
					_stock[i].setQuantity(_stock[i].getQuantity() + newitem.getQuantity());
					return true;
				} else

					while (_stock[k] != null) {// it means some of the values wasn't the same in that case i need to
												// enter
												// the product to a new cell in the array
						// if(_stock[k].productionDate.before(newitem.productionDate))

						k++;
					}
				_stock[k] = newitem;

				return true;

			}

		}
		return false;
	}*/

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		int i = 0;
		for (; i < _stock.length - 1; i++) {
			System.out.println(_stock[i].toString());
		}

		return _stock[i].toString();
	}

	public static void main(String[] args) {
		Stock stock = new Stock();
		Date productionDate = new Date(8, 3, 2001);
		Date expiryDate = new Date(8, 6, 2001);
		FoodItem food1 = new FoodItem("Chreios", 1234, 1, productionDate, expiryDate, 0, 5, 25);
		FoodItem food2 = new FoodItem("dog Food", 4321, 2, productionDate, expiryDate, 0, 20, 50);

		// System.out.println(stock.addItem(food1));
		stock.addItem(food1);

		// stock.addItem(food2);
		// stock.toString();
		// stock.addItem(food2);
	}
}
