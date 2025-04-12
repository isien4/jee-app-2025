package src.main.dao;

import src.main.database.JPAUtil;
import src.main.entity.Product;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Logger;

public class ProductRepository implements Repository<Product> {

    private static final Logger LOGGER = Logger.getLogger(ProductRepository.class.getName());
    private EntityManager entityManager;

    public ProductRepository() {
        this.entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
    }

    @Override
    public void insert(Product product) {
        LOGGER.info("Start Insertation Product " + product);
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
        LOGGER.info("End Insertation Product " + product);
    }

    @Override
    public boolean delete(int id) {
        LOGGER.info("Start Delete Product " + id);
        entityManager.getTransaction().begin();
        Product product = getById(id);
        if (product != null) {
            entityManager.remove(product);
            entityManager.getTransaction().commit();
            LOGGER.info("End Delete Product " + id);
            return true;
        } else {
            entityManager.getTransaction().commit();
            return false;
        }
    }

    @Override
    public List<Product> getAll() {
        LOGGER.info("Start GET ALL Products");
        entityManager.getTransaction().begin();
        List<Product> products = entityManager.createQuery("FROM Product", Product.class).getResultList();
        entityManager.getTransaction().commit();
        LOGGER.info("End GET ALL Products");
        return products;
    }

    @Override
    public void update(Product product) {
        LOGGER.info("Start Update Product " + product);
        entityManager.getTransaction().begin();
        entityManager.merge(product);
        entityManager.getTransaction().commit();
        LOGGER.info("End Update Product " + product);
    }

    @Override
    public Product getById(int id) {
        LOGGER.info("Start Get Product by ID " + id);
        Product product = entityManager.find(Product.class, id);
        LOGGER.info("End Get Product by ID " + id);
        return product;
    }
    public List<Product> searchByKeyword(String keyword) {
        LOGGER.info("Start Search Product by keyword: " + keyword);
        entityManager.getTransaction().begin();
        List<Product> products = entityManager
                .createQuery("SELECT p FROM Product p WHERE LOWER(p.designation) LIKE :kw", Product.class)
                .setParameter("kw", "%" + keyword.toLowerCase() + "%")
                .getResultList();
        entityManager.getTransaction().commit();
        LOGGER.info("End Search Product by keyword: " + keyword);
        return products;
    }


}
