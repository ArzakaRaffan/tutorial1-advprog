package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductServiceImpl productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void accessProductListPage() throws Exception {
        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk());
    }

    @Test
    public void accessCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk());
    }

    @Test
    public void createProductRedirectToList() throws Exception {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6a");
        product.setProductName("Sabun Cap Ayam");
        product.setProductQuantity(10);

        mockMvc.perform(post("/product/create")
                .param("productId", "eb558e9f-1c39-460e-8860-71af6af63bd6a")
                .param("productName", "Sabun Cap Ayam")
                .param("productQuantity", "10"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("list"));
    }

    @Test
    public void accessEditProductPageWhenProductExists() throws Exception {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6a");
        product.setProductName("Sabun Cap Ayam");
        product.setProductQuantity(10);

        when(productService.findById("eb558e9f-1c39-460e-8860-71af6af63bd6a")).thenReturn(product);

        mockMvc.perform(get("/product/edit/eb558e9f-1c39-460e-8860-71af6af63bd6a"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("product"));
    }

    @Test
    public void editProductWhenProductNotExists() throws Exception {
        when(productService.findById("Invalid ID")).thenReturn(null);
        mockMvc.perform(get("/product/edit/Invalid ID"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }

    @Test
    public void updateProductRedirectToList() throws Exception{
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6a");
        product.setProductName("Sabun Cap Ayam");
        product.setProductQuantity(10);

        mockMvc.perform(post("/product/edit/eb558e9f-1c39-460e-8860-71af6af63bd6a")
                .param("productName", "Sabun Cap Bango")
                .param("productQuantity", "100"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }

    @Test
    public void accessDeletePageIfProductExists() throws Exception{
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6a");
        product.setProductName("Sabun Cap Ayam");
        product.setProductQuantity(10);

        when(productService.findById("eb558e9f-1c39-460e-8860-71af6af63bd6a")).thenReturn(product);

        mockMvc.perform(get("/product/delete/eb558e9f-1c39-460e-8860-71af6af63bd6a"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteExistingProductThenRedirect() throws Exception{
        mockMvc.perform(post("/product/delete/eb558e9f-1c39-460e-8860-71af6af63bd6a"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));
    }

    @Test
    public void deleteNonExistingProductThenRedirect() throws Exception{
        when(productService.findById("nonexistingproduct")).thenReturn(null);

        mockMvc.perform(get("/product/delete/nonexistingproduct"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

    }



}