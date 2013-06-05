package grp09616;


public class ManagerMenu
{
	public static final int NUM_MAIN_BUTTONS = 3;
	public static final int NUM_INFO_BUTTONS = 3;
	public static final int NUM_INFO_PAGES = 1;
	public static final int NUM_STORY_BUTTONS = 3;
	public static final int NUM_STORY_PAGES = 2;
	
	public static final int START_X_MAIN = 620;
	public static final int DELTA_X_MAIN = -50;
	public static final int START_Y_MAIN = 345;
	public static final int DELTA_Y_MAIN = 80;
	public static final int START_X_INFO = 120;
	public static final int DELTA_X_INFO = 290;
	public static final int START_Y_INFO = 620;
	public static final int DELTA_Y_INFO = 0;
	public static final int START_X_STORY = 120;
	public static final int DELTA_X_STORY = 290;
	public static final int START_Y_STORY = 620;
	public static final int DELTA_Y_STORY = 0;
	public static final int START_X_GOVER = 340;
	public static final int DELTA_X_GOVER = 0;
	public static final int START_Y_GOVER = 620;
	public static final int DELTA_Y_GOVER = 0;
	
	private enum MenuPage
	{
		MAIN, INSTRUCTIONS, STORY, GAME_OVER
	}
	
	private static MenuPage curMenu;
	
	private static int mainTexture;
	private static int[] mainButtonTextures;
	private static int[] infoTextures;
	private static int[] infoButtonTextures;
	private static int[] storyTextures;
	private static int[] storyButtonTextures;
	private static int mouseX;
	private static int mouseY;
	private static int buttonWidth;
	private static int buttonHeight;
	private static int curPage;
	
	static
	{
		loadTextures();
		curMenu = MenuPage.MAIN;
		curPage = 0;
		buttonWidth = ManagerTextures.getTexture(mainButtonTextures[0]).getImageWidth();
		buttonHeight = ManagerTextures.getTexture(mainButtonTextures[0]).getImageHeight();
	}
	
	public static void loadTextures()
	{
		// splashTexture = ManagerTextures.loadTextures(BearGame.TEXTURE_PATH +
		// "menu/splash.png");
		 mainTexture = ManagerTextures.loadTextures(BearGame.TEXTURE_PATH + "menu/TitleScreen.png");
		 mainButtonTextures = RendererMain.loadTextureArray("menu/button", NUM_MAIN_BUTTONS);
		 infoTextures = RendererMain.loadTextureArray("menu/instructionsscreen", NUM_INFO_PAGES);
		 infoButtonTextures = RendererMain.loadTextureArray("menu/infobutton", NUM_INFO_BUTTONS);
		 storyTextures = RendererMain.loadTextureArray("menu/infoscreen", NUM_STORY_PAGES);
		 storyButtonTextures = RendererMain.loadTextureArray("menu/infobutton", NUM_STORY_BUTTONS);
	}
	
	public static void render()
	{
		switch(curMenu)
		{
		case MAIN:
			renderMain();
			break;
		case INSTRUCTIONS:
			renderInstructions();
			break;
		case STORY:
			renderStory();
			break;
		case GAME_OVER:
			break;
		default:
			break;
		}
	}
	
	public static void renderMain()
	{
		RendererMain.drawRect(0, 0, BearGame.WINDOW_WIDTH, BearGame.WINDOW_HEIGHT, mainTexture, false);
		double scale;
		for(int i = 0; i < NUM_MAIN_BUTTONS; i++)
		{
			scale = isMouseOver(i) ? 1.5 : 1;
			RendererMain.drawRectScaleCenter(START_X_MAIN + (DELTA_X_MAIN * i), START_Y_MAIN + (DELTA_Y_MAIN * i), scale, scale, mainButtonTextures[i]);
		}
	}
	
	public static void renderInstructions()
	{
		RendererMain.drawRect(0, 0, BearGame.WINDOW_WIDTH, BearGame.WINDOW_HEIGHT, infoTextures[curPage], false);
		double scale;
		for(int i = 0; i < NUM_INFO_BUTTONS; i++)
		{
			if((i == 0) && (curPage == 0))
			{
				continue;
			}
			if((i == (NUM_INFO_BUTTONS - 1)) && (curPage == (NUM_INFO_PAGES - 1)))
			{
				continue;
			}
			scale = isMouseOver(i) ? 1.5 : 1;
			RendererMain.drawRectScaleCenter(START_X_INFO + (DELTA_X_INFO * i), START_Y_INFO + (DELTA_Y_INFO * i), scale, scale, infoButtonTextures[i]);
		}
	}
	
