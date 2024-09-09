package com.DataConv.Statepop.Controller;

import com.DataConv.Statepop.Entity.Districtpop;
import com.DataConv.Statepop.Service.DistrictService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.IllegalFormatException;

@RestController
@RequestMapping("StatePop")
public class DistController {

    @Autowired
    private DistrictService distservice;

    private static final Logger logger= LogManager.getLogger(DistController.class);

    @PostMapping("upload/file")
    public ResponseEntity<String> fileUpload(@RequestParam("file")MultipartFile file) throws IOException {
        logger.info("Requested for a file upload \n");

        logger.info("Checking whether the file is empty or not \n");

        if(file.isEmpty()){
            logger.info("File is Empty please upload a valid file");
            return new ResponseEntity<>("File is empty!",HttpStatus.BAD_REQUEST);
        }
        try(BufferedReader br=new BufferedReader(new InputStreamReader(file.getInputStream()))){
            String line;
            while((line=br.readLine())!=null){
                String[] parts=line.split(" ");
                if(parts.length!=2) return new ResponseEntity<>("Invalid data format", HttpStatus.BAD_REQUEST);

                String distname=parts[0];
                long population=Long.parseLong(parts[1]);

                Districtpop districtpop=new Districtpop();
                districtpop.setDistname(distname);
                districtpop.setPopulation(population);
                distservice.savepop(districtpop);
            }
            logger.info("File has been uploaded successfully!");
            return new ResponseEntity<>("File uploaded successfully!",HttpStatus.OK);

        }catch (IOException e){
            logger.error("Failed to read the file due to internal server error.");
            return new ResponseEntity<>("Failed to read file", HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(NumberFormatException e){
            logger.info("Format for population is invalid and its a BAD_Request!");
            return new ResponseEntity<>("Invalid population format", HttpStatus.BAD_REQUEST);
        }
        catch(IllegalFormatException e){
            logger.info("Format for State Name is invalid and its a BAD_Request!");
            return new ResponseEntity<>("Invalid State Name format", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/population")
    public ResponseEntity<String> findPopulation(){
        logger.info("Trying to fetch the entire state population");
        long totalPopulation= distservice.getPopulation();
        logger.info("The total state population is: "+ totalPopulation);
        String response="The total state population is: "+ totalPopulation;
        return new ResponseEntity<>(response,HttpStatus.OK);

    }
}
