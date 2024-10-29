package com.example.project2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MyController {

    private final ObjectMapper mapper = new ObjectMapper();
    File inventoryFile = new File("inventory.txt");

    @RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
    public Vehicle addVehicle(@RequestBody Vehicle newVehicle)  throws IOException {
        String vehicleJson = mapper.writeValueAsString(newVehicle);
        FileUtils.writeStringToFile(inventoryFile, vehicleJson + "\n", "UTF-8", true);
        return newVehicle;
    }

    @RequestMapping(value = "/deleteVehicle/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteVehicle(@PathVariable int id) throws IOException {
        boolean isMatched = false;
        List<String> lines = FileUtils.readLines(inventoryFile, "UTF-8");
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            Vehicle vehicle = mapper.readValue(line, Vehicle.class);
            if(vehicle.getVehicleId() == id) {
                lines.remove(i);
                FileUtils.writeStringToFile(inventoryFile, "", "UTF-8", false);
                isMatched = true;
                System.out.println("Vehicle deleted: " + vehicle.getVehicleId());
            }

        }
        if(isMatched) {
            for(String line : lines) {
                FileUtils.writeStringToFile(inventoryFile, line + "\n", "UTF-8", true);
            }
            return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not found buddy", HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/updateVehicle", method = RequestMethod.PUT)
    public Vehicle updateVehicle(@RequestBody Vehicle newVehicle) throws IOException {
        boolean isMatched = false;
        List<String> lines = FileUtils.readLines(inventoryFile, "UTF-8");
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            Vehicle vehicle = mapper.readValue(line, Vehicle.class);
            if (vehicle.getVehicleId() == newVehicle.getVehicleId()) {
                lines.remove(i);
                lines.add(mapper.writeValueAsString(newVehicle));
                FileUtils.writeStringToFile(inventoryFile, "", "UTF-8", false);
                isMatched = true;
                System.out.println("Vehicle updated: " + vehicle.getVehicleId());
            }

        }
        if (isMatched) {
            for (String line : lines) {
                FileUtils.writeStringToFile(inventoryFile, line + "\n", "UTF-8", true);
            }

        }
        return newVehicle;
    }



    @RequestMapping(value="/getLatestVehicleReport",method=RequestMethod.GET)
    public List<Vehicle> getLatestVehiclesReport() throws IOException {
        List<String> lines = FileUtils.readLines(inventoryFile, "UTF-8");
        for (String line : lines) {
            System.out.println("Here are the latest vehicles" + line);
        }
        return null;
    }

}
