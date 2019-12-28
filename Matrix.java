

/**
 * 
 * @author Aviv buhbut
 * 
 * I.D: 204445084
 *
 */



/**
 * 
 * matrix contractor
 *
 */
public class Matrix {

	int[][] arr;

	public Matrix(int[][] array) {

		arr = new int[array.length][array[0].length];

		for (int i = 0; i < array.length; i++) {

			for (int j = 0; j < array[i].length; j++) {

				this.arr[i][j] = array[i][j];

			}

		}
	}


	
	/**
	 * this method constracts a matrix of zeros 
	 * @param size1 - size of the 
	 * @param size2
	 */
	public Matrix(int size1, int size2) {
		
	this.arr= new int [size1][size2] ;
	
	

		for (int i = 0; i < this.arr.length; i++) {

			for (int j = 0; j < this.arr[i].length; j++) {

				this.arr[i][j] = 0;
			

			}

		}
		
		

	}



/**
 * this method creates the negative matrix of the current matrix
 * @return a negative matrix
 */
	public Matrix makeNegative() {

		int negativeArr[][] = new int[this.arr.length][this.arr[0].length];

		for (int i = 0; i < arr.length; i++) {

			for (int j = 0; j < arr[i].length; j++) {

				negativeArr[i][j] = Math.abs(arr[i][j] - 255);

			}

		}

		Matrix negativeMatrix = new Matrix(negativeArr);

		return negativeMatrix;

	}

	/**
	 * this method creates avarages of all the near cells of a current cell in the matrix
	 * @return a matrix that is after a filter of avarage
	 */
	public Matrix imageFilterAverage() {

		int imageFilterAvarageArr[][] = new int[this.arr.length][this.arr[0].length];

		for (int i = 0; i < arr.length; i++) {

			for (int j = 0; j < arr[i].length; j++) {

				/************* if has 3 neigbores ******************/

				/* if has 3 neigbores (Front) */
				if (j == 0 && i == 0) // if its the first index
					imageFilterAvarageArr[i][j] = (arr[i][j] + arr[i][j + 1] + arr[i + 1][j + 1] + arr[i + 1][j]) / 4;
				else if (i == arr.length - 1 && j == 0) // if its the first index of the last calum (Front)
					imageFilterAvarageArr[i][j] = (arr[i][j] + arr[i - 1][j] + arr[i - 1][j + 1] + arr[i][j + 1]) / 4;
				/* if has 3 neigbores (Back) */
				else if (j == arr[0].length - 1 && i == 0) // first row last cal (Back)
					imageFilterAvarageArr[i][j] = (arr[i][j] + arr[i][j - 1] + arr[i + 1][j - 1] + arr[i + 1][j]) / 4;
				else if (i == arr.length - 1 && j == arr.length - 1) // last row last cal (Back)
					imageFilterAvarageArr[i][j] = (arr[i][j] + arr[i - 1][j] + arr[i - 1][j - 1] + arr[i][j - 1]) / 4;
				/* if has 5 neigbores (Front) */
				else if (i > 0 && i < arr.length - 1 && j < 1) {
					imageFilterAvarageArr[i][j] = (arr[i][j] + arr[i - 1][j] + arr[i - 1][j + 1] + arr[i][j + 1]
							+ arr[i + 1][j + 1] + arr[i + 1][j]) / 6;
					/* if has 5 neigbores (Back) */
				} else if (i > 0 && i < arr.length - 1 && j == arr.length - 1) {
					imageFilterAvarageArr[i][j] = (arr[i][j] + arr[i - 1][j] + arr[i - 1][j - 1] + arr[i][j - 1]
							+ arr[i + 1][j - 1] + arr[i + 1][j]) / 6;
				}
				/* if has 5 neigbores (Up) */
				else if (i == 0 && j > 0 && j < arr.length - 1) {
					imageFilterAvarageArr[i][j] = (arr[i][j] + arr[i][j - 1] + arr[i + 1][j - 1] + arr[i + 1][j]
							+ arr[i + 1][j + 1] + arr[i][j + 1]) / 6;
				} /* if has 5 neigbores (Down) */
				else if (i == arr.length - 1 && j > 0 && j < arr.length - 1) {
					imageFilterAvarageArr[i][j] = (arr[i][j] + arr[i][j - 1] + arr[i - 1][j - 1] + arr[i - 1][j]
							+ arr[i - 1][j + 1] + arr[i][j + 1]) / 6;
				}
				/* if has 8 neigbores (all of the middle of the matrix without the boarders ) */
				else if (i > 0 && i < arr.length - 1 && j > 0 && j < arr.length - 1) {
					imageFilterAvarageArr[i][j] = (arr[i][j] + arr[i - 1][j] + arr[i - 1][j + 1] + arr[i][j + 1]
							+ arr[i + 1][j + 1] + arr[i + 1][j] + arr[i + 1][j - 1] + arr[i][j - 1] + arr[i - 1][j - 1])
							/ 9;
				}

			}

		}

		Matrix filterMatrix = new Matrix(imageFilterAvarageArr);

		// System.out.println(filterMatrix.toStirng());

		return filterMatrix;

	}

	
	/**
	 * this method rotates the matrix clock wise
	 * @return the matrix rotated clock wise
	 */
	public Matrix rotateClockwise() {

		int rotateClockwise[][] = new int[arr[0].length][arr.length];

		int r = 0;
		int c = 0;

		for (int j = 0; j < arr.length; j++) {

			for (int i = arr.length - 1; i >= 0; i--) {

				if (c == rotateClockwise[r].length) {

					r++;
					c = 0;
				}

				if (!(r == rotateClockwise.length)) {

					rotateClockwise[r][c] = arr[i][j];
				} else
					break;

				c++;

			}

		}

		Matrix rotateMatrixClockwise = new Matrix(rotateClockwise);

		return rotateMatrixClockwise;
	}
	
	/**
	 * this method rotates the matrix counter clock wise
	 * @return the matrix rotated counter clock wise
	 */
	public Matrix rotateCounterClockwise() {
		
	
		

			int totalRowsOfRotatedMatrix = arr[0].length; // Total columns of Original Matrix
			int totalColsOfRotatedMatrix = arr.length; // Total rows of Original Matrix

			int[][] rotatedMatrix = new int[totalRowsOfRotatedMatrix][totalColsOfRotatedMatrix];

			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[0].length; j++) {
					rotatedMatrix[(totalRowsOfRotatedMatrix - 1) - j][i] = arr[i][j];
				}
			}
			
			Matrix counterClockMatrix = new Matrix(rotatedMatrix);
			return counterClockMatrix;
		}
		

	
     @Override
     public String toString()
     {
    	 int  j=0;
    	 String retString = "";

 		for (int i = 0; i < arr.length;) {

 			if (j == arr[i].length) {
 				retString += "\n";
 				j = 0;
 				i++;

 			}
 			if ( i < arr.length)
				retString += arr[i][j] + "\t";
 		
			j++;
		}

 			
           return retString;
     }
     
     
    

}
