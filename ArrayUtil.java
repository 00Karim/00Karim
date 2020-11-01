package arrayApp1;

class ArrayUtil {

	/**
	 * Prints out a given integer array in a formatted manner.
	 * 
	 * @param array
	 */
	public static void printIntegerArray(int[] array) {
		for (int i : array) {
			System.out.print(i + ", ");
		}
		System.out.println();
	}

	/**
	 * Removes a specified number from a given integer array. It achieves this by
	 * creating a new array of length one smaller than the given array and copies
	 * over all the values except the one being removed.
	 * 
	 * @param intArray  The array being modified.
	 * @param removeNum The number being removed from the given array.
	 * @return The modified array.
	 */
	public static int[] removeNumberFromArray(int[] intArray, int removeNum) {
		int[] modifiedArray;
		int count;

		modifiedArray = new int[intArray.length - 1];
		count = 0;

		for (int i = 0; i < intArray.length; ++i) {
			if (intArray[i] != removeNum) {
				modifiedArray[count] = intArray[i];
				count++;
			}
		}
		return modifiedArray;
	}

	/**
	 * Adds a specified amount of given numbers to the existing array. It achieves
	 * this by asking the user for two pieces of information: the amount of numbers
	 * being added and the value of each number being added. It then creates a new
	 * array that is the length of the old array plus the amount of numbers being
	 * added; then it copies all the existing values over to the new array alongside
	 * the values being added.
	 * 
	 * @param array      The array being modified.
	 * @param amntAdded  The amount of numbers being added to the given array.
	 * @param numbsToAdd The values of the numbers to be added to the existing
	 *                   array.
	 * @return The modified array.
	 */
	public static int[] addNumberToArray(int[] array, int amntAdded, int[] numbsToAdd) {
		int[] modifiedArray;
		int count;

		modifiedArray = new int[array.length + amntAdded];
		count = 0;

		for (int i = 0; i < array.length; ++i) {
			modifiedArray[i] = array[i];
		}
		for (int i = array.length; i < array.length + amntAdded; ++i) {
			modifiedArray[i] = numbsToAdd[count++];
			// Removed count++ from this line and passed it as index pos for numbsToAdd
			// array
		}
		return modifiedArray;
	}

	public static int[] mergeSort(int[] array) {

		// base case
		if (array.length <= 1) {
			return array;
		}

		int midpoint = array.length / 2;

		int[] left = new int[midpoint];
		int[] right;

		if (array.length % 2 == 0) {
			right = new int[midpoint];
		} else {
			right = new int[midpoint + 1];
		}

		for (int i = 0; i < left.length; i++) {
			left[i] = array[i];
		}

		for (int j = 0; j < right.length; j++) {
			right[j] = array[midpoint + j];
		}

		left = mergeSort(left);
		right = mergeSort(right);

		int[] result = merge(left, right);

		return result;
	}

	private static int[] merge(int[] left, int[] right) {

		int[] result = new int[left.length + right.length];

		int leftPointer, rightPointer, resultPointer;
		leftPointer = rightPointer = resultPointer = 0;

		// while either the left or right has stuff
		while (leftPointer < left.length || rightPointer < right.length) {

			// while they both have stuff
			while (leftPointer < left.length && rightPointer < right.length) {
				if (left[leftPointer] < right[rightPointer]) {
					result[resultPointer++] = left[leftPointer++];
				} else {
					result[resultPointer++] = right[rightPointer++];
				}
			}

			// adds on leftovers from left array if it didn't finish
			if (leftPointer < left.length) {
				result[resultPointer++] = left[leftPointer++];
			}

			// adds on leftovers from right array if it didn't finish
			if (rightPointer < right.length) {
				result[resultPointer++] = right[rightPointer++];
			}

		}

		return result;
	}

}
