package dk.easv;

import javafx.concurrent.Task;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class Slideshow implements Runnable {
    private List<Image> images;
    private ImageView imageView;
    private int currentImageIndex = 0;
    int time;

    public Slideshow(List<Image> images, ImageView imageView,int time) {
        this.images = images;
        this.imageView = imageView;
        this.time = time;
    }
    @Override
    public void run() {
        if(!images.isEmpty()) {
            while(true) {
                imageView.setImage(images.get(currentImageIndex));
                currentImageIndex++;
                if(currentImageIndex == images.size()) {
                    currentImageIndex = 0;
                }
            }
        }

    }
}
