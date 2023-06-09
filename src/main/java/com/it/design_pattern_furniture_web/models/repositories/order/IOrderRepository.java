package com.it.design_pattern_furniture_web.models.repositories.order;

import com.it.design_pattern_furniture_web.common.interfaces.IModifyEntity;
import com.it.design_pattern_furniture_web.common.interfaces.IRetrieveEntity;
import com.it.design_pattern_furniture_web.models.view_models.order_items.OrderItemCreateRequest;
import com.it.design_pattern_furniture_web.models.view_models.order_items.OrderItemViewModel;
import com.it.design_pattern_furniture_web.models.view_models.orders.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;

public interface IOrderRepository extends IModifyEntity<OrderCreateRequest, OrderUpdateRequest, Integer>,
        IRetrieveEntity<OrderViewModel, OrderGetPagingRequest, Integer> {
    ArrayList<OrderViewModel> retrieveDeliveredOrder(OrderGetPagingRequest request);

    ArrayList<OrderViewModel> retrieveNewOrder(OrderGetPagingRequest request);

    ArrayList<OrderViewModel> retrieveOrderByUserId(int userId);

    BigDecimal getRevenue();

    long getTotalOrder();

    OrderOverviewViewModel getOrderOverviewStatistics();

    ArrayList<OrderViewModel> getTopOrderSoon(int top);

    boolean createOrder(HttpServletRequest request, OrderCreateRequest orderReq, int userId);

    boolean clearOrder(int orderId);

    ArrayList<OrderItemViewModel> getItemByOrderId(int orderId);

    public int insertOrderItem(OrderItemCreateRequest request);

    public boolean deleteOrderItem(Integer entityId);
}
