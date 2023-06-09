package com.it.design_pattern_furniture_web.models.services.cart;

import com.it.design_pattern_furniture_web.models.view_models.cart_items.CartItemCreateRequest;
import com.it.design_pattern_furniture_web.models.view_models.cart_items.CartItemGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.cart_items.CartItemUpdateRequest;
import com.it.design_pattern_furniture_web.models.view_models.cart_items.CartItemViewModel;


import java.math.BigDecimal;
import java.util.ArrayList;

public interface ICartService {
    int insertCartItem(CartItemCreateRequest request);
    boolean updateCartItem(CartItemUpdateRequest request);
    boolean deleteCartItem(Integer cartItemId);
    CartItemViewModel retrieveCartItemById(Integer cartItemId);
    ArrayList<CartItemViewModel> retrieveAllCartItem(CartItemGetPagingRequest request);
    boolean deleteCartByUserId(int userId);
    ArrayList<CartItemViewModel> retrieveCartByUserId(int userId);
    int getCartIdByUserId(int userId);
    CartItemViewModel getCartItemContain(int cartId, int productId);
    int canUpdateQuantity(int cartItemId, int quantity);
    void updateQuantityByProductId(int productId, int quantity);
    String addProductToCart(int productId, int quantity, int userId);

    BigDecimal getTotalCartItemPriceByUserId(int userId);
}
