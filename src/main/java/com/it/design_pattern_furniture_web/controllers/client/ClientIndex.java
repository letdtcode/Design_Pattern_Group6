package com.it.design_pattern_furniture_web.controllers.client;

import com.it.design_pattern_furniture_web.models.services.category.CategoryService;
import com.it.design_pattern_furniture_web.models.services.product.ProductService;
import com.it.design_pattern_furniture_web.models.view_models.categories.CategoryGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.categories.CategoryViewModel;
import com.it.design_pattern_furniture_web.models.view_models.products.ProductGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.products.ProductViewModel;
import com.it.design_pattern_furniture_web.utils.ServletUtils;
import com.it.design_pattern_furniture_web.utils.constants.CATEGORY_STATUS;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ClientIndex", value = "/home")
public class ClientIndex extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ProductGetPagingRequest req1 = new ProductGetPagingRequest();
        ArrayList<ProductViewModel> products = ProductService.getInstance().retrieveAllProduct(req1);
        products.sort((o1, o2) -> (int) (o2.getAvgRating() - o1.getAvgRating()));
        ArrayList<ProductViewModel> popularProducts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            try {
                popularProducts.add(products.get(i));
            } catch (Exception e) {
                break;
            }
        }
        CategoryGetPagingRequest req2 = new CategoryGetPagingRequest();
        ArrayList<CategoryViewModel> categories = CategoryService.getInstance().retrieveAllCategory(req2);
        categories.removeIf(x -> x.getParentCategoryId() != 0 || x.getStatus() == CATEGORY_STATUS.IN_ACTIVE);
        request.setAttribute("products", popularProducts);
        request.setAttribute("categories", categories);
        ServletUtils.forward(request, response, "/views/client/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
