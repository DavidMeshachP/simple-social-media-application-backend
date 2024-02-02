package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import com.zerplabsintern.simplesocialmediawebapplication.service.CompressImageService;

public class CompressImageServiceImpl implements CompressImageService { 


    @Override
    public String compressImage(String base64Image, int targetWidth, int targetHeight, float quality) throws Exception {
        
        byte[] imageData = Base64.getDecoder().decode(base64Image);

        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
        BufferedImage image = ImageIO.read(bais);

        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        
        resizedImage.createGraphics().drawImage(image.getScaledInstance(targetWidth, targetHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, "jpg", baos);
        baos.flush();
        byte[] compressedImageData = baos.toByteArray();
        baos.close();
        
        String compressedBase64String = Base64.getEncoder().encodeToString(compressedImageData);

        return compressedBase64String;

    }

    public int getSizeOfImage(String base64String) {
        String base64Image = base64String;
        byte[] decodedImage = Base64.getDecoder().decode(base64Image);
        int imageSizeInBytes = decodedImage.length;
        return imageSizeInBytes;
    }
    
}
