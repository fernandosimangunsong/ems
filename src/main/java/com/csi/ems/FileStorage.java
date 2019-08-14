package com.csi.ems;

import com.csi.ems.model.Employee;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource ;
import java.io.File;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorage {
     void init();
     void store(MultipartFile file);
     Resource loadFile(String filename);
     void deleteAll();
     Stream<Path> loadFiles();
     String saveImg(MultipartFile file, Employee employee);
     String saveFile(MultipartFile file, Employee employee);
}
