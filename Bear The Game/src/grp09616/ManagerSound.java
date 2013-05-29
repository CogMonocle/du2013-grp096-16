package grp09616;

import java.io.IOException;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class ManagerSound
{
	private static Audio music;
	
	public static void initialize()
	{
		try
		{
			music = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(BearGame.SOUND_PATH + "music/MemphisJugBand-StealinStealin.wav"));
			music.playAsMusic(1.0f, 1.0f, true);
		}
		catch (IOException e)
		{
			//e.printStackTrace();
		}
	}
	
	public static void cleanup()
	{
		music.release();
	}
}
