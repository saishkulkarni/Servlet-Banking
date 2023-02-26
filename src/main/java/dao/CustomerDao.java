package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Customer;
import dto.Management;

public class CustomerDao {

EntityManagerFactory factory=Persistence.createEntityManagerFactory("dev");
EntityManager manager=factory.createEntityManager();
EntityTransaction transaction=manager.getTransaction();

public void save(Customer customer)
{
	transaction.begin();
	manager.persist(customer);
	transaction.commit();
}

public Customer find(String email)
{
	List<Customer> list=manager.createQuery("select x from Customer x where email=?1").setParameter(1, email).getResultList();
	if(list.isEmpty())
	{
		return null;
	}
	else
		return list.get(0);
}

public Customer find(long mobile)
{
	List<Customer> list=manager.createQuery("select x from Customer x where mobile=?1").setParameter(1, mobile).getResultList();
	if(list.isEmpty())
	{
		return null;
	}
	else
		return list.get(0);
}

public Customer find(int id)
{
	return manager.find(Customer.class, id);
}

public List<Customer> fetchFalse()
{
	return manager.createQuery("select x from Customer x where status=false").getResultList();
}

}
