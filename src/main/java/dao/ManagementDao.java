package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Management;

public class ManagementDao 
{
EntityManagerFactory factory=Persistence.createEntityManagerFactory("dev");
EntityManager manager=factory.createEntityManager();
EntityTransaction transaction=manager.getTransaction();

public void save(Management management)
{
	transaction.begin();
	manager.persist(management);
	transaction.commit();
}

public Management find(int id)
{
	return manager.find(Management.class, id);
}
}
