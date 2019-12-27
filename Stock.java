
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

	public void updateStock(String[] itemsList) {

		int sameItemsSum = 0;
		for (int j = 0; j < _noOfItems; j++) {

			for (int i = 0; i < itemsList.length; i++) {
				if (_stock[j].getName().equals(itemsList[i])) {
					int startIndex = i;
					sameItemsSum += howManyOfSameKind(itemsList, startIndex);
				}
			}

			sameItemsSum = 0;

		}
	}

	public int getTempOfStock() {

		if (_noOfItems == 0)
			return Integer.MAX_VALUE;

		int flag = 0;

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

	public static void main(String[] args) {

		Stock stock = new Stock();
		Date productionDate1 = new Date(8, 3, 2001);
		Date expiryDate1 = new Date(8, 6, 2001);

		Date DIFF_productionDate = new Date(8, 5, 2001);
		Date DIFF_expiryDate = new Date(8, 10, 2001);

		FoodItem food1 = new FoodItem("Chreios", 1234, 4, DIFF_productionDate, DIFF_expiryDate, 6, 11, 25);

		FoodItem food2 = new FoodItem("Chreios", 1234, 4, productionDate1, expiryDate1, 7, 10, 50);

		FoodItem food5 = new FoodItem("Oreo", 1234, 5, productionDate1, expiryDate1, 9, 13, 50);

		FoodItem food3 = new FoodItem("dog", 4321, 1, productionDate1, expiryDate1, 14, 17, 50);

		// FoodItem food4 = new FoodItem("cat", 4321, 1, productionDate1, expiryDate1,
		// 0, 20, 900);

		stock.addItem(food1);

		stock.addItem(food2);

		stock.addItem(food3);

		// stock.addItem(food4);

		stock.addItem(food5);

		String itemsList[] = { "Chreios", "dog", "dog", "Chreios", "Oreo", "dog", "cat", "cat" };
		stock.updateStock(itemsList);

		System.out.println(stock.getTempOfStock());

	}
}
