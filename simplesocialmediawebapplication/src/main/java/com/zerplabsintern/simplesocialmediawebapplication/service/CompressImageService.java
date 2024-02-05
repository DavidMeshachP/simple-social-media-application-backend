package com.zerplabsintern.simplesocialmediawebapplication.service;

public interface CompressImageService {

    String compressImage(String base64Image, int targetWidth, int targetHeight, float quality) throws Exception;

    boolean checkLessThanFiveMB( String base64String );
    
}
