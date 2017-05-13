package mtom1.ws.endpoint;

import java.io.IOException;

import org.springframework.spring_ws.samples.mtom.LoadContentRequest;
import org.springframework.spring_ws.samples.mtom.LoadContentResponse;
import org.springframework.spring_ws.samples.mtom.StoreContentRequest;
import org.springframework.spring_ws.samples.mtom.StoreContentResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;

@Endpoint
public interface MtomEndpointInterface {
	@SoapAction("")
    @ResponsePayload
    public LoadContentResponse loadContent(@RequestPayload LoadContentRequest loadContentRequest);
	
	@SoapAction("")
    @ResponsePayload
    public StoreContentResponse storeContent(@RequestPayload StoreContentRequest storeContentRequest) throws IOException;
	
}
