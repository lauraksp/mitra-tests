package com.mitraecp.api.mitratests.feature.cube;

import com.mitraecp.api.mitratests.feature.dimension.Dimension;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CubeRequest {

    private Integer id;
    private String name;
    private String cubeGroup;
    private String dataType;
    private List<Dimension> dimensions;
    private List<Integer> dimensionsId;
    private Boolean encrypted;
    private Integer size;
    private String heatColor;

}
