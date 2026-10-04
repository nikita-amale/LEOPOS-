package com.leonet.controller;

import java.awt.image.BufferedImage;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leonet.serviceimpl.BarbecueBarcodeGenerator;

@Controller
public class BarcodeController {

	
	
	@GetMapping(value = "/barbecue/ean13/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
   @ResponseBody public BufferedImage barbecueEAN13Barcode(@PathVariable("barcode") String barcode)
    throws Exception {
        return  BarbecueBarcodeGenerator.generateEAN13BarcodeImage(barcode);
    }
	
	@GetMapping(value = "/barbecue/show/barcode")
    public String showBarcode() {
    return "barcode";
    }
}
