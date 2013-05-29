package grp09616;


public class RendererMenu
{
		public static final int START_X = 600;
		public static final int DELTA_X = -50;
		public static final int START_Y = 300;
		public static final int DELTA_Y = 80;
		
		private static int menuTexture;
		
		enum MenuPage
		{
			SPLASH, MAIN, INSTRUCTIONS, SCORES, STORY
		}
		
		static
		{
			loadTextures();
		}
		
		public static void loadTextures()
		{
			// splashTexture = ManagerTextures.loadTextures(BearGame.TEXTURE_PATH +
			// "menu/splash.png");
			 menuTexture = ManagerTextures.loadTextures(BearGame.TEXTURE_PATH +
			 "menu/background.png");
		}
}