	public static void renderStory()
	{
		RendererMain.drawRect(0, 0, BearGame.WINDOW_WIDTH, BearGame.WINDOW_HEIGHT,storyTextures[curPage], false);
		double scale;
		for(int i = 0; i < NUM_STORY_BUTTONS; i++)
		{
			if((i == 0) && (curPage == 0))
			{
				continue;
			}
			if((i == (NUM_STORY_BUTTONS - 1)) && (curPage == (NUM_STORY_PAGES - 1)))
			{
				continue;
			}
			scale = isMouseOver(i) ? 1.5 : 1;
			RendererMain.drawRectScaleCenter(START_X_STORY + (DELTA_X_STORY * i), START_Y_STORY + (DELTA_Y_STORY * i), scale, scale, storyButtonTextures[i]);
		}
	}
	
	public static void updateMousePos(int x, int y)
	{
		mouseX = x;
		mouseY = BearGame.WINDOW_HEIGHT - (y - buttonHeight);
	}
	
	public static boolean isMouseOver(int button)
	{
		int buttonX;
		int buttonY;
		switch(curMenu)
		{
		case MAIN:
			buttonX = START_X_MAIN + (DELTA_X_MAIN * button);
			buttonY = START_Y_MAIN + (DELTA_Y_MAIN * button);
			if((mouseX > (buttonX - (buttonWidth / 2))) && (mouseX < (buttonX + (buttonWidth / 2))) && (mouseY > (buttonY - (buttonHeight / 2))) && (mouseY < (buttonY + (buttonHeight / 2))))
			{
				return true;
			}
			return false;
		case INSTRUCTIONS:
			buttonX = START_X_INFO + (DELTA_X_INFO * button);
			buttonY = START_Y_INFO + (DELTA_Y_INFO * button);
			if((mouseX > (buttonX - (buttonWidth / 2))) && (mouseX < (buttonX + (buttonWidth / 2))) && (mouseY > (buttonY - (buttonHeight / 2))) && (mouseY < (buttonY + (buttonHeight / 2))))
			{
				return true;
			}
			return false;
		case STORY:
			buttonX = START_X_STORY + (DELTA_X_STORY * button);
			buttonY = START_Y_STORY + (DELTA_Y_STORY * button);
			if((mouseX > (buttonX - (buttonWidth / 2))) && (mouseX < (buttonX + (buttonWidth / 2))) && (mouseY > (buttonY - (buttonHeight / 2))) && (mouseY < (buttonY + (buttonHeight / 2))))
			{
				return true;
			}
			break;
		case GAME_OVER:
			buttonX = START_X_GOVER + (DELTA_X_GOVER * button);
			buttonY = START_Y_GOVER + (DELTA_Y_GOVER * button);
			if((mouseX > (buttonX - (buttonWidth / 2))) && (mouseX < (buttonX + (buttonWidth / 2))) && (mouseY > (buttonY - (buttonHeight / 2))) && (mouseY < (buttonY + (buttonHeight / 2))))
			{
				return true;
			}
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
				curPage = 0;
			}
			if(button == 2)
			{
				curMenu = MenuPage.STORY;
				curPage = 0;
			}
			break;
		case INSTRUCTIONS:
			for(int i = 0; i < NUM_INFO_BUTTONS; i++)
			{
				if(isMouseOver(i))
				{
					button = i;
				}
			}
			if(button == 0)
			{
				if(curPage > 0)
				{
					curPage--;
				}
			}
			if(button == 1)
			{
				curMenu = MenuPage.MAIN;
				curPage = 0;
			}
			if(button == 2)
			{
				if(curPage < (NUM_INFO_PAGES - 1))
				{
					curPage++;
				}
			}
			break;
		case STORY:
			for(int i = 0; i < NUM_STORY_BUTTONS; i++)
			{
				if(isMouseOver(i))
				{
					button = i;
				}
			}
			if(button == 0)
			{
				if(curPage > 0)
				{
					curPage--;
				}
			}
			if(button == 1)
			{
				curMenu = MenuPage.MAIN;
				curPage = 0;
			}
			if(button == 2)
			{
				if(curPage < (NUM_STORY_PAGES - 1))
				{
					curPage++;
				}
			}
			break;
		case GAME_OVER:
			if(isMouseOver(0))
			{
				curMenu = MenuPage.MAIN;
				curPage = 0;
			}
			break;
		default:
			break;
		}
	}
	
	public static void gameOver()
	{
		curMenu = MenuPage.GAME_OVER;
		curPage = 0;
	}
}