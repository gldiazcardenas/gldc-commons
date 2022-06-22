package com.gldc.commons.http;

/**
 * Base filter class for querying data via HTTP calls.
 *
 * @author Gabriel Diaz, 17/12/2020.
 */
public abstract class Page {

    private int pageNumber;
    private int pageSize;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
