package arrayApp1;

import java.util.Random;
import java.util.Scanner;

class MainArrayApp {

	private Scanner scanner;
	private int[] intArray;
	private int[] originalArray;
	private Random random;
	private int endRecursion;

	/**
	 * Creates an integer array and calls the initial prompt method.
	 */
	public MainArrayApp() {
		endRecursion = 0;
		scanner = new Scanner(System.in);
		random = new Random();
		// new integer array of random size 5 - 15;
		intArray = new int[(random.nextInt(21) / 2) + 5];

//		// fills the intArray with non duplicate random values from 0 to 150
//		intArray[0] = random.nextInt(151);
//		int count = 1;
//		boolean duplicate = false;
//		while (count < intArray.length) {
//			int temp = random.nextInt(151);
//			for (int i = 0; i < count; i++) {
//				if (intArray[i] == temp) {
//					duplicate = true;
//				}
//			}
//			if (!duplicate) {
//				intArray[count++] = temp;
//			}
//			duplicate = false;
//		}

		// fills the intArray with possible duplicate random values from 0 to 150
		for (int i = 0; i < intArray.length; i++) {
			intArray[i] = random.nextInt(151);
		}

		resetOriginalArray();

		initialPrompt();
	}

	/**
	 * A method to hold the bulk of the messages displayed to the user. This method
	 * exists to declutter the constructor, main method, and other method calls.
	 */
	private void initialPrompt() {
		if (endRecursion == 1) {
			return;
		}

		System.out.println();
		System.out.println("___________________________________________________");
		System.out.println("Here in an array of numbers: ");
		ArrayUtil.printIntegerArray(intArray);
		System.out.println();
		System.out.println("Would you like to modify it?");
		System.out.print("Enter your response (Yes or No): ");

		String responseStr = scanner.next();
		switch (responseStr.toLowerCase()) {
		case "yes":
			System.out.println();
			System.out.println("What would you like to do?");
			System.out.println("1. Remove numbers from the Array");
			System.out.println("2. Add numbers to the Array");
			System.out.println("3. Sort the array (merge sort)");
			System.out.println("4. End the program.");
			System.out.print("Enter you response: ");

			int response = requestInt();

			System.out.println();

			if (response == 1) {
				removeValues();
			} else if (response == 2) {
				addValues();
			} else if (response == 3) {
				sortProgram();
			} else if (response == 4) {
				System.out.println("End of program.");
				endRecursion = 1;
			}
			break;
		case "no":
			System.out.println();
			System.out.println("Okay. End of program.");
			endRecursion = 1;
			break;
		default:
			System.out.println();
			System.out.println("That response is invalid. Please try again.");
			System.out.println();
			break;
		}

		initialPrompt();
	}

	/**
	 * Allows the user to delete a specified number of values from the intArray
	 * field.
	 */
	private void removeValues() {
		if (intArray.length == 0) {
			System.out.println("This array is empty.  Nothing to remove.");
			return;
		}

		System.out.print("Enter the amount of numbers you would like to remove: ");

		int temp = -1;
		boolean invalidAmount = true;
		while (invalidAmount) {
			temp = requestInt();
			if (temp > intArray.length) {
				System.out.println("There aren't that many numbers in the array to remove." + "\n" + "Try Again: ");
				continue;
			}
			invalidAmount = false;
		}

		int amountToRemove = temp;

		int[] valuesToRemove = new int[amountToRemove];

		System.out.println();
		System.out.println("Enter the values you would like to remove: ");

		int[] tempArray = new int[intArray.length];
		for (int i = 0; i < tempArray.length; i++) {
			tempArray[i] = intArray[i];
		}

		// Ensures values inputed exist within the array
		int value;
		int count = 0;
		boolean valNotFound = true;
		boolean alreadyRemovedAll = false;
		while (count < amountToRemove) {
			value = requestInt();
			for (int i = 0; i < tempArray.length; i++) {
				if (value == tempArray[i]) {
					valuesToRemove[count++] = value;
					tempArray[i] = -1;
					valNotFound = false;
					// once the value is found in the array, break, because otherwise it would
					// overwrite all duplicate values in the array and fill the valuesToRemove array
					// with false values
					break;
				}
			}
			if (valNotFound) {
				for (int i : intArray) {
					if (value == i) {
						alreadyRemovedAll = true;
						break;
					}
				}
				if (alreadyRemovedAll) {
					System.out.print("You've already removed all instances of " + value + " from the array." + "\n"
							+ " Please enter a new value:");
				} else {
					System.out.print("That value does not exist in the array." + "\n" + " Please enter a new value: ");
				}
			}
			valNotFound = true;
			alreadyRemovedAll = false;
		}

		// copies values to new int array to print them once finished, as valuesToRemove
		// array is modified in the removeValuesFromArray method.
		int[] valuesRemoved = new int[valuesToRemove.length];
		for (int i = 0; i < valuesRemoved.length; i++) {
			valuesRemoved[i] = valuesToRemove[i];
		}

		intArray = ArrayUtil.removeValuesFromArray(intArray, valuesToRemove);

		System.out.println();
		System.out.println("Original array: ");
		ArrayUtil.printIntegerArray(originalArray);
		System.out.println("\nHere is the new array: ");
		ArrayUtil.printIntegerArray(intArray);
		System.out.print("You removed the number(s): ");
		ArrayUtil.printIntegerArray(valuesRemoved);

		resetOriginalArray();
	}

	/**
	 * Allows the user to add a specified amount of values to the intArray field.
	 */
	private void addValues() {
		System.out.print("Enter the amount of numbers you would like to add: ");

		int amountToAdd = requestInt();

		int[] valuesToAdd = new int[amountToAdd];

		System.out.println();
		System.out.println("Enter the numbers you would like to add: ");

		int count = 0;
		while (count < amountToAdd) {
			valuesToAdd[count++] = requestInt();
		}

		intArray = ArrayUtil.addValuesToArray(intArray, valuesToAdd);

		System.out.println();
		System.out.println("Original array: ");
		ArrayUtil.printIntegerArray(originalArray);
		System.out.println("\nHere is the new array: ");
		ArrayUtil.printIntegerArray(intArray);
		System.out.print("You added the number(s): ");
		ArrayUtil.printIntegerArray(valuesToAdd);

		resetOriginalArray();
	}

	private void sortProgram() {
		System.out.println("Initial array: ");
		ArrayUtil.printIntegerArray(intArray);

		// intArray = ArrayUtil.mergeSort(intArray);
		ArrayUtil.mergeSort(intArray);
		System.out.println();
		System.out.println("Sorted Array: ");
		ArrayUtil.printIntegerArray(intArray);

		resetOriginalArray();
	}

	/**
	 * Helper method that requests a value from the user until an integer is
	 * entered.
	 * 
	 * @return The integer value entered by the user.
	 */
	private int requestInt() {
		int value = -1;

		boolean illegalValue = true;
		while (illegalValue) {
			if (scanner.hasNextInt()) {
				value = scanner.nextInt();
			} else {
				scanner.next();
				System.out.println("Error; please enter numbers only." + "\n" + "Try again:");
				continue;
			}
			illegalValue = false;
		}
		return value;
	}

	/**
	 * Helper method that makes the originalArray field equal to the intArray field,
	 * as the next iteration of code will alter the intArray and the old values of
	 * intArray will become the new originalArray.
	 */
	private void resetOriginalArray() {
		int[] temp = new int[intArray.length];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = intArray[i];
		}

		originalArray = temp;
	}

}
