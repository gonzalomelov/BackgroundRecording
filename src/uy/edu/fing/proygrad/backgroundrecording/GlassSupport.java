package uy.edu.fing.proygrad.backgroundrecording;

import android.content.Context;
import android.media.AudioManager;

public class GlassSupport {

    public static void playSoundEffect(Context context, int sound) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.playSoundEffect(sound);
    }

}
