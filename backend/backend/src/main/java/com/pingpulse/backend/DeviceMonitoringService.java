package com.pingpulse.backend;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

@Service
public class DeviceMonitoringService {
    @Autowired
    private NetworkDeviceRepository repository;

    @Scheduled(fixedRate = 15000) // Runs every 12 seconds
    public void checkDeviceStatus() {
        System.out.println("--> Running device status check... ");

        List<NetworkDevice> devices = repository.findAll();
        for(NetworkDevice device : devices) {
            try {
                InetAddress address = InetAddress.getByName(device.getIpAddress());
                // Ping the device with a 5-second timeout
                if (address.isReachable(5000)) {
                    device.setStatus("Up");
                } else {
                    device.setStatus("Down");
                }
            } catch (IOException e) {
                // If an exception occurs, mark it as Down
                device.setStatus("Down");
                System.err.println("Error pinging " + device.getIpAddress() + ": " + e.getMessage());
            }

            //Save the updated status back to the database
            repository.save(device);
        }
    }
}
