package com.gldc.commons.businesslogic.impl;

import com.gldc.commons.bean.Domain;
import com.gldc.commons.businesslogic.CreateService;
import com.gldc.commons.businesslogic.ObjectNotFoundException;
import com.gldc.commons.businesslogic.ServiceFacade;
import com.gldc.commons.businesslogic.DeleteService;
import com.gldc.commons.businesslogic.MergeService;
import com.gldc.commons.businesslogic.ReadService;
import com.gldc.commons.businesslogic.UpdateService;

import java.io.Serializable;
import java.util.List;

public class ServiceFacadeImpl<D extends Domain<ID>, ID extends Serializable> implements ServiceFacade<D, ID> {

    private final CreateService<D, ID> createService;
    private final ReadService<D, ID> readService;
    private final UpdateService<D, ID> updateService;
    private final DeleteService<D, ID> deleteService;
    private final MergeService<D, ID> mergeService;

    public ServiceFacadeImpl(CreateService<D, ID> createService,
                             ReadService<D, ID> readService,
                             UpdateService<D, ID> updateService,
                             DeleteService<D, ID> deleteService,
                             MergeService<D, ID> mergeService) {
        this.createService = createService;
        this.readService = readService;
        this.updateService = updateService;
        this.deleteService = deleteService;
        this.mergeService = mergeService;
    }

    @Override
    public ID create(D domainBean) {
        return createService.create(domainBean);
    }

    @Override
    public void delete(ID id) throws ObjectNotFoundException {
        deleteService.delete(id);
    }

    @Override
    public void update(ID id, D domainBean) throws ObjectNotFoundException {
        updateService.update(id, domainBean);
    }

    @Override
    public void merge(ID id, D domainBean) throws ObjectNotFoundException {
        mergeService.merge(id, domainBean);
    }

    @Override
    public D findById(ID id) throws ObjectNotFoundException {
        return readService.findById(id);
    }

    @Override
    public D getById(ID id) {
        return readService.getById(id);
    }

    @Override
    public List<D> getAll() {
        return readService.getAll();
    }

}
