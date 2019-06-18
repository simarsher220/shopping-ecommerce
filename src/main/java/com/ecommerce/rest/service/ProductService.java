package com.ecommerce.rest.service;

import com.ecommerce.rest.entity.UserProduct;
import com.ecommerce.rest.dao.ProductRepository;
import com.ecommerce.rest.dao.UserProductRepository;
import com.ecommerce.rest.entity.Product;
import com.ecommerce.rest.entity.User;
import com.ecommerce.rest.error.exception.GenericException;
import com.ecommerce.rest.mapper.ProductMapper;
import com.ecommerce.rest.mapper.UserProductMapper;
import com.ecommerce.rest.model.product.ProductsResponse;
import com.ecommerce.rest.model.product.PurchasedProductsResponse;
import com.ecommerce.rest.model.product.RateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private UserProductRepository userProductRepository;
    private UserService userService;

    @Autowired
    public ProductService(ProductRepository productRepository, UserProductRepository userProductRepository, UserService userService) {
        this.productRepository = productRepository;
        this.userProductRepository = userProductRepository;
        this.userService = userService;
    }

    public ProductsResponse getProductsByCategory(Integer categoryId) {
        return ProductMapper.getProductsByCategory(productRepository.findByCategory_categoryId(categoryId));
    }

    public Product getProductById(Integer productId) {
        Product product = productRepository.findByProductId(productId);
        return product;
    }

    public void rateProduct(String authorization, Integer productId, RateProductRequest rateProductRequest) throws GenericException {
        User user = userService.getValidUser(authorization);
        Product product = productRepository.findByProductId(productId);
        UserProduct userProduct = userProductRepository.findByUserAndProduct(user, product);
        if (userProduct == null) {
            throw new GenericException("Product not purchased yet!!", HttpStatus.BAD_REQUEST);
        }
        if (userProduct.getRating() != null) {
            throw new GenericException("Product already rated!!", HttpStatus.BAD_REQUEST);
        }
        userProduct.setRating(rateProductRequest.getRating());
        if (product.getRating() == null) {
            product.setRating(rateProductRequest.getRating());
            product.setRatedBy(1);
        }
        else {
            product.setRating(((product.getRating() * product.getRatedBy()) + (rateProductRequest.getRating())) / (product.getRatedBy() + 1));
        }
        updateProduct(product);
        userProduct.setProduct(product);
        userProductRepository.saveAndFlush(userProduct);
    }

    public void updateRating(String authorization, Integer productId, RateProductRequest rateProductRequest) throws GenericException {
        User user = userService.getValidUser(authorization);
        Product product = productRepository.findByProductId(productId);
        UserProduct userProduct = userProductRepository.findByUserAndProduct(user, product);
        if (userProduct == null) {
            throw new GenericException("Product not purchased yet!!", HttpStatus.BAD_REQUEST);
        }
        if (userProduct.getRating() == null) {
            rateProduct(authorization, productId, rateProductRequest);
            return ;
        }
        Double initialRating = userProduct.getRating();
        userProduct.setRating(rateProductRequest.getRating());
        product.setRating(((product.getRating() * product.getRatedBy()) + rateProductRequest.getRating() - initialRating) / (product.getRatedBy()));
        updateProduct(product);
        userProduct.setProduct(product);
        userProductRepository.saveAndFlush(userProduct);
    }

    public PurchasedProductsResponse getPurchasedProducts(String authorization) throws GenericException {
        User user = userService.getValidUser(authorization);
        List<UserProduct> userProducts = userProductRepository.findByUser(user);
        if (CollectionUtils.isEmpty(userProducts)) {
            throw new GenericException("No products purchased yet!!", HttpStatus.BAD_REQUEST);
        }
        return UserProductMapper.getPurchasedProducts(userProducts);
    }

    public void updateProduct(Product product) {
        productRepository.saveAndFlush(product);
    }
}
