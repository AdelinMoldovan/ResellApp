package com.example.resell.dataAccessObject;


import com.example.resell.model.ShoppingCart;
import com.example.resell.model.SingleCartItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SingleCartItemDAOImpl implements SingleCartItemDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public void addShoppingCartItem(SingleCartItem singleCartItem) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(singleCartItem);
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


    public void removeShoppingCartItem(int singleCartItemId){
        Session session = null;
        try {
            session = sessionFactory.openSession();
            SingleCartItem singleCartItem = (SingleCartItem) session.get(SingleCartItem.class, singleCartItemId);
            ShoppingCart shoppingCart = singleCartItem.getShoppingCart();
            List<SingleCartItem> cartItems = shoppingCart.getSingleProductCart();
            cartItems.remove(singleCartItem);
            session.beginTransaction();
            session.delete(singleCartItem);
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


    @Override
    public void removeAllShoppingCartItems(ShoppingCart shoppingCart) {
        List<SingleCartItem> shoppingCartItems = shoppingCart.getSingleProductCart();
        for(SingleCartItem singleCartItem : shoppingCartItems){
            removeShoppingCartItem(singleCartItem.getId());
        }

    }
}
