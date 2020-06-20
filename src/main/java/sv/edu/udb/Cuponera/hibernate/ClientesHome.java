package sv.edu.udb.Cuponera.hibernate;
// Generated Jun 19, 2020 11:51:31 PM by Hibernate Tools 5.4.14.Final

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Clientes.
 * @see sv.edu.udb.Cuponera.hibernate.Clientes
 * @author Hibernate Tools
 */
public class ClientesHome {

	private static final Logger logger = Logger.getLogger(ClientesHome.class.getName());

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Clientes transientInstance) {
		logger.log(Level.INFO, "persisting Clientes instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			logger.log(Level.INFO, "persist successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Clientes instance) {
		logger.log(Level.INFO, "attaching dirty Clientes instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void attachClean(Clientes instance) {
		logger.log(Level.INFO, "attaching clean Clientes instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void delete(Clientes persistentInstance) {
		logger.log(Level.INFO, "deleting Clientes instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			logger.log(Level.INFO, "delete successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete failed", re);
			throw re;
		}
	}

	public Clientes merge(Clientes detachedInstance) {
		logger.log(Level.INFO, "merging Clientes instance");
		try {
			Clientes result = (Clientes) sessionFactory.getCurrentSession().merge(detachedInstance);
			logger.log(Level.INFO, "merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "merge failed", re);
			throw re;
		}
	}

	public Clientes findById(java.lang.Integer id) {
		logger.log(Level.INFO, "getting Clientes instance with id: " + id);
		try {
			Clientes instance = (Clientes) sessionFactory.getCurrentSession()
					.get("sv.edu.udb.Cuponera.hibernate.Clientes", id);
			if (instance == null) {
				logger.log(Level.INFO, "get successful, no instance found");
			} else {
				logger.log(Level.INFO, "get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "get failed", re);
			throw re;
		}
	}

	public List findByExample(Clientes instance) {
		logger.log(Level.INFO, "finding Clientes instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("sv.edu.udb.Cuponera.hibernate.Clientes")
					.add(Example.create(instance)).list();
			logger.log(Level.INFO, "find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "find by example failed", re);
			throw re;
		}
	}
	
	public List<Clientes> findAll() {
		return sessionFactory.getCurrentSession().createQuery("SELECT * FROM Clientes", Clientes.class).getResultList();
	}

}
