package dk.easv;

import java.io.File;
import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ImageViewerWindowController
{
    @FXML
    private Label fileLabel;
    @FXML
    private Slider speedSlider;
    private final List<Image> images = new ArrayList<>();
    private int currentImageIndex = 0;
    private Slideshow slideshow;


    @FXML
    Parent root;

    @FXML
    private ImageView imageView;

    @FXML
    private void handleBtnLoadAction()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select image files");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Images",
                "*.png", "*.jpg", "*.gif", "*.tif", "*.bmp"));
        List<File> files = fileChooser.showOpenMultipleDialog(new Stage());

        if (!files.isEmpty())
        {
            files.forEach((File f) ->
            {
                images.add(new Image(f.toURI().toString()));
            });
            displayImage();
        }
    }

    @FXML
    private void handleBtnPreviousAction()
    {
        if (!images.isEmpty())
        {
            currentImageIndex =
                    (currentImageIndex - 1 + images.size()) % images.size();
            displayImage();
        }
    }

    @FXML
    private void handleBtnNextAction()
    {
        if (!images.isEmpty())
        {
            currentImageIndex = (currentImageIndex + 1) % images.size();
            displayImage();
        }
    }

    private void displayImage()
    {
        if (!images.isEmpty())
        {
            imageView.setImage(images.get(currentImageIndex));
            Platform.runLater(this::setFileLabel);
        }
    }

    private void setFileLabel()
    {
        fileLabel.setText(images.get(currentImageIndex).getUrl());
    }

    public void handleBtnSlideshowAction(ActionEvent actionEvent) {
        ExecutorService exec = Executors.newCachedThreadPool();
        int time = (int) speedSlider.getValue();
        slideshow = new Slideshow(images, imageView, time, fileLabel);
        System.out.println(time);
        exec.submit(slideshow);

    }
    public void handleBtnStopSlides(ActionEvent actionEvent) {
        slideshow.stop();
    }


}