package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;

import auth.AuthFilter;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import services.StoreService;
import utils.ConfigManager;


public class BaseTest {
	
	
	protected RequestSpecification requestSpec;
	protected final Logger logger = LogManager.getLogger(getClass());
	protected StoreService storeService;
	
	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		
		
		 logger.info("BaseTest setup running");
		
		requestSpec = new RequestSpecBuilder()
				 .setBaseUri(ConfigManager.getInstance().get("base.url"))
				 .setContentType(ContentType.JSON)
				 .addFilter(new AuthFilter())
				 .log(LogDetail.ALL)
				 .build();
				
		System.out.println("BASE SETUP EXECUTED");
		storeService = new StoreService(requestSpec,ConfigManager.getInstance().get("base.url"));
		
	}
	
	

}
