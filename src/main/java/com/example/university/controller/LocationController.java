package com.example.university.controller;


import com.example.university.model.Location;
import com.example.university.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping
    public ResponseEntity<String> createLocation(@RequestBody Location location) {
        String result = locationService.saveLocation(location);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/with-parent")
    public ResponseEntity<String> createLocationWithParent(@RequestBody Location location, @RequestParam Long parentId) {
        String result = locationService.saveLocationWithParent(location, parentId);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        return ResponseEntity.ok(locationService.getAllLocations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        Location location = locationService.getLocationById(id);
        if (location == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(location);
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Location> getLocationByCode(@PathVariable String code) {
        Location location = locationService.getLocationByCode(code);
        if (location == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(location);
    }

    @GetMapping("/provinces")
    public ResponseEntity<List<Location>> getAllProvinces() {
        return ResponseEntity.ok(locationService.getAllProvinces());
    }

    @GetMapping("/provinces/{provinceId}/districts")
    public ResponseEntity<List<Location>> getDistrictsByProvince(@PathVariable Long provinceId) {
        return ResponseEntity.ok(locationService.getDistrictsByProvince(provinceId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateLocation(@PathVariable Long id, @RequestBody Location location) {
        String result = locationService.updateLocation(id, location);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteLocation(@PathVariable Long id) {
        boolean deleted = locationService.deleteLocation(id);
        return ResponseEntity.ok(deleted);
    }
}