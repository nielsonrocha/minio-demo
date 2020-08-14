package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	final MinioAdapter minioAdapter;

	public DemoApplicationTests(MinioAdapter minioAdapter) {
		this.minioAdapter = minioAdapter;
	}



}
