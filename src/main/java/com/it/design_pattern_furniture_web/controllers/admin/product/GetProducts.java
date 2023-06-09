package com.it.design_pattern_furniture_web.controllers.admin.product;

import com.it.design_pattern_furniture_web.models.services.brand.BrandService;
import com.it.design_pattern_furniture_web.models.services.category.CategoryService;
import com.it.design_pattern_furniture_web.models.services.product.ProductService;
import com.it.design_pattern_furniture_web.models.view_models.brands.BrandGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.brands.BrandViewModel;
import com.it.design_pattern_furniture_web.models.view_models.categories.CategoryGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.categories.CategoryViewModel;
import com.it.design_pattern_furniture_web.models.view_models.products.ProductGetPagingRequest;
import com.it.design_pattern_furniture_web.models.view_models.products.ProductViewModel;
import com.it.design_pattern_furniture_web.utils.HibernateUtils;
import com.it.design_pattern_furniture_web.utils.ServletUtils;
import com.it.design_pattern_furniture_web.utils.StringUtils;
import com.it.design_pattern_furniture_web.utils.constants.PAGING_PARAM;
import com.it.design_pattern_furniture_web.utils.constants.SORT_BY;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@WebServlet(name = "GetProducts", value = "/admin/products")
public class GetProducts extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductGetPagingRequest req = new ProductGetPagingRequest();
        int pageIndex = StringUtils.toInt(request.getParameter("pageIndex"));
        String grid = request.getParameter("grid");
        int pageSize = PAGING_PARAM.PAGE_SIZE;
        if(pageIndex == 0){
            pageIndex = 1;
        }
        if(grid != null && !grid.equals("")) {
            int categoryId = StringUtils.toInt(request.getParameter("categoryId"));
            int brandId = StringUtils.toInt(request.getParameter("brandId"));
            String sortBy = request.getParameter("sortBy");
            String keyword = request.getParameter("keyword");
            req.setPageIndex(pageIndex);
            req.setPageSize(pageSize);

            String condition = "";
            if(categoryId != 0){
                condition += " categoryId = " + categoryId;
                request.setAttribute("categoryId",categoryId);
            }
            if(brandId != 0){
                if(!condition.equals(""))
                    condition += " and ";
                condition += " brandId = " + brandId;
                request.setAttribute("brandId",brandId);
            }
            req.setCondition(condition);
            if(!Objects.equals(sortBy, "") && sortBy != null){
                int s = StringUtils.toInt(sortBy);
                if(s == SORT_BY.BY_NAME_AZ){
                    req.setSortBy("name");
                    req.setTypeSort("ASC");
                }
                else if (s == SORT_BY.BY_NAME_ZA){
                    req.setSortBy("name");
                    req.setTypeSort("DESC");
                }
                else if (s == SORT_BY.BY_PRICE_AZ){
                    req.setSortBy("price");
                    req.setTypeSort("ASC");
                }
                else if(s == SORT_BY.BY_PRICE_ZA){
                    req.setSortBy("price");
                    req.setTypeSort("DESC");
                }
                request.setAttribute("sortBy", s);
            }
            long totalPage = (long) Math.ceil((HibernateUtils.count("Product", condition) * 1.0 / pageSize));
            if(keyword != null) {
                req.setKeyword(keyword);
                ArrayList<String> columns = new ArrayList<>();
                columns.add("name");
                req.setColumnName(columns);
                totalPage = (long) Math.ceil((HibernateUtils.count("Product"," name like '%" + keyword + "%'") * 1.0 / pageSize));
            }
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("pageIndex", req.getPageIndex());
        }
        ArrayList<ProductViewModel> products = ProductService.getInstance().retrieveAllProduct(req);

        request.setAttribute("products", products);
        String error = request.getParameter("error");
        if(error != null && !error.equals("")){
            request.setAttribute("error",error);
        }

        if(grid == null || grid.equals(""))
            ServletUtils.forward(request, response, "/views/admin/product/list-product.jsp");
        else {

            CategoryGetPagingRequest req1 = new CategoryGetPagingRequest();
            ArrayList<CategoryViewModel> categories = CategoryService.getInstance().retrieveAllCategory(req1);

            BrandGetPagingRequest req2 = new BrandGetPagingRequest();
            ArrayList<BrandViewModel> brands = BrandService.getInstance().retrieveAllBrand(req2);
            request.setAttribute("categories",categories);
            request.setAttribute("brands",brands);

            ServletUtils.forward(request, response, "/views/admin/product/grid-product.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
