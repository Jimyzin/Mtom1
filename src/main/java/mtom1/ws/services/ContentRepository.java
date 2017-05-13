package mtom1.ws.services;

import java.io.File;
import java.io.IOException;

import javax.activation.DataHandler;

public interface ContentRepository {
	/**
	 * 
	 * @param name
	 * @return
	 */
	File loadContent(String name);
	/**
	 * 
	 * @param name
	 * @param content
	 * @throws IOException
	 */
	void storeContent(String name, DataHandler content) throws IOException;

}
