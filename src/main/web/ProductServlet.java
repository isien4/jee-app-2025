package src.main.web;

import src.main.dao.ProductRepository;
import src.main.dao.CategoryRepository;
import src.main.entity.Product;
import src.main.entity.Category;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = new ProductRepository();
        this.categoryRepository = new CategoryRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action") == null ? "list" : req.getParameter("action");
        RequestDispatcher dispatcher;

        switch (action) {
            case "delete":
                int id = Integer.parseInt(req.getParameter("id"));
                productRepository.delete(id);
                resp.sendRedirect("?action=list");
                break;
            case "add":
                req.setAttribute("categories", categoryRepository.getAll());
                dispatcher = req.getRequestDispatcher("views/add.jsp");
                dispatcher.forward(req, resp);
                break;
            case "edit":
                int idEdit = Integer.parseInt(req.getParameter("id"));
                Product product = productRepository.getById(idEdit);
                req.setAttribute("product", product);
                req.setAttribute("categories", categoryRepository.getAll());
                dispatcher = req.getRequestDispatcher("views/edit.jsp");
                dispatcher.forward(req, resp);
                break;
            case "update":
                // Logique de mise à jour
                break;
            default:
                String keyword = req.getParameter("keyword");
                List<Product> products;

                if (keyword != null && !keyword.trim().isEmpty()) {
                    products = productRepository.searchByKeyword(keyword);
                } else {
                    products = productRepository.getAll();
                }

                req.setAttribute("listProduct", products);
                dispatcher = req.getRequestDispatcher("views/list.jsp");
                dispatcher.forward(req, resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Product product;
        RequestDispatcher dispatcher;

        switch (action) {
            case "save":
                product = Product.builder()
                        .designation(req.getParameter("designation"))
                        .prix(Double.parseDouble(req.getParameter("prix")))
                        .quantite(Integer.parseInt(req.getParameter("quantite")))
                        .category(categoryRepository.getById(Integer.parseInt(req.getParameter("category"))))
                        .build();
                productRepository.insert(product);
                resp.sendRedirect("?action=list");
                break;
            case "update":
                product = Product.builder()
                        .id(Integer.parseInt(req.getParameter("id")))
                        .designation(req.getParameter("designation"))
                        .prix(Double.parseDouble(req.getParameter("prix")))
                        .quantite(Integer.parseInt(req.getParameter("quantite")))
                        .category(categoryRepository.getById(Integer.parseInt(req.getParameter("category"))))
                        .build();
                productRepository.update(product);
                resp.sendRedirect("?action=list");
                break;
        }
    }
}
