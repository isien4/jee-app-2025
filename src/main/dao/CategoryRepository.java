package src.main.dao;

import src.main.database.JPAUtil;
import src.main.entity.Categorie;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Logger;

public class CategoryRepository implements Repository<Categorie> {

    private static final Logger LOGGER = Logger.getLogger(CategoryRepository.class.getName());
    private EntityManager entityManager;

    public CategoryRepository() {
        this.entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
    }

    @Override
    public void insert(Categorie categorie) {
        LOGGER.info("Start Insertation Category " + categorie);
        entityManager.getTransaction().begin();
        entityManager.persist(categorie);
        entityManager.getTransaction().commit();
        LOGGER.info("End Insertation Category " + categorie);
    }

    @Override
    public boolean delete(int id) {
        LOGGER.info("Start Delete Category " + id);
        entityManager.getTransaction().begin();
        Categorie categorie = getById(id);
        if (categorie != null) {
            entityManager.remove(categorie);
            entityManager.getTransaction().commit();
            LOGGER.info("End Delete Category " + id);
            return true;
        } else {
            entityManager.getTransaction().commit();
            return false;
        }
    }

    @Override
    public List<Categorie> getAll() {
        LOGGER.info("Start GET ALL Categories");
        entityManager.getTransaction().begin();
        List<Categorie> categories = entityManager.createQuery("FROM Categorie", Categorie.class).getResultList();
        entityManager.getTransaction().commit();
        LOGGER.info("End GET ALL Categories");
        return categories;
    }

    @Override
    public void update(Categorie categorie) {
        LOGGER.info("Start Update Category " + categorie);
        entityManager.getTransaction().begin();
        entityManager.merge(categorie);
        entityManager.getTransaction().commit();
        LOGGER.info("End Update Category " + categorie);
    }

    @Override
    public Categorie getById(int id) {
        LOGGER.info("Start Get Category by ID " + id);
        Categorie categorie = entityManager.find(Categorie.class, id);
        LOGGER.info("End Get Category by ID " + id);
        return categorie;
    }
}
