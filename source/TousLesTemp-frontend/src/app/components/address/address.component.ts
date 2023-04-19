import { GoogleMapsAPIWrapper } from '@agm/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { GoogleMap, MapInfoWindow, MapMarker } from '@angular/google-maps';
import { NgToastService } from 'ng-angular-popup';
import { GeocodingService } from 'src/app/services/geocoding.service';
import { GeocoderResponse } from 'src/app/share/geocoder-response.model';

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
  //   constructor( ) {}
  //     ngOnInit(): void {
  //       navigator.geolocation.getCurrentPosition((position) => {
  //         this.center = {
  //           lat: position.coords.latitude,
  //           lng: position.coords.longitude,
  //         }
  //       })
  //     }
  //     display: any;
  //     @ViewChild(MapInfoWindow) infoWindow: MapInfoWindow | undefined;
  //     center: google.maps.LatLngLiteral = {
  //         lat: 12.238791,
  //         lng: 109.196749
  //     };
  //     zoom = 15;
  //     moveMap(event: google.maps.MapMouseEvent) {
  //         if (event.latLng != null) this.center = (event.latLng.toJSON());
  //     }
  //     move(event: google.maps.MapMouseEvent) {
  //         if (event.latLng != null) this.display = event.latLng.toJSON();
  //     }

  //     markerOptions: google.maps.MarkerOptions = {
  //       draggable: false
  //   };
  //   markerPositions: google.maps.LatLngLiteral[] = [
  //    {
  //     lat: 12.238791,
  //     lng: 109.196749
  //    },
  //    {
  //     lat:  12.249500,
  //     lng: 109.188049
  //    }
  //   ];

  //   addMarker(event: google.maps.MapMouseEvent) {
  //       if (event.latLng != null) this.markerPositions.push(event.latLng.toJSON());
  //   }
  //   openInfoWindow(marker: MapMarker) {
  //     if (this.infoWindow != undefined) this.infoWindow.open(marker);
  // }

  //https://maps.googleapis.com/maps/api/geocode/json?address=${storeAddress}&sensor=false&key=API_KEY
  constructor(private geocodingService: GeocodingService, private toast: NgToastService,
    private http: HttpClient) { }

  @ViewChild(GoogleMap, { static: false }) map!: GoogleMap;
  @ViewChild(MapInfoWindow, { static: false }) infoWindow!: MapInfoWindow;

  mapZoom = 15;
  mapCenter!: google.maps.LatLng;
  mapOptions: google.maps.MapOptions = {
    mapTypeId: google.maps.MapTypeId.ROADMAP,
    zoomControl: true,
    scrollwheel: false,
    disableDoubleClickZoom: true,
    maxZoom: 20,
    minZoom: 15,
    center: { lat: 12.238791, lng: 109.196749 },
  };

  markerInfoContent = '';
  markerOptions: google.maps.MarkerOptions = {
    draggable: false,
    animation: google.maps.Animation.DROP,
  };

  geocoderWorking = false;
  geolocationWorking = false;

  address?: any;
  formattedAddress?: string | null = null;
  locationCoords?: google.maps.LatLng | null = null;

  reset(){
    this.address = "";
  }
  get isWorking(): boolean {
    return this.geolocationWorking || this.geocoderWorking;
  }

  openInfoWindow(marker: MapMarker) {
    this.infoWindow?.open(marker);
  }

  getCurrentLocation() {
    this.geolocationWorking = true;
    navigator.geolocation.getCurrentPosition(
      (position) => {
        this.geolocationWorking = false;

        const point: google.maps.LatLngLiteral = {
          lat: position.coords.latitude,
          lng: position.coords.longitude,
        };

        this.geocoderWorking = true;
        this.geocodingService
          .geocodeLatLng(point)
          .then((response: GeocoderResponse) => {
            if (response.status === 'OK' && response.results?.length) {
              const value = response.results[0];

              this.locationCoords = new google.maps.LatLng(point);

              this.mapCenter = new google.maps.LatLng(point);
              this.map?.panTo(point);

              this.address = value.formatted_address;
              this.formattedAddress = value.formatted_address;
              this.markerInfoContent = value.formatted_address;

              this.markerOptions = {
                draggable: true,
                animation: google.maps.Animation.DROP,
              };
            } else {
              this.toast.error({ detail: "Thông báo lỗi", summary: " Sản phẩm chưa được cập nhật!", duration: 3000 })

            }
          })
          .finally(() => {
            this.geocoderWorking = false;
          });
      },
      (error) => {
        this.geolocationWorking = false;

        if (error.PERMISSION_DENIED) {
          this.toast.error({ detail: "Thông báo lỗi", summary: " Sản phẩm chưa được cập nhật!", duration: 3000 })

        } else if (error.POSITION_UNAVAILABLE) {
          this.toast.error({ detail: "Thông báo lỗi", summary: " Sản phẩm chưa được cập nhật!", duration: 3000 })

        } else if (error.TIMEOUT) {
          this.toast.error({ detail: "Thông báo lỗi", summary: " Sản phẩm chưa được cập nhật!", duration: 3000 })

        } else {
          this.toast.error({ detail: "Thông báo lỗi", summary: " Sản phẩm chưa được cập nhật!", duration: 3000 })

        }
      },
      { enableHighAccuracy: true }
    );
  }

  findAddress() {
    if (this.address.length === 0) {
      return;
    }
    this.geocoderWorking = true;

    this.geocodingService.getLocation(this.address)
      .subscribe(
        (response: GeocoderResponse) => {
          if (response.status === 'OK' && response.results.length) {
            const location = response.results[0];
            const loc: any = location.geometry.location;
            this.locationCoords = new google.maps.LatLng(loc.lat, loc.lng);
            this.mapCenter = location.geometry.location;
            setTimeout(() => {
              if (this.map !== undefined) {
                this.map.panTo(location.geometry.location);
              }
            }, 500);

            this.address = location.formatted_address;
            this.formattedAddress = location.formatted_address;
            this.markerInfoContent = location.formatted_address;

            this.markerOptions = {
              draggable: true,
              animation: google.maps.Animation.DROP,
            };
          } else {
            this.toast.error({ detail: "Thông báo lỗi", summary: " Lỗi!", duration: 3000 })

          }
        },
        (err: HttpErrorResponse) => {
          console.error('Geocoder error', err);
        }
      )
      .add(() => {
        this.geocoderWorking = false;
      });
  }

  onMapDragEnd(event: any) {
    const point: google.maps.LatLngLiteral = {
      lat: event.latLng.lat(),
      lng: event.latLng.lng(),
    };

    this.geocoderWorking = true;
    this.geocodingService
      .geocodeLatLng(point)
      .then((response: GeocoderResponse) => {
        if (response.status === 'OK') {
          if (response.results.length) {
            const value = response.results[0];

            this.locationCoords = new google.maps.LatLng(point);

            this.mapCenter = new google.maps.LatLng(point);
            this.map?.panTo(point);

            this.address = value.formatted_address;
            this.formattedAddress = value.formatted_address;

            this.markerOptions = {
              draggable: true,
              animation: google.maps.Animation.DROP,
            };

            this.markerInfoContent = value.formatted_address;
          }
        }
      })
      .finally(() => {
        this.geocoderWorking = false;
      });
  }
  ngOnInit(): void {

  }
}
