package com.ezgicinan.springbootapplication.repository;

import com.ezgicinan.springbootapplication.model.MarketOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketOrderRepository extends JpaRepository<MarketOrder, Integer> {


}
