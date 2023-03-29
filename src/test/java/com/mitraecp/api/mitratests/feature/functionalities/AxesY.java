package com.mitraecp.api.mitratests.feature.functionalities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AxesY {

    private Integer id;
    private String idDataType;
    private Boolean isCube;
    private String name;
    private Integer sonId;
    private String type;

}
