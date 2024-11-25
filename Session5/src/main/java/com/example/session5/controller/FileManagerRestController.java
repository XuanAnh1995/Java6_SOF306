package com.example.session5.controller;

import com.example.session5.repository.FileManagerService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
public class FileManagerRestController {

    @Autowired
    private FileManagerService fileManagerService;

    @GetMapping("/rest/files/{folder}/{file}")
    public byte[] download(@PathVariable("folder") String folder, @PathVariable("file") String file){
        return fileManagerService.read(folder, file);
    }

    @PostMapping("/rest/files/{folder}")
    public List<String> upload(@PathVariable("folder") String folder, @PathParam("files")MultipartFile[] files){
        return fileManagerService.save(folder, files);
    }

    @DeleteMapping("/rest/files/{folder}/{file}")
    public void delete(@PathVariable("folder") String folder, @PathVariable("file") String file){
        fileManagerService.delete(folder, file);
    }

    @GetMapping("/rest/files/{folder}")
    public List<String> list(@PathVariable("folder") String folder){
        return fileManagerService.list(folder);
    }

}
