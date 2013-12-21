/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.bean;


import erms.model.CategoryModel;
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
@Path("/json/category")
public class JSONCategory {
 
	@GET
	@Path("/get")
	@Produces("application/json")
	public List<CategoryModel> getAllCategoryJSON(@Context UriInfo info) {
 
		String appkey = info.getQueryParameters().getFirst("appkey");
                
		return FactoryDao.getCategoryDao().getWSAllCategory(appkey);
 
	}
        
        /*
	@POST
	@Path("/post")
	@Consumes("application/json")
	public Response createProductInJSON(List<CategoryModel> categoryList) {
 
		String result = "Product created : " + categoryList;
		return Response.status(201).entity(result).build();
 
	}
        */
}
