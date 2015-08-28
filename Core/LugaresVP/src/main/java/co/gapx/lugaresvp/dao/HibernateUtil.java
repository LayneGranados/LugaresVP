package co.gapx.lugaresvp.dao;

public interface HibernateUtil {
	public  <T> T initializeAndUnproxy(T entity);
}
