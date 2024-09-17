package org.demo.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.demo.entities.Product;


import java.util.List;

@ApplicationScoped
public class ProductRepository {

    @Inject
    EntityManager em;

    @Transactional
    public void createProduct(Product p){
        em.persist(p);
    }

    @Transactional
    public Product updateProduct(Product p) {
        return em.merge(p);
    }

    @Transactional
    public Product findProductById(Long id) {
        return em.find(Product.class, id);
    }


    @Transactional
    public void deleteProduct(Product p){
        em.persist(p);
    }

    @Transactional
    public List<Product> listProduct() {
        List<Product> products = em.createQuery("select p from Product p", Product.class).getResultList();
        return products;
    }

}
