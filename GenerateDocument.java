package com.dandi.api.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dandi.api.helper.POIDOCXTableHelper;

@RestController
public class GenerateDocument {

		@GetMapping("/testGeneration")
		public ResponseEntity<String> testGeneration() {
			try {
				POIDOCXTableHelper.modifyAndCopy();
			} catch (IOException | URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return new ResponseEntity<>(HttpStatus.OK);
		}
}
