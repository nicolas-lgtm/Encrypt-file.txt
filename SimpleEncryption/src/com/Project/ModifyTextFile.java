package com.openclassrooms;

import java.io.BufferedReader;
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner; // Import the Scanner class to read text files

public class ModifyTextFile {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		String fileAdress;

		Scanner s = new Scanner(System.in);

		int choice = 0;

		do {
			System.out.println("Do you want to:\n" + "Press 1) Encrypt a file\n"
					+ "Press 2) Decrypt a file which has already been encrypted?");

			choice = s.nextInt();

		} while (choice != 1 && choice != 2);

		System.out.println("Give the absolute address of the .txt file to ");
		
		if(choice == 1) System.out.println("encrypt:");
		else System.out.println("decrypt:");
		
		fileAdress = s.next();

		File myFile = new File(fileAdress);

		if (myFile.exists()) {
			encryption(myFile, choice);
		} else {
			System.out.println("No file found at this adress: " + fileAdress);
		}

	}

	public static void encryption(File file, int a_choice) {
		Boolean cryptage = true;
		int mult = 1;

		if (a_choice == 2)
			cryptage = false;

		if (!cryptage)
			mult = -1;

		String wholeText = "";
		String line;

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			try {
				while ((line = br.readLine()) != null)
					wholeText += line + "\n";
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		PrintWriter writer;
		try {
			writer = new PrintWriter(file);
			writer.print(EncryptedText(mult, wholeText));
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static String EncryptedText(int cryptageMult, String textToEncrypt) {

		StringBuilder newString = new StringBuilder();

		for (int i = 0; i < textToEncrypt.length(); i++) {
			char currentChar = textToEncrypt.charAt(i);

			if (currentChar != '\n' || currentChar != ' ') {
				char newChar = (char) (currentChar + (1 * cryptageMult));
				newString.append(newChar);
			} else {
				newString.append(currentChar);
			}

		}

		return newString.toString();
	}
}
