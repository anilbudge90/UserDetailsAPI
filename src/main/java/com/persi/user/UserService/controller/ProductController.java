package com.persi.user.UserService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.persi.user.UserService.entities.ProductMaster;
import com.persi.user.UserService.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	@PostMapping("/addProduct")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<ProductMaster> addProduct(@RequestBody ProductMaster product)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(product));
	}
	
	@PutMapping("/updateProduct/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<ProductMaster> updateProduct(@PathVariable long id, @RequestBody ProductMaster product)
	{
		ProductMaster updatedProduct = productService.updateProduct(id, product);
		return ResponseEntity.ok(updatedProduct);
	}
	
	@DeleteMapping("/deleteProduct/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> deleteProduct(@PathVariable long id)
	{
		String msg=productService.deleteProduct(id);
		return ResponseEntity.ok(msg);
	}
	
	@GetMapping("/getProduct/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<ProductMaster> getProductById(@PathVariable("id") long id)
	{
		ProductMaster product =productService.getProductBuId(id);
		return ResponseEntity.ok(product);
	}
	
	@GetMapping("/getAllProducts")
	public ResponseEntity<List<ProductMaster>> getAllProduct()
	{
		List<ProductMaster> allProducts = productService.getAllProducts();
		return ResponseEntity.ok(allProducts);
	}


}
