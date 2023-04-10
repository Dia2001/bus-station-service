package com.busstation.services;

import com.busstation.payload.request.ChangePasswordRequest;
import com.busstation.payload.response.ApiResponse;

public interface AccountService {
    ApiResponse changePassword(ChangePasswordRequest changePasswordRequest);
}
