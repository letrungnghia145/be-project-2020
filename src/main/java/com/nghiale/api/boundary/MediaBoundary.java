package com.nghiale.api.boundary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MediaBoundary {
	private static final String DIR = "/images";

	@GetMapping(value = "/media/{path}", produces = { "image/png" })
	public FileOutputStream getTestFile(@PathVariable String path) throws FileNotFoundException {
		return new FileOutputStream(new File(DIR + File.separator + path + ".png"));
	}
}
