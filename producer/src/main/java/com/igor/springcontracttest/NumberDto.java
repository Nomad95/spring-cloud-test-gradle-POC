package com.igor.springcontracttest;

import lombok.Data;

import java.io.Serializable;


@Data
public class NumberDto implements Serializable {
    private Integer number;
}
