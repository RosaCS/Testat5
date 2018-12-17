import java.io.IOException;
import java.util.ArrayList;

public class App {
		
	public static void main(String args[]) throws IOException {
		
		//Paths to the files we want to read and save our encryption
		String readpath = "read.txt";
		String encryptedDataFile = "encrypted.txt";

		//Create FileIO 
		FileIO file = new FileIO();
		
		//read string from File
		String dataToEncrypt = file.fileRead(readpath);
		
		//Show String to encrypt
		System.out.println("String to encrypt: " +  dataToEncrypt);
		
		//Instantiate rsa object
		RSA rsa = new RSA();
		
		//ArrayList to save our encrypted output 
		String encryptedData ="";
		
		//encrpyt the data
		for (int i = 0; i < dataToEncrypt.length(); i++) {
			// gets char on the index i
			char x = dataToEncrypt.charAt(i);
			// create k1
			int k1 = x%20;
			//create k2
			int k2 = x/20;
			////////////////////////////////
			//System.out.println("k1: "+k1 + " k2: "+ k2);
			////////////////////////////////
			//encrypted k1 into c
			int encryptedk1 = rsa.encrypt(k1);
			//encryted k2 into c
			int encryptedk2 = rsa.encrypt(k2);
			//Append (anhängen) k1 and k2 to encryted data
			encryptedData +=encryptedk1 + "-";
			encryptedData +=encryptedk2 + "-";
			
		}
		
		// write encryted Data into a encryted data file (phath, Input-string)
		file.fileWrite(encryptedDataFile, encryptedData);
		
		
		// read data to decryt
		String dataToDecrypt = file.fileRead(encryptedDataFile);
		// put out data to decrypt
		System.out.println("Data to Decrypt: " +dataToDecrypt);
		
		// Array: string.split= splits on char - so we have an array with 11 17 11 ....
		String[] cs = dataToDecrypt.split("-");
				
		// needed to ceck if we are on a even or uneven position at the array
		int counter = 1;
		// needed to save the decrypted letters
		String letters = "";
		// needs to be instantiated to save c1 at uneven position
		int c1 = 0;
		// loop to decrypt the c's from entcryped data
		for (int i = 0; i< cs.length; i++) {
			// save c1 at uneven position as Integer
			if(counter%2 != 0) {
				// pars (übersetzten) String to int
				c1 = Integer.parseInt(cs[i]);
			}
			// ceck if we are at even position, and pars c2 to in, after that decrypt to k1 and k2, and calculate k from k1 and k2, and then create char from k
			if(counter%2 == 0) {
				// pars c2 from string to int 
				 int c2 =  Integer.parseInt(cs[i]);
				 //create k1 by decrypting c1
				 int k1 = rsa.decrypt(c1);
				 // create k2 by decryting c2
				 int k2 = rsa.decrypt(c2);

				 // create k from k1 and k2 by formula
				 int k = (k2 * 20) + k1;
				 // pars int to char (ASCII)
				 String letter = Character.toString((char) k);
				 //put the chars together to a string
				 letters += letter;
			}		

			counter++;
		}
		
		// output decrypted string
		System.out.println("Decrypted String: " + letters);	

	}

}
