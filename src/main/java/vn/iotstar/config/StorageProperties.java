package vn.iotstar.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("storage")
public class StorageProperties {
	// duong dan thu muc luu file, cau hinh trong application.properties: storage.location=uploads
	private String location = "uploads";
}
