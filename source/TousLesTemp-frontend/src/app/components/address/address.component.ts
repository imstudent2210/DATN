import { GoogleMapsAPIWrapper } from '@agm/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, ElementRef, EventEmitter, Input, NgZone, OnInit, Output, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { GoogleMap, MapInfoWindow, MapMarker } from '@angular/google-maps';
import { NgToastService } from 'ng-angular-popup';
import { Observable, map, startWith } from 'rxjs';
import { GeocodingService } from 'src/app/services/geocoding.service';
import { StoresService } from 'src/app/services/stores.service';
import { GeocoderResponse } from 'src/app/model/geocoder-response.model';

export interface PlaceSearchResult {
  address: string;
  location?: google.maps.LatLng;
  imageUrl?: string;
  iconUrl?: string;
  name?: string;
}

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.scss']
})
export class AddressComponent implements OnInit {

  constructor(private geocodingService: GeocodingService, private toast: NgToastService,
    private fb: FormBuilder, private storeService: StoresService, private ngZone: NgZone) { }

  @ViewChild(GoogleMap, { static: false }) map!: GoogleMap;
  @ViewChild(MapInfoWindow, { static: false }) infoWindow!: MapInfoWindow;
  @ViewChild('scroller1') scrollbar!: ElementRef;

  myControl = new FormControl('');
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

  reset() {
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

  findAddres2(addressDetail:any) {
    this.geocoderWorking = true;
    this.geocodingService.getLocation(addressDetail)
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

  filteredOptions?: Observable<string[]>;
  options: string[] = [];

  ngOnInit() {
    this.address = this.inputField;
    this.getListStore();

    const div = this.scrollbar.nativeElement as HTMLDivElement;
    div.addEventListener('mouseover', e => {
      console.log('Mouse Over');
    });
    div.addEventListener('mouseout', e => {
      console.log('Mouse Out');
    });
  }


  @ViewChild('inputField')
  inputField!: ElementRef;


  @Output() placeChanged = new EventEmitter<PlaceSearchResult>();
  autocomplete!: google.maps.places.Autocomplete;
  listener: any;

  ngAfterViewInit() {
    this.autocomplete = new google.maps.places.Autocomplete(
      this.inputField.nativeElement
    );

    this.autocomplete.addListener('place_changed', () => {
      this.ngZone.run(() => {
        const place = this.autocomplete?.getPlace();
        const result: PlaceSearchResult = {
          address: this.inputField.nativeElement.value,
          name: place?.name,
          location: place?.geometry?.location,
          imageUrl: this.getPhotoUrl(place),
          iconUrl: place?.icon,
        };

        this.placeChanged.emit(result);
      });
    });

  }

  listStore?:any;

  getListStore(){
    this.storeService.getStores().subscribe(
      data => {
        this.listStore = data
        console.log(this.listStore);
      }
    )
  }


  getPhotoUrl(
    place: google.maps.places.PlaceResult | undefined
  ): string | undefined {
    return place?.photos && place?.photos.length > 0
      ? place?.photos[0].getUrl({ maxWidth: 500 })
      : undefined;
  }

  ngOnDestroy() {
    if (this.autocomplete) {
      google.maps.event.clearInstanceListeners(this.autocomplete);
    }
  }
}
