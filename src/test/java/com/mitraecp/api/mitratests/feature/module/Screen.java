package com.mitraecp.api.mitratests.feature.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Screen {

    private Integer id;
    private String name;
    private Integer moduleId;
    private Double heightExpansion;
    private Double widthExpansion;
    private Integer initialScreen;
    private Integer maskId;
    private Boolean isMask;
    private Integer sourceId;
    private List<ScreenComponent> components;
    private Screen mask;
    private Module module;
    private String backgroundColor;
    private SideMenu sideMenu;
    private List<UniversalSourceContent> universalSourceContents;



}
