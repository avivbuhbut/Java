
public class Stock {

	FoodItem[] _stock;
	private int _noOfItems;
	final int MAX_ARRAY_lENGTH = 100;

	public Stock() {
		_stock = new FoodItem[MAX_ARRAY_lENGTH];
	}

	public int getNumOfItems() {

		return _noOfItems;
	}

	/**
	 * 
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

	private void moveForward(int index) {

		for (int i = _noOfItems; i > index; i--) {

			_stock[i] = _stock[i - 1];

		}

	}

	private int findIndexByName(String name) {

		for (int i = 0; i < _noOfItems; i++) {

			if (_stock[i].getName().equals(name))
				return i;
		}

		return -1;

	}

	public String order(int amount) {
		// if the product quantity in stock is bigger or equal to the amount to dont add
		// it to the list
		// other wise add it

		String order = "";
		for (int i = 0; i < _noOfItems; i++) {
			int totalQuantity = 0;
			if (amountOfItem(i) > 1) {
				System.out.println("in");
				for (int j = i; j < i + amountOfItem(i); j++) { // iterating range of items of same kind
					System.out.println(_stock[j].getName());
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
	 * returns how many items of the same kind(same name)
	 * 
	 * @param index
	 * @return
	 */
	private int amountOfItem(int index) {

		int equalItems = 0;

		for (int i = index; i < _noOfItems - 1; i++) {
			if (_stock[i].getName().equals(_stock[i + 1].getName()))
				equalItems++;
		}

		return equalItems;
	}

	public int howMany(int temp) {
		int numOfItmToTranfer = 0;

		for (int i = 0; i < _noOfItems; i++) {
			if (_stock[i].getMaxTemperature() < temp)
				numOfItmToTranfer += _stock[i].getQuantity();
		}

		return numOfItmToTranfer;
	}

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
		// TODO Auto-generated method stub
		int i = 0;
		if (_noOfItems < 1)
			return "out of stock";

		for (; i < _noOfItems - 1; i++) {
			System.out.println(_stock[i].toString());
		}

		return _stock[i].toString();
	}

	public int mostExpensive() {
		int maxPrice = 0;

		for (int i = 0; i < _noOfItems; i++) {
			if (_stock[i].getPrice() > maxPrice)
				maxPrice = _stock[i].getPrice();
		}
		return maxPrice;
	}

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
	
	// need to retrun how many are from the same kins in the string array
	private int howManyOfSameKind(String[] itemsList, int startIndex) {

		String forPrint = itemsList[startIndex];


		int j = 0;
		int sameItemsSum = 1;

		/* printing the string array ***************delete after debug **************/
		
		for (int i = 0; i < itemsList.length; i++) {
			if (i != itemsList.length - 1)
				System.out.print(itemsList[i] + ",");
			else
				System.out.print(itemsList[i]);

		}
		System.out.println();

		int indexToOverRide = 0;

		for (int i = startIndex+1; i < itemsList.length - 1;i++) {
			if (itemsList[startIndex].equals(itemsList[i ])) {
	
				System.out.println(startIndex + " ==  " + i);
				indexToOverRide = startIndex;
				sameItemsSum++;
				itemsList[i] = "1";
			
			}
			
		}
		itemsList[startIndex] = "1";
		return sameItemsSum;
	}
	
	public void updateStock(String[] itemsList) {

		// itretite over the array and find how many do i need to reduce from each item

		int sameItemsSum = 0;
		for (int j = 0; j < _noOfItems; j++) {

			for (int i = 0; i < itemsList.length; i++) {
				if (_stock[j].getName().equals(itemsList[i])) {
					int startIndex = i;
					sameItemsSum += howManyOfSameKind(itemsList, startIndex);
				}
			}
			System.out.println("you have to remove  " + sameItemsSum + " " + _stock[j].getName());
			sameItemsSum = 0;

			System.out.println(sameItemsSum);

			/* printing the string array */
			for (int i = 0; i < itemsList.length; i++) {
				if (i != itemsList.length - 1)
					System.out.print(itemsList[i] + ",");
				else
					System.out.print(itemsList[i]);

			}
			System.out.println();
		}
	}

	
	/*get the min max 
	 * and get the max min with 2 for loops
	 * 
	 * if the products are not in the range of those two numbers 
	 * return the maxInteger
	 * */
	public int getTempOfStock() {
		int minTemp = 0;
		for (int i = 0; i < _noOfItems; i++) {
			// if()
		}

		return 0;
	
}

	public static void main(String[] args) {

		Stock stock = new Stock();
		Date productionDate1 = new Date(8, 3, 2001);
		Date expiryDate1 = new Date(8, 6, 2001);

		Date DIFF_productionDate = new Date(8, 5, 2001);
		Date DIFF_expiryDate = new Date(8, 10, 2001);

		FoodItem food1 = new FoodItem("Chreios", 1234, 4, DIFF_productionDate, DIFF_expiryDate, 0, 5, 25);

		FoodItem food2 = new FoodItem("Chreios", 1234, 4, productionDate1, expiryDate1, 0, 20, 50);

		FoodItem food5 = new FoodItem("Oreo", 1234, 5, productionDate1, expiryDate1, 0, 20, 50);

		FoodItem food3 = new FoodItem("dog", 4321, 1, productionDate1, expiryDate1, 0, 20, 50);

		FoodItem food4 = new FoodItem("cat", 4321, 1, productionDate1, expiryDate1, 0, 20, 900);

		stock.addItem(food1);

		stock.addItem(food2);

		stock.addItem(food3);

		stock.addItem(food4);

		stock.addItem(food5);

		String itemsList[] = { "Chreios", "dog", "dog", "Chreios", "Oreo", "dog", "cat", "cat" };
		stock.updateStock(itemsList);

	}
}
