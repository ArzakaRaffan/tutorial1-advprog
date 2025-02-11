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
            productData.set(index, product);
            return product;
        }
        return null;
    }

    public void delete(String id){
        productData.removeIf(p -> p.getProductId().equals(id));
    }
}