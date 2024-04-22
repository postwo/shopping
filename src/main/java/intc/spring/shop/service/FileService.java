package intc.spring.shop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.cert.Extension;
import java.util.UUID;

@Service
@Slf4j
public class FileService {

    public String uploadFile(String uploadPath, String originalFileName, byte[] fileDate) throws IOException {
        UUID uuid = UUID.randomUUID();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String savedFileName = uuid.toString() + extension;
        String fileUploadFullUrl= uploadPath + "/"+ savedFileName;

        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
        fos.write(fileDate);
        fos.close();

        return savedFileName;
    }


    public void deleteFile(String filePath){
        File deleteFile = new File(filePath);

        //deleteFile.exists() 파일이 존재하냐
        if (deleteFile.exists()){
            deleteFile.delete();
            log.info("파일을 삭제했습니다.");
        }else{
            log.info("파일이 존재하지 않습니다.");
        }
    }



}
