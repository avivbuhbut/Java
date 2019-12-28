

/**
 * 
 * @author Aviv buhbut
 * 
 * I.D: 204445084
 *
 */

public class Stock {

	FoodItem[] _stock;
	private int _noOfItems;
	final int MAX_ARRAY_lENGTH = 100;

	/*
	 * 
	 * constractor - constract a new stock with the max array length
	 */
	public Stock() {
		_stock = new FoodItem[MAX_ARRAY_lENGTH];
	}

	public int getNumOfItems() {

		return _noOfItems;
	}

	/**
	 *  this method checks if an item is exist 
	 * @return 1 for same name,barcode,expiery date, production date
	 * @return 0 for same name,barcode but different expirey date and production
	 *         date
	 */
	private boolean checkIfItemExist(FoodItem newitem, int i) {

		if (_stock[i].getName().equals(newitem.getName())
				&& _stock[i].getCatalogueNumber() == newitem.getCatalogueNumber()
				&& _stock[i].getExpiryDate().equals(newitem.getExpiryDate())
				&& _stock[i].getProductionDate().equals(newitem.getProductionDate()))
			return true;

		else
			return false;

	}
	
	
	/**
	 * this method adds an item 
	 * @param newitem - adds a new item to the list
	 * @return true if successful ,fasle otherwise
	 */

	public boolean addItem(FoodItem newitem) {

		int k = 0;

		for (int i = 0; i < _noOfItems; i++) {

			if (checkIfItemExist(newitem, i)) {

				_stock[i].setQuantity(_stock[i].getQuantity() + newitem.getQuantity());

				return true;
			}

		}

		if (_noOfItems == _stock.length) {

			return false;
		}

		while (_stock[k] != null) {

			k++;
		}

		if (findIndexByName(newitem.getName()) == -1) {

			_stock[k] = new FoodItem(newitem);
			_noOfItems++;
		} else {

			int index = findIndexByName(newitem.getName());
			this.moveForward(index);
			_stock[index] = newitem;

			k = index;
			_noOfItems++;

		}

		return true;

	}
	
	
	/**
	 * this method removes an item 
	 * @param index - the index to move the array forward from (overriding a item)
	 */

	private void moveForward(int index) {

		for (int i = _noOfItems; i > index; i--) {

			_stock[i] = _stock[i - 1];

		}

	}

	
	/**
	 * this method finds an item by name
	 * @param name - the name of the item that needs to be found
	 * @return the index of the item
	 */
	private int findIndexByName(String name) {

		for (int i = 0; i < _noOfItems; i++) {

			if (_stock[i].getName().equals(name))
				return i;
		}

		return -1;

	}

	
	/**
	 * this method get the items need to order 
	 * @param amount - the amount of items want from the stock
	 * @return a string with a list of the items the are avilable
	 */
	public String order(int amount) {


		String order = "";
		for (int i = 0; i < _noOfItems; i++) {
			int totalQuantity = 0;
			if (amountOfItem(i) > 1) {
				System.out.println("in");
				for (int j = i; j < i + amountOfItem(i); j++) { // iterating range of items of same kind

					totalQuantity += _stock[j].getQuantity(); // adding each items quantity to a total one
				}
			} else // only one item of this kind
				totalQuantity += _stock[i].getQuantity();

			if (totalQuantity < amount && i != _noOfItems - 1)
				order += _stock[i].getName() + ",";
			else
				order += _stock[i].getName();
			i += amountOfItem(i); // increasing i to be after range
		}

		return order;

	}

	/**
	 * 
	 * this method returns the emount of equal items in the stock
	 * @param index to start from 
	 * @return returns how many items of the same kind(same name)
	 */
	private int amountOfItem(int index) {

		int equalItems = 0;

		for (int i = index; i < _noOfItems - 1; i++) {
			if (_stock[i].getName().equals(_stock[i + 1].getName()))
				equalItems++;
		}

		return equalItems;
	}
	
	/**
	 * this method finds how many item can be transfer into a specific fridge
	 * @param temp of a specific fridge
	 * @return how many items can transfer to that fridge
	 */

