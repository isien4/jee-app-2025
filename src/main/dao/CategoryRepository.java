package src.main.dao;

import src.main.database.JPAUtil;
import src.main.entity.Category;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Logger;

public class CategoryRepository implements Repository<Category> {

    private static final Logger LOGGER = Logger.getLogger(CategoryRepository.class.getName());
    private EntityManager entityManager;

    public CategoryRepository() {
        this.entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
    }

    @Override
    public void insert(Category category) {
        LOGGER.info("Start Insertation Category " + category);
        entityManager.getTransaction().begin();
        entityManager.persist(category);
        entityManager.getTransaction().commit();
        LOGGER.info("End Insertation Category " + category);
    }

    @Override
    public boolean delete(int id) {
        LOGGER.info("Start Delete Category " + id);
        entityManager.getTransaction().begin();
        Category category = getById(id);
        if (category != null) {
            entityManager.remove(category);
            entityManager.getTransaction().commit();
            LOGGER.info("End Delete Category " + id);
            return true;
        } else {
            entityManager.getTransaction().commit();
            return false;
        }
    }

    @Override
    public List<Category> getAll() {
        LOGGER.info("Start GET ALL Categories");
        entityManager.getTransaction().begin();
        List<Category> categories = entityManager.createQuery("FROM Category", Category.class).getResultList();
        entityManager.getTransaction().commit();
        LOGGER.info("End GET ALL Categories");
        return categories;
    }

    @Override
    public void update(Category category) {
        LOGGER.info("Start Update Category " + category);
        entityManager.getTransaction().begin();
        entityManager.merge(category);
        entityManager.getTransaction().commit();
        LOGGER.info("End Update Category " + category);
    }

    @Override
    public Category getById(int id) {
        LOGGER.info("Start Get Category by ID " + id);
        Category category = entityManager.find(Category.class, id);
        LOGGER.info("End Get Category by ID " + id);
        return category;
    }
}
