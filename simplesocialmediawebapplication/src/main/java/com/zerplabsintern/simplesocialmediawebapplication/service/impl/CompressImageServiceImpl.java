package com.zerplabsintern.simplesocialmediawebapplication.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.zerplabsintern.simplesocialmediawebapplication.service.CompressImageService;

@Service
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
    
            if( checkLessThanFiveMB( compressedBase64String ) ) {
    
                return compressedBase64String;
            }
    
            else {
                return compressImage( base64Image, 100, 100, quality - 0.1f );
            }
        
    }


    public long getSizeOfImage(String base64String) {

        String base64Image = base64String;
        byte[] decodedImage = Base64.getDecoder().decode(base64Image);
        long imageSizeInBytes = decodedImage.length;

        return imageSizeInBytes;

    }

    @Override
    public boolean checkLessThanFiveMB( String base64String ) {

        double decodedSizeInMB = getSizeOfImage(base64String) / (1024.0 * 1024.0);

        return decodedSizeInMB < 2.0;

    }
    
}
