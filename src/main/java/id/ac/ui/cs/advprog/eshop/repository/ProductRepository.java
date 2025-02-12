package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository{
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
        if (product.getProductQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        if (product.getProductName() == null || product.getProductName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty.");
        }
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public Product findById(String id){
        for(Product p: productData){
            if(p.getProductId().equals(id)){
                return p;
            }
        }
        return null;
    }

    public Product edit(Product product){
        Product existProduct = findById(product.getProductId());
        if(existProduct != null){
            int index  = productData.indexOf(existProduct);
            if (product.getProductQuantity() <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than zero.");
            }
            if (product.getProductName() == null || product.getProductName().trim().isEmpty()) {
                throw new IllegalArgumentException("Product name cannot be null or empty.");
            }
            productData.set(index, product);
            return product;
        }
        return null;
    }

    public void delete(String id){
        productData.removeIf(p -> p.getProductId().equals(id));
    }
}