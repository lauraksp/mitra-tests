package com.mitraecp.api.mitratests.feature.functionalities;

import com.google.gson.Gson;
import com.mitraecp.api.mitratests.feature.cube.CubeRequest;
import com.mitraecp.api.mitratests.feature.dimension.Dimension;
import com.mitraecp.api.mitratests.feature.dimension.DimensionsTests;
import com.mitraecp.api.mitratests.feature.login.FeatureLoginTest;
import com.mitraecp.api.mitratests.feature.module.Module;
import com.mitraecp.api.mitratests.feature.module.ModuleRequest;
import com.mitraecp.api.mitratests.utils.Constants;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.given;

public class FunctionalitiesTests {

    private static String TOKEN = "";

    @BeforeClass
    public static void init() {
        TOKEN = FeatureLoginTest.getTokenLogin();
    }

    @Test
    public void TestingFeatures(){

        ModuleRequest module = new ModuleRequest();
        module.setKey(null);
        module.setName("Modulo ".concat(String.valueOf(new Date())));
        module.setEditMode(true);

        Response createModule = given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + TOKEN)
                .body(module)
                .post(Constants.MODULES_PATH)
                .then()
                .extract()
                .response();

        Module moduleResponse = new Gson().fromJson(createModule.getBody().asString(), Module.class);

        Response responseDimension = DimensionsTests.postDimensionsResponse();
        Dimension dimension = new Gson().fromJson(responseDimension.getBody().asString(), Dimension.class);

        CubeRequest cube = new CubeRequest();
        cube.setCubeGroup("Cube test");
        cube.setDimensionsId(Collections.singletonList(dimension.getId()));
        cube.setDataType("DOUBLE");
        cube.setName("Cube create test".concat(String.valueOf(LocalDateTime.now())));

        given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + TOKEN)
                .body(cube)
                .post(Constants.CUBES_PATH);

        TableRequest table = new TableRequest();
        table.setBackgroundColor("none");
        table.setBorderRadius("0px 0px 0px 0px");
        table.setCanDrill(true);
        table.setCanExportXls(false);
        table.setCanFocus(false);
        table.setCanSwitchComponent(false);
        table.setChartIsGradient(true);
        table.setDisplayLegend(true);
        table.setConfigDTO(table.getConfigDTO());
        table.setDocumentType("bodyList");
        table.setDynamicImage(false);
        table.setEnableHideColumns(false);
        table.setEnabledSelectAll(false);
        table.setEnabledStateView(true);
        table.setFont("Roboto,sans-serif");
        table.setFontColor("#000000");
        table.setFontSize("1.6");
        table.setFunnelPercentage(false);
        table.setH(250);
        table.setHasBlocks(false);
        table.setHasBolder(false);
        table.setHasItalic(false);
        table.setHasUnderscore(false);
        table.setIsChildrenOfFolder(false);
        table.setIsEnable(true);
        table.setIsHorizontal(true);
        table.setIsInverse(false);
        table.setIsPage(false);
        table.setLabelDetails(table.getLabelDetails());
        table.setLabelHover(false);
        table.setLabelHoverColor("#f5f5f5");
        table.setLabelMenuId(0);
        table.setLabelShadow(false);
        table.setLegendDirection("top");
        table.setLabelTooltip("");
        table.setLabelValue("");
        table.setLockScreen(true);
        table.setMarkerFontSize("13");
        table.setMarkerManualScale(0);
        table.setMarkerOrientation(0);
        table.setPageText("/");
        table.setPosition("absolute");
        table.setRadius(0);
        table.setScapePreLoading(false);
        table.setScreenId(moduleResponse.getId().intValue());
        table.setShowActionModal("none");
        table.setSelectTableText(false);
        table.setShowLabel(false);
        table.setShowLabelUSBased(false);
        table.setText("label");
        table.setTextAlign("center");
        table.setTitle("Component");
        table.setTransparency("10");
        table.setTypeId(5);
        table.setUndraggable(false);
        table.setUseAttributesFromScreen(false);
        table.setW(500);
        table.setX(260);
        table.setY(246);
        table.setZ(1);

        Response createTableResponse = given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + TOKEN)
                .queryParam("screenId", moduleResponse.getId())
                .queryParam("pageHeight", 580)
                .queryParam("pageWidth", 700)
                .body(table)
                .post(Constants.SCREENCOMPONENT_PATH)
                .then()
                .extract()
                .response();

        Object tableResponse = new Gson().fromJson(createTableResponse.getBody().asString(), Object.class);

        AxesY axesY = AxesY.builder()
                .sonId(dimension.getSonId())
                .id(dimension.getId())
                .idDataType(dimension.getIdDataType())
                .isCube(dimension.getIsCube())
                .name(dimension.getName())
                .type(dimension.getType())
                .build();

        List<AxesY> axesYs = new ArrayList<>();
        axesYs.add(axesY);

//
////        AxisDisplay axisDisplay = new AxisDisplay();
////
////        List<AxisDisplay> axisDisplays = new ArrayList<>();
////        axisDisplays.add(axisDisplay);
//
        SourceRequest source = SourceRequest
                .builder()
                .axesY(axesYs)
                .axisDisplay(Collections.singletonList(new AxisDisplay()))
                .escapedSelections(Collections.singletonList(new EscapedSelections()))
                .filters(Collections.singletonList(new Filters()))
                .hasFilter()
                .build();




    }




}
