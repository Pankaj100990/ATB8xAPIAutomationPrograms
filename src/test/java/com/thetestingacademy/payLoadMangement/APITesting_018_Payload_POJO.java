package com.thetestingacademy.payLoadMangement;

import com.thetestingacademy.payLoadMangement.diificultWay.Booking;
import com.thetestingacademy.payLoadMangement.diificultWay.BookingDates;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.requestSpecification;

public class APITesting_018_Payload_POJO {
    ValidatableResponse validatableResponse;

    @Test
    public void testPOSTReq() {
        //{
        //            "firstname" : "Jim",
        //                "lastname" : "Brown",
        //                "totalprice" : 111,
        //                "depositpaid" : true,
        //                "bookingdates" : {
        //            "checkin" : "2018-01-01",
        //                    "checkout" : "2019-01-01"
        //        },
        //            "additionalneeds" : "Breakfast"
        //        }


        // POJO Class -> JSON

        Booking booking = new Booking();
        booking.setFirstname("Pramod");
        booking.setLastname("Dutta");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-01");
        booking.setBookingDates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);


        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(booking).log().all();

        Response response = requestSpecification.when().post();

        Integer bookingId = response.then().extract().path("bookingid");

        // Get Validatable response to perform validation
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        System.out.println("Your Booking Id is -> " +  bookingId);





    }

}
