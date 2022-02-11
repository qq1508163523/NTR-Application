package com.ntr.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class Weekday implements Serializable {
    private static final long serialVersionUID = 10086L;
    private String nameCn;
    private String nameEn;
    private List<Branch> branchList;
}
