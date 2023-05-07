package com.example.resell.repository;


import com.example.resell.model.SingleCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SingleCartItemRepository extends JpaRepository<SingleCartItem, Long> {

    Optional<SingleCartItem> findById(long id);

}
