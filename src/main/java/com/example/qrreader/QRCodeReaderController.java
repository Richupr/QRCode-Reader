package com.example.qrreader;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;



@RestController
@RequestMapping("/api/qrcode")
public class QRCodeReaderController {

    @Autowired
    private QRCodeReaderService qrCodeReaderService;

    @PostMapping("/read")
    public String readQRCode(@RequestParam("file") MultipartFile file) {
        try {
            // Save the uploaded file to the local file system
            File savedFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
            file.transferTo(savedFile);

            // Use the service to read the QR code from the saved file
            String qrCodeText = qrCodeReaderService.readQRCode(savedFile.getAbsolutePath());

            // Return the decoded text
            return qrCodeText;

        } catch (IOException e) {
            return "Error saving the file: " + e.getMessage();
        }
    }
}
