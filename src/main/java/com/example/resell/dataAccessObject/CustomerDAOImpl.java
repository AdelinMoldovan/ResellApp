package com.example.resell.dataAccessObject;


import com.example.resell.model.AppPersonRole;
import com.example.resell.model.Customer;
import com.example.resell.model.Person;
import com.example.resell.model.ShoppingCart;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.PersistentObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAOImpl{

    @Autowired
    private SessionFactory sessionFactory;


    public void addCustomer(Customer customer){

        customer.getPerson().setEnabled(true);

        AppPersonRole appPersonRole = new AppPersonRole();
        appPersonRole.setAppPersonRole("ROLE_USER");
        appPersonRole.setEmailId(customer.getPerson().getEmail());

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCustomer(customer);
        customer.setShoppingCart(shoppingCart);

        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(appPersonRole);
            session.save(customer);
            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Customer getCustomerByUserName(String userName){
        Person person = null;

        try(Session session = sessionFactory.openSession()){
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Person> criteriaQuery = builder.createQuery(Person.class);
            Root<Person> root = criteriaQuery.from(Person.class);
            criteriaQuery.select(root).where(builder.equal(root.get("email"), userName));
            person = session.createQuery(criteriaQuery).getSingleResult();
        } catch (Exception e){
            e.printStackTrace();
        }
        if(person != null){
            return  person.getCustomer();
        }
        return null;
    }

}
