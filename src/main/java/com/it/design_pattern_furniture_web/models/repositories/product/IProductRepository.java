package com.it.design_pattern_furniture_web.models.repositories.product;

import com.it.design_pattern_furniture_web.common.interfaces.IModifyEntity;
import com.it.design_pattern_furniture_web.common.interfaces.IRetrieveEntity;
import com.it.design_pattern_furniture_web.models.view_models.product_images.ProductImageCreateRequest;
import com.it.design_pattern_furniture_web.models.view_models.product_images.ProductImageGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.product_images.ProductImageUpdateRequest;
import com.it.design_pattern_furniture_web.models.view_models.product_images.ProductImageViewModel;
import com.it.design_pattern_furniture_web.models.view_models.products.ProductCreateRequest;
import com.it.design_pattern_furniture_web.models.view_models.products.ProductGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.products.ProductUpdateRequest;
import com.it.design_pattern_furniture_web.models.view_models.products.ProductViewModel;

import java.util.ArrayList;

public interface IProductRepository extends IModifyEntity<ProductCreateRequest, ProductUpdateRequest, Integer>,
        IRetrieveEntity<ProductViewModel, ProductGetPagingRequest, Integer> {
    boolean updateQuantity(int productId, int quantity);

    int getQuantity(int productId);

    int insertImage(ProductImageCreateRequest request);

    boolean updateImage(ProductImageUpdateRequest request);

    boolean deleteImage(Integer entityId);

    ProductImageViewModel retrieveImageById(Integer entityId);

    ArrayList<ProductImageViewModel> retrieveAllImage(ProductImageGetPagingRequest request);
}
