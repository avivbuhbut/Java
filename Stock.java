import com.sun.jmx.remote.util.OrderClassLoaders;
import com.sun.org.apache.bcel.internal.generic.IUSHR;

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
		// System.out.println("in checkIfItem: i: "+i );
		// System.out.println(_stock[i].getName());
		if (_stock[i] == null)
			System.out.println("_stock[i] == nul!!");
		if (_stock[i].getName().equals(newitem.getName())
				&& _stock[i].getCatalogueNumber() == newitem.getCatalogueNumber()
				&& _stock[i].getExpiryDate().equals(newitem.getExpiryDate())
				&& _stock[i].getProductionDate().equals(newitem.getProductionDate()))
			return true;

		else
			return false;

	}

	public boolean addItem(FoodItem newitem) {
		// ystem.out.println("getNum: " +getNumOfItems());

		int k = 0;

		for (int i = 0; i < _noOfItems; i++) {
			// System.out.println("in add Item: i: "+i);

			if (checkIfItemExist(newitem, i)) {
				// System.out.println("in addItem: item "+_stock[i].getName() +" exist");
				// System.out.println(_stock[i].getName());
				_stock[i].setQuantity(_stock[i].getQuantity() + newitem.getQuantity());

				// System.out.println("in add item: " + _stock[i].toString());
				return true;
			}

		}

		if (_noOfItems == _stock.length) {
			// System.out.println("in addItem: no more space. not added");
			return false;
		}

		while (_stock[k] != null) {

			k++;
		}

		if (findIndexByName(newitem.getName()) == -1) {
			// System.out.println("in addItem: index not exist. new item added");
			_stock[k] = new FoodItem(newitem);
			_noOfItems++;
		} else {
			// System.out.println("in addItem: name exist. new item added before");
			int index = findIndexByName(newitem.getName());
			this.moveForward(index);
			_stock[index] = newitem;
			// System.out.println("index: "+index);
			// System.out.println("_stock[index] " +_stock[index].getName());
			// System.out.println("_stock[index+1] " +_stock[index+1].getName());
			k = index;
			_noOfItems++;
		}

		// System.out.println("in addItem: item added: "+_stock[k].toString());

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

		// checking for duplication
		return getList(amount, order);

	}

	private String getList(int amount, String order) {
		int k = 0;
		int sameProductsQuantity;

		for (int j = 0; j < _noOfItems - 1; j++) {
			sameProductsQuantity = _stock[j].getQuantity();

			if (_stock[j].getName().equals(_stock[j + 1].getName())
					&& (_stock[j].getQuantity() + _stock[j + 1].getQuantity()) < amount) { // if there are a few items
				// with the same name i

				k = j + 1;
				// sameProductsQuantity = _stock[j].getQuantity() + _stock[k].getQuantity() ;
				sameProductsQuantity = _stock[k].getQuantity();

				if (_stock[k].getName().equals(_stock[k + 1].getName())) {
					while (k < _noOfItems && _stock[k].getName().equals(_stock[k + 1].getName())) {

						if (_stock[k].getName().equals(_stock[k + 1].getName())
								&& (sameProductsQuantity + _stock[k + 1].getQuantity()) < amount)
							sameProductsQuantity += _stock[k + 1].getQuantity();
						// System.out.println(k);
						k++;

					}

				}
				else {
				// if (_stock[j].getQuantity() < amount)
				if (sameProductsQuantity < amount)
					order += _stock[k].getName() + ",";
				}

			}

			
			if (sameProductsQuantity < amount)
				order += _stock[j].getName() + ",";

		}
		return order;
	}

	/*
	 * if non of the above happend than the for loope ended - it means that there
	 * isnt an item line the parameter newitem in the array and that there was no
	 * free space in the array to put the newitem than the method additem didnt work
	 * and it will return false
	 */
	/*
	 * private boolean checkIfItemExist(FoodItem newitem) {
	 * 
	 * int k = 0; if (_stock.length > 1) { for (int i = 0; i < _stock.length; i++) {
	 * System.out.println(i);
	 * 
	 * if (checkProductsValues(newitem, i) == 1) {
	 * _stock[i].setQuantity(_stock[i].getQuantity() + newitem.getQuantity());
	 * return true; } else
	 * 
	 * while (_stock[k] != null) {
	 * 
	 * if (_stock[k].productionDate.before(newitem.productionDate))
	 * 
	 * k++; } _stock[k] = newitem;
	 * 
	 * return true;
	 * 
	 * }
	 * 
	 * } return false; }
	 */
	
	
	public 	int howMany(int temp) {
		int numOfItmToTranfer = 0;
		
		for(int i =0 ; i < _noOfItems; i++) {
			if(_stock[i].getMaxTemperature() < temp)
				numOfItmToTranfer += _stock[i].getQuantity();
		}
		
		return numOfItmToTranfer;
	}
	
	
	
	
	
	
	public void removeAfterDate (Date d) {
		
		for(int i=0; i<_noOfItems; i++) {
			if(_stock[i].getExpiryDate().before(d))
					for(int j=i; j<_noOfItems-1 ; j++) {
						System.out.println(_stock[j].getName()+ "is going to be override by:" + _stock[j+1].getName());
						_stock[j] = _stock[j+1];
						System.out.println(j);
					//	_stock[j] = null;
					}
		}
		
	}
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		int i = 0;
		for (; i < _noOfItems - 1; i++) {
			System.out.println(_stock[i].toString());
		}

		return _stock[i].toString();
	}
	
	
	

	public static void main(String[] args) {
		Stock stock = new Stock();
		Date productionDate1 = new Date(8, 3, 2001);
		Date expiryDate1 = new Date(8, 6, 2001);

		Date DIFF_productionDate = new Date(8, 9, 2001);
		Date DIFF_expiryDate = new Date(8, 10, 2001);

		FoodItem food1 = new FoodItem("Chreios", 1234, 1, productionDate1, expiryDate1, 0, 5, 25);
		FoodItem food2 = new FoodItem("Chreios", 1234, 1, DIFF_productionDate, DIFF_expiryDate, 0, 20, 50);
		// System.out.println(food1.toString());
		FoodItem food3 = new FoodItem("dog", 4321, 1, DIFF_productionDate, DIFF_expiryDate, 0, 20, 50);
		FoodItem food4 = new FoodItem("cat", 4321, 1, DIFF_productionDate, DIFF_expiryDate, 0, 20, 50);
		// System.out.println(stock.addItem(food1));
		// System.out.println("******\nadding food1");
		stock.addItem(food1);

		// System.out.println("******\nadding food2");
		stock.addItem(food2);
		stock.addItem(food3);

		stock.addItem(food4);
		System.out.println(stock.toString());
	
System.out.println("how many: " + stock.howMany(30));
		System.out.println(stock.order(10));
		
		Date TodaysDate = new Date(8, 10, 2002);
		stock.removeAfterDate(TodaysDate);
		
		System.out.println(stock.toString());
		
		
		
		

	}
}
