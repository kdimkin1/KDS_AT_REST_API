package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqresinTestsHW {

    @Test
    @DisplayName("successfulCreate: https://reqres.in/api/users")
    void successfulCreate() {
        /*
        request: https://reqres.in/api/users

        data:
        {
            "name": "morpheus",
            "job": "leader"
        }

        response: 201
        {
            "name": "morpheus",
            "job": "leader",
            "id": "820",
            "createdAt": "2022-04-07T19:09:08.000Z"
        }
         */

        String authorizedData = "{ \"name\": \"morpheus\", " +
                "\"job\": \"leader\" }";

        given()
                .body(authorizedData)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("job", is("leader"));
    }

    @Test
    @DisplayName("successfulRegister: https://reqres.in/api/register")
    void successfulRegister() {
        /*
        request: https://reqres.in/api/register

        data:
        {
            "email": "eve.holt@reqres.in",
            "password": "pistol"
        }

        response: 200
        {
            "id": 4,
            "token": "QpwL5tke4Pnpja7X4"
        }
         */

        String authorizedData = "{ \"email\": \"eve.holt@reqres.in\", " +
                "\"password\": \"pistol\" }";

        given()
                .body(authorizedData)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(200)
                .body("id", is(4));
    }
    @Test
    @DisplayName("successfulGetDelay: https://reqres.in/api/users?delay=3")
    void successfulGetDelay() {
        /*
        request: https://reqres.in/api/users?delay=3

        response: 200
        {
    "page": 1,
    "per_page": 6,
    "total": 12,
    "total_pages": 2,
    "data": [
        {
            "id": 1,
            "email": "george.bluth@reqres.in",
            "first_name": "George",
            "last_name": "Bluth",
            "avatar": "https://reqres.in/img/faces/1-image.jpg"
        },
        {
            "id": 2,
            "email": "janet.weaver@reqres.in",
            "first_name": "Janet",
            "last_name": "Weaver",
            "avatar": "https://reqres.in/img/faces/2-image.jpg"
        },
        {
            "id": 3,
            "email": "emma.wong@reqres.in",
            "first_name": "Emma",
            "last_name": "Wong",
            "avatar": "https://reqres.in/img/faces/3-image.jpg"
        },
        {
            "id": 4,
            "email": "eve.holt@reqres.in",
            "first_name": "Eve",
            "last_name": "Holt",
            "avatar": "https://reqres.in/img/faces/4-image.jpg"
        },
        {
            "id": 5,
            "email": "charles.morris@reqres.in",
            "first_name": "Charles",
            "last_name": "Morris",
            "avatar": "https://reqres.in/img/faces/5-image.jpg"
        },
        {
            "id": 6,
            "email": "tracey.ramos@reqres.in",
            "first_name": "Tracey",
            "last_name": "Ramos",
            "avatar": "https://reqres.in/img/faces/6-image.jpg"
        }
    ],
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
}
         */

        given()
                .when()
                .get("https://reqres.in/api/users?delay=3")
                .then()
                .statusCode(200)
                .body("total", is(12))
                .body("support.url", is("https://reqres.in/#support-heading"))
                .body("support.text", is("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }
    @Test
    @DisplayName("successDelete: https://reqres.in/api/users/2")
    void successDelete() {
        /*
        request: https://reqres.in/api/users/2
        response: 204
         */

        given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204);
    }

    @Test
    @DisplayName("getUnknown: https://reqres.in/api/unknown")
    void getUnknown() {
        /*
        request: https://reqres.in/api/unknown

        response: 200
        {
    "page": 1,
    "per_page": 6,
    "total": 12,
    "total_pages": 2,
    "data": [
        {
            "id": 1,
            "name": "cerulean",
            "year": 2000,
            "color": "#98B2D1",
            "pantone_value": "15-4020"
        },
        {
            "id": 2,
            "name": "fuchsia rose",
            "year": 2001,
            "color": "#C74375",
            "pantone_value": "17-2031"
        },
        {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        {
            "id": 4,
            "name": "aqua sky",
            "year": 2003,
            "color": "#7BC4C4",
            "pantone_value": "14-4811"
        },
        {
            "id": 5,
            "name": "tigerlily",
            "year": 2004,
            "color": "#E2583E",
            "pantone_value": "17-1456"
        },
        {
            "id": 6,
            "name": "blue turquoise",
            "year": 2005,
            "color": "#53B0AE",
            "pantone_value": "15-5217"
        }
    ],
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
}
         */

        Response response = get("https://reqres.in/api/unknown")
                .then()
                .extract()
                .response();
//        System.out.println("Response: " + response);
//        System.out.println("Response .toString(): " + response.toString());
//        System.out.println("Response .asString(): " + response.asString());
        System.out.println("Response .path(\"total\"): " + response.path("total"));
        int responseActual = response.path("total");
        int expectedResponse = 12;
        System.out.println("expectedResponse: " + expectedResponse);
        assertEquals(expectedResponse, responseActual);
    }


}
