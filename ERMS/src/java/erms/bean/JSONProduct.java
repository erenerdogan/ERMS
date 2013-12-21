/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.bean;


import erms.model.CategoryModel;
import erms.model.ProductModel;
import erms.service.FactoryDao;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
/**
 *
 * @author eren
 */
@Path("/json/product")
public class JSONProduct {
 
	@GET
	@Path("/get")
	@Produces("application/json")
	public List<ProductModel> getAllProductJSON(@Context UriInfo info) {
 
		String appkey = info.getQueryParameters().getFirst("appkey");
                
		return FactoryDao.getProductDao().getWSAllProduct(appkey);
	}
        
}
