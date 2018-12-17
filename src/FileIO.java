import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;


public class FileIO {
	//reads bytearray from file and returns as String
	public String fileRead(String path) throws IOException {
		
		//read bytearray from File
		//File file = new File(path);
		//byte[] fileBytes = Files.readAllBytes(file.toPath());
		
		byte[] fileBytes = MyClass.readFile(path);
		//Convert bytearray to String and Set format
		String txt = new String(fileBytes); // for UTF-8 encoding
		//return read
		return txt;

	}

	
	//writes bytearray to file 
	public void fileWrite(String path, String input) throws IOException {
		//converts String to UTF-8 byteArray
        byte[] bytes = input.getBytes(Charset.forName("UTF-8"));
        //Writes bytes into file
        MyClass.writeFile(path,bytes);
				
	}

}
