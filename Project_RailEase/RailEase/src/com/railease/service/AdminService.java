package com.railease.service;

import com.railease.model.*;
import java.util.*;

public class AdminService {
    
    public boolean loginAdmin(String adminId, String password) {
        List<Admin> admins = FileStorageService.readAdmins();
        for (Admin admin : admins) {
            if (admin.getAdminId().equals(adminId) && admin.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}