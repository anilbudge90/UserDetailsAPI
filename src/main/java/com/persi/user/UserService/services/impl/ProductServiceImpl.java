package com.persi.user.UserService.services.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persi.user.UserService.entities.ProductMaster;
import com.persi.user.UserService.repositories.ProductRepository;
import com.persi.user.UserService.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository productRepo;
	
	@Override
	public ProductMaster addProduct(ProductMaster usr) {
		
		return productRepo.save(usr);
	}

	@Override
	public ProductMaster updateProduct(long id, ProductMaster product) {
		
		ProductMaster oldProduct = productRepo.getByProductId(id);
		if(Objects.isNull(oldProduct))
		{
			System.out.println("Product Not Found");
		}
		else
		{
			oldProduct.setProductName(product.getProductName());
			oldProduct.setPrice(product.getPrice());
			productRepo.save(oldProduct);
		}
		return oldProduct;
	}

	@Override
	public String deleteProduct(long id) {
		String msg="";
		
		ProductMaster oldProduct = productRepo.getByProductId(id);
		
		if(Objects.isNull(oldProduct))
		{
			msg="Product Not Found";
		}
		else
		{
		productRepo.deleteById(id);
		msg="Product Deleted";
		}
		
		return msg;
	}

	@Override
	public ProductMaster getProductBuId(long id) {
		ProductMaster product = productRepo.getByProductId(id);
		return product;
	}

	@Override
	public List<ProductMaster> getAllProducts() {
		List<ProductMaster> findAllProducts = productRepo.findAll();
		return findAllProducts;
	}

}
