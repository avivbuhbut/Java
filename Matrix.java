
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

	/*
	 * Problem -
	 * 
	 * returns the adress
	 * 
	 */
	public Matrix(int size1, int size2) {
		arr = new int[size1][size2];

		for (int i = 0; i < arr.length; i++) {

			for (int j = 0; j < arr[i].length; j++) {

				arr[i][j] = 0;

			}

		}

	}

	public String toStirng() {

		int j = 0;

		String retString = "";

		for (int i = 0; i < arr.length;) {

			if (j == arr[i].length) {
				retString += "\n";
				j = 0;
				i++;

			}

			if (i < arr.length)
				retString += arr[i][j] + "\t";

			j++;
		}

		return retString;

	}

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

	public Matrix imageFilterAvarage() {

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
	
	
	public Matrix rotateCounterClockwise() {
		
		
		
		int rotateClockwise[][] = new int[arr[0].length][arr.length];

		int r = 0;
		int c = 0;

		for (int j = 0; j < arr.length; j++) {

			for (int i = arr.length - 1; i <= 0; i--) {

		

			}

		}

		Matrix rotateMatrixClockwise = new Matrix(rotateClockwise);

		return rotateMatrixClockwise;
	}
	
	
	
	

	public static void main(String[] args) {

		int arr[][] = { { 19, 124, 28, 35 }, { 115, 22, 25, 230 }, { 19, 21, 22, 249 }, { 0, 16, 9, 232 },
				{ 62, 35, 10, 116 }, };

		Matrix matrix = new Matrix(arr);

		System.out.println(matrix.rotateCounterClockwise().toStirng());

	}

}
