package Myba.UI.customComponents;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by Mixmania on 11-06-15 at 17:34.
 */

public abstract class FileUploader extends Upload implements Upload.Receiver, Upload.SucceededListener, Upload.ProgressListener
{
    private File file;
    private boolean created = false;

    public FileUploader()
    {
        super.setReceiver(this);
        super.setButtonCaption(null);
        super.addSucceededListener(this);
        super.addProgressListener(this);
    }

    @Override
    public OutputStream receiveUpload(String filename, String mimeType)
    {
        try
        {
            file = new File("/tmp/uploads/" + filename);
            return new FileOutputStream(file);
        }
        catch (FileNotFoundException e)
        {
            new Notification("Could not open file<br/>", e.getMessage(), Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
            return null;
        }
    }

    @Override
    public void updateProgress(long readBytes, long contentLength)
    {
        if (!created)
        {
            Notification n = new Notification("Téléchargement de votre fichier sur le serveur en cours...", Notification.Type.WARNING_MESSAGE);
            n.setDelayMsec(-1);
            n.show(Page.getCurrent());
            created = true;
        }
    }

    @Override
    public void uploadSucceeded(SucceededEvent event)
    {
        created = false;
        uploadSucceeded(file, event);
    }

    public abstract void uploadSucceeded(File file, SucceededEvent event);
}
