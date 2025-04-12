package src.main.dao;

import src.main.database.JPAUtil;
import src.main.entity.produit;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Logger;

public class ProductRepository implements Repository<produit> {

    private static final Logger LOGGER = Logger.getLogger(ProductRepository.class.getName());
    private EntityManager entityManager;

    public ProductRepository() {
        this.entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
    }

    @Override
    public void insert(produit produit) {
        LOGGER.info("Start Insertation Product " + produit);
        entityManager.getTransaction().begin();
        entityManager.persist(produit);
        entityManager.getTransaction().commit();
        LOGGER.info("End Insertation Product " + produit);
    }

    @Override
    public boolean delete(int id) {
        LOGGER.info("Start Delete Product " + id);
        entityManager.getTransaction().begin();
        produit produit = getById(id);
        if (produit != null) {
            entityManager.remove(produit);
            entityManager.getTransaction().commit();
            LOGGER.info("End Delete Product " + id);
            return true;
        } else {
            entityManager.getTransaction().commit();
            return false;
        }
    }

    @Override
    public List<produit> getAll() {
        LOGGER.info("Start GET ALL Products");
        entityManager.getTransaction().begin();
        List<produit> produits = entityManager.createQuery("FROM produit", produit.class).getResultList();
        entityManager.getTransaction().commit();
        LOGGER.info("End GET ALL Products");
        return produits;
    }

    @Override
    public void update(produit produit) {
        LOGGER.info("Start Update Product " + produit);
        entityManager.getTransaction().begin();
        entityManager.merge(produit);
        entityManager.getTransaction().commit();
        LOGGER.info("End Update Product " + produit);
    }

    @Override
    public produit getById(int id) {
        LOGGER.info("Start Get Product by ID " + id);
        produit produit = entityManager.find(produit.class, id);
        LOGGER.info("End Get Product by ID " + id);
        return produit;
    }
    public List<produit> searchByKeyword(String keyword) {
        LOGGER.info("Start Search Product by keyword: " + keyword);
        entityManager.getTransaction().begin();
        List<produit> produits = entityManager
                .createQuery("SELECT p FROM produit p WHERE LOWER(p.designation) LIKE :kw", produit.class)
                .setParameter("kw", "%" + keyword.toLowerCase() + "%")
                .getResultList();
        entityManager.getTransaction().commit();
        LOGGER.info("End Search Product by keyword: " + keyword);
        return produits;
    }


}
