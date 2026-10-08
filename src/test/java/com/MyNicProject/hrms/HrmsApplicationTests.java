package com.MyNicProject.hrms;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import com.MyNicProject.hrms.entity.TrainingRecord;
import com.MyNicProject.hrms.repository.TrainingRecordRepository;
import com.MyNicProject.hrms.service.TrainingRecordService;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class HrmsApplicationTests {
	@Autowired
	private TrainingRecordService trainingService;

	@Autowired
	private TrainingRecordRepository trainingRecordRepository;

	@TempDir
	Path uploadDirectory;

	@Test
	void contextLoads() {
	}

	@Test
	void savesCertificateWithSeparateMimeTypeAndPath() throws Exception {
		ReflectionTestUtils.setField(trainingService, "uploadDir", uploadDirectory.toString());
		byte[] pdf = "%PDF-1.7\ncertificate".getBytes();
		MockMultipartFile file = new MockMultipartFile(
				"certificateFile", "certificate.pdf", "application/pdf", pdf);

		TrainingRecord saved = trainingService.saveRecord(
				"Test Employee", "TEST-EMP-1", "Test Department", "Security Training",
				"Online", "Test Instructor", "Completed", LocalDate.now(), "", "CERT-TEST-1", file);

		TrainingRecord loaded = trainingRecordRepository.findById(saved.getRecordId()).orElseThrow();
		Path storedPath = Path.of(loaded.getFilePath());
		assertEquals("application/pdf", loaded.getFileType());
		assertEquals("certificate.pdf", loaded.getFileName());
		assertTrue(storedPath.startsWith(uploadDirectory));
		assertArrayEquals(pdf, Files.readAllBytes(storedPath));
	}

	@Test
	void rejectsUnsupportedCertificateExtensions() {
		ReflectionTestUtils.setField(trainingService, "uploadDir", uploadDirectory.toString());
		MockMultipartFile file = new MockMultipartFile(
				"certificateFile", "certificate.docx", "application/zip", new byte[] {1, 2, 3});

		assertThrows(IllegalArgumentException.class, () -> trainingService.saveRecord(
				"Test Employee", "TEST-EMP-2", "Test Department", "Security Training",
				"Online", "Test Instructor", "Completed", LocalDate.now(), "", "CERT-TEST-2", file));
	}

}
