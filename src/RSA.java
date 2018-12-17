
public class RSA {
	int e = 5; // public key Schlüssel
	int N = 21; // public key Schlüssel
	int d = 5;	//private key
	
	//encrypts given k to c
	public int encrypt(int k) {
		
		int c = (int)(Math.pow(k,e)) % N;
		return c;
		
	}
	
	//Decrypts given c to k
	public int decrypt(int c) {
		
		int k = (int)(Math.pow(c, d)) % N;
		return k ;
	}
	
	
}
