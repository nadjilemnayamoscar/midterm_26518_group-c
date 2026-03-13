package com.example.university.service;


import com.example.university.model.Location;
import com.example.university.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public String saveLocation(Location location) {
        Optional<Location> existing = locationRepository.findByCode(location.getCode());
        if (existing.isPresent()) {
            return "Location with code " + location.getCode() + " already exists";
        }
        locationRepository.save(location);
        return "Location saved successfully";
    }

    public String saveLocationWithParent(Location location, Long parentId) {
        Optional<Location> parentOpt = locationRepository.findById(parentId);
        if (!parentOpt.isPresent()) {
            return "Parent location not found";
        }
        location.setParent(parentOpt.get());
        locationRepository.save(location);
        return "Location saved with parent successfully";
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location getLocationById(Long id) {
        Optional<Location> location = locationRepository.findById(id);
        return location.orElse(null);
    }

    public Location getLocationByCode(String code) {
        Optional<Location> location = locationRepository.findByCode(code);
        return location.orElse(null);
    }

    public List<Location> getAllProvinces() {
        return locationRepository.findAllProvinces();
    }

    public List<Location> getDistrictsByProvince(Long provinceId) {
        return locationRepository.findByParentAndLevel(provinceId, "DISTRICT");
    }

    public String updateLocation(Long id, Location updatedLocation) {
        Optional<Location> existingOpt = locationRepository.findById(id);
        if (!existingOpt.isPresent()) {
            return "Location not found";
        }
        Location existing = existingOpt.get();
        existing.setName(updatedLocation.getName());
        existing.setCode(updatedLocation.getCode());
        existing.setLevel(updatedLocation.getLevel());
        locationRepository.save(existing);
        return "Location updated successfully";
    }

    public boolean deleteLocation(Long id) {
        if (locationRepository.existsById(id)) {
            locationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}