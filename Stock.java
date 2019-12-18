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
			System.out.println(i);
			// System.out.println("_stock[i] " +_stock[i].getName());
			// System.out.println("_stock[i+1] " +_stock[i+1].getName());
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

		String order = " ";

		for (int i = 0; i < _noOfItems; i++) {
			System.out.println(_stock[i].getQuantity());

			if (i < _noOfItems - 1) {
				int k = 0;
				
				// checking for duplication
				order=  getList(amount,order );

				if (_stock[i].getQuantity() < amount)
					order += _stock[i].getName() + ",";
			} else
				order += _stock[i].getName();

		}

		System.out.println(order);

		return "";

	}
	
	private String getList(int amount , String order) {
		int k =0;
		int sameProductsQuantity = 0;
		int flagFound =0;
		for (int j = 0; j < _noOfItems - 1; j++) {

			if (_stock[j].getName().equals(_stock[j + 1].getName())  && (_stock[j].getQuantity() + _stock[j+1].getQuantity()) < amount ) { // if there are a few items with the same name i
				
			
				
				while (k != _noOfItems - 1) {
					if (_stock[j + 1].getName().equals(_stock[k].getName()) && (_stock[j].getQuantity() + _stock[j+1].getQuantity()) < amount) {
						sameProductsQuantity = _stock[j + 1].getQuantity() + _stock[k].getQuantity();
						flagFound =1;
					}
					k++;
					
					if(k == _noOfItems-1 && flagFound==1)
						order += _stock[j].getName() + ",";
				}
					if(flagFound==0)
						order += _stock[j].getName() + ",";
					
				k = j + 1;
			}
			if (_stock[j].getQuantity() < amount)
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
		// System.out.println(stock.addItem(food1));
		// System.out.println("******\nadding food1");
		stock.addItem(food1);
		stock.addItem(food1);

		// System.out.println("******\nadding food2");
		stock.addItem(food2);
		stock.addItem(food1);
//		System.out.println("after adding : " +_stock[0].toString());
		// System.out.println("_num of items before adding FOOD3:
		// "+stock.getNumOfItems());
		stock.addItem(food3);
		System.out.println(stock.toString());

		stock.order(10);
		// stock.addItem(food2);
		// stock.toString();
		// stock.addItem(food2);
	}
}
