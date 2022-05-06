package p2;

import java.beans.Beans;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
/**
 * Esta es la clase que internacionaliza la aplicación
 * @author diegopc
 *
 */
public class Messages {
	////////////////////////////////////////////////////////////////////////////
	//
	// Constructor
	//
	////////////////////////////////////////////////////////////////////////////
	private Messages() {
		// do not instantiate
	}
	////////////////////////////////////////////////////////////////////////////
	//
	// Bundle access
	//
	////////////////////////////////////////////////////////////////////////////
	private static final String BUNDLE_NAME = "p2.messages"; //$NON-NLS-1$
	private static ResourceBundle RESOURCE_BUNDLE = loadBundle();
	private static ResourceBundle loadBundle() {
		return ResourceBundle.getBundle(BUNDLE_NAME);
	}
	////////////////////////////////////////////////////////////////////////////
	//
	// Strings access
	//
	////////////////////////////////////////////////////////////////////////////
	public static String getString(String key) {
		try {
			ResourceBundle bundle = Beans.isDesignTime() ? loadBundle() : RESOURCE_BUNDLE;
			return bundle.getString(key);
		} catch (MissingResourceException e) {
			return "" + key + "";
			
		}
	
	}
	private static Locale getLocale(String idioma) {
		Locale locale = new Locale("es");//Default español
		if(idioma.equals("ingles")) {
			locale = new Locale("en");
		}
		if(idioma.equals("frances")) {
			locale = new Locale("fr");
		}
		return locale;
	}
	public static void setIdioma(String idioma) {
		RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME,getLocale(idioma));
	}
}
