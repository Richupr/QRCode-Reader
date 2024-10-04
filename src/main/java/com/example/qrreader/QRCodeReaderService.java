package com.example.qrreader;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

;

@Service
public class QRCodeReaderService {

    public String readQRCode(String filePath) {
        try {
            // Load the image file
            BufferedImage bufferedImage = ImageIO.read(new File(filePath));

            // Convert the image to a binary bitmap source
            LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));

            // Decode the QR code
            Result result = new MultiFormatReader().decode(binaryBitmap);

            // Return the decoded text
            return result.getText();

        } catch (IOException e) {
            return "Error reading the file: " + e.getMessage();
        } catch (NotFoundException e) {
            return "QR Code not found in the image.";
        }
    }
}
