package com.mitraecp.api.mitratests.feature.functionalities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SourceRequest {

    private List<AxesY> axesY;
    private List<AxisDisplay> axisDisplay;
    private List<EscapedSelections> escapedSelections;
    private List<Filters> filters;
    private Boolean hasFilter;
    private Boolean hasOrdenation;
    private Boolean isEscapeSelection;
    private String name;
    private Integer screenComponentId;
    private Boolean showAllAxes;
    private List<SimpleBlocks> simpleBlocks;
    private List<SimpleBlocksWithRelationship> simpleBlocksWithRelationship;
    private List<SimpleBlocksWithVirtualCube> simpleBlocksWithVirtualCube;
    private List<SimplesBlocksWithAlgorithm> simplesBlocksWithAlgorithm;


}
