
package pl.java.scalatech.service.common;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.java.scalatech.entity.common.PKNature;


public interface PaginationService<T extends PKNature<K>, K extends Serializable> {

    Page<T> findPageByCondition(Pageable pageable);

    String convert2Json(Object object);

    Iterable<T> getAll();

    Page<T> getPage();

    T persist(T t);

    void delete(T t);

    T update(T t);

    T findById(K id);
    
    long count();


}
