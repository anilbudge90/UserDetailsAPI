package com.persi.user.UserService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.persi.user.UserService.entities.ProductMaster;
import com.persi.user.UserService.entities.User;

public interface ProductRepository extends JpaRepository<ProductMaster, Long>{

	ProductMaster getByProductId(long productId);

}
