package com.bjpowernode.crm.vo;


import java.util.List;

public class PaginationVO<T> {
    private int total;
    private List<T> dataLsit;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getDataLsit() {
        return dataLsit;
    }

    public void setDataLsit(List<T> dataLsit) {
        this.dataLsit = dataLsit;
    }
}
