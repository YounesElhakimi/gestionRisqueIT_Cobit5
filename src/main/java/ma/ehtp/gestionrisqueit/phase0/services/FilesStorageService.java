package ma.ehtp.gestionrisqueit.phase0.services;

import java.nio.file.Path;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {
    public void init();
    public void save(MultipartFile file);
    public void saveIn(MultipartFile file , String s);
    public Resource load(String filename);
    public void deleteAll();
    public Stream<Path> loadAll();
    public void setSousFolder(String s);
    public void delet(String filename);
}