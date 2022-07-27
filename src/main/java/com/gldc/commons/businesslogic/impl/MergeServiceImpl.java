package com.gldc.commons.businesslogic.impl;

import com.gldc.commons.bean.Domain;
import com.gldc.commons.bean.Entity;
import com.gldc.commons.bean.mapping.service.ConverterException;
import com.gldc.commons.bean.mapping.service.ConverterService;
import com.gldc.commons.bean.mapping.service.MergerException;
import com.gldc.commons.bean.mapping.service.MergerService;
import com.gldc.commons.bean.validation.scopes.Update;
import com.gldc.commons.bean.validation.service.ValidatorException;
import com.gldc.commons.bean.validation.service.ValidatorService;
import com.gldc.commons.data.Repository;
import com.gldc.commons.util.ReflectionUtils;

import java.io.Serializable;

public class MergeServiceImpl<D extends Domain<ID>, E extends Entity<ID>, ID extends Serializable>
        extends AbstractMergeService<D, E, ID> {

    private final Repository<E, ID> repository;
    private final ConverterService converterService;
    private final ValidatorService validatorService;
    private final MergerService mergerService;
    private final Class<D> domainClass;
    private final Class<E> entityClass;

    public MergeServiceImpl(Repository<E, ID> repository,
                            ConverterService converterService,
                            ValidatorService validatorService,
                            MergerService mergerService) {
        this.repository = repository;
        this.converterService = converterService;
        this.validatorService = validatorService;
        this.mergerService = mergerService;
        this.domainClass = ReflectionUtils.getGenericClassType(MergeServiceImpl.class, 1);
        this.entityClass = ReflectionUtils.getGenericClassType(MergeServiceImpl.class, 2);
    }

    @Override
    protected Repository<E, ID> getRepository() {
        return repository;
    }

    @Override
    protected void validateAndThrow(D domainBean) throws ValidatorException {
        validatorService.validateAndThrow(domainBean, Update.class);
    }

    @Override
    protected D convertToDomain(E entity) throws ConverterException {
        return converterService.convertTo(entity, domainClass);
    }

    @Override
    protected E convertToEntity(D domainBean) throws ConverterException {
        return converterService.convertTo(domainBean, entityClass);
    }

    @Override
    protected void merge(D currentDomain, D newDomain) throws MergerException {
        mergerService.merge(currentDomain, newDomain);
    }

}
