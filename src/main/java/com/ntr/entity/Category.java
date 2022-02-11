package com.ntr.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
public class Category implements Serializable {
    private static final long serialVersionUID = 114514L;
    private List<Integer> yearList = Arrays.asList(2009,2010,2011,2012,2013,2014,2015,2016,2017,2018,2019,2020,2021,2022);
    private List<Integer> monthList = Arrays.asList(1,4,7,10);
    private List<String> statusList = Arrays.asList("连载","完结","未播放");
    private List<Genre> genreList;
    private List<String> sortList = Collections.singletonList("更新时间");
}
