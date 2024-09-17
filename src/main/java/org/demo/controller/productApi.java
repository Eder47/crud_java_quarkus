package org.demo.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.demo.config.ApiResponse;
import org.demo.dto.ProductDto;
import org.demo.service.ServiceProduct;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class productApi {

    @Inject
    ServiceProduct serviceProduct ;

    @Inject
    @ConfigProperty(name = "message.product.created.update")
    String productCreateUpdatedMessage;


    @GET
    @Path("/list")
    public List<ProductDto> list() {
        return serviceProduct.listProducts();
    }

    @GET
    @Path("/{id}")
    public Response getProductById(@PathParam("id") Long id) {
        ProductDto product = serviceProduct.findProductById(id);
        if (product != null) {
            return Response.ok(product).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/add")
    public Response add(ProductDto p) {
        serviceProduct.createProduct(p);
        ApiResponse<String> response = new ApiResponse<>(
                Response.Status.OK.getStatusCode(),
                productCreateUpdatedMessage
        );
        return Response.status(Response.Status.OK)
                .entity(response)
                .build();
    }

    @DELETE
    @Path("/delete")
    public Response delete(ProductDto p) {
        serviceProduct.deleteProduct(p);
        return Response.ok().build();
    }

    @PUT
    @Path("/update")
    public Response update(ProductDto p) {
        serviceProduct.updateProduct(p);
        ApiResponse<String> response = new ApiResponse<>(
                Response.Status.OK.getStatusCode(),
                productCreateUpdatedMessage
        );
        return Response.status(Response.Status.OK)
                .entity(response)
                .build();
    }

}
