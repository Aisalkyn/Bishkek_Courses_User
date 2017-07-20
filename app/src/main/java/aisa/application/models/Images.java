package aisa.application.models;

/**
 * Created by admin on 7/7/17.
 */

public class Images {

    private boolean isLogo;
    private String imgpath;

    public Images(boolean isLogo, String imgpath){
        this.isLogo = isLogo;
        this.imgpath = imgpath;

    }

    public boolean isLogo() {
        return isLogo;
    }

    public String getImagePath() {
        return imgpath;
    }
}
