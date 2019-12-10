
public class Date {

	private int int_day;
	private int int_month;
	private int int_year;

	final int jan = 31;
	final int march = 31;
	final int april = 30;
	final int may = 30;
	final int june = 30; //message from mac
	final int july = 31; // message from pc
	final int aug = 31;
	final int sep = 30;
	final int oct = 31;
	final int nov = 30;
	final int dec = 31;

	final int[] arr = { jan, march, april, may, june, july, aug, sep, oct, nov, dec };

	private int check30Or31Days(int[] arr, int month) {

		if (month == arr[0])
			return arr[0];
		if (month == arr[1])
			return arr[1];
		if (month == arr[2])
			return arr[2];
		if (month == arr[3])
			return arr[3];
		if (month == arr[4])
			return arr[4];
		if (month == arr[5])
			return arr[5];
		if (month == arr[6])
			return arr[6];
		if (month == arr[7])
			return arr[7];
		if (month == arr[8])
			return arr[8];
		if (month == arr[9])
			return arr[9];

		return arr[10];

	}

	public Date(int day, int month, int year) {

		if (day > 0 && day < 32)
			int_day = day;

		if (month > 0 && month < 13)
			int_month = month;

		if (year > 0 && (year / 100) < 100)
			int_year = year;

		/* checks for leap year and case the user enters an illegal day for February */
		if (month == 2 && day  >28 && year % 4 == 0 || month == 2 && day > 28 && year % 4 == 1) {
			int_day = 1;
			int_month = 1;
			int_year = 2000;
		}
	}

	public Date(Date other) {
		other.int_day = int_day;
		other.int_month = int_month;
		other.int_year = int_year;

	}

	public int getDay() {

		return int_day;
	}

	public int getMonth() {
		return int_month;
	}

	public int getYear() {
		return int_year;
	}

	public int setDay(int dayToSet) {

		if (dayToSet > 0 && dayToSet < 32) {
			int_day = dayToSet;
			return int_day;
		}

		return int_day;

	}

	public int setMonth(int monthToSet) {

		if (monthToSet > 0 && monthToSet < 13) {
			int_month = monthToSet;
			return int_month;
		}

		return int_month;

	}

	public int setYear(int yearToSet) {

		if (yearToSet > 0 && (yearToSet / 100) < 100) {
			int_year = yearToSet;
			return int_year;
		}

		return int_year;

	}

	public boolean equals(Date other) {
		return (other.int_day == this.int_day && other.int_month == this.int_month && other.int_year == this.int_year);
	}

	/*
	 * Date ExpieryDate = new Date(01, 02, 2020	);
		Date CheckDate = new Date(01, 01, 2020	);
	 */
	public boolean before(Date other) {


/*
System.out.println(this.int_year+"<"+other.int_year+ " || " + this.int_year+"=="+other.int_year+ " && " + 
		this.int_month+ " < " + other.int_month + "|| " + this.int_year + "==" +  other.int_year +" && "+
		this.int_month+ " == " + other.int_month + " && " +this.int_day + "<" + other.int_day );*/
		
		return (this.int_year < other.int_year || ((this.int_year == other.int_year) && (this.int_month < other.int_month))
				|| ((this.int_year == other.int_year) && (this.int_month == other.int_month)
						&& (this.int_day < other.int_day)));

	}

	public boolean after(Date other) {
		return (!before(other));

	}

	public int difference(Date date) {

		return Math.abs(calculateDate(this.int_day, this.int_month, this.int_year)
				- calculateDate(date.int_day, date.int_month, date.int_year));

	}

	private int calculateDate(int day, int month, int year) {
		if (month < 3) {
			year--;
			month = month + 12;
		}
		return 365 * year + year / 4 - year / 100 + year / 400 + ((month + 1) * 306) / 10 + (day - 62);
	}

	@Override
	public String toString() {

		if (this.int_month < 10 && this.int_day < 10)
			return "0" + this.int_day + "/0" + this.int_month + "/" + this.int_year;

		if (this.int_month < 10)
			return this.int_day + "/0" + this.int_month + "/" + this.int_year;
		if (this.int_day < 10)
			return "0" + this.int_day + "/" + this.int_month + "/" + this.int_year;

		return this.int_day + "/" + this.int_month + "/" + this.int_year;
	}

	public Date tommarow() {

		int updatedDay = 0;
		int updatedMonth = 0;
		int updatedYear = 0;

		if (this.int_day == 31) {
			if (check30Or31Days(arr, this.int_month) == 31) {
				if (this.int_month != 12) { // if its not dec add 1 to the current month
					updatedMonth = this.int_month + 1; // update the month
					updatedDay = 1;
					updatedYear = this.int_year;
				} else { // it means that the its the end of the year, in that case i need to update the
							// year
					updatedYear = this.int_year + 1;
					updatedMonth = 1;
					updatedDay = 1;
				}
			} else {
				updatedMonth = this.int_month + 1;
				updatedDay = 1;
				updatedYear = this.int_year;

			}

		} else {
			updatedDay = this.int_day + 1;
			updatedMonth = this.int_month;
			updatedYear = this.int_year;
		}

		// .out.println("" + updatedDay + "."+updatedMonth+ "."+updatedYear);

		Date updatedDate = new Date(updatedDay, updatedMonth, updatedYear);

		return updatedDate;
	}
	
	
	public int dayInWeek() {
	
	     String year  = "" +getYear();
		String first2DigYear = "" + year.charAt(1) + year.charAt(2);
		String last2DigYear = "" + year.charAt(2) + year.charAt(3);
		
		int intF2DYear =  Integer.parseInt(first2DigYear);
		int intL2DYear =  Integer.parseInt(last2DigYear);
	     

		
		
		//return Day = (D + (26×(M+1))/10 + Y + Y/4 + C/4 - 2×C) mod 7 =
		int day = (this.int_day + (26*(this.int_month+1))/10 + intL2DYear + intL2DYear/4 + intF2DYear/4 + 2*intF2DYear)%7;
		return day;
		 
	
		

	}
	
	

	public static void main(String args[]) {

		// every 4 years there is a 29 days in feb ,2008, 2012 , 2016, 2020

		//Date date = new Date(8, 12, 2019	);
		//System.out.p(date.toString());
		//Date date2 = new Date(30, 8, 2015);
	

		//date.dayInWeek();
		//
		//System.out.println(total%100);
		
		//System.out.println(date.dayInWeek());
	}
}
