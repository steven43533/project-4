package com.example.project2;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

@Component

public class MyTasks {

    private RestTemplate restTemplate = new RestTemplate();
    int vehicleId = 1;
    Random rnd = new Random();


    @Scheduled (cron="*/5 * * * * *")
    public void addVehicle() {
        String addURL = "http://localhost:8080/addVehicle";

        String randomMakeAndModel = RandomStringUtils.randomAlphanumeric(10);
        int randomVehicleYear = rnd.nextInt(1986,2016);
        int randomVehiclePrice = rnd.nextInt(15000,45000);
        Vehicle v = new Vehicle(randomMakeAndModel, randomVehicleYear, randomVehiclePrice,vehicleId);
        restTemplate.postForObject(addURL, v, Vehicle.class);
        vehicleId++;

    }

    @Scheduled (cron="*/10 * * * * *")
    public void deleteVehicle() {
        String deleteURL = "http://localhost:8080/deleteVehicle/";
        int randomNumber = rnd.nextInt(1,10);
        System.out.println("Random ID generated:" + randomNumber);
        restTemplate.delete(deleteURL+randomNumber);
    }

    @Scheduled (cron = "*/15 * * * * *")
    public void updateVehicle() {
        int randomUpdatedVehicleId = rnd.nextInt(1,10);
        int randomVehicleYear = rnd.nextInt(1986,2006);
        int randomVehiclePrice = rnd.nextInt(15000,20000);
        String randomMakeAndModel = "Toyota Highlander";
        String updateURL = "http://localhost:8080/updateVehicle";
        Vehicle updatedVehicle = new Vehicle();
        updatedVehicle.setVehicleId(randomUpdatedVehicleId);
        updatedVehicle.setMakeModel(randomMakeAndModel);
        updatedVehicle.setYear(randomVehicleYear);
        updatedVehicle.setRetailPrice(randomVehiclePrice);
        restTemplate.put(updateURL, updatedVehicle);

    }

    @Scheduled(cron="0 * * * * *")
    public void getLatestVehicleReport() {
        restTemplate.getForObject("http://localhost:8080/getLatestVehicleReport", Vehicle.class);
    }


}

