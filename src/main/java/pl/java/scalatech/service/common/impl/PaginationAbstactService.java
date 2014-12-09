package pl.java.scalatech.service.common.impl;

import java.io.IOException;
import java.io.Serializable;

import lombok.extern.slf4j.Slf4j;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import pl.java.scalatech.entity.common.PKNature;
import pl.java.scalatech.service.common.PaginationService;

@Slf4j
public abstract class PaginationAbstactService<T extends PKNature<K>, K extends Serializable> implements PaginationService<T, K> {
    
    private ObjectMapper objectMapper = new ObjectMapper();
    
    @Value(value="${pageSize:20}")
    private int defaultPageSize;

    protected PagingAndSortingRepository<T, Serializable> repository;

  
    protected PaginationAbstactService() {}

    public PaginationAbstactService(PagingAndSortingRepository<T, Serializable> repository) {
        this.repository = repository;
    }

    @Override
    public String convert2Json(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonGenerationException e) {
            log.error("convertToJson ==> JsonGenerationException ", e);
        } catch (JsonMappingException e) {
            log.error("convertToJson ==> JsonMappingException ", e);
        } catch (IOException e) {
            log.error("convertToJson ==> IOException ", e);
        }
        return null;
    }

    @Override
    public Page<T> findPageByCondition(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional
    public T persist(T t) {
        return repository.save(t);
    }

    @Override
    @Transactional
    public void delete(T t) {
        repository.delete(t);
    }

    @Override
    @Transactional
    public T update(T t) {
        return repository.save(t);
    }

    @Override
    public T findById(K id) {
        return repository.findOne(id);
    }

    @Override
    public Iterable<T> getAll() {
        return repository.findAll();
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public Page<T> getPage() {
        return repository.findAll(new PageRequest(0, defaultPageSize));
    }

  
 
}
