package fileio;

import paint.PaintPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FileIO {
    private static JFileChooser saveFileChooser;
    private static JFileChooser openFileChooser;
    private static FileNameExtensionFilter savFilter;
    private static FileNameExtensionFilter jpgFilter;
    private static FileIO instance = null;

    public static synchronized FileIO getFileIO() {
        if(instance==null)
            return new FileIO();
        else
            return instance;
    }

    private FileIO() {
        saveFileChooser = new JFileChooser();
        openFileChooser = new JFileChooser();
        savFilter = new FileNameExtensionFilter("*.sav(paint saved file)", "sav");
        jpgFilter = new FileNameExtensionFilter("*.png(png image)", "png");
        saveFileChooser.setFileFilter(savFilter);
        saveFileChooser.setFileFilter(jpgFilter);
        openFileChooser.setFileFilter(savFilter);
    }

    public File saveFile(Component c, PaintPanel board) {
        saveFileChooser.showSaveDialog(c);
        File file = saveFileChooser.getSelectedFile();
        if(saveFileChooser.getFileFilter() == jpgFilter) {
            file = new File(file.getAbsoluteFile() + ".png");
            try {
                ImageIO.write(board.getImage(), "png", file);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(c, "save file error");
                e.printStackTrace();
            }
        } else if (saveFileChooser.getFileFilter() == savFilter) {
            if(!file.getName().endsWith(".sav")) {
                file = new File(file.getAbsoluteFile() + ".sav");
                board.saveImageToFile(file);
            }
        }
        return file;
    }

    public File openFile(Component c) {
        openFileChooser.showOpenDialog(c);
        return openFileChooser.getSelectedFile();
    }

}

