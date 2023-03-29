package com.mitraecp.api.mitratests.feature.dimension;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DimensionRequest {

    private String idDataType;
    private String name;

}
