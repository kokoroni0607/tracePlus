package com.bdth.traceplus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author weiming.zhu
 * @date 2019/4/24 9:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page implements Serializable {
    private static final long serialVersionUID = 1L;
    private int index = 1;
    private int size = 10;
    private int total;
}
