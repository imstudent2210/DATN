(function ($) {
	var init_map = ['10.8029367610941', '106.715879101212', 'Tous Les Temps chi nhĂ¡nh SG'];

	for (i = 0; i < stores.length; i++) {
		if (Number(stores[i]['id']) == Number(ss_store_choice.id)) {
			init_map = [stores[i]['lat'], stores[i]['lang'], stores[i]['name']]
		}
	}

	var gmarkers = [];
	// Map Settings
	var map = new google.maps.Map(document.getElementById("store_map"), {
		zoom: 16,
		center: new google.maps.LatLng(init_map[0], init_map[1]),
		mapTypeId: google.maps.MapTypeId.ROADMAP,
	});

	var infowindow = new google.maps.InfoWindow;

	var marker, i;
	for (i = 0; i < stores.length; i++) {
		marker = new google.maps.Marker({
			position: new google.maps.LatLng(stores[i]['lat'], stores[i]['lang']),
			map: map,
			// label: String(stores[i]['id']),
			animation: google.maps.Animation.DROP,
			icon: $site_url + 'front/images/icons/marker.png',
		});

		gmarkers.push(marker);

		google.maps.event.addListener(marker, 'click', (function (marker, i) {
			return function () {
				infowindow.setContent(`<div><div>` + stores[i]['name'] + `</div><p>`+ stores[i]['address'] +`</p></div>`);
				infowindow.open(map, marker);
				map.setCenter(marker.getPosition());
			}
		})(marker, i));
	}

	$('.list-store-map a').each(function (i, e) {
		$(e).click(function (i) {
			return function (e) {
				google.maps.event.trigger(gmarkers[i], 'click');
			}
		}(i));
	});

	$('.list-store-map a').click(function () {
		$('.list-store-map a').removeClass('list-group-item-dark');
		$(this).addClass('list-group-item-dark');
	});

})(jQuery);
