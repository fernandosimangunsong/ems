package com.csi.ems;

import com.csi.ems.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileStorageImpl implements FileStorage {

    @Autowired
    private EmployeeRepository repoEmployee;

    private Path rootLocation = Paths.get("/Users/fernandosimangunsong/IntelliJIDEA/ems/foto");

    @Override
    public void init(){
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void store(MultipartFile file){
        try {
            Files.copy(file.getInputStream(), rootLocation.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resource loadFile(String filename){
        Path file = rootLocation.resolve(filename);
        UrlResource resource = null;
        try {
            resource = new UrlResource(file.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if(resource.exists() || resource.isReadable()){
            return resource;
        }else {
            throw new RuntimeException("Fail");
        }

    }

    public void deleteAll(){
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public Stream<Path> loadFiles(){
        try {
            return Files.walk(rootLocation, 1)
                    .filter(path -> !path.equals(rootLocation))
                    .map(rootLocation::relativize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String saveImg(MultipartFile Imgname, Employee employee){
        store(Imgname);
        String newFileNametoString = Imgname.getOriginalFilename().toString();
        String simpan = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
                .path(newFileNametoString)
                .toUriString();
        employee.setPhoto(simpan);
        return newFileNametoString;
    }

    public String saveFile(MultipartFile file, Employee employee){
        store(file);
        String newFileNametoString = file.getOriginalFilename().toString();
        String save = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
                .path(newFileNametoString)
                .toUriString();
        employee.setCv(save);
        repoEmployee.save(employee);
        return newFileNametoString;
    }
}
