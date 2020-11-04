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
	 * Removes a specified amount of numbers from a given integer array. It achieves
	 * this by creating a new integer array that has a length of the original array
	 * minus the amount of numbers being removed. It then copies over all of the non
	 * removed values to the new array.
	 * 
	 * @param intArray       The array being modified.
	 * @param valuesToRemove The array of values being removed.
	 * @return The modified array.
	 */
	public static int[] removeValuesFromArray(int[] array, int[] valuesToRemove) {
		int[] modifiedArray = new int[array.length - valuesToRemove.length];

		// if a value of the original array exists within the array of values to remove,
		// it is not copied over to the new modified array.
		int modifiedArrayPointer = 0;
		boolean removed = false;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < valuesToRemove.length; j++) {
				if (array[i] == valuesToRemove[j]) {
					removed = true;
					valuesToRemove[j] = -1;
					// once it finds one that's the same, break, becuase there might be duplicate
					// values that it will end up overwriting when we only want it to overwrite one
					// value at a time
					break;
				}
			}
			if (!removed) {
				modifiedArray[modifiedArrayPointer++] = array[i];
			}
			removed = false;
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
	public static int[] addValuesToArray(int[] array, int[] valuesToAdd) {
		int[] modifiedArray = new int[array.length + valuesToAdd.length];

		for (int i = 0; i < array.length; ++i) {
			modifiedArray[i] = array[i];
		}

		for (int j = array.length, k = 0; k < valuesToAdd.length; j++, k++) {
			modifiedArray[j] = valuesToAdd[k];
		}
		return modifiedArray;
	}

	/**
	 * Client side method with only one parameter
	 * 
	 * @param array The array being sorted
	 */
	public static void mergeSort(int[] array) {
		mergeSort(array, 0, array.length - 1);
	}

	/**
	 * Splits the given array into two pieces; the left and right, and merges them
	 * together in sorted order in the merge method
	 * 
	 * @param array The array being sorted
	 * @param low   The starting index of the array or sub array
	 * @param high  The ending index of the array or sub array
	 */
	private static void mergeSort(int[] array, int low, int high) {
		if (low < high) {
			int middle = low + ((high - low) / 2);

			mergeSort(array, low, middle);
			mergeSort(array, middle + 1, high);

			merge(array, low, middle, high);
		}
	}

	/**
	 * Merges together two sorted arrays
	 * 
	 * @param array  The array being sorted
	 * @param low    The starting index position of the left array
	 * @param middle The ending index position of the left array
	 * @param high   The ending index position of the right array
	 */
	private static void merge(int[] array, int low, int middle, int high) {
		// helper array
		int[] temp = new int[array.length];

		// copy both the left and right array into this helper array
		for (int i = 0; i < array.length; i++) {
			temp[i] = array[i];
		}

		// pointer to the left array who's start index is low
		int leftPointer = low;
		// pointer to right array who's start index is middle plus 1. (this is specified
		// in the mergeSort method, where the right array is formed under the assumption
		// that its start index is middle + 1)
		int rightPointer = middle + 1;
		// pointer to original array as we sort it
		int sortedPointer = low;

		// while either the left or right sub array has stuff leftover
		while (leftPointer <= middle || rightPointer <= high) {
			// while they both have stuff leftover
			while (leftPointer <= middle && rightPointer <= high) {
				if (temp[leftPointer] < temp[rightPointer]) {
					array[sortedPointer++] = temp[leftPointer++];
				} else {
					array[sortedPointer++] = temp[rightPointer++];
				}
			}

			// adds on leftover values from the left array into the original array if there
			// are any
			if (leftPointer <= middle) {
				array[sortedPointer++] = temp[leftPointer++];
			}

			// adds on leftover values from the right array into the original array if there
			// are any
			if (rightPointer <= high) {
				array[sortedPointer++] = temp[rightPointer++];
			}
		}
	}

}
