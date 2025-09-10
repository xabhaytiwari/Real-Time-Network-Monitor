import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// Structure of our device object for type safety
export interface NetworkDevice {
  id: number;
  name: string;
  ipAddress: string;
  status: string;
}

@Injectable({
  providedIn: 'root'
})
export class Api {
  private apiURL = '/api/devices';
  constructor(private http: HttpClient) {}
  
  //Method to get all devices from the API
  getDevices(): Observable<NetworkDevice[]> {
    return this.http.get<NetworkDevice[]>(this.apiURL);
  }
}
