package com.housecallpro.pro.tests.testdata;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LineItem {

    private String name;

    private String description;

    private String quantity;

    private String unitPrice;
}
