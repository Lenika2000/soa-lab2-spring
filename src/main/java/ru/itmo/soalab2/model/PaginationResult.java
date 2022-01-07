package ru.itmo.soalab2.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "paginationResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaginationResult {
    @XmlElement
    private final int pageSize;
    @XmlElement
    private final int pageIndex;
    @XmlElement
    private final long totalItems;
    @XmlElementWrapper(name = "list")
    @XmlElement
    private final City[] items;

    public PaginationResult() {
        this.items = new City[]{new City()};
        pageSize = 0;
        pageIndex = 0;
        totalItems = 0;
    }

    public PaginationResult(int pageSize, int pageIndex, long totalItems, City[] list) {
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.totalItems = totalItems;
        this.items = list;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public City[] getList() {
        return items;
    }
}
