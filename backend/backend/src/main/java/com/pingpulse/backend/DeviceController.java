package com.pingpulse.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/devices")

public class DeviceController {

    @Autowired
    private NetworkDeviceRepository repository;

    @GetMapping
    public List<NetworkDevice> getAllDevices() {
        return repository.findAll();
    }

    @PostMapping
    public NetworkDevice addDevice(@RequestBody NetworkDevice newDevice) {
        // Set a default status for any new device being added
        newDevice.setStatus("Pending");
        return repository.save(newDevice);
    }

}
