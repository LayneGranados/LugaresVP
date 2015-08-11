package co.gapx.lugaresvp.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Felix Ernesto Orduz Grimaldo<felix.orduz@gmail.com>
 */
public interface CRUDDAO {

    /**
     *
     * @param <T>
     * @param klass
     * @return
     */
    <T> List<T> getAll(Class<T> klass);

    /**
     *
     * @param <T>
     * @param klass
     *
     */
    <T> void save(T klass);

    /**
     *
     * @param <T>
     * @param klass
     *
     */
    <T> void delete(T klass);

    /**
     *
     * @param <T>
     * @param klass
     * @param values
     * @return
     */
    public <T> List<T> get(Class<T> klass, Map values);
}