package fxlauncher;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Owner on 2016-10-31.
 */
public class FFUIProvider implements UIProvider {
    private Stage stage;
    private ProgressBar progressBar;
    private Locale default_Locale = Locale.getDefault();
    private Locale en_US_Local = new Locale("en","US");
    private ResourceBundle bundle = ResourceBundle.getBundle("fxlauncher.info",default_Locale);
    private String  charSetName = "ISO-8859-1";
    @Override
    public void init(Stage stage) {
        this.stage = stage;
        String title = null;
        try {
            title = new String(bundle.getString("title").getBytes(charSetName));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.stage.setTitle(title);
    }

    @Override
    public Parent createLoader() {
        StackPane root = new StackPane(new ProgressIndicator());
        root.setPrefSize(400, 220);
        root.setPadding(new Insets(10));
        return root;
    }

    @Override
    public Parent createUpdater(FXManifest manifest) {
        progressBar = new ProgressBar();
        progressBar.setStyle(manifest.progressBarStyle);
        String updateText = null;
        try {
            updateText = new String(bundle.getString("updating").getBytes(charSetName));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Label upDesc = new Label("更新描述:自动更新框架测试");
        Label updateLabel = new Label(updateText);
        updateLabel.setStyle(manifest.updateLabelStyle);
        VBox wrapper = new VBox(upDesc,updateLabel, progressBar);
        wrapper.setStyle(manifest.wrapperStyle);
        return wrapper;
    }

    @Override
    public void updateProgress(double progress) {
        progressBar.setProgress(progress);
    }
}
