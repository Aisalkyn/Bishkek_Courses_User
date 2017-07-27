package aisa.application.model;


public class Images{
    private boolean isLogo;
    private String imagePath;

    public Images(String imagePath, boolean isLogo){
        this.imagePath = imagePath;
        this.isLogo = isLogo;
    }

    public boolean isLogo() {
        return isLogo;
    }

    public String getImagePath() {
        return imagePath;
    }

}
