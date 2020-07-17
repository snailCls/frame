package com.zzh.logic.utils.page;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageData implements Serializable {

    //总页数
    private int pageTotal;
    //总记录数
    private int numTotal;
    //当前页，默认值为1
    private int pageNo = 1;
    //当前最大记录数，默认值为20
    private int pageSize = 20;

}
