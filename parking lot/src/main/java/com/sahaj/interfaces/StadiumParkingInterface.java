package com.sahaj.interfaces;

import com.sahaj.model.CarSuv;
import com.sahaj.model.Motorcycle;
import com.sahaj.model.ParkingReceipt;
import com.sahaj.model.ParkingTicket;

import java.time.LocalDateTime;

public interface StadiumParkingInterface {
    ParkingTicket parkMotorcycle(Motorcycle motorcycle) throws Exception;
    ParkingReceipt unParkMotorcycle(String ticketNumber, LocalDateTime unparkTime) throws Exception;
    ParkingTicket parkCarSuv(CarSuv car) throws Exception;
    ParkingReceipt unParkCarSuv(String ticketNumber,LocalDateTime unparkTime) throws Exception;
}
