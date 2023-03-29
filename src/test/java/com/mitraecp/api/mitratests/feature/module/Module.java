package com.mitraecp.api.mitratests.feature.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Module {

    private Long id;
    private String name;
    private Boolean businessUserDataEntry;
    private Boolean businessUserScreenSearch;
    private Long homeScreenId;
    private List<Screen> initialScreens;
    private Screen homeScreen;

}
