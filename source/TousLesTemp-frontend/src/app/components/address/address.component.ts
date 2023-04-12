import { Component } from '@angular/core';
interface marker {
	lat: number;
	lng: number;
	label?: string;
	draggable: boolean;
}

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.scss']
})
export class AddressComponent {
  zoom: number = 8;

  // initial center position for the map
  lat: number = 12.25308681634095;
  lng: number = 109.18813488645958;

  clickedMarker(label: string, index: number) {
    console.log(`clicked the marker: ${label || index}`)
  }

  // mapClicked($event: MouseEvent) {
  //   this.markers.push({
  //     lat: $event.coords.lat,
  //     lng: $event.coords.lng,
  //     draggable: true
  //   });
  // }

  markerDragEnd(m: marker, $event: MouseEvent) {
    console.log('dragEnd', m, $event);
  }

  markers: marker[] = [
	  {
		  lat: 13.710723269085635,
		  lng: 109.17056322025972,
		  label: 'A',
		  draggable: true
	  },
	  // {
		//   lat: 51.373858,
		//   lng: 7.215982,
		//   label: 'B',
		//   draggable: false
	  // },
	  // {
		//   lat: 51.723858,
		//   lng: 7.895982,
		//   label: 'C',
		//   draggable: true
	  // }
  ]
}
