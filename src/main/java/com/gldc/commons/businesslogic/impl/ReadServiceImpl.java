package com.gldc.commons.businesslogic.impl;

import com.gldc.commons.bean.Domain;
import com.gldc.commons.bean.Entity;
import com.gldc.commons.bean.mapping.service.ConverterException;
import com.gldc.commons.bean.mapping.service.ConverterService;
import com.gldc.commons.data.Repository;
import com.gldc.commons.util.ReflectionUtils;

import java.io.Serializable;

public class ReadServiceImpl <D extends Domain<ID>, E extends Entity<ID>, ID extends Serializable>
        extends AbstractReadService<D, E, ID> {

    private final Repository<E, ID> repository;
    private final ConverterService converterService;
    private final Class<D> domainClass;

    public ReadServiceImpl(Repository<E, ID> repository,
                           ConverterService converterService) {
        this.repository = repository;
        this.converterService = converterService;
        this.domainClass = ReflectionUtils.getGenericClassType(ReadServiceImpl.class, 1);
    }

    @Override
    protected Repository<E, ID> getRepository() {
        return repository;
    }

    @Override
    protected D convertToDomain(E entity) throws ConverterException {
        return converterService.convertTo(entity, domainClass);
    }

}
