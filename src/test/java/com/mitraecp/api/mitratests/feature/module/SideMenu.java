package com.mitraecp.api.mitratests.feature.module;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class SideMenu {

    private Integer screenId;
    private String backgroundColor;
    private String hoverColor;
    private String fontColor;
    private String dividerColor;
    private String initialsBgColor;
    private Double fontSize;
    private String width;
    private String miniWidth;
    private String shadowlevel;
    private Boolean mini;
    private Boolean absolute;
    private SideMenuItem header;
    private SideMenuItem footer;
    private SideMenuItem overFooter;
    private Boolean roundIcon;
    private Boolean showModalAction;
    private Boolean evenClosed;
    private String typeModalAction;
    private Boolean fontBold;
    private Boolean fontItalic;
    private Integer cellGrid;
    private String fontType;
    private String bodyPadding;
    private String iconSize;
    private String activatedScreenBgColor;
    private String toggleArrowColor;
    private Boolean hasUnderscore;
    private Integer dividerFontSize;
    @JsonIgnore
    private Integer headerSideMenuItemId;
    @JsonIgnore
    private Integer footerSideMenuItemId;
    @JsonIgnore
    private Integer overFooterId;
    private List<SideMenuItem> contents;
    private String boxShadow;
    private String hoverMargin;
    private String rightBorder;
    private String hoverRadius;
    private String hoveredFontColor;
    private String btnToggleColor;
    private Boolean hideFooter;
    private Boolean hideHeader;
    private String fontIconColor;
    private String fontIconSize;
    private String fontIconHoveredColor;

}
