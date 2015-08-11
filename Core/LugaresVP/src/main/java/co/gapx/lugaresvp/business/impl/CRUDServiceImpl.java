package co.gapx.lugaresvp.business.impl;

import co.gapx.lugaresvp.business.CRUDService;
import co.gapx.lugaresvp.dao.CRUDDAO;
import co.gapx.lugaresvp.dao.HibernateUtil;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.gapx.lugaresvp.exceptions.DataAccessLayerException;

/**
 *
 * @author Felix Ernesto Orduz Grimaldo<felix.orduz@gmail.com>
 */
@Service("CRUDService")
public class CRUDServiceImpl implements CRUDService {

    @Autowired
    private CRUDDAO crudDAO;
    @Autowired
    private HibernateUtil hibernateUtil;

    /**
     *
     * @param <T>
     * @param klass
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public <T> List<T> getAll(Class<T> klass) {
        return crudDAO.getAll(klass);
    }

    /**
     *
     * @param <T>
     * @param klass
     */
    @Transactional
    @Override
    public <T> void save(T klass) {
        crudDAO.save(klass);
    }

    /**
     *
     * @param <T>
     * @param klases
     */
    @Transactional
    @Override
    public <T> void save(List<T> klases) {
        for (T k : klases) {
            this.save(k);
        }
    }

    /**
     *
     * @param <T>
     * @param klass
     * @throws DataAccessLayerException
     */
    @Transactional
    @Override
    public <T> void delete(T klass) {
        crudDAO.delete(klass);
    }

    @Transactional
    @Override
    public <T> void delete(List<T> klases) {
        for (T k : klases) {
            this.delete(k);
        }
    }

    /**
     *
     * @param <T>
     * @param klass
     */
    @Transactional
    @Override
    public <T> void refresh(T klass) {
        if (klass == null) {
            return;
        }
        hibernateUtil.initializeAndUnproxy(klass);
    }

    /**
     *
     * @param <T>
     * @param klass
     * @param values
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public <T> List<T> get(Class<T> klass, Map values) {
        return crudDAO.get(klass, values);
    }

    @Transactional(readOnly = true)
    @Override
    public <T> Object getOne(Class<T> klass, Map values) {
        List t = crudDAO.get(klass, values);
        if (t != null && t.size() > 0) {
            return t.get(0);
        }
        return null;
    }
}
