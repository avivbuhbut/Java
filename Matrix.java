
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

		System.out.println(arr.toString());

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

				if (arr[i][j] == 0)
					negativeArr[i][j] = 255;

				if (arr[i][j] == 255)
					negativeArr[i][j] = 0;
				else
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
					imageFilterAvarageArr[i][j] = (arr[i][j ]+arr[i][j + 1] + arr[i + 1][j + 1] + arr[i + 1][j]) / 4;
				else if (i == arr.length - 1 && j == 0) // if its the first index of the last calum (Front)
					imageFilterAvarageArr[i][j] = (arr[i][j ]+arr[i - 1][j] + arr[i - 1][j + 1] + arr[i][j + 1]) / 4;
				/* if has 3 neigbores (Back) */
				else if (j == arr[0].length - 1 && i == 0) // first row last cal (Back)
					imageFilterAvarageArr[i][j] = (arr[i][j ]+arr[i][j - 1] + arr[i + 1][j - 1] + arr[i + 1][j]) / 4;
				else if (i == arr.length - 1 && j == arr.length - 1) // last row last cal (Back)
					imageFilterAvarageArr[i][j] = (arr[i][j ]+arr[i - 1][j] + arr[i - 1][j - 1] + arr[i][j - 1]) / 4;
				/* if has 5 neigbores (Front) */
				else if (i > 0 && i < arr.length - 1 &&j<2) {
					imageFilterAvarageArr[i][j] = (arr[i][j ]+arr[i - 1][j] + arr[i - 1][j + 1] + arr[i][j + 1] + arr[i + 1][j + 1]
							+ arr[i + 1][j]) / 6;
				}

				/*
				 * if (arr[i][j] == 0) imageFilterAvarageArr[i][j] = 255;
				 * 
				 * if (arr[i][j] == 255) imageFilterAvarageArr[i][j] = 0; else
				 * imageFilterAvarageArr[i][j] = Math.abs(arr[i][j] - 255);
				 */

			}

		}

		Matrix filterMatrix = new Matrix(imageFilterAvarageArr);

		System.out.println(filterMatrix.toStirng());

		return filterMatrix;

	}

	public static void main(String[] args) {

		int arr[][] = { { 19, 124, 28, 35, 38 },
				{ 115, 22, 25, 230, 31 },
				{ 9, 21, 22, 249, 230 },
				{ 0, 6, 9, 232, 255 }, { 2, 5, 10, 116, 129 }, };

		Matrix matrix = new Matrix(arr);

		// Matrix matrix2 = new Matrix(arr[0].length, arr.length);

		// System.out.println(matrix.toStirng());
		matrix.imageFilterAvarage();

	}

}
