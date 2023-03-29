package com.mitraecp.api.mitratests.feature.functionalities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabelDetails {

    private Integer actionId;
    private Boolean activeAction;
    private Boolean activeCustomModal;
    private Boolean activeRegisterDetailsModal;
    private Boolean customModalFloating;
    private Integer customModalHeight;
    private Integer customModalScreenId;
    private String customModalTransitionSide;
    private String customModalWidth;
    private Boolean rebuildLayout;
    private Integer registerDetailsModalWidth;
    private Integer screenComponentId;

}
