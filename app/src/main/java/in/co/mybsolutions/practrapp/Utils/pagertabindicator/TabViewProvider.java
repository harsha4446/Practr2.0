package in.co.mybsolutions.practrapp.Utils.pagertabindicator;

import android.net.Uri;
import android.view.View;

/**
 * Created by dhaval.mehta on 17-May-18.
 */

public class TabViewProvider {

    public interface ImageProvider {
        Uri getImageUri(int position);

        int getImageResourceId(int position);
    }
    
    public interface CustomView {
        View getView(int position);
    }

}
