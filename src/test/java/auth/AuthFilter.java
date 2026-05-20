package auth;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import utils.TokenManager;

public class AuthFilter implements Filter{

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {

        requestSpec.header("api_key",TokenManager.getToken());
		
		return ctx.next(requestSpec, responseSpec);
	}
	
	
	

}
