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
 * Home object for domain model class Empresas.
 * @see sv.edu.udb.Cuponera.hibernate.Empresas
 * @author Hibernate Tools
 */
public class EmpresasHome {

	private static final Logger logger = Logger.getLogger(EmpresasHome.class.getName());

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Empresas transientInstance) {
		logger.log(Level.INFO, "persisting Empresas instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			logger.log(Level.INFO, "persist successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Empresas instance) {
		logger.log(Level.INFO, "attaching dirty Empresas instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void attachClean(Empresas instance) {
		logger.log(Level.INFO, "attaching clean Empresas instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void delete(Empresas persistentInstance) {
		logger.log(Level.INFO, "deleting Empresas instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			logger.log(Level.INFO, "delete successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete failed", re);
			throw re;
		}
	}

	public Empresas merge(Empresas detachedInstance) {
		logger.log(Level.INFO, "merging Empresas instance");
		try {
			Empresas result = (Empresas) sessionFactory.getCurrentSession().merge(detachedInstance);
			logger.log(Level.INFO, "merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "merge failed", re);
			throw re;
		}
	}

	public Empresas findById(java.lang.String id) {
		logger.log(Level.INFO, "getting Empresas instance with id: " + id);
		try {
			Empresas instance = (Empresas) sessionFactory.getCurrentSession()
					.get("sv.edu.udb.Cuponera.hibernate.Empresas", id);
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

	public List findByExample(Empresas instance) {
		logger.log(Level.INFO, "finding Empresas instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("sv.edu.udb.Cuponera.hibernate.Empresas")
					.add(Example.create(instance)).list();
			logger.log(Level.INFO, "find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "find by example failed", re);
			throw re;
		}
	}
	public List<Empresas> findAll() {
		return sessionFactory.getCurrentSession().createQuery("SELECT * FROM Empresas", Empresas.class).getResultList();
	}

}
