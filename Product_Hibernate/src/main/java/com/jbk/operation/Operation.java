package com.jbk.operation;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.jbk.config.HibernateUtil;
import com.jbk.entity.Product;

public class Operation {

	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public void saveProduct(Product product) {

		Session session = null;

		try {

			session = sessionFactory.openSession();

			Transaction transaction = session.beginTransaction(); // save , update, delete

			session.save(product);
			transaction.commit();
			System.out.println("Product Saved");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public Product getProductById(int productId) {

		Session session = sessionFactory.openSession();

		Product product = session.get(Product.class, productId);

		return product;
	}

	public void deleteProductById(int productId) {

		Session session = null;

		try {

			session = sessionFactory.openSession();

			Transaction transaction = session.beginTransaction(); // save , update, delete
			Product product = session.get(Product.class, productId);
			session.delete(product);
			transaction.commit();
			System.out.println("Product Deleted");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public void updateProduct(Product product) {

		Session session = null;

		try {

			session = sessionFactory.openSession();

			Transaction transaction = session.beginTransaction(); // save , update, delete

			session.update(product);
			transaction.commit();
			System.out.println("Product Updated");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}

	public List<Product> getProductList() {
		Session session = null;
		List<Product> list = null;

		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class); // From Product

			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;

	}

	public List<Product> restrictionEx() {
		Session session = null;
		List<Product> list = null;

		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class); // From Product
			criteria.add(Restrictions.eq("productName", "book"));
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;

	}

	public double projectionEx() {
		Session session = null;
		List list = null;
		double sum = 0;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class); // From Product
			criteria.setProjection(Projections.sum("productPrice"));
			list = criteria.list();
			sum = (Double) list.get(0);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return sum;

	}

	public List<Product> projectionEx2() {
		Session session = null;
		List list = null;
		double max = 0;
		try {
			session = sessionFactory.openSession();
			Criteria criteria1 = session.createCriteria(Product.class); // From Product
			criteria1.setProjection(Projections.max("productPrice"));
			max = (Double) criteria1.list().get(0);
			Criteria criteria2 = session.createCriteria(Product.class); // From Product
			criteria2.add(Restrictions.eq("productPrice", max));
			list = criteria2.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;

	}

	public List<Product> getListOfProductByQuery() {
		Session session = null;
		List<Product> list = null;

		try {

			session = sessionFactory.openSession();
			String hql = "From Product";
			Query<Product> query = session.createQuery(hql);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;

	}

	public List<Product> getListOfProductByName_Query(String productName) {
		Session session = null;
		List<Product> list = null;

		try {

			session = sessionFactory.openSession();
			String hql = "From Product Where productName = : pName"; // Named Parameter
			Query<Product> query = session.createQuery(hql);

			query.setParameter("pName", productName);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return list;

	}
}
