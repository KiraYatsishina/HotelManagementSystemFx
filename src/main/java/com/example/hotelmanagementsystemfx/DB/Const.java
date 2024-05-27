package com.example.hotelmanagementsystemfx.DB;

public class Const {
    public static final String CLIENT_TABLE = "client";
    public static final String CLIENT_FIRSTNAME = "firstName";
    public static final String CLIENT_LASTNAME = "lastName";
    public static final String CLIENT_PHONE_NUMBER = "phoneNumber";
    public static final String CLIENT_GENDER = "gender";

    public static final String COMPLETE_SERVICE_ORDER_TABLE = "complete_service_order";
    public static final String COMPLETE_SERVICE_ORDER_ID_SERVICE_ORDER = "idServiceOrder";
    public static final String COMPLETE_SERVICE_ORDER_ID_SERVICE_TYPE = "idServiceType";
    public static final String COMPLETE_SERVICE_ORDER_ID_EMPLOYEE = "idEmployee";
    public static final String COMPLETE_SERVICE_ORDER_STATUS = "status";
    public static final String COMPLETE_SERVICE_ORDER_COMPLETE_DATE = "completeDate";

    public static final String EMPLOYEE_TABLE = "employee";
    public static final String EMPLOYEE_ID_EMPLOYEE_TYPE = "idEmployeeType";
    public static final String EMPLOYEE_FIRSTNAME = "firstName";
    public static final String EMPLOYEE_LASTNAME = "lastName";
    public static final String EMPLOYEE_EMAIL = "email";
    public static final String EMPLOYEE_PHONE_NUMBER = "phoneNumber";
    public static final String EMPLOYEE_GENDER = "gender";
    public static final String EMPLOYEE_STATUS = "status";

    public static final String EMPLOYEE_LOGIN = "login";
    public static final String EMPLOYEE_PASSWORD = "password";

    public static final String EMPLOYEE_TYPE_TABLE = "employee_type";
    public static final String EMPLOYEE_TYPE_ID = "idEmployeeType";
    public static final String EMPLOYEE_TYPE_NAME = "name";
    public static final String EMPLOYEE_TYPE_DESCRIPTION = "description";
    public static final String EMPLOYEE_TYPE_SALARY = "salary";

    public static final String RESERVATION_TABLE = "reservation";
    public static final String RESERVATION_ID_CLIENT = "idClient";
    public static final String RESERVATION_ID_ROOM = "idRoom";
    public static final String RESERVATION_ID_EMPLOYEE = "idEmployee";
    public static final String RESERVATION_NUMBER_OF_GUESTS = "numderOfGuests";
    public static final String RESERVATION_DATE = "reservationDate";
    public static final String RESERVATION_CHECK_IN_DATE = "checkInDate";
    public static final String RESERVATION_CHECK_OUT_DATE = "checkOutDate";
    public static final String RESERVATION_PRICE = "price";
    public static final String RESERVATION_STATUS = "status";
    public static final String RESERVATION_ACTUAL_REVENUE = "actualRevenue";

    public static final String ROOM_TABLE = "room";
    public static final String ROOM_ID = "idRoom";
    public static final String ROOM_TYPE = "roomType";
    public static final String ROOM_CAPACITY = "capacity";
    public static final String ROOM_PRICE_PER_NIGHT = "pricePerNight";
    public static final String ROOM_FLOOR = "floor";
    public static final String ROOM_NUMBER = "RoomNumber";
    public static final String ROOM_HAS_REFRIGERATOR = "HasRefrigerator";
    public static final String ROOM_HAS_AIR_CONDITIONING = "HasAirConditioning";

    public static final String ROOM_TYPE_TABLE = "room_type";
    public static final String ROOM_TYPE_NAME = "name";
    public static final String ROOM_TYPE_DESCRIPTION = "description";

    public static final String SERVICE_ORDER_TABLE = "service_order";
    public static final String SERVICE_ORDER_ID_CLIENT = "idClient";
    public static final String SERVICE_ORDER_ID_EMPLOYEE = "idEmployee";
    public static final String SERVICE_ORDER_DATE = "orderDate";
    public static final String SERVICE_ORDER_PRICE = "price";
    public static final String SERVICE_ORDER_STATUS = "status";

    public static final String SERVICE_TYPE_TABLE = "service_type";
    public static final String SERVICE_TYPE_ID = "idServiceType";
    public static final String SERVICE_TYPE_NAME = "name";
    public static final String SERVICE_TYPE_DESCRIPTION = "description";
    public static final String SERVICE_TYPE_PRICE = "price";
    public static final String SERVICE_TYPE_STATUS = "status";
}
