import com.sun.istack.internal.FragmentContentHandler;
import com.sun.jmx.remote.util.OrderClassLoaders;
import com.sun.org.apache.bcel.internal.generic.IUSHR;
import com.sun.org.apache.xml.internal.security.algorithms.implementations.IntegrityHmac;
import com.sun.xml.internal.bind.v2.runtime.InlineBinaryTransducer;

import jdk.nashorn.internal.ir.annotations.Ignore;

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
			System.out.println("in Same Name. addItem");
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
		for(int i=0; i<_noOfItems;i++) {
			int totalQuantity = 0;
			if (amountOfItem(i)>1) {
				System.out.println("in");
				for(int j=i;j<i+amountOfItem(i);j++) { // iterating range of items of same kind
					System.out.println(_stock[j].getName());
					totalQuantity+=_stock[j].getQuantity();	//adding each items quantity to a total one			
				}
			}else //only one item of this kind
				totalQuantity+=_stock[i].getQuantity();
			
			if(totalQuantity<amount && i!= _noOfItems-1)
				order += _stock[i].getName()+ ",";
			else
				order += _stock[i].getName();
			
			i+=	amountOfItem(i); //increasing i to be after range
			
		}
						
		return order;

		// checking for duplication
		//return getList(amount, order);

	}
	
	/**
	 * returns how many items of the same kind(same name)
	 * @param index
	 * @return
	 */
	private int amountOfItem(int index) {
		
		int equalItems = 0;
		
		for(int i = index; i<_noOfItems-1;i++) {
			if(_stock[i].getName().equals(_stock[i+1].getName()))	
				equalItems++;
		}
		
		
		
		return equalItems;
	}
	
	

	private String getList(int amount, String order) {
		int k = 0;
		int sameProductsQuantity;

		for (int j = 0; j < _noOfItems - 1; j++) {
			sameProductsQuantity = _stock[j].getQuantity();

			if (_stock[j].getName().equals(_stock[j + 1].getName())
					&& (_stock[j].getQuantity() + _stock[j + 1].getQuantity()) < amount) { // if there are a few items
		

				k = j + 1;
				sameProductsQuantity = _stock[k].getQuantity();

				if (_stock[k].getName().equals(_stock[k + 1].getName())) {
					while (k < _noOfItems && _stock[k].getName().equals(_stock[k + 1].getName())) {

						if (_stock[k].getName().equals(_stock[k + 1].getName())
								&& (sameProductsQuantity + _stock[k + 1].getQuantity()) < amount)
							sameProductsQuantity += _stock[k + 1].getQuantity();
						// System.out.println(k);
						k++;

					}

				} else {
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
			}else
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

	public static void main(String[] args) {
		
		Stock stock = new Stock();
		Date productionDate1 = new Date(8, 3, 2001);
		Date expiryDate1 = new Date(8, 6, 2001);

		Date DIFF_productionDate = new Date(8, 5, 2001);
		Date DIFF_expiryDate = new Date(8, 10, 2001);

		FoodItem food1 = new FoodItem("Chreios", 1234, 1, DIFF_productionDate, DIFF_expiryDate, 0, 5, 25);
		//System.out.println(food1.toString());
		FoodItem food2 = new FoodItem("Chreios", 1234, 1, productionDate1, expiryDate1, 0, 20, 50);
	//	System.out.println(food2.toString());
		FoodItem food5 = new FoodItem("Oreo", 1234, 1, productionDate1, expiryDate1, 0, 20, 50);

		FoodItem food3 = new FoodItem("dog", 4321, 1, productionDate1, expiryDate1, 0, 20, 50);
		//System.out.println(food3.toString());
		FoodItem food4 = new FoodItem("cat", 4321, 1, productionDate1, expiryDate1, 0, 20, 900);

		stock.addItem(food1);
		//System.out.println(stock.getNumOfItems());
		//System.out.println(food1.toString());

		stock.addItem(food2);
		//System.out.println(stock.getNumOfItems());
		//System.out.println(food2.toString());
		stock.addItem(food3);
		//System.out.println(stock.getNumOfItems());
		//System.out.println(food3.toString());

		stock.addItem(food4);
		//System.out.println(stock.getNumOfItems());
		stock.addItem(food5);
		//System.out.println(stock.getNumOfItems());
/*
		System.out.println(stock.toString());
		Date TodaysDate = new Date(8, 7, 2002);
		//stock.getList(10);
		System.out.println(stock.toString());
	*/
		
		System.out.println(stock.order(10));
		
		

	}
}
