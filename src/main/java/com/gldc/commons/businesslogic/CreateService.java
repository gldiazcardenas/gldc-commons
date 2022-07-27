package com.gldc.commons.businesslogic;

import com.gldc.commons.bean.Domain;

import java.io.Serializable;

public interface CreateService<D extends Domain<ID>, ID extends Serializable> {

    /**
     * Creates a new object in the persistent data repository.
     *
     * @param domainBean The domain bean to be inserted.
     * @return The ID assigned to the object inserted.
     */
    ID create(D domainBean);

}
