package com.firstdockerapp.dockerspringboot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;

import org.springframework.web.bind.annotation.*;

@RestController
class RootController {

    public void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                System.out.println(fileEntry.getName());
            }
        }
    }

    @RequestMapping(method=RequestMethod.GET, path="/")
    public String root() {
        File dataDirectory = new File("/data");
        if(!dataDirectory.exists()) {
            System.out.println("'/data' directory does not exist");
        }
        else {
            System.out.println("Files inside '/data' directory");
            listFilesForFolder(dataDirectory);
            File logFile = new File("/data/log_file.txt");
            if(!logFile.exists()) {
                try{
                    if(!logFile.createNewFile()) {
                        System.out.println("Unable to create log file");
                    }
                }
                catch(Exception e) {
                    System.out.println("Unable to create log file");
                    e.printStackTrace();
                }
            }
            try {
                FileWriter writer = new FileWriter(logFile, true);
                writer.write(System.getProperty("ID") + " : A worker wrote line at : "+ Instant.now()+"\n");
                writer.close();

                FileInputStream fis = new FileInputStream(logFile);
                byte[] data = new byte[(int) logFile.length()];
                fis.read(data);
                fis.close();

                System.out.println(data);

            } catch (IOException e) {
                e.printStackTrace();
			}
        }
        return "Hello World";
    }

}