package com.gldc.commons.businesslogic.impl;

import com.gldc.commons.bean.Domain;
import com.gldc.commons.bean.Entity;
import com.gldc.commons.bean.mapping.service.ConverterException;
import com.gldc.commons.businesslogic.ObjectNotFoundException;
import com.gldc.commons.businesslogic.ReadService;
import com.gldc.commons.data.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractReadService<D extends Domain<ID>, E extends Entity<ID>, ID extends Serializable>
        implements ReadService<D, ID> {

    @Override
    public D findById(ID id) throws ObjectNotFoundException {
        E entity = getRepository().findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
        return convertToDomain(entity);
    }

    @Override
    public D getById(ID id) {
        E entity = getRepository().findById(id).orElse(null);
        return Optional.ofNullable(entity).map(this::convertToDomain).orElse(null);
    }

    @Override
    public List<D> getAll() {
        Iterable<E> entities = getRepository().findAll();
        List<D> domainBeans = new ArrayList<>();
        entities.forEach(e -> domainBeans.add(convertToDomain(e)));
        return domainBeans;
    }

    /**
     * @return The repository object providing persistent storage.
     */
    protected abstract Repository<E, ID> getRepository();

    /**
     * Converts the entity to domain bean.
     * @param entity The entity to be converted.
     * @return The domain result of the conversion.
     * @throws ConverterException In case the conversion cannot happen.
     */
    protected abstract D convertToDomain(E entity) throws ConverterException;

}
