package com.persi.user.UserService.services;

import java.util.List;

import com.persi.user.UserService.entities.ProductMaster;
import com.persi.user.UserService.entities.User;

public interface ProductService {
	
	ProductMaster addProduct(ProductMaster usr);


	ProductMaster updateProduct(long id, ProductMaster product);


	String deleteProduct(long id);


	ProductMaster getProductBuId(long id);


	List<ProductMaster> getAllProducts();

}
