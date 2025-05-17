//package com.example.VehicleManagement.config;
//
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestControllerAdvice
//public class GlobalExceptionHandler {
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException ex) {
//        Map<String, Object> errorBody = new HashMap<>();
//        errorBody.put("timestamp", LocalDateTime.now());
//        errorBody.put("message", ex.getMessage());
//        errorBody.put("status", HttpStatus.NOT_FOUND.value());
//
//        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
//    }
//
//    // Handle all other exceptions (fallback)
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex, HttpServletRequest request) {
//        String requestURI = request.getRequestURI();
//
//      if (requestURI.startsWith("/v3/api-docs") || requestURI.startsWith("/swagger-ui")) {
//            // Throw the exception to allow Spring to handle it as a regular request
//            throw new RuntimeException(ex);
//        }
//        Map<String, Object> errorBody = new HashMap<>();
//        errorBody.put("timestamp", LocalDateTime.now());
//        errorBody.put("message", "An unexpected error occurred");
//        errorBody.put("details", ex.getMessage());
//        errorBody.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
//
//        return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
