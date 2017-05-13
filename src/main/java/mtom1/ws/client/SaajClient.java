package mtom1.ws.client;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.URLDataSource;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StopWatch;
import org.springframework.spring_ws.samples.mtom.LoadContentRequest;
import org.springframework.spring_ws.samples.mtom.LoadContentResponse;
import org.springframework.spring_ws.samples.mtom.ObjectFactory;
import org.springframework.spring_ws.samples.mtom.StoreContentRequest;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;


/**
 * Simple SAAJ based MTOM enabled client
 *
 * @author Gyula Szalai <gyula.szalai@vanio.hu>
 */
public class SaajClient extends WebServiceGatewaySupport {

    /** JAXB object factory */

    private final ObjectFactory objectFactory = new ObjectFactory();

    /** Stopwatch to measure times */
    private final StopWatch stopWatch = new StopWatch(getClass().getSimpleName());

    /**
     * Constructor
     * @param messageFactory The SAAJ message factory
     */
    public SaajClient(SaajSoapMessageFactory messageFactory) {
        super(messageFactory);
    }

    /**
     * Sends the test content file to the WebService
     */
    public void storeContent() throws MalformedURLException {

        StoreContentRequest storeContentRequest = objectFactory.createStoreContentRequest();
        storeContentRequest.setName("SaajClientFile3.txt");
        //Testing
        FileDataSource ds = new FileDataSource(new File("C:\\Users\\User\\Desktop\\twitter.png"));
        byte[] decodedData = Base64.decodeBase64("Copied from Edm");
        DataHandler dataHandler = new DataHandler(new ByteArrayDataSource(decodedData, "application/octet-stream"));
        //Test ends
        storeContentRequest.setContent(new DataHandler(ds));
        DataHandler chk = storeContentRequest.getContent();
        stopWatch.start("store");
        getWebServiceTemplate().marshalSendAndReceive(storeContentRequest);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

    /**
     * Loads the test content from the WebService
     * @throws IOException If an IO error occurs
     */
    public void loadContent() throws IOException {
        LoadContentRequest loadContentRequest = objectFactory.createLoadContentRequest();
        loadContentRequest.setName("FreeFile.txt");
        //String tmpDir = System.getProperty("java.io.tmpdir");
        //File outFile = new File(tmpDir, "spring_mtom_tmp.bin");

        long freeBefore = Runtime.getRuntime().freeMemory();

        stopWatch.start("load");
        LoadContentResponse loadImageResponse = (LoadContentResponse) getWebServiceTemplate().marshalSendAndReceive(loadContentRequest);
        stopWatch.stop();

        DataHandler content = loadImageResponse.getContent();

        long freeAfter = Runtime.getRuntime().freeMemory();
        logger.info("Memory usage [kB]: " + ((freeAfter - freeBefore)/1024));

        stopWatch.start("loadAttachmentContent");
        //long size = ClientUtil.saveContentToFile(content, outFile);

        //logger.info("Received file size [kB]: " + size);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

}