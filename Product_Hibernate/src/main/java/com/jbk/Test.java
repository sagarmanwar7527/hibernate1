package com.jbk;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.jbk.entity.Product;
import com.jbk.operation.Operation;

public class Test {

	public static void main(String[] args) {

		Operation operation = new Operation();
		Product product = new Product("book", 20, 20, "Stationary");

		// operation.saveProduct(product);

		Product product2 = new Product(2, "Pencil", 1, 1, "Stationary");
		// operation.updateProduct(product2);

		/*
		 * List<Product> list = operation.restrictionEx();
		 * 
		 * for (Product product3 : list) { System.out.println(product3); }
		 */

		/*
		 * double sum = operation.projectionEx(); System.out.println(sum);
		 */

		// List<Product> list = operation.projectionEx2();
		// System.out.println(list);
		
		
	/*	List<Product> lsit = operation.getListOfProductByQuery();
		
		for (Product product3 : lsit) {
			System.out.println(product3);
		}  */
		
		List<Product> list = operation.getListOfProductByName_Query("book");
		
		for (Product product3 : list) {
			System.out.println(product3);
		}
		
		
	}

}
