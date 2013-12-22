/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erms.bean;

import erms.model.OrderModel;
import erms.model.ProductModel;
import erms.model.TableModel;
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
@Path("/json/order")
public class JSONOrder {

    @GET
    @Path("/get")
    @Produces("application/json")
    public Response addOrderJSON(@Context UriInfo info) {

        String productID = info.getQueryParameters().getFirst("pid");
        String orderNumber = info.getQueryParameters().getFirst("onumber");
        ProductModel pm = new ProductModel();
        pm.setProductID(Integer.parseInt(productID));

        OrderModel om = new OrderModel();
        om.setOrderNum(Integer.parseInt(orderNumber));
        om.setOrderProductModel(pm);
        FactoryDao.getOrderDao().addWSOrder(om);
        String result = "Success";
        return Response.status(200).entity(result).build();
    }

    @GET
    @Path("/edit")
    @Produces("application/json")
    public Response editOrderStatusJSON(@Context UriInfo info) {

        FactoryDao.getOrderDao().editStatus();

        String result = "Edit";
        return Response.status(200).entity(result).build();
    }
}
