package dk.easv;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class Slideshow implements Runnable {
    private List<Image> images;
    private ImageView imageView;
    private Label fileLabel;
    private int currentImageIndex = 0;
    int time;
    private boolean running = true;

    public Slideshow(List<Image> images, ImageView imageView,int time, Label fileLabel) {
        this.images = images;
        this.imageView = imageView;
        this.time = time;
        this.fileLabel = fileLabel;
    }
    @Override
    public void run() {
        if(!images.isEmpty()) {
            System.out.println(time);
                while(running) {
                    currentImageIndex = (currentImageIndex + 1) % images.size();
                    imageView.setImage(images.get(currentImageIndex));
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }

    }

    private void setFileLabel() {
        fileLabel.setText(images.get(currentImageIndex).getUrl());
    }

    public void stop()
    {
        this.running = false;
    }

}
