package mtom1.ws.client;

/**
 * Created by User on 09-04-2017.
 */
import org.junit.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SaajClientTest {

    @Test
    public void testLoad() throws Exception {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("/client.xml", SaajClientTest.class);

        SaajClient saajClient = applicationContext.getBean("saajClient", SaajClient.class);
        //saajClient.storeContent();
        saajClient.loadContent();
    }

}
