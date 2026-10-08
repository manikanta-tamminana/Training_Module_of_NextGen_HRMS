package com.MyNicProject.hrms.service;

import com.MyNicProject.hrms.entity.Department;
import com.MyNicProject.hrms.entity.Employee;
import com.MyNicProject.hrms.entity.TrainingModule;
import com.MyNicProject.hrms.entity.TrainingRecord;
import com.MyNicProject.hrms.repository.DepartmentRepository;
import com.MyNicProject.hrms.repository.EmployeeRepository;
import com.MyNicProject.hrms.repository.TrainingModuleRepository;
import com.MyNicProject.hrms.repository.TrainingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import java.util.Locale;

@Service
public class TrainingRecordService {

    @Value("${file.upload-dir:uploads/certificates}")
    private  String uploadDir;

    @Autowired
    private DepartmentRepository departmentRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private TrainingRecordRepository recordRepo;

    @Autowired
    private TrainingModuleRepository moduleRepo;


   @Transactional
    public TrainingRecord saveRecord(
            String employeeName, String employeeId, String departmentName,
            String moduleName, String trainingType , String instructorName,
            String status, LocalDate issueDate , String remarks,String certificateNum,
            MultipartFile file) throws IOException{

       Optional<Department> dept = departmentRepo.findByDepartmentName(departmentName);
       Department department;
       if(dept.isPresent()){
           department = dept.get();
       }else{
           Department newDept = new Department();
           newDept.setDepartmentName(departmentName);
           department = departmentRepo.save(newDept);
       }

       Optional<Employee> emp = employeeRepo.findByEmployeeId(employeeId);
       Employee employee;
       if(emp.isPresent()){
           employee = emp.get();
       }else{
           Employee newEmp = new Employee();
           newEmp.setEmployeeName(employeeName);
           newEmp.setEmployeeId(employeeId);
           newEmp.setDepartment(department);
           employee = employeeRepo.save(newEmp);
       }

       Optional< TrainingModule> Tmodule = moduleRepo.findByModuleName(moduleName);
       TrainingModule module ;
       if(Tmodule.isPresent()){
           module = Tmodule.get();
       }else{
           TrainingModule newModule = new TrainingModule();
           newModule.setModuleName(moduleName);
           newModule.setTrainingType(trainingType);
           module = moduleRepo.save(newModule);
       }

       TrainingRecord record = new TrainingRecord();
       record.setEmployee(employee);
       record.setModule(module);
       record.setInstructorName(instructorName);
       record.setCertificateNumber(certificateNum);
       record.setRemarks(remarks);
       record.setIssueDate(issueDate);
       record.setStatus(status);

       if(file != null){
           validateCertificate(file);
           Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
           Files.createDirectories(uploadPath);
           String originalFname = StringUtils.cleanPath(file.getOriginalFilename() == null ? "" : file.getOriginalFilename());
           String extension = originalFname.substring(originalFname.lastIndexOf('.') + 1).toLowerCase(Locale.ROOT);
           String uniqueFname = UUID.randomUUID() + "." + extension;
           Path targetLocation = uploadPath.resolve(uniqueFname).normalize();
           if (!targetLocation.startsWith(uploadPath)) {
               throw new IllegalArgumentException("Invalid certificate filename");
           }

           Files.copy(file.getInputStream(), targetLocation);

           record.setFileName(originalFname);
           record.setFileType(file.getContentType());
           record.setFilePath(targetLocation.toString());
       }

     return recordRepo.save(record);
   }

   private void validateCertificate(MultipartFile file) throws IOException {
       if (file.isEmpty()) {
           throw new IllegalArgumentException("Certificate file must not be empty");
       }
       String filename = StringUtils.cleanPath(file.getOriginalFilename() == null ? "" : file.getOriginalFilename());
       if (filename.isBlank() || filename.contains("..") || filename.lastIndexOf('.') < 0) {
           throw new IllegalArgumentException("Invalid certificate filename");
       }
       String extension = filename.substring(filename.lastIndexOf('.') + 1).toLowerCase(Locale.ROOT);
       String contentType = file.getContentType();
       byte[] header = file.getInputStream().readNBytes(8);
       boolean valid = switch (extension) {
           case "pdf" -> "application/pdf".equalsIgnoreCase(contentType)
                   && header.length >= 5 && header[0] == '%' && header[1] == 'P' && header[2] == 'D' && header[3] == 'F' && header[4] == '-';
           case "png" -> "image/png".equalsIgnoreCase(contentType)
                   && header.length == 8 && (header[0] & 0xff) == 0x89 && header[1] == 'P'
                   && header[2] == 'N' && header[3] == 'G' && header[4] == 0x0d
                   && header[5] == 0x0a && header[6] == 0x1a && header[7] == 0x0a;
           case "jpg", "jpeg" -> "image/jpeg".equalsIgnoreCase(contentType)
                   && header.length >= 3 && (header[0] & 0xff) == 0xff && (header[1] & 0xff) == 0xd8 && (header[2] & 0xff) == 0xff;
           default -> false;
       };
       if (!valid) {
           throw new IllegalArgumentException("Only valid PDF, PNG, and JPEG certificates are accepted");
       }
   }

}
