package ma.ehtp.gestionrisqueit.phase0.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import lombok.Data;
import ma.ehtp.gestionrisqueit.phase0.tools.U;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
@Data
public class FilesStorageServiceImpl implements FilesStorageService {
    private Path root = Paths.get("uploads");
    private String sousFolder;

    @Override
    public void init() {
        try {

            if (!Files.exists(root)){
                U.ptxt("Directory not exist");

                Files.createDirectory(root);

            }

            U.ptxt("init Files.createDirectory(root)");
        } catch (Exception e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public void saveIn(MultipartFile file, String s) {
        try {
            Files.copy(file.getInputStream(), this.root.resolve(s + file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }


    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files! e : "+e.getMessage());
        }
    }

    @Override
    public void setSousFolder(String s) {
        this.sousFolder = s;
        this.root =  Paths.get("uploads/"+s);
        try {
            if (!Files.exists(root))
            Files.createDirectory(root);
        } catch (IOException e) {
            //throw new RuntimeException("Could not initialize folder for upload!");

            U.ptxt("setSousFolder e : "+e.getMessage());
        }
    }


    public void cDir(String s){
        this.root =  Paths.get("/"+s);
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            //throw new RuntimeException("Could not initialize folder for upload!");

            U.ptxt("this.root"+"  :  "+this.root);
            U.ptxt("Could not initialize folder for upload! error "+e.getMessage());
        }

    }


    public void delet(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            File f = new File(file.toUri());
            f.delete();
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}


