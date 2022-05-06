package p2;

import java.io.*;

import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

import org.bouncycastle.util.encoders.Hex;

import srt.Header;
import srt.Options;

/**
 * Esta clase es la que se encarga de cifrar los archivos
 * @author Diego Gallardo Zancada
 *
 */
public class Practica2 {
	private char[] passwd;
	private String algorithm = "PBEWithMD5AndDES";
	private byte[] salt = Hex.decode("7d60435f02c9ea0e");
	private int iterationCount = 1024;
	private byte[] input = Hex.decode("a0a1a2a3a4a5a6a7a0a1a2a3a4a5a6a7" + "a0a1a2a3a4a5a6a7a0");
	private byte output[] = null;

	// private Options options = new Options();
	private Operations operations = null;

	public Practica2() {
		operations = new Operations();
	}
/** 
 * [DEPRECATED]
 * Es la función main del programa. Actualmente no tiene funcionalidad.
 * @param args No tiene funcionalidad.
 * @throws Exception
 */
	public static void main(String[] args) throws Exception {
		Practica2 practica2 = new Practica2();
		System.out.println("BySS, Ejemplo de cifrado de mensajes con PBE.");
		practica2.doMenu();
	}
	
/**
 * [DEPRECATED]
 * Es la función main del programa. Actualmente no tiene funcionalidad.
 * @throws Exception
 */
	public void doMenu() throws Exception {
		do {
			System.out.println();
			System.out.println("Operación a realizar:");
			System.out.println("1-Cifrado    PBE");
			System.out.println("2-Descifrado PBE");
			System.out.println("3 CIFRAR FICHERO");
			System.out.println("4 DESCIFRAR FICHERO");
			System.out.println("3-Cambiar parámetros PBE");

			System.out.println("0-Exit.");
			System.out.print("operation:-");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int c = br.read();
			switch (c) {
			case '1':
				doEncrypt();
				break;
			case '2':
				doDecrypt();
				break;
			case '3':
				// doEncryptFile();
				break;
			case '4':
				// doDecryptFile();
				break;
			case '5':
				changeParametersPBE();
				break;
			case '0':
				return;
			}
		} while (true);
	}
/**
 * [DEPRECATED]
 * Este método realiza la encriptación de un mensaje escrito por consola.
 */
	public void doEncrypt() {
		try {
			algorithm = readAlgorithm();
			input = readMsgToEncrypt().getBytes();

			// if (passwd!=null) {
			if ((passwd = readCryptPasswd()) != null) {
				operations.prepareCypher(algorithm);
				operations.prepareKey(passwd, salt, iterationCount);
				operations.initEncrypt();
				output = operations.symmetricOperation(input);
				System.out.println("input    : " + Hex.toHexString(input));
				if (output != null) {
					System.out.println("output   : " + Hex.toHexString(output));
				} else {
					System.out.println("No hay salida.");
				}
			} else {
				System.out.println("Passwds diferentes.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/**
 * Este método es utilizado para encriptar el contenido de un archivo cuya ruta es pasada por parámetro.
 * @param filePath ruta del fichero de entrada que se quiere encriptar
 * @param algorithm nombre del algoritmo utilizado para la encriptación
 * @param pwd contraseña para la encriptación
 */
	public void doEncryptFile(String filePath, String algorithm, String pwd) {
		try {

			byte[] line = new byte[256];
			FileInputStream readFile = new FileInputStream(filePath);
			FileOutputStream fos = new FileOutputStream(filePath + "_out.cif");
			boolean finFichero = false;

			// if (passwd!=null) {
			if (pwd != null) {
				operations.prepareCypher(algorithm);
				operations.prepareKey(pwd.toCharArray(), salt, iterationCount);
				operations.initEncrypt();

				Header hdr = new Header(Options.OP_SYMMETRIC_CIPHER, algorithm, "none", salt);
				hdr.save(fos);
				CipherOutputStream cos = new CipherOutputStream(fos, operations.getCipher());

				while (!finFichero) {
					line = readFile.readNBytes(256);
					if (line.length > 0) {
						// System.out.println("input : " + Hex.toHexString(input));
						// System.out.println("output : " + Hex.toHexString(output));
						cos.write(line);
					} else {
						finFichero = true;
						cos.close();
						fos.close();
						readFile.close();
					}

				}

			} else {
				System.out.println("Passwds diferentes.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/**
 * [DEPRECATED]
 * Este método realiza la desencriptación de un mensaje escrito por consola.
 */
	public void doDecrypt() {
		try {
			algorithm = readAlgorithm();
			input = readMsgToDecrypt().getBytes();
			input = Hex.decode(input);

			if ((passwd = readDecryptPasswd()) != null) {
				// if (passwd!=null) {
				operations.prepareCypher(algorithm);
				operations.prepareKey(passwd, salt, iterationCount);
				operations.initDecrypt();
				byte output[] = operations.symmetricOperation(input);
				System.out.println("cipher    : " + Hex.toHexString(input));
				if (output != null) {
					System.out.println("decipher  : " + Hex.toHexString(output));
					System.out.println("text      : " + new String(output));
				} else {
					System.out.println("No hay salida.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/**
 * Este método es utilizado para desencriptar el contenido de un archivo cuya ruta es pasada por parámetro.
 * @param filePath ruta del fichero de entrada que se quiere desencriptar
 * @param passwd contraseña para la desencriptación
 * @throws Exception
 */
	public void doDecryptFile(String filePath, String passwd) throws Exception {
		try {
			// algorithm = readAlgorithm();

			if (passwd != null) {
				// if (passwd!=null) {

				FileInputStream fis = new FileInputStream(filePath);
				Header hdr = new Header();
				hdr.load(fis);
				operations.prepareCypher(hdr.getAlgorithm1());
				operations.prepareKey(passwd.toCharArray(), salt, iterationCount);
				operations.initDecrypt();
				CipherInputStream cis = new CipherInputStream(fis, operations.getCipher());
				String outFile = filePath.replace("_out.cif", "");
				FileOutputStream fos = new FileOutputStream(outFile);

				// System.out.println("estoy en el desencriptado:"+passwd);

				boolean finFichero = false;
				while (!finFichero) {

					byte output[] = cis.readNBytes(256);
					if (output.length > 0) {
						fos.write(output);

					} else {
						finFichero = true;
						fos.close();
						cis.close();
						fis.close();
					}

				}
			}
		} catch (IOException e) {
			String outfile = filePath.replace("_out.cif", "");
			File oFile = new File(outfile);
			oFile.delete();
			throw e;
		}
	}
/**
 * [DEPRECATED]
 * Este método cambia el parámetro salt de la cabecera. No es usado.
 * @throws Exception
 */
	public void changeParametersPBE() throws Exception {
		do {
			System.out.println();
			System.out.println("Configuraci�n de par�metros PBE actual:");
			System.out.println("Iteration Count: " + iterationCount);
			System.out.println("Salt: " + Hex.toHexString(salt));
			System.out.print("Iteration Count: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int ic = Integer.parseInt(br.readLine());
			System.out.print("Salt (8 bytes en Hexadecimal, sin espacios):");
			String s = br.readLine();
			System.out.print("�Cambiar los valores? (s/n)");
			int c = br.read();
			if ((c == 's') || (c == 'S')) {
				iterationCount = ic;
				salt = Hex.decode(s);
			}
			return;
		} while (true);
	}
/**
 * [DEPRECATED]
 * Metodo que devuelve el mensaje a cifrar mediante un String
 * @return mensaje a cifrar
 * @throws Exception
 */
	public String readMsgToEncrypt() throws Exception {
		do {
			System.out.print("Mensaje a cifrar:");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String msg = br.readLine();
			if (msg != null)
				return msg;
		} while (true);
	}
/**
 * [DEPRECATED]
 * Método que devuelve el nombre del archivo a cifrar mediante un String 
 * @return nombre del archivo a cifrar
 * @throws Exception
 */
	public String readFileToEncrypt() throws Exception {
		do {
			System.out.print("Escriba el archivo a cifrar: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String msg = br.readLine();
			if (msg != null)
				return msg;
		} while (true);
	}
/**
 * [DEPRECATED]
 * Método que devuelve el mensaje a descifrar mediante un String
 * @return mensaje a descifrar
 * @throws Exception
 */
	public String readMsgToDecrypt() throws Exception {
		do {
			System.out.print("Mensaje a descifrar (en Hexadecimal, sin espacios, ejemplo 0a56340f):");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String msg = br.readLine();
			if (msg != null)
				return msg;
		} while (true);
	}
/**
 * [DEPRECATED]
 * Método que devuelve el nombre del archivo a descifrar mediante un String
 * @return nombre del archivo a descifrar
 * @throws Exception
 */
	public String readFileToDecrypt() throws Exception {
		do {
			System.out.print("Archivo a descifrar: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String msg = br.readLine();
			if (msg != null)
				return msg;
		} while (true);
	}
/**
 * [DEPRECATED]
 * Método que devuelve el nombre del algoritmo a utilizar
 * @return nombre del algoritmo a utilizar
 * @throws Exception
 */
	private String readAlgorithm() throws Exception {
		do {
			System.out.println("Algoritmo a utilizar:");
			for (int i = 0; i < srt.Options.symmetricalAlgorithms.length; i++)
				System.out.println(i + "-" + srt.Options.symmetricalAlgorithms[i]);
			System.out.print("algorithm:-");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int c = br.read();
			c = c - '0';
			if ((c >= 0) && (c < srt.Options.symmetricalAlgorithms.length))
				return srt.Options.symmetricalAlgorithms[c];
		} while (true);
	}

/**
 * [DEPRECATED]
 * Método que devuelve la contraseña si las dos contraseñas introducidas son iguales
 * @return devuelve la contraseña si coinciden las dos entradas o en caso contrario devuelve null
 */
	private char[] readCryptPasswd() {
		char[] passwd1;
		if ((passwd1 = readPasswd()) != null) {
			String sp1 = new String(passwd1);
			System.out.print("Repitir ");
			String sp2 = new String(readPasswd());
			if (sp1.equals(sp2))
				return passwd1;
			else {
				System.out.println("Frases diferentes.");
				return null;
			}
		} else
			return null;
	}

	/**
	 * [DEPRECATED]
	 * Devuelve la contraseña 
	 */
	private char[] readDecryptPasswd() {
		return readPasswd();
	}

	/**
	 * [DEPRECATED]
	 * Este método lee la contraseña del usuario de una entrada dada
	 */
	private char[] readPasswd() {
		Console cons;
		char[] passwd = null;
		if ((cons = System.console()) != null) {
			passwd = cons.readPassword("[%s]", "Frase de paso:");
			return passwd;
		} else {
			System.out.print("Frase de paso: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try {
				String cadena = br.readLine();
				return cadena.toCharArray();
			} catch (Exception e) {
				return null;
			}
		}
	}
}
