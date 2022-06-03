package guiExpoloration;

import javax.swing.*;
import java.io.File;

public class FileChooser {
    public File chooseImage() {
        JFileChooser chooser = new JFileChooser();
        int isSelected = chooser.showOpenDialog(null);
        if (isSelected == JFileChooser.APPROVE_OPTION) {
            File imageFile = chooser.getSelectedFile();
            return imageFile;
        } else {
            return null;
        }
    }
}
