package com.it.design_pattern_furniture_web.models.services.order;

import com.it.design_pattern_furniture_web.models.view_models.order_items.OrderItemCreateRequest;
import com.it.design_pattern_furniture_web.models.view_models.order_items.OrderItemViewModel;
import com.it.design_pattern_furniture_web.models.view_models.orders.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;

public interface IOrderService {
    int insertOrder(OrderCreateRequest request);

    boolean updateOrder(OrderUpdateRequest request);

    boolean deleteOrder(Integer orderId);

    OrderViewModel retrieveOrderById(Integer orderId);

    ArrayList<OrderViewModel> retrieveAllOrder(OrderGetPagingRequest request);

    ArrayList<OrderViewModel> retrieveOrderByUserId(int userId);

    ArrayList<OrderViewModel> retrieveDeliveredOrder(OrderGetPagingRequest request);

    ArrayList<OrderViewModel> retrieveNewOrder(OrderGetPagingRequest request);

    long getTotalOrder();

    BigDecimal getRevenue();

    ArrayList<OrderViewModel> getTopOrderSoon(int top);

    OrderOverviewViewModel getOrderOverviewStatistics();

    boolean createOrder(HttpServletRequest request, OrderCreateRequest orderReq, int userId);

    boolean clearOrder(int orderId);

    ArrayList<OrderItemViewModel> getItemByOrderId(int orderId);

    public int insertOrderItem(OrderItemCreateRequest request);

    public boolean deleteOrderItem(Integer entityId);
}
