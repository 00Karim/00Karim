// Author:  Karim Nekzad

package arrayApp1;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

public class arrayApp1 {
	private Scanner scanner;
	private int[] intArray;
	private Random random;
	
	private int userInputInt;
	private String userResponseString;

	/**
	 * Creates an integer array and calls the initial prompt method.
	 */
	public arrayApp1() {
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
	private void printIntegerArray(int[] array) {
		for (int i : array) {
			System.out.print(i + ", ");
		}
		System.out.println();
	}

	/**
	 * A method to hold the bulk of the messages displayed to the user. This method
	 * exists to declutter the constructor, main method, and other method calls.
	 */
	private void intialPrompt() {
		System.out.println("Here in an array of numbers: ");
		printIntegerArray(intArray);
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

		int[] result = removeNumberFromArray(intArray, userInputInt);

		System.out.println();
		System.out.println("Original Array: ");
		printIntegerArray(intArray);
		System.out.println("Here is the new array: ");
		printIntegerArray(result);
		System.out.println("You removed the number: " + userInputInt);
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
	private int[] removeNumberFromArray(int[] intArray, int removeNum) {
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
	 * One of the possible user choices leads to this method. When called, this
	 * method prompts the user for the amount of numbers he would like to append to
	 * the existing array and each of their respective values.
	 */
	private void addArrayProgram() {
		System.out.print("Enter the amount of numbers you would like to add: ");
		userInputInt = scanner.nextInt();

		int[] holdValuesAdded = new int[userInputInt];

		System.out.println();
		System.out.println("Enter the numbers you would like to add: ");
		for (int i = 0; i < userInputInt; ++i) {
			holdValuesAdded[i] = scanner.nextInt();
		}

		int[] result= addNumberToArray(intArray, userInputInt, holdValuesAdded);

		System.out.println();
		System.out.println("Original array: ");
		printIntegerArray(intArray);
		System.out.println("Here is the new array: ");
		printIntegerArray(result);
		System.out.print("You added the number(s): ");
		printIntegerArray(holdValuesAdded);

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
	private int[] addNumberToArray(int[] array, int amntAdded, int[] numbsToAdd) {
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

	private void sortProgram(int[] array) {

		System.out.println("Initial array: ");
		printIntegerArray(array);
		
		array = mergeSort(array);
		System.out.println("\nSorted Array: ");
		printIntegerArray(array);

	}

	private int[] mergeSort(int[] array) {

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

	private int[] merge(int[] left, int[] right) {

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

	/**
	 * A main method to run the arrayApp1 class by creating a new instance of the
	 * class.
	 * 
	 * @param args The String array of command line arguments.
	 */
	public static void main(String[] args) {
		new arrayApp1();
	}
}
