package mtom1.ws.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataHandler;

import org.springframework.stereotype.Service;
@Service
public class ContentRepositoryImpl implements ContentRepository {

	public File loadContent(String name) {
		return new File("C:\\Users\\User\\Desktop\\ContentRepository\\", name);
	}

    public void storeContent(String name, DataHandler content) throws IOException {
        File outFile = new File("C:\\Users\\User\\Desktop\\ContentRepository\\", name);
        System.out.println("Storing content in file:"+ outFile.getAbsolutePath());
        int i = 0;
        byte[] buffer = new byte[1024];

            InputStream is = content.getInputStream();

                OutputStream outStream = new FileOutputStream(outFile);
                while ((i = is.read(buffer, 0, buffer.length)) > 0) {
                    outStream.write(buffer, 0, i);
        }
        outStream.close();
        System.out.println("Content stored.");

    }

}
