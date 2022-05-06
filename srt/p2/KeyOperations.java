/**
 * 
 */
package p2;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * @author lorenzom
 *
 */
public class KeyOperations {

	public KeyOperations() {
	}
	
	public static SecretKey generatePBEKey(String algorithm,char[]passwd) {
		 try {
			   PBEKeySpec	pbeKeySpec = new PBEKeySpec(passwd);
			   SecretKeyFactory    keyFact = SecretKeyFactory.getInstance(algorithm);
			   SecretKey pbeKey = keyFact.generateSecret(pbeKeySpec);
			   return pbeKey;
		    }
		    catch (Exception e) {
		        e.printStackTrace();
		        return null;
		    }
	}
	
	public static PBEParameterSpec generatePBEParameters(byte [] salt, int iterationCount) {
		   try {
			// Create PBE parameter set
		    	PBEParameterSpec pbeParamSpec = new PBEParameterSpec(salt, iterationCount);
		    	return pbeParamSpec;
		    }
		    catch (Exception e) {
		        e.printStackTrace();
		        return null;
		   }
		}	
}
