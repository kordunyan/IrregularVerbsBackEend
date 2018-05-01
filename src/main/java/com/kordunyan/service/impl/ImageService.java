package com.kordunyan.service.impl;

import com.kordunyan.config.AppProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

@Service
public class ImageService {

	private final String IMAGES_PATH;

	public ImageService(Environment env) {
		IMAGES_PATH = env.getProperty(AppProperties.PROP_EXTERNAL_IMAGES);
	}

	public String saveImage(String imgURL) throws IOException {
		URL url = new URL(imgURL);
		URLConnection connection = url.openConnection();
		String imageName = generateImageName(getExtension(imgURL));
		try (InputStream input = connection.getInputStream();
			 OutputStream ouptupStream = new FileOutputStream(IMAGES_PATH + imageName)) {
			byte[] buffer = new byte[2048];
			int length;
			while ((length = input.read(buffer)) != -1) {
				ouptupStream.write(buffer, 0, length);
			}
		}
		return imageName;
	}

	private String getExtension(String imageSrc) {
		if (StringUtils.isBlank(imageSrc)) {
			throw new IllegalStateException("[imageSrc] param should not be blank");
		}
		return imageSrc.substring(imageSrc.lastIndexOf('.'), imageSrc.length());
	}

	private String generateImageName(String extension) {
		return String.valueOf(UUID.randomUUID()) + extension;
	}

}
