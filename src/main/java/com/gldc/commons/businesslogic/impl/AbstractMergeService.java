package com.gldc.commons.businesslogic.impl;

import com.gldc.commons.bean.Domain;
import com.gldc.commons.bean.Entity;
import com.gldc.commons.bean.mapping.service.ConverterException;
import com.gldc.commons.bean.mapping.service.MergerException;
import com.gldc.commons.bean.validation.service.ValidatorException;
import com.gldc.commons.businesslogic.MergeService;
import com.gldc.commons.businesslogic.ObjectNotFoundException;
import com.gldc.commons.data.Repository;

import java.io.Serializable;

public abstract class AbstractMergeService<D extends Domain<ID>, E extends Entity<ID>, ID extends Serializable>
        implements MergeService<D, ID> {

    @Override
    public void merge(ID id, D newDomain) throws ValidatorException, MergerException, ObjectNotFoundException {
        E entity = getRepository().findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
        D currentDomain = convertToDomain(entity);
        merge(currentDomain, newDomain);
        validateAndThrow(currentDomain);
        entity = convertToEntity(currentDomain);
        getRepository().update(id, entity);
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

    /**
     * Converts the domain bean to entity bean.
     * @param domainBean The domain bean to be converted.
     * @return The entity result of the conversion.
     */
    protected abstract E convertToEntity(D domainBean) throws ConverterException;

    /**
     * Merges the data of the {@code newDomain} into the {@code currentDomain}.
     * @param currentDomain The current state of the object, will be modified during the merge.
     * @param newDomain The new (partial) state of the object.
     * @throws MergerException In case the merge cannot be done.
     */
    protected abstract void merge(D currentDomain, D newDomain) throws MergerException;

}
