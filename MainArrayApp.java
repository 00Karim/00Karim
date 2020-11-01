package arrayApp1;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

class MainArrayApp {
	
	private Scanner scanner;
	private int[] intArray;
	private Random random;

	private int userInputInt;
	private String userResponseString;

	/**
	 * Creates an integer array and calls the initial prompt method.
	 */
	public MainArrayApp() {
		scanner = new Scanner(System.in);
		random = new Random();
		// new integer array of random size 5 - 15;
		intArray = new int[(random.nextInt(21) / 2) + 5];

		for (int i = 0; i < intArray.length; i++) {
			// fills the array with random numbers from 0 to 150
			intArray[i] = random.nextInt(151);
		}

		intialPrompt();
	}

	/**
	 * Prints out a given array in a formatted manner.
	 * 
	 * @param array The integer array being printed.
	 */

	/**
	 * A method to hold the bulk of the messages displayed to the user. This method
	 * exists to declutter the constructor, main method, and other method calls.
	 */
	private void intialPrompt() {
		System.out.println("Here in an array of numbers: ");
		ArrayUtil.printIntegerArray(intArray);
		System.out.println();
		System.out.println("Would you like to modify it?");
		System.out.print("Enter your response (Yes or No): ");

		userResponseString = scanner.next();
		switch (userResponseString.toLowerCase()) {
		case "yes":
			System.out.println();
			System.out.println("What would you like to do?");
			System.out.println("1. Remove a number from the Array");
			System.out.println("2. Add numbers to the Array");
			System.out.println("3. Sort the array (merge sort)");
			System.out.print("Enter you response: ");

			userInputInt = scanner.nextInt();

			System.out.println();

			if (userInputInt == 1) {
				removeIntFromArrayProgram();
			} else if (userInputInt == 2) {
				addArrayProgram();
			} else if (userInputInt == 3) {

				sortProgram(intArray);

			}
			break;
		case "no":
			System.out.println();
			System.out.println("Okay. End of program.");
			break;
		default:
			System.out.println();
			System.out.println("That response is invalid. End of program.");
			break;
		}
	}

	/**
	 * One of the user choices leads to this method. When called, this method
	 * prompts the user for the a number he would like to remove from the existing
	 * array. It also does error checking if the entered values are illegal.
	 */
	private void removeIntFromArrayProgram() {

		System.out.print("Enter the number you would like to remove: ");

		userInputInt = 999;
		int sentinel = 1;
		do {
			try {
				userInputInt = scanner.nextInt();
				for (int i = 0; i < intArray.length; ++i) {
					if (userInputInt == intArray[i]) {
						sentinel = 0;
					}
				}
				if (sentinel == 1) {
					System.out.println();
					System.out.println("That number does not exist within the Array." + " Please try again.");
					userInputInt = 999;
					System.out.print("Enter a new number: ");
				}
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("Error! Please enter only numbers!");
				System.out.print("Try again: ");
				scanner.next();
			}
		} while (sentinel == 1);

		int[] result = ArrayUtil.removeNumberFromArray(intArray, userInputInt);

		System.out.println();
		System.out.println("Original Array: ");
		ArrayUtil.printIntegerArray(intArray);
		System.out.println("Here is the new array: ");
		ArrayUtil.printIntegerArray(result);
		System.out.println("You removed the number: " + userInputInt);
	}

	private void addArrayProgram() {
		System.out.print("Enter the amount of numbers you would like to add: ");
		userInputInt = scanner.nextInt();

		int[] holdValuesAdded = new int[userInputInt];

		System.out.println();
		System.out.println("Enter the numbers you would like to add: ");
		for (int i = 0; i < userInputInt; ++i) {
			holdValuesAdded[i] = scanner.nextInt();
		}

		int[] result = ArrayUtil.addNumberToArray(intArray, userInputInt, holdValuesAdded);

		System.out.println();
		System.out.println("Original array: ");
		ArrayUtil.printIntegerArray(intArray);
		System.out.println("Here is the new array: ");
		ArrayUtil.printIntegerArray(result);
		System.out.print("You added the number(s): ");
		ArrayUtil.printIntegerArray(holdValuesAdded);

	}

	private void sortProgram(int[] array) {

		System.out.println("Initial array: ");
		ArrayUtil.printIntegerArray(array);

		array = ArrayUtil.mergeSort(array);
		System.out.println("\nSorted Array: ");
		ArrayUtil.printIntegerArray(array);

	}
	
}
