package com.gldc.commons.businesslogic.impl;

import com.gldc.commons.bean.Domain;
import com.gldc.commons.bean.Entity;
import com.gldc.commons.bean.mapping.service.ConverterException;
import com.gldc.commons.bean.validation.service.ValidatorException;
import com.gldc.commons.businesslogic.DeleteService;
import com.gldc.commons.businesslogic.ObjectNotFoundException;
import com.gldc.commons.data.Repository;

import java.io.Serializable;

public abstract class AbstractDeleteService<D extends Domain<ID>, E extends Entity<ID>, ID extends Serializable>
        implements DeleteService<D, ID> {

    @Override
    public void delete(ID id) throws ValidatorException, ObjectNotFoundException {
        E entity = getRepository().findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
        D domain = convertToDomain(entity);
        validateAndThrow(domain);
        getRepository().deleteById(id);
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
     * Converts the entity to domain bean.
     * @param entity The entity to be converted.
     * @return The domain result of the conversion.
     * @throws ConverterException In case the conversion cannot happen.
     */
    protected abstract D convertToDomain(E entity) throws ConverterException;

}
