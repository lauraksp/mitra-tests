package com.mitraecp.api.mitratests.feature.dimension;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dimension {

    private Integer id;
    private String name;
    private Boolean isCube;
    private Integer amountOfContent;
    private String idDataType;
    private String type;
    private DisplayType display;
    private Integer sonId;

}
