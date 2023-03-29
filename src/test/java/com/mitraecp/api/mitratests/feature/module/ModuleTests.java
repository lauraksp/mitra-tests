package com.mitraecp.api.mitratests.feature.module;

import com.google.gson.Gson;
import com.mitraecp.api.mitratests.feature.login.FeatureLoginTest;
import com.mitraecp.api.mitratests.utils.Constants;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ModuleTests {

    private static String TOKEN = "";

    @BeforeClass
    public static void init() {
        TOKEN = FeatureLoginTest.getTokenLogin();
    }

//    @Test
//    public void GetModules_Success() {
//
//        Response response = given()
//                .header("Authorization", "Bearer " + TOKEN)
//                .queryParam("ref", true)
//                .queryParam("username", "admin")
//                .contentType(ContentType.JSON)
//                .when()
//                .get(Constants.MODULES_PATH)
//                .then()
//                .extract().response();
//
//        Assertions.assertEquals(200, response.statusCode());
//    }
//
//    @Test
//    public void GetModules_Error() {
//
//        Response response = given()
//                .contentType(ContentType.JSON)
//                .when()
//                .get(Constants.MODULES_PATH)
//                .then()
//                .extract().response();
//
//        Assertions.assertEquals(403, response.statusCode());
//    }
    @Test
    public void CreateModules_Success() {

        List<Module> moduleList = getModulesConvertedList();
        Integer sizeModuleList = moduleList.size();

        ModuleRequest module = new ModuleRequest();
        module.setKey(null);
        module.setName("Modulo ".concat(String.valueOf(new Date())));
        module.setEditMode(true);

        given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + TOKEN)
                .body(module)
                .post(Constants.MODULES_PATH)
                .then()
                .statusCode(200);

//      chamada de método dentro de outro método da mesma classe
        List<Module> moduleList2 = getModulesConvertedList();
        Integer sizeModuleList2 = moduleList2.size();

//      vai assertar que o sizeModuleList é diferente(NotEquals) do sizeModuleList2;
        assertNotEquals(sizeModuleList, sizeModuleList2);
//        vai somar 1 módulo no sizeModuleList e assertar que está igual ao sizeModuleList2;
        Assertions.assertEquals(sizeModuleList + 1, sizeModuleList2);
    }

    @Test
    public void CreateModulesWithSameNames_Error() {

        String nameModule = "ModuleTest";

        Module[] modules = new Gson().fromJson(getModulesResponse().getBody().asString(), Module[].class);
        List<Module> moduleList = Arrays.asList(modules);

        Optional<Module> optional = moduleList.stream()
                .filter(m -> m.getName().equals(nameModule))
                .findFirst();

        ModuleRequest module = new ModuleRequest();
        module.setKey(null);
        module.setName(nameModule);
        module.setEditMode(true);

        if(!optional.isPresent()){
            postModule(module);
        }

        Response postResponse = postModule(module);
        Assertions.assertEquals(409, postResponse.statusCode());
    }

    @Test
    public void UpdateModules_Success(){

        Module module;
        List<Module> moduleList = getModulesConvertedList();
        Integer sizeModuleList = moduleList.size();

        if (sizeModuleList == 0) {
            ModuleRequest moduleRequest = new ModuleRequest();
            moduleRequest.setKey(null);
            moduleRequest.setName("TestPut");
            moduleRequest.setEditMode(true);

//          método post sendo executado com argumento moduleRequest e atribuindo o retorno no responseModule.
            Response responseModule = postModule(moduleRequest);
//          fez a conversão e atribuiu na variavel module.
            module = new Gson().fromJson(responseModule.getBody().asString(), Module.class);
        } else {
//          atribui a posição 0 no module.
            module = moduleList.get(0);
        }

        Long idModule = module.getId();
        String oldNameModule = module.getName();

        ModuleRequest moduleRequest = new ModuleRequest();
        moduleRequest.setId(idModule);
//      nome passa NULL e vazio como string SÓ NO BACK
        moduleRequest.setName("Module ".concat(String.valueOf(new Date())));
        moduleRequest.setEditMode(true);
        moduleRequest.setBusinessUserDataEntry(module.getBusinessUserDataEntry());
        moduleRequest.setBusinessUserScreenSearch(module.getBusinessUserScreenSearch());
        moduleRequest.setHomeScreenId(module.getHomeScreenId());
        moduleRequest.setInitialScreens(module.getInitialScreens());
        moduleRequest.setHomeScreen(module.getHomeScreen());

        Response responsePut = given()
        .contentType("application/json")
        .header("Authorization", "Bearer " + TOKEN)
        .body(moduleRequest)
        .when()
        .put(Constants.MODULES_PATH)
        .then()
        .extract().response();

        List<Module> moduleList1 = getModulesConvertedList();
        Module selectedModule = moduleList1.stream().filter(m-> m.getId() == idModule).findFirst().get();

        assertNotEquals(oldNameModule, selectedModule.getName());
        assertEquals(responsePut.getStatusCode(),200);
    }

    @Test
    public void DeleteModules_Success() {
        List<Module> moduleList = getModulesConvertedList();

        Integer sizeModuleList = moduleList.size();

        if(sizeModuleList == 0){

            ModuleRequest module = new ModuleRequest();
            module.setKey(null);
            module.setName("Modulo5");
            module.setEditMode(true);

            given()
                    .contentType("application/json")
                    .header("Authorization", "Bearer " + TOKEN)
                    .body(module)
                    .post(Constants.MODULES_PATH);
            sizeModuleList = 1;
        }

        Module module = moduleList.get(0);

        Response response = given()
                .header("Authorization", "Bearer " + TOKEN)
                .queryParam("ref", true)
                .queryParam("username", "admin")
                .contentType(ContentType.JSON)
                .when()
                .delete(Constants.MODULES_PATH.concat("/").concat(String.valueOf(module.getId())))
                .then()
                .extract().response();

        List<Module> moduleList2 = getModulesConvertedList();
        Integer sizeModuleList2 = moduleList2.size();

        Assertions.assertNotEquals(sizeModuleList,sizeModuleList2);
        Assertions.assertEquals(sizeModuleList -1,sizeModuleList2);

        Assertions.assertEquals(200,response.statusCode());

    }

    @Test
    public void DeleteModules_Error(){

        given()
                .header("Authorization", "Bearer " + TOKEN)
                .queryParam("ref", true)
                .queryParam("username", "admin")
                .contentType(ContentType.JSON)
                .when()
                .delete(Constants.MODULES_PATH.concat("/").concat("Teste Modulo"))
                .then()
                .statusCode(400);
    }

    public static Response getModulesResponse() {

        Response response = given()
                .header("Authorization", "Bearer " + TOKEN)
                .queryParam("ref", true)
                .queryParam("username", "admin")
                .contentType(ContentType.JSON)
                .when()
                .get(Constants.MODULES_PATH)
                .then()
                .extract().response();
        //Se existe o módulo que eu criei após a inserção. Tá igual eu mandei inserir? Modulo, cubo e dimensão.
        return response;
    }

    public static Response postModule(ModuleRequest module){
        return    given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + TOKEN)
                .body(module)
                .post(Constants.MODULES_PATH)
                .then()
                .extract().response();
    }

    public List<Module> getModulesConvertedList(){
        Response getModulesResponse = getModulesResponse();
        Module[] modules = new Gson().fromJson(getModulesResponse.getBody().asString(), Module[].class);
        return Arrays.asList(modules);
    }
}

