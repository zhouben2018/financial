package com.zben.saller.repository;

import com.zben.entity.Order;
import com.zben.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: zben
 * @Description:
 * JpaSpecificationExecutor:可以做复杂的条件查询
 * @Date: 09:40 2019/6/12
 */
public interface OrderRepository extends JpaRepository<Order, String>, JpaSpecificationExecutor<Product> {
}
