package grp09616;


public class ManagerMenu
{
		public static final int NUM_MAIN_BUTTONS = 4;
		public static final int NUM_INFO_SCREENS = 3;
		public static final int START_X = 620;
		public static final int DELTA_X = -50;
		public static final int START_Y = 345;
		public static final int DELTA_Y = 80;
		
		private enum MenuPage
		{
			SPLASH, MAIN, INSTRUCTIONS, SCORES, STORY
		}
		
		private static MenuPage curMenu;
		
		private static int mainTexture;
		private static int[] mainButtonTextures;
		private static int[] infoTextures;
		private static int[] infoButtonTextures;
		private static int mouseX;
		private static int mouseY;
		private static int buttonWidth;
		private static int buttonHeight;
		
		static
		{
			loadTextures();
			curMenu = MenuPage.MAIN;
			buttonWidth = ManagerTextures.getTexture(mainButtonTextures[0]).getImageWidth();
			buttonHeight = ManagerTextures.getTexture(mainButtonTextures[0]).getImageHeight();
		}
		
		public static void loadTextures()
		{
			// splashTexture = ManagerTextures.loadTextures(BearGame.TEXTURE_PATH +
			// "menu/splash.png");
			 mainTexture = ManagerTextures.loadTextures(BearGame.TEXTURE_PATH + "menu/TitleScreen.png");
			 mainButtonTextures = RendererMain.loadTextureArray("menu/button", NUM_MAIN_BUTTONS);
			 //infoTextures = RendererMain.loadTextureArray("menu/infoscreen", NUM_INFO_SCREENS);
		}
		
		public static void render()
		{
			switch(curMenu)
			{
			case SPLASH:
				renderSplash();
				break;
			case MAIN:
				renderMain();
				break;
			case INSTRUCTIONS:
				renderInstructions();
				break;
			case SCORES:
				break;
			case STORY:
				break;
			default:
				break;
			}
		}
		
		public static void renderSplash()
		{

		}
		
		public static void renderMain()
		{
			RendererMain.drawRect(0, 0, BearGame.WINDOW_WIDTH, BearGame.WINDOW_HEIGHT, mainTexture, false);
			double scale;
			for(int i = 0; i < NUM_MAIN_BUTTONS; i++)
			{
				scale = isMouseOver(i) ? 1.5 : 1;
				RendererMain.drawRectScaleCenter(START_X + (DELTA_X * i), START_Y + (DELTA_Y * i), scale, scale, mainButtonTextures[i]);
			}
		}
		
		public static void renderInstructions()
		{
			
		}
		
		public static void updateMousePos(int x, int y)
		{
			mouseX = x;
			mouseY = BearGame.WINDOW_HEIGHT - (y - buttonHeight);
		}
		
		public static boolean isMouseOver(int button)
		{
			switch(curMenu)
			{
			case SPLASH:
				break;
			case MAIN:
				int buttonX = START_X + (DELTA_X * button);
				int buttonY = START_Y + (DELTA_Y * button);
				if((mouseX > (buttonX - (buttonWidth / 2))) && (mouseX < (buttonX + (buttonWidth / 2))) && (mouseY > (buttonY - (buttonHeight / 2))) && (mouseY < (buttonY + (buttonHeight / 2))))
				{
					return true;
				}
				return false;
			case INSTRUCTIONS:
				break;
			case SCORES:
				break;
			case STORY:
				break;
			default:
				break;
			}
			return false;
		}
		
		public static void click(BearGame g)
		{
			int button = -1;
			switch(curMenu)
			{
			case SPLASH:
				break;
			case MAIN:
				for(int i = 0; i < NUM_MAIN_BUTTONS; i++)
				{
					if(isMouseOver(i))
					{
						button = i;
					}
				}
				if(button == 0)
				{
					g.play();
				}
				if(button == 1)
				{
					curMenu = MenuPage.INSTRUCTIONS;
				}
				if(button == 2)
				{
					curMenu = MenuPage.SCORES;
				}
				if(button == 3)
				{
					curMenu = MenuPage.STORY;
				}
				break;
			case INSTRUCTIONS:
				break;
			case SCORES:
				break;
			case STORY:
				break;
			default:
				break;
			}
		}
}