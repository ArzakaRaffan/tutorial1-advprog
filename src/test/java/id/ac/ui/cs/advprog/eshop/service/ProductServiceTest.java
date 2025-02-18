package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void createTest() throws Exception {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6a");
        product.setProductName("Sabun Cap Ayam");
        product.setProductQuantity(10);

        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertEquals(product.getProductName(), createdProduct.getProductName());
        assertEquals(product.getProductId(), createdProduct.getProductId());
        assertEquals(product.getProductQuantity(), createdProduct.getProductQuantity());
    }

    @Test
    public void findAllTest() throws Exception{
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6a");
        product1.setProductName("Sabun Cap Ayam");
        product1.setProductQuantity(10);

        Product product2 = new Product();
        product2.setProductId("c9e08e9f-1c39-460e-8860-71af6af63bb7e");
        product2.setProductName("Soda Cap Kaki");
        product2.setProductQuantity(20);

        List<Product> productList = Arrays.asList(product1, product2);
        Iterator<Product> productIterator = productList.iterator();

        when(productRepository.findAll()).thenReturn(productIterator);

        List<Product> result = productService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6a", result.get(0).getProductId());
        assertEquals("Sabun Cap Ayam", result.get(0).getProductName());
        assertEquals(10, result.get(0).getProductQuantity());
        assertEquals("c9e08e9f-1c39-460e-8860-71af6af63bb7e", result.get(1).getProductId());
        assertEquals("Soda Cap Kaki", result.get(1).getProductName());
        assertEquals(20, result.get(1).getProductQuantity());
    }

    @Test
    public void findByIdTest() throws Exception{
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6a");
        product.setProductName("Sabun Cap Ayam");
        product.setProductQuantity(10);

        when(productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6a")).thenReturn(product);

        Product result = productService.findById("eb558e9f-1c39-460e-8860-71af6af63bd6a");

        assertNotNull(result);
        assertEquals("Sabun Cap Ayam", result.getProductName());
        assertEquals(10, result.getProductQuantity());
    }

    @Test
    public void editTest() throws Exception {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6a");
        product.setProductName("Sabun Cap Ayam");
        product.setProductQuantity(10);

        when(productRepository.edit(product)).thenReturn(product);

        Product result = productService.edit(product);

        assertNotNull(result);
        assertEquals("Sabun Cap Ayam", result.getProductName());
        assertEquals(10, result.getProductQuantity());
    }

    @Test
    public void deleteTest() throws Exception{
        String productIDOnlyForTest = "abc";

        doNothing().when(productRepository).delete(productIDOnlyForTest);
        productService.delete(productIDOnlyForTest);
    }

}
