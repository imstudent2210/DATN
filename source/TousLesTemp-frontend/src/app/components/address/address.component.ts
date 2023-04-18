import { GoogleMapsAPIWrapper } from '@agm/core';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MapInfoWindow, MapMarker } from '@angular/google-maps';

// interface marker {
//   lat: number;
//   lng: number;
//   label?: string;
//   draggable: boolean;
// }

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.scss']
})
export class AddressComponent implements OnInit {
  constructor() {}
    ngOnInit(): void {
      navigator.geolocation.getCurrentPosition((position) => {
        this.center = {
          lat: position.coords.latitude,
          lng: position.coords.longitude,
        }
      })
    }
    display: any;
    @ViewChild(MapInfoWindow) infoWindow: MapInfoWindow | undefined;
    center: google.maps.LatLngLiteral = {
        lat: 12.238791,
        lng: 109.196749
    };
    zoom = 15;
    moveMap(event: google.maps.MapMouseEvent) {
        if (event.latLng != null) this.center = (event.latLng.toJSON());
    }
    move(event: google.maps.MapMouseEvent) {
        if (event.latLng != null) this.display = event.latLng.toJSON();
    }

    markerOptions: google.maps.MarkerOptions = {
      draggable: false
  };
  markerPositions: google.maps.LatLngLiteral[] = [
   {
    lat: 12.238791,
    lng: 109.196749
   },
   {
    lat:  12.249500,
    lng: 109.188049
   }
  ];

  addMarker(event: google.maps.MapMouseEvent) {
      if (event.latLng != null) this.markerPositions.push(event.latLng.toJSON());
  }
  openInfoWindow(marker: MapMarker) {
    if (this.infoWindow != undefined) this.infoWindow.open(marker);
}

}
