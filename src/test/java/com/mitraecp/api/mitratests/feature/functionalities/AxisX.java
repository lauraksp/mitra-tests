package com.mitraecp.api.mitratests.feature.functionalities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AxisX {

    private Integer id;
    private String idDatatype;
    private Boolean isCube;
    private String name;
    private Integer sonId;
    private String type;

}
