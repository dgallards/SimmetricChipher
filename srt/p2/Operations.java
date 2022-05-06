package p2;
import javax.crypto.*;
import javax.crypto.spec.*;

public class Operations{	
	private String algorithm;
	@SuppressWarnings("unused")
	private byte[]	salt = new byte[] { 0x7d, 0x60, 0x43, 0x5f, 0x02, (byte)0xe9, (byte)0xe0, (byte)0xae };
	private SecretKey sKey;
	private PBEParameterSpec pbeParamSpec;
	private Cipher cipher;
	
	public Operations(){
		cipher = null;
	}
	
	public void prepareCypher(String algorithm) throws Exception{
		this.algorithm = algorithm;
		System.out.println("lo preparo:"+algorithm);
    	cipher = Cipher.getInstance(algorithm);
	}
	
	public Cipher getCipher() {
		return this.cipher;
	}
	
	public void prepareKey(char[]passwd,byte [] salt, int iterationCount) {
		sKey = KeyOperations.generatePBEKey(algorithm, passwd);
    	pbeParamSpec = KeyOperations.generatePBEParameters(salt,iterationCount);
	}
	
	public void initEncrypt() throws Exception{
		cipher.init(Cipher.ENCRYPT_MODE,sKey,pbeParamSpec);
	}
	
	public void initDecrypt() throws Exception{
		cipher.init(Cipher.DECRYPT_MODE,sKey,pbeParamSpec);
	}
	
	public byte[] symmetricOperation(byte[] inputData) throws Exception{
		return cipher.doFinal(inputData);
	}
}
