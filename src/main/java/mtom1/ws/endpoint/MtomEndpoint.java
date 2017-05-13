package mtom1.ws.endpoint;

import java.io.File;
import java.io.IOException;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.ws.soap.SOAPBinding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.spring_ws.samples.mtom.LoadContentRequest;
import org.springframework.spring_ws.samples.mtom.LoadContentResponse;
import org.springframework.spring_ws.samples.mtom.ObjectFactory;
import org.springframework.spring_ws.samples.mtom.StoreContentRequest;
import org.springframework.spring_ws.samples.mtom.StoreContentResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import mtom1.ws.services.ContentRepository;

@Endpoint
public class MtomEndpoint {
	
	@Autowired
	ContentRepository contentRepository;
	
	/** Target namespace */
    private static final String TNS = "http://www.springframework.org/spring-ws/samples/mtom";
    
    /** JAXB object factory */
    private final ObjectFactory objectFactory = new ObjectFactory();
    
    @PayloadRoot(localPart = "LoadContentRequest", namespace = TNS)
	public @ResponsePayload LoadContentResponse loadContent(@RequestPayload LoadContentRequest loadContentRequest) {
        LoadContentResponse resp = objectFactory.createLoadContentResponse();
        
        File contentFile = contentRepository.loadContent(loadContentRequest.getName());
        DataHandler dataHandler = new DataHandler(new FileDataSource(contentFile));
        resp.setName(loadContentRequest.getName());
        resp.setContent(dataHandler);
        return resp;
	}

    @PayloadRoot(localPart = "StoreContentRequest", namespace = TNS)

	public @ResponsePayload StoreContentResponse storeContent(@RequestPayload StoreContentRequest storeContentRequest) throws IOException {
		System.out.println("Request received");
		contentRepository.storeContent(storeContentRequest.getName(), storeContentRequest.getContent());
        StoreContentResponse resp = objectFactory.createStoreContentResponse();
        resp.setMessage("Success");
        return resp;
	}

}
