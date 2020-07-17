package backendApplication.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

@Service
public class QRCodeService {

    @Autowired
    private ImageService imageService;

    public String getQRCode(int tourid) {

        final String uri = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=http://localhost:9002/#/tour/8";

        try {

            BufferedImage bufferedImage = ImageIO.read(new URL(uri));

            return imageService.storeImage(bufferedImage, tourid + "-qrcode.png");

        } catch(IOException e) {
            e.printStackTrace();
        }

        return null;

    }

}
