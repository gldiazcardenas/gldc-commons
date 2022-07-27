package com.gldc.commons.businesslogic.impl;

import com.gldc.commons.bean.Domain;
import com.gldc.commons.bean.Entity;
import com.gldc.commons.bean.mapping.service.ConverterException;
import com.gldc.commons.bean.validation.service.ValidatorException;
import com.gldc.commons.businesslogic.ObjectNotFoundException;
import com.gldc.commons.businesslogic.UpdateService;
import com.gldc.commons.data.Repository;

import java.io.Serializable;

public abstract class AbstractUpdateService<D extends Domain<ID>, E extends Entity<ID>, ID extends Serializable>
        implements UpdateService<D, ID> {

    @Override
    public void update(ID id, D domainBean) throws ValidatorException, ConverterException {
        getRepository().findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
        validateAndThrow(domainBean);
        domainBean.setId(id);
        E entity = convertToEntity(domainBean);
        getRepository().update(id, entity);
    }

    /**
     * @return The repository object providing persistent storage.
     */
    protected abstract Repository<E, ID> getRepository();

    /**
     * @param domainBean Performs some business rule validations before updating the object.
     * @throws ValidatorException In case any business rule is broken.
     */
    protected abstract void validateAndThrow(D domainBean) throws ValidatorException;

    /**
     * Converts the domain bean to entity bean.
     * @param domainBean The domain bean to be converted.
     * @return The entity result of the conversion.
     */
    protected abstract E convertToEntity(D domainBean) throws ConverterException;

}
