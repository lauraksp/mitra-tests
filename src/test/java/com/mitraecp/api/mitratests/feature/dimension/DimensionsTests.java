package com.mitraecp.api.mitratests.feature.dimension;

import com.google.gson.Gson;
import com.mitraecp.api.mitratests.feature.cube.Cube;
import com.mitraecp.api.mitratests.feature.login.FeatureLoginTest;
import com.mitraecp.api.mitratests.utils.Constants;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DimensionsTests {

    private static String TOKEN = "";

    @BeforeClass
    public static void init() {
        TOKEN = FeatureLoginTest.getTokenLogin();
    }

    @Test
    public void GetDimensions_Success(){

        Response response = getDimensionsResponse();
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    public void GetDimensions_Error(){

        Response response = given()
            .contentType(ContentType.JSON)
            .when()
            .get(Constants.DIMENSIONS_PATH)
            .then()
            .extract().response();

        Assertions.assertEquals(403, response.statusCode());
    }

    @Test
    public void CreateDimensions_Success() {

        postDimensionsResponse();
        Response getDimensionsResponse = getDimensionsResponse();
        List<Dimension> dimensionList = getDimensionsResponse.jsonPath().getList("$");
        Integer sizeDimensionList = dimensionList.size();

        DimensionRequest dimension = new DimensionRequest();
        dimension.setIdDataType("INT");
        dimension.setName("Teste 01");

        Response getDimensionsResponse2 = getDimensionsResponse();
        List<Dimension> dimensionList2 = getDimensionsResponse2.jsonPath().getList("$");
        Integer sizeDimensionList2 = dimensionList2.size();

        assertNotEquals(sizeDimensionList, sizeDimensionList2);
        Assertions.assertEquals(sizeDimensionList + 1, sizeDimensionList2);

    }

    @Test
    public void CreateDimensions_Error(){

        DimensionRequest dimension = new DimensionRequest();
        dimension.setIdDataType("INT");
        dimension.setName("Teste 01");

        Response response = given()
                .body(dimension)
                .post(Constants.DIMENSIONS_PATH)
                .then()
                .extract().response();

        Assertions.assertEquals(403, response.statusCode());
    }

//    @Test
//    public void UpdateDimensions_Success(){
//
//        Dimension dimension;
//
//        List<Dimension> dimensionList = getDimensionsConvertedList();
//        Integer sizeDimensionList = dimensionList.size();
//
//        if(sizeDimensionList == 0)  {
//            Response responseDimension = postDimensionsResponse();
//            dimension = new Gson().fromJson(responseDimension.getBody().asString(), Dimension.class);
//        } else {
//            dimension = dimensionList.get(0);
//        }

    public static Response postDimensionsResponse() {

        if(TOKEN.equals("")) {
            TOKEN = FeatureLoginTest.getTokenLogin();
        }

        DimensionRequest dimension = new DimensionRequest();
        dimension.setIdDataType("INT");
        dimension.setName("Teste 01");

        Response response = given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + TOKEN)
                .body(dimension)
                .post(Constants.DIMENSIONS_PATH)
                .then()
                .extract().response();

        return response;
    }

    public static Response getDimensionsResponse() {

        if(TOKEN.equals("")) {
            TOKEN = FeatureLoginTest.getTokenLogin();
        }

        Response response = given()
                .header("Authorization", "Bearer " + TOKEN)
                .queryParam("group", "LONLELY")
                .contentType(ContentType.JSON)
                .when()
                .get(Constants.DIMENSIONS_PATH)
                .then()
                .extract().response();

        return response;
    }

    public static List<Dimension> getDimensionsConvertedList(){
        Response getDimensionsResponse = getDimensionsResponse();
        Dimension[] dimensions = new Gson().fromJson(getDimensionsResponse.getBody().asString(), Dimension[].class);
        return Arrays.asList(dimensions);
    }
}
