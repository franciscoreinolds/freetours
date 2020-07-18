package backendApplication.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

@Service
public class QRCodeService {

    @Autowired
    private ImageStoreService imageStoreService;

    @Autowired
    private Environment env;

    public String getQRCode(int tourid) {

        String token = env.getProperty("frontend.url") + "/#/tour/" + tourid;
        token = token.replaceAll("#","%23");
        String uri = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=" + token;

        System.out.println(uri);

        try {

            BufferedImage bufferedImage = ImageIO.read(new URL(uri));

            return imageStoreService.storeImage(bufferedImage, tourid + "-qrcode.png");

        } catch(IOException e) {
            e.printStackTrace();
        }

        return null;

    }

}
