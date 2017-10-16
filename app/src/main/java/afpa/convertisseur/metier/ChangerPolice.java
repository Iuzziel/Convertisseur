package afpa.convertisseur.metier;

import android.graphics.Typeface;
import android.util.Log;

import static java.security.AccessController.getContext;

/**
 * Created by DL101 on 16/10/2017.
 */

public class ChangerPolice {

    public static void setTypeFace(String fontName) {
        if (fontName != null) {
            try {
                Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + fontName);
                setTypeface(typeface);
            } catch (Exception e) {
                Log.e("FONT", fontName + " not found", e);
            }
        }
    }
}
