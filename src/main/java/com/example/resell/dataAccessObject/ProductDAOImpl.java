package com.example.resell.dataAccessObject;


import com.example.resell.model.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.resell.dataAccessObject.ProductDAO;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO{


    @Autowired
    private SessionFactory sessionFactory;

    public void addProduct(Product product){
        Session session = null;
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            if (session != null) {

                session.close();
            }
        }
    }

    public void deleteProduct(int productId){
        Session session = null;
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            Product product = (Product) session.get(Product.class, productId);
            session.delete(product);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();

        } finally {
            if(session != null){
                session.close();
            }
        }
    }

    public void updateProduct(Product product) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    public Product getProductById(int productId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Product product = (Product) session.get(Product.class, productId);
            session.getTransaction().commit();
            return product;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> getAllProducts () {
        List<Product> products = new ArrayList<Product>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
            Root<Product> root = criteriaQuery.from(Product.class);
            criteriaQuery.select(root);
            products = session.createQuery(criteriaQuery).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
            return products;
    }
}
