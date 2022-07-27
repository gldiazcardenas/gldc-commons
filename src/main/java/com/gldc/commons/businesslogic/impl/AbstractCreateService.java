package com.gldc.commons.businesslogic.impl;

import com.gldc.commons.bean.Domain;
import com.gldc.commons.bean.Entity;
import com.gldc.commons.bean.mapping.service.ConverterException;
import com.gldc.commons.bean.validation.service.ValidatorException;
import com.gldc.commons.businesslogic.CreateService;
import com.gldc.commons.data.Repository;

import java.io.Serializable;

public abstract class AbstractCreateService<D extends Domain<ID>, E extends Entity<ID>, ID extends Serializable>
        implements CreateService<D, ID> {

    @Override
    public ID create(D domainBean) throws ValidatorException, ConverterException {
        validateAndThrow(domainBean);
        E entity = convertToEntity(domainBean);
        return getRepository().insert(entity);
    }

    /**
     * @return The repository object providing persistent storage.
     */
    protected abstract Repository<E, ID> getRepository();

    /**
     * @param domainBean Performs some business rule validations before creating the object.
     * @throws ValidatorException In case any business rule is broken.
     */
    protected abstract void validateAndThrow(D domainBean) throws ValidatorException;

    /**
     * Converts the domain bean to entity bean.
     * @param domainBean The domain bean to be converted.
     * @return The entity result of the conversion.
     * @throws ConverterException In case the conversion cannot happen.
     */
    protected abstract E convertToEntity(D domainBean) throws ConverterException;

}
