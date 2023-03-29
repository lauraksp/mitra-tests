package com.mitraecp.api.mitratests.feature.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleRequest {

        private Long id;
        private Long key;
        private String name;
        private Boolean editMode;
        private Screen homeScreen;
        private List<Screen> initialScreens;
        private Boolean businessUserDataEntry;
        private Boolean businessUserScreenSearch;
        private Long homeScreenId;

}