	public int howMany(int temp) {
		int numOfItmToTranfer = 0;

		for (int i = 0; i < _noOfItems; i++) {
			if (_stock[i].getMaxTemperature() < temp)
				numOfItmToTranfer += _stock[i].getQuantity();
		}

		return numOfItmToTranfer;
	}
	
	
	/**
	 * this method removes an item from stock after the date
	 * @param d - all the items after that date will be removed
	 */

	public void removeAfterDate(Date d) {

		for (int i = 0; i < _noOfItems;) {
			if (_stock[i].getExpiryDate().before(d)) {
				for (int j = i; j < _noOfItems - 1; j++) {
					_stock[j] = _stock[j + 1];
				}
				_stock[_noOfItems - 1] = null;
				_noOfItems--;
			} else
				i++;
		}
	}
	
	


	@Override
	public String toString() {

		int i = 0;
		if (_noOfItems < 1)
			return "out of stock";

		for (; i < _noOfItems - 1; i++) {
			System.out.println(_stock[i].toString());
		}

		return _stock[i].toString();
	}

	
	/**
	 * this method finds the most expensive item
	 * @return the most expensive item
	 */
	public int mostExpensive() {
		int maxPrice;

		if (_noOfItems > 0)
			maxPrice = _stock[0].getPrice();
		else {
			return 0;
		}

		for (int i = 0; i < _noOfItems; i++) {

			System.out.println(_stock[i].getPrice());
			if (_stock[i].getPrice() > maxPrice)
				maxPrice = _stock[i].getPrice();
		}
		return maxPrice;
	}

	
	/**
	 * this method returns how many items are in stock
	 * @return - the total quantity of the items that are in stock
	 */
	public int howManyPieces() {

		int totalQuantity = 0;
		if (_noOfItems > 0) {
			for (int i = 0; i < _noOfItems; i++) {
				totalQuantity += _stock[i].getQuantity();
			}
			return totalQuantity;
		} else
			return 0;
	}

	
	/**
	 * this method returns how many items are the same kind
	 * @param itemsList - array of string contains a list of items 
	 * @param startIndex - the start index to check from how many are the same
	 * @return - how many items in the string itemlist are the same
	 */
	private int howManyOfSameKind(String[] itemsList, int startIndex) {

		String forPrint = itemsList[startIndex];

		int j = 0;
		int sameItemsSum = 1;

		int indexToOverRide = 0;

		for (int i = startIndex + 1; i < itemsList.length - 1; i++) {
			if (itemsList[startIndex].equals(itemsList[i])) {

				indexToOverRide = startIndex;
				sameItemsSum++;
				itemsList[i] = "1";

			}

		}
		itemsList[startIndex] = "1";
		return sameItemsSum;
	}
	
	/**
	 * this method updates stock
	 * @param itemsList - updating the stock according to the list (array of string)
	 */

	public void updateStock(String[] itemsList) {

		int sameItemsSum = 0;
		for (int j = 0; j < _noOfItems; j++) {

			for (int i = 0; i < itemsList.length; i++) {
				if (_stock[j].getName().equals(itemsList[i])) {
					int startIndex = i;
					sameItemsSum = howManyOfSameKind(itemsList, startIndex);
					_stock[j].setQuantity(_stock[j].getQuantity() - sameItemsSum);
				}
			}

			sameItemsSum = 0;

		}
	}
	
	/**
	 * this method gets the minimum tempeture needed in stock
	 * @return gets the minimum tempeture needed for the stock 
	 */

	public int getTempOfStock() {

		if (_noOfItems == 0)
			return Integer.MAX_VALUE;

		int minTemp = _stock[0].getMinTemperature();

		int maxTemp = _stock[0].getMaxTemperature();

		for (int i = 1; i < _noOfItems; i++) {

			// gets the maximum of all the min tempetures
			if (_stock[i].getMinTemperature() > minTemp)
				minTemp = _stock[i].getMinTemperature();

			// gets the minimum of all the max tempetures
			if (_stock[i].getMaxTemperature() < maxTemp)
				maxTemp = _stock[i].getMaxTemperature();

		}

		if (maxTemp < minTemp)
			return Integer.MAX_VALUE;

		return Math.min(minTemp, maxTemp);

	}

}
