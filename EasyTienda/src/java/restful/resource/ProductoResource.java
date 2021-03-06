/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restful.resource;

import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import restful.Model.ProductoModel;
import restful.service.ProductoService;

/**
 *
 * @author SENA
 */
@Path("productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ProductoResource {
    
    ProductoService servicio = new ProductoService();

    @GET
    public ArrayList<ProductoModel> getProductos() {

        return servicio.getProductos();
    }

    @Path("/{ProductoId}")
    @GET
    public ProductoModel getProducto(@PathParam("ProductoId") int id) {

        return servicio.getProducto(id);
    }

    @POST
    public ProductoModel addProducto(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        ProductoModel producto = gson.fromJson(JSON, ProductoModel.class);
        return servicio.addProducto(producto);
    }

    @DELETE
    @Path("/{ProductoId}")
    public String delProducto(@PathParam("ProductoId") int id) {

        return servicio.delProducto(id);

    }

    @PUT
    public ProductoModel updateProducto(String JSON) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        ProductoModel producto = gson.fromJson(JSON, ProductoModel.class);
        return servicio.updateProducto(producto);
    }

}
