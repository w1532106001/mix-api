package com.whc.mix_api.auth.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author whc
 * @date 2020/5/31 0:19
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class ImageCode extends ValidateCode {

    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireId) {
        super(code, LocalDateTime.now().plusSeconds(expireId));
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime localDateTime) {
        super(code, localDateTime);
        this.image = image;
    }

}
