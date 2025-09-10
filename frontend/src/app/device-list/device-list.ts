import { Component, OnInit } from '@angular/core';
import { Api, NetworkDevice } from '../api';
import { CommonModule } from '@angular/common'; 

@Component({
  selector: 'app-device-list',
  standalone: true,
  imports: [CommonModule], // Add CommonModule here
  templateUrl: './device-list.html',
  styleUrl: './device-list.css'
})
export class DeviceListComponent implements OnInit {

  // A property to hold our list of devices
  devices: NetworkDevice[] = [];

  constructor(private apiService: Api) { }

  // This method runs automatically when the component is first loaded
  ngOnInit(): void {
    this.apiService.getDevices().subscribe(data => {
      this.devices = data;
    });
  }
}