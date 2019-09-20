import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.File;

public class File_Review {

    VBox fileReview(File file) {
        //работает
        VBox fileReview = new VBox();
        ImageView fileIcon = new ImageView();
        Hyperlink fileName = new Hyperlink();
        String extension;
        int i = file.getName().lastIndexOf('.');
        if (i > 0) {
            extension = file.getName().substring(i + 1);
        } else extension = "file";
        switch (extension) {
            case "jpg": fileIcon.setImage(new Image("icons\\file types\\jpeg.png")); break;
            case "png": fileIcon.setImage(new Image("icons\\file types\\png.png")); break;
            case "txt": fileIcon.setImage(new Image("icons\\file types\\txt.png")); break;
            case "pdf": fileIcon.setImage(new Image("icons\\file types\\pdf.png")); break;
            case "psd": fileIcon.setImage(new Image("icons\\file types\\psd.png")); break;
            case "gif": fileIcon.setImage(new Image("icons\\file types\\gif.png")); break;
            case "doc":
            case "docx":
                fileIcon.setImage(new Image("icons\\file types\\doc.png")); break;
            case "xlsx":
            case "xls":
                fileIcon.setImage(new Image("icons\\file types\\xls.png")); break;
            default: fileIcon.setImage(new Image("icons\\file types\\file.png"));
        }
        fileName.setText(file.getName());
        fileReview.setAlignment(Pos.CENTER);
        fileIcon.setPreserveRatio(true);
        fileIcon.setFitHeight(40);
        fileReview.getChildren().add(fileIcon);
        fileReview.getChildren().add(fileName);
        return fileReview;
    }
/*        //не работает
        //fileName.setText(file.getName());
        //fileIcon.setImage(new Image("icons\\file types\\jpeg.png"));
        fileName1 = file.getName();
        icon = new Image("icons\\file types\\jpeg.png");
        fileReview = FXMLLoader.load(getClass().getResource("File_Review.fxml"));
        return fileReview;*/
}

