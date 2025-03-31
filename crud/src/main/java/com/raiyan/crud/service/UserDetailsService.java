package com.raiyan.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.raiyan.crud.model.UserDetails;
import com.raiyan.crud.repository.UserDetailsRepository;

import java.util.Optional;

@Service
public class UserDetailsService {
    
    @Autowired
    private UserDetailsRepository userDetailsRepository;

    // ✅ Get UserDetails by UID
    public UserDetails getUserDetailsByUid(int uid) {
        return userDetailsRepository.findByUID(uid).orElse(null);
    }

    // ✅ Create UserDetails
    public void createUserDetails(UserDetails userDetails) {
        userDetailsRepository.saveUserDetails(userDetails);
    }

    // ✅ Update UserDetails
    public UserDetails updateUserDetails(int uid, UserDetails updatedUserDetails) {
        Optional<UserDetails> existingDetails = userDetailsRepository.findByUID(uid);

        if (existingDetails.isPresent()) {
            UserDetails userDetails = existingDetails.get();
            userDetails.setFullName(updatedUserDetails.getFullName());
            userDetails.setAddress(updatedUserDetails.getAddress());
            userDetails.setContact(updatedUserDetails.getContact());
            userDetails.setDriverLicenseNumber(updatedUserDetails.getDriverLicenseNumber());

            // Save the updated details
            userDetailsRepository.updateUserDetails(userDetails);
            return userDetails;
        }
        return null;
    }

    // ✅ Delete UserDetails
    public void deleteUserDetails(int uid) {
        userDetailsRepository.deleteUserDetails(uid);
    }
}
