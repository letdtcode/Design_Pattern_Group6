package com.it.design_pattern_furniture_web.models.services.product;

import com.it.design_pattern_furniture_web.models.view_models.product_images.ProductImageCreateRequest;
import com.it.design_pattern_furniture_web.models.view_models.product_images.ProductImageGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.product_images.ProductImageUpdateRequest;
import com.it.design_pattern_furniture_web.models.view_models.product_images.ProductImageViewModel;
import com.it.design_pattern_furniture_web.models.view_models.products.ProductCreateRequest;
import com.it.design_pattern_furniture_web.models.view_models.products.ProductGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.products.ProductUpdateRequest;
import com.it.design_pattern_furniture_web.models.view_models.products.ProductViewModel;

import java.util.ArrayList;

public interface IProductService {
    int insertProduct(ProductCreateRequest request);

    boolean updateProduct(ProductUpdateRequest request);

    boolean deleteProduct(Integer productId);

    ProductViewModel retrieveProductById(Integer productId);

    ArrayList<ProductViewModel> retrieveAllProduct(ProductGetPagingRequest request);

    boolean updateQuantity(int productId, int quantity);

    int getQuantity(int productId);

    int insertImage(ProductImageCreateRequest request);

    boolean updateImage(ProductImageUpdateRequest request);

    boolean deleteImage(Integer entityId);

    ProductImageViewModel retrieveImageById(Integer entityId);

    ArrayList<ProductImageViewModel> retrieveAllImage(ProductImageGetPagingRequest request);
}
