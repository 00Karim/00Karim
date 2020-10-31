// Author:  Karim Nekzad

package arrayApp1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class arrayApp1 {
	private Scanner scanner;
	private int[] intArray;
	private int userInputInt;
	private int[] newArray;
	private int sentinel;
	private String userResponseString;
	private int userResponseInt;
	private int[] addedArray;
	private int[] holdValuesAdded;

	/**
	 * Creates an integer array and calls the initial prompt method.
	 */
	public arrayApp1() {
		scanner = new Scanner(System.in);
		intArray = new int[5];

		intArray[0] = 13;
		intArray[1] = 65;
		intArray[2] = 105;
		intArray[3] = 24;
		intArray[4] = 88;

		intialPrompt();
	}

	/**
	 * Prints out a given array in a formatted manner.
	 * 
	 * @param array The integer array being printed.
	 */
	public void printIntegerArray(int[] array) {
		for (int i = 0; i < array.length; ++i) {
			System.out.print(array[i] + ", ");
			if (i == array.length - 1) {
				System.out.println();
			}
		}
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
	public int[] removeNumberFromArray(int[] intArray, int removeNum) {
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
	public int[] addNumberToArray(int[] array, int amntAdded, int[] numbsToAdd) {
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

	/**
	 * One of currently two possible user choices leads to this method. When called,
	 * this method prompts the user for the amount of numbers he would like to
	 * append to the existing array and each of their respective values.
	 */
	public void addArrayProgram() {
		System.out.println();
		System.out.print("Enter the amount of numbers you would like to add: ");
		userInputInt = scanner.nextInt();

		holdValuesAdded = new int[userInputInt];

		System.out.println();
		System.out.println("Enter the numbers you would like to add: ");
		for (int i = 0; i < userInputInt; ++i) {
			holdValuesAdded[i] = scanner.nextInt();
		}

		addedArray = addNumberToArray(intArray, userInputInt, holdValuesAdded);

		System.out.println();
		System.out.println("Original array: ");
		printIntegerArray(intArray);
		System.out.println("Here is the new array: ");
		printIntegerArray(addedArray);
		System.out.print("You added the number(s): ");
		printIntegerArray(holdValuesAdded);
	}

	/**
	 * One of currently two possible user choices leads to this method. When called,
	 * this method prompts the user for the a number he would like to remove from
	 * the existing array. It also does error checking if the entered values are
	 * illegal.
	 */
	public void removeIntFromArrayProgram() {
		System.out.println();
		System.out.print("Enter the number you would like to remove: ");

		userInputInt = 999;
		sentinel = 1;
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

		newArray = removeNumberFromArray(intArray, userInputInt);

		System.out.println();
		System.out.println("Original Array: ");
		printIntegerArray(intArray);
		System.out.println("Here is the new array: ");
		printIntegerArray(newArray);
		System.out.println("You removed the number: " + userInputInt);
	}

	/**
	 * A method to hold the bulk of the messages displayed to the user. This method
	 * exists to declutter the constructor, main method, and other method calls.
	 */
	public void intialPrompt() {
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
			System.out.print("Enter you response: ");

			userResponseInt = scanner.nextInt();
			if (userResponseInt == 1) {
				removeIntFromArrayProgram();
			} else if (userResponseInt == 2) {
				addArrayProgram();
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
	 * A main method to run the arrayApp1 class by creating a new instance of the
	 * class.
	 * 
	 * @param args The String array of command line arguments.
	 */
	public static void main(String[] args) {
		new arrayApp1();
	}
}
