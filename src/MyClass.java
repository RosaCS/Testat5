import java.io.*;

/**
 * @author Jörn Fischer
 * @brief This example class is written to  
 *        simplify your first JAVA programs
 */
public class MyClass {
	/****************************************************
	 * main function
	 * @brief: The main function is the first function 
	 * called in this project. Please insert your code
	 * into this function...
	 * 
	 ****************************************************/
	public static void main(String[] arg){

		// insert code here
		example();
	}
	/**
	 * @brief This is an example function
	 */
	public static void example()
	{		
		// --- print "Zufallszahl:" on the screen
		System.out.print("Zufallszahl:");
		// --- Math.random() returns a random number
		System.out.println(Math.random()); 
	
		// --- write "...text written in the file..." 
		// --- in the File "d:\\temp\\test.txt"
		// -------------------------------------------------------
		String outText = "...text written in the file...";
		// --- outText.getBytes() converts the String outText into a byte Array
		writeFile("d:\\temp\\test.txt", outText.getBytes()); 
		
		// --- read the text from the file "d:\\temp\\test.txt" 
		// --- and print it on the screen
		// -------------------------------------------------------
		byte[] inText = new byte[1000];
		try{
			inText = readFile("d:\\temp\\test.txt");
		}catch(IOException ex){System.out.println("File: Read Error...");}
		String myString = new String(inText); // conversion from byteArray to String
		System.out.println(myString);	
	}

	
	/**
	 * Simplifying functions: 
	 * readInt      - reads an integer from Keyboard
	 * readDouble   - reads a double floating point number from keayboard 
	 * readString   - reads a String from Keyboard
	 * readFile     - reads a byte Array from a file on the harddisk
	 * writeFile    - writes a byte Array to a file on the harddisk  
	 */
	public static int readInt()
	{
		byte []charArray = new byte[1000];
		int num = 0;
		try{
			System.in.read(charArray);
		}
		catch(IOException ioe){
			System.out.println("Fehler bei der Eingabe!");
		};
		for (int t=0; charArray[t]>='0' && charArray[t]<='9'; t++){
			num = (num * 10) + charArray[t]-'0';
		}
		return (num);
	}
	
	public static double readDouble()
	{
		byte []charArray = new byte[1000];
		double num = 0;
		int t;
		try{
			System.in.read(charArray);
		}
		catch(IOException ioe){
			System.out.println("Fehler bei der Eingabe!");
		};
		for (t=0; charArray[t]>='0' && charArray[t]<='9'; t++){
			num = (num * 10) + charArray[t]-'0';
		}
		if (charArray[t]=='.'){
			double digit = 10.0;
			for (int i=t+1;charArray[i]>='0' && charArray[i]<='9';i++){
				num = num + (double)(charArray[i]-'0')/digit;
				digit *= 10.0;
			}
			
		}
		return (num);
	}
	
	public static String readString(){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    String inStr = "";
	    try {
	    	inStr = reader.readLine();
	    }
	    catch(IOException e) {
	        e.printStackTrace();
	    }
	    return inStr;
	}
	
	
    public static byte[] readFile(String fileName) throws IOException {        
    	File file = new File(fileName);
    	
        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
            throw new IOException("File is too large!");
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;

        InputStream is = new FileInputStream(file);
        try {
            while (offset < bytes.length
                   && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
                offset += numRead;
            }
        } finally {
            is.close();
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }
		
        return bytes;
    }		
    
    public static void writeFile(String fileName, byte[] buf)
    {
		
		FileOutputStream fos = null;
		
		try
		{
		   fos = new FileOutputStream(fileName);
		   fos.write(buf);
		}
		catch(IOException ex)
		{
		   System.out.println(ex);
		}
		finally
		{
		   if(fos!=null)
		      try
		      {
		         fos.close();
		      }
		      catch(Exception ex)
		      {
		      }
		}
    }
	
}