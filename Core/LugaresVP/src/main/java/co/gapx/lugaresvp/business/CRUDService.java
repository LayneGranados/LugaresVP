package co.gapx.lugaresvp.business;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Felix E. Orduz G. <felix.orduz@gmail.com>
 */
public interface CRUDService {

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
     * @param klases
     */
    <T> void delete(List<T> klases);

    /**
     *
     * @param <T>
     * @param klass
     */
    <T> void refresh(T klass);

    /**
     *
     * @param <T>
     * @param klass
     * @param values
     * @return
     */
    <T> List<T> get(Class<T> klass, Map values);

    /**
     * Retorna el primer de una busqueda
     *
     * @param <T>
     * @param klass
     * @param values
     * @return
     */
    <T> Object getOne(Class<T> klass, Map values);

    /**
     *
     * @param <T>
     * @param klases
     */
    <T> void save(List<T> klases);
}
