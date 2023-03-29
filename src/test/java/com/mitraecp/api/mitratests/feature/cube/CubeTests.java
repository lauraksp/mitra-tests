package com.mitraecp.api.mitratests.feature.cube;

import com.google.gson.Gson;
import com.mitraecp.api.mitratests.feature.dimension.Dimension;
import com.mitraecp.api.mitratests.feature.dimension.DimensionsTests;
import com.mitraecp.api.mitratests.feature.login.FeatureLoginTest;
import com.mitraecp.api.mitratests.utils.Constants;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CubeTests {

    private static String TOKEN = "";

    @BeforeClass
    public static void init() {
        TOKEN = FeatureLoginTest.getTokenLogin();
    }

    @Test
    public void GetCube_Success() {
        Response response = getCubesResponse();
        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    public void GetCube_Error() {

        Response response = given()
            .contentType(ContentType.JSON)
            .when()
            .get(Constants.CUBES_PATH)
            .then()
            .extract().response();

        Assertions.assertEquals(403, response.statusCode());
    }

    @Test
    public void CreateCube_Success() {

        Dimension dimension;
        List<Dimension> dimensionList = DimensionsTests.getDimensionsConvertedList();
        Integer sizeDimensionList = dimensionList.size();

        if(sizeDimensionList == 0)  {
            Response responseDimension = DimensionsTests.postDimensionsResponse();
            dimension = new Gson().fromJson(responseDimension.getBody().asString(), Dimension.class);
        } else {
            dimension = dimensionList.get(0);
        }

        Integer idDimension = dimension.getId();
        List<Cube> cubeList = getCubesConvertedList();
        Integer sizeCubeList = cubeList.size();

        CubeRequest cube = new CubeRequest();
        cube.setCubeGroup("Cube test");
        cube.setDimensionsId(Collections.singletonList(idDimension));
        cube.setDataType("DOUBLE");
        cube.setName("Cube create test".concat(String.valueOf(LocalDateTime.now())));

        given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + TOKEN)
                .body(cube)
                .post(Constants.CUBES_PATH)
                .then()
                .statusCode(200);

        List<Cube> cubeList2 = getCubesConvertedList();
        Integer sizeCubeList2 = cubeList2.size();

        assertNotEquals(sizeCubeList, sizeCubeList2);
        Assertions.assertEquals(sizeCubeList + 1, sizeCubeList2);
    }

    @Test
    public void CreateCube_Error() {

        String nameCube = "Cube Test";

        CubeRequest cube = new CubeRequest();
        cube.setCubeGroup("Cube test");
        cube.setDataType("DOUBLE");
        cube.setName(nameCube);

        given()
            .contentType("application/json")
            .header("Authorization", "Bearer " + TOKEN)
            .body(cube)
            .post(Constants.CUBES_PATH)
            .then()
            .statusCode(500);
    }

    @Test
    public void UpdateCubes_Success(){

        Cube cube;
        Dimension dimension;
        String nameCubeReq;

        List<Cube> cubeList = getCubesConvertedList();
        Integer sizeCubeList = cubeList.size();

        List<Dimension> dimensionList = DimensionsTests.getDimensionsConvertedList();
        Integer sizeDimensionList = dimensionList.size();

        CubeRequest cubeRequest = new CubeRequest();
        cubeRequest.setCubeGroup("Cube test");
        cubeRequest.setDataType("DOUBLE");

        if(sizeDimensionList == 0)  {
            Response responseDimension = DimensionsTests.postDimensionsResponse();
            dimension = new Gson().fromJson(responseDimension.getBody().asString(), Dimension.class);
        } else {
            dimension = dimensionList.get(0);
        }

        if (sizeCubeList == 0) {
            Integer idDimension = dimension.getId();

            cubeRequest.setDimensionsId(Collections.singletonList(idDimension));
            nameCubeReq = "Cube create test".concat(String.valueOf(LocalDateTime.now()));
            cubeRequest.setName(nameCubeReq);

            Response responsePost = given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + TOKEN)
                .body(cubeRequest)
                .when()
                .post(Constants.CUBES_PATH)
                .then()
                .extract()
                .response();

            cube = new Gson().fromJson(responsePost.getBody().asString(), Cube.class);

        } else {
            cube = cubeList.get(0);
        }

        String oldNameCube = cube.getName();

        cubeRequest.setId(cube.getId());
        nameCubeReq = "Cube create test".concat(String.valueOf(LocalDateTime.now()));
        cubeRequest.setName(nameCubeReq);
        cubeRequest.setDimensionsId(Collections.singletonList(dimension.getId()));

        Response responsePut = given()
            .contentType("application/json")
            .header("Authorization", "Bearer " + TOKEN)
            .body(cubeRequest)
            .when()
            .put(Constants.CUBES_PATH)
            .then()
            .extract()
            .response();

        Cube cubResp = new Gson().fromJson(responsePut.getBody().asString(), Cube.class);

        List<Cube> cubeList1 = getCubesConvertedList();
        Cube cubeSelected = cubeList1.stream().filter(c -> c.getId() == cubResp.getId()).findFirst().get();

        Assertions.assertNotEquals(cubeSelected.getName(), oldNameCube);
        Assertions.assertEquals(cubeSelected.getName(), nameCubeReq);
    }

    @Test
    public void UpdateCubes_Error(){

        CubeRequest cubeRequest = new CubeRequest();
        cubeRequest.setCubeGroup("Cube test");
        cubeRequest.setDataType("DOUBLE");
        cubeRequest.setId(null);
        cubeRequest.setName("teste");
        cubeRequest.setDimensionsId(Collections.singletonList(null));

        given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + TOKEN)
                .body(cubeRequest)
                .when()
                .put(Constants.CUBES_PATH)
                .then()
                .statusCode(500);
    }

    @Test
    public void DeleteCubes_Success(){

        Cube cube;
        Dimension dimension;
        String nameCubeReq;

        List<Cube> cubeList = getCubesConvertedList();
        Integer sizeCubeList = cubeList.size();

        List<Dimension> dimensionList = DimensionsTests.getDimensionsConvertedList();
        Integer sizeDimensionList = dimensionList.size();

        CubeRequest cubeRequest = new CubeRequest();
        cubeRequest.setCubeGroup("Cube test");
        cubeRequest.setDataType("DOUBLE");

        if(sizeDimensionList == 0)  {
            Response responseDimension = DimensionsTests.postDimensionsResponse();
            dimension = new Gson().fromJson(responseDimension.getBody().asString(), Dimension.class);
        } else {
            dimension = dimensionList.get(0);
        }

        if (sizeCubeList == 0) {
            Integer idDimension = dimension.getId();

            cubeRequest.setDimensionsId(Collections.singletonList(idDimension));
            nameCubeReq = "Cube create test".concat(String.valueOf(LocalDateTime.now()));
            cubeRequest.setName(nameCubeReq);

            Response responsePost = given()
                    .contentType("application/json")
                    .header("Authorization", "Bearer " + TOKEN)
                    .body(cubeRequest)
                    .post(Constants.CUBES_PATH);

            cube = new Gson().fromJson(responsePost.getBody().asString(), Cube.class);
            sizeCubeList = 1;
        }else{
            cube = cubeList.get(0);
        }

        Response responseDelete = given()
                .header("Authorization", "Bearer " + TOKEN)
                .contentType(ContentType.JSON)
                .when()
                .delete(Constants.CUBES_PATH.concat("/").concat(String.valueOf(cube.getId())))
                .then()
                .extract().response();

        List<Cube> cubeList2 = getCubesConvertedList();
        Integer sizeCubeList2 = cubeList2.size();

        Assertions.assertNotEquals(sizeCubeList,sizeCubeList2);
        Assertions.assertEquals(sizeCubeList -1,sizeCubeList2);

        Assertions.assertEquals(200,responseDelete.statusCode());

    }

    @Test
    public void DeleteCubes_Error(){

        given()
                .header("Authorization", "Bearer " + TOKEN)
                .contentType(ContentType.JSON)
                .when()
                .delete(Constants.CUBES_PATH.concat("/").concat("Teste Cubo"))
                .then()
                .statusCode(400);
    }


    public static Response getCubesResponse() {
        Response response = given()
            .header("Authorization", "Bearer " + TOKEN)
            .queryParam("ref", true)
            .queryParam("username", "admin")
            .contentType(ContentType.JSON)
            .when()
            .get(Constants.CUBES_PATH)
            .then()
            .extract().response();

        return response;
    }

    public List<Cube> getCubesConvertedList(){
        Response getCubesResponse = getCubesResponse();
        Cube[] cubes = new Gson().fromJson(getCubesResponse.getBody().asString(), Cube[].class);
        return Arrays.asList(cubes);
    }
}
