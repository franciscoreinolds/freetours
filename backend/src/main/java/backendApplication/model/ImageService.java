package backendApplication.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class ImageService {

    @Autowired
    private Environment env;

    @SafeVarargs
    public final List<String> storeImage(Pair<MultipartFile, String>... images) {

        createDirectory();

        List<String> filenames = new ArrayList<>();

        for(Pair<MultipartFile, String> pair : images) {

            MultipartFile image = pair.getFirst();
            String imageName = pair.getSecond();

            String fileName = StringUtils.cleanPath(imageName);
            Path path = Paths.get(env.getProperty("app.shared.images") + fileName);

            copyImage(image, path);

            filenames.add(fileName);

        }

        return filenames;

    }

    public String storeImage(RenderedImage image, String imageName) {

        createDirectory();

        String fileName = StringUtils.cleanPath(imageName);
        String filePath = env.getProperty("app.shared.images") + fileName;
        File file = new File(filePath);

        try {

            if(!file.exists())
                file.createNewFile();

            filePermissions(Paths.get(filePath));
            ImageIO.write(image, "png", file);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;

    }

    private void createDirectory() {

        File dir = new File(Objects.requireNonNull(env.getProperty("app.shared.images")));
        if (!dir.exists())
            dir.mkdir();

    }

    private void copyImage(MultipartFile image, Path path) {

        try {

            Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            filePermissions(path);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void filePermissions(Path path) throws IOException {

        Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rw-r--r--");
        Files.setPosixFilePermissions(path, permissions);

    }

}
