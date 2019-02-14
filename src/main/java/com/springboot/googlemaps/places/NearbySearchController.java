/**
 * Build By SALMAN EL FADILI
 **/
package com.springboot.googlemaps.places;

import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class NearbySearchController {

    @Autowired
    NearbySearchServices nearbySearchServices;

    @GetMapping("/googlemaps/api/nearbyshops")
    public ResponseEntity<?> nearByLocationAndDistance(@RequestParam double lat, @RequestParam double lng, @RequestParam int distance) throws Exception {
        LatLng location = new LatLng(lat,lng);

        SearchResponseModel response = nearbySearchServices.byLocationAndDistance(location,distance);
        if (response.isNextPageExists()) {
            return ResponseEntity.ok().header("next-page").body(response.getPlaces());
        }

        return ResponseEntity.ok().body(response.getPlaces());

    }
}
