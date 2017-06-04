//  15/15  Nicely done.

/**
 *  Decipher.java
 *  Decipher prompts the user for 4 inputs:
 *  (1) A key code, which is an english word, phrase, or sentence.
 *  (2) The name of the text file to be opened and then encrypted or decrypted.
 *  (3) The name of the text file to be created.
 *  (4) A String of "1" or "2" to indicate a choice of encryption or decryption.
 *  The program employs the concept of a substitution cipher with a key, so that
 *  if the user enters the phrase "Monta Vista" as the key code, a substitution
 *  will be made using the following scheme:
 *  fullKey:  MONTAVISBCDEFGHJKLPQRUWXYZ
 *  alphabet: ABCDEFGHIJKLMNOPQRSTUVWXYZ
 *  The user will choose whether to go from fullKey to alphabet (encrypt), or
 *  from alphabet to fullKey (decrypt).  The result is always printed to the
 *  terminal window, and saved in the text file chosen by the user.
 *  @author Shiv Ansal
 *  @version 1.0
 *  @since 9/20/2016
 */

import java.util.Scanner;
import java.io.PrintWriter;

public class Decipher
{
	/**    The full key, created from the user's key word, phrase, or sentence.    */
	private String fullKey;

	/**    The name of the text file to be encrypted or decrypted, chosen by the user.    */
	private String fileNameIn;

	/**    The name of the text file to be created, chosen by the user.    */
	private String fileNameOut;

	/**    The choice made by the user.  "1" to encrypt, and "2" to decrypt.    */
	private String choice;

	private String alphabetKey="";

	/**
	 *  Creates a Decipher object.  The 4 String fields are initialized her.
	 *  These Strings will be changed with user input.
	 */
	public Decipher ( )
	{
		fullKey = new String("");
		fileNameIn = new String("");
		fileNameOut = new String("");
		choice = new String("");
	}

	/**
	 *  Sets up and runs Decipher.
	 *  @param  args     An array of String arguments (not used here).
	 */
	public static void main(String [] args)
	{
		Decipher run = new Decipher();
		run.getInfo();
		run.convertFile();
	}

	/**
	 *  Prompts the user for the 4 Strings necessary to create the encrypted/decrypted output and file.
	 */
	public void getInfo ( )
	{
		int choiceint=0;
		char next;
		String intialKey;
		Prompt ask = new Prompt();
		intialKey = ask.getString("\n\nPlease enter a word or phrase to serve as your key       : ");
		intialKey = intialKey.toUpperCase();
		fileNameIn = ask.getString("\n\nPlease enter the name of the text file to encrypt/decrypt: ");
		fileNameOut = ask.getString("\n\nPlease enter the name of the new text fle to be created  : ");
		choiceint = ask.getInt("\n\nPlease enter (1) to encrypt or (2) to decrypt            : ",1,2);
		choice = "" + choiceint;

			for(int i=0; i<(intialKey.length()); i++)
			{
				 next = intialKey.charAt(i);
				 if(fullKey.indexOf(next)==-1)
				 {
					 if(((int)next)>=65&&((int)next)<=90)
					      fullKey = fullKey + next;
				 }
			}

			for(int i=65; i<=90; i++)
			{
				if((fullKey.indexOf((char)i)==-1))
				{
					fullKey+= (char)i;
				}
				alphabetKey+= (char)i;
			}

      System.out.println("\nHere is your encryption code: \n\n");
			System.out.println(fullKey);
			System.out.println(alphabetKey);
      System.out.println("\nThe encoded/decoded message is printed below, and the same text has been printed to "+fileNameOut+".\n\n\n");
	}

	/**
	 *  Creates the substitution cipher with a key, then does the conversion for the given file,
	 *  printing the output to the terminal window and to the text file chosen by the user.
	 */
	public void convertFile ( )
	{
		String fina;
		Scanner infile = OpenFile.openToRead(fileNameIn);
		PrintWriter outfile = OpenFile.openToWrite(fileNameOut);
		String temp = null;
    int find;
    char tempChar = 'a';
    String toTemp = null;
		while(infile.hasNext()){
			temp = infile.nextLine();
			temp = temp.toUpperCase();
			toTemp="";
				for(int i = 0;i<temp.length();i++){
					if(choice.equals("1")){
					  if(!(temp.charAt(i)>=65&&temp.charAt(i)<=90)){
						  toTemp = toTemp +temp.charAt(i);
					  }
					  else if(temp.charAt(i)!=32){
						  tempChar=temp.charAt(i);
						  if(fullKey.indexOf(tempChar)!=-1){
							int x = fullKey.indexOf(tempChar);
							toTemp = toTemp +alphabetKey.charAt(x);
						  }
					  }
					}
					if(choice.equals("2")){
					  if(!(temp.charAt(i)>=65&&temp.charAt(i)<=90)){
						  toTemp = toTemp +temp.charAt(i);
					  }
					  else if(temp.charAt(i)!=32){
						  tempChar=temp.charAt(i);
						  if(alphabetKey.indexOf(tempChar)!=-1){
							int x = alphabetKey.indexOf(tempChar);
							toTemp = toTemp +fullKey.charAt(x);
						  }
					  }
					}
				}
			System.out.println(toTemp);
			outfile.println(toTemp);
		}
		System.out.println("\n\n\n");
		infile.close();
		outfile.close();
	}
}
