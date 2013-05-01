package grp09616;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;


public class BearRenderer
{
	private static final int NUM_GROUND_TEXTURES = 1;
	private static final int NUM_BUTTONS = 3;
	private static final double GROUND_TEXT_HEIGHT = BearGame.WINDOW_HEIGHT - BearGame.GROUND_HEIGHT;
	private static final double GROUND_TEXT_WIDTH = BearGame.WINDOW_WIDTH;
	private static final double BACKGROUND_SPEED_MODIFIER = 0.2;
	
	private static int[] groundTextures;
	private static int[] buttonTextures;
	private static int backgroundTexture;
	private static int splashTexture;
	private static int menuTexture;
	
	static
	{
		initGL();
		loadGlobalTextures();
	}
	
	public static void renderWorld(BearWorld w)
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glBegin(GL11.GL_QUADS);
		
		//Draw background
		GL11.glColor3f(1.0f, 1.0f, 1.0f);
		drawBackground(w);
		
		//Draw ground
		GL11.glColor3f(0.2f, 0.8f, 0.3f);
		drawRectNoText(0, GROUND_TEXT_HEIGHT, GROUND_TEXT_WIDTH, BearGame.WINDOW_HEIGHT);
		
		//Draw bear
		GL11.glColor3f(0.4f, 0.2f, 0.0f);
		drawRectNoText(BearGame.BEAR_LEFTPOS, GROUND_TEXT_HEIGHT - 200, 120, 200);
		
		//Draw roar effects
		if(w.isRoaring())
		{
			GL11.glColor3f(0.8f, 0.0f, 0.0f);
			drawRoar();
		}		
		
		//Draw entities
		GL11.glColor3f(0.6f, 1.0f, 0.7f);
		for(int i = 0;  i < BearWorld.MAX_ENTITIES; i++)
		{
			Entity e = w.getEntity(i);
			if(e != null)
			{
				drawRectNoText(e.getXPos() - w.getDMoved(), BearGame.WINDOW_HEIGHT - (e.getYPos() + e.getHeight()), e.getWidth(), e.getHeight());
			}
		}
		
		//drawRect(0, 0, GROUND_TEXT_WIDTH, BearGame.WINDOW_HEIGHT, groundTextures[0]);
		
		GL11.glEnd();
	}
	
	public static void renderMenu()
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glColor3f(0.2f, 0.9f, 0.2f);
		BearRenderer.drawRect(0, 0, BearGame.WINDOW_WIDTH, BearGame.WINDOW_HEIGHT, menuTexture);	
		
		GL11.glColor3f(0.4f, 0.2f, 0.2f);
		BearRenderer.drawRect(200, 200, 300, 100, buttonTextures[0]);
	}
	
	public static void initGL()
	{
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, BearGame.WINDOW_WIDTH, BearGame.WINDOW_HEIGHT, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	public static void loadGlobalTextures()
	{
		groundTextures = new int[NUM_GROUND_TEXTURES];
		for(int i = 0; i < NUM_GROUND_TEXTURES; i++)
		{
			groundTextures[i] = TextureManager.loadTextures(BearGame.TEXTURE_PATH + "scenery/ground" + i + ".png");
		}
		buttonTextures = new int[NUM_BUTTONS];
		for(int i = 0; i < NUM_GROUND_TEXTURES; i++)
		{
			buttonTextures[i] = TextureManager.loadTextures(BearGame.TEXTURE_PATH + "menu/button" + i + ".png");
		}
		backgroundTexture = TextureManager.loadTextures(BearGame.TEXTURE_PATH + "scenery/background.png");
		splashTexture = TextureManager.loadTextures(BearGame.TEXTURE_PATH + "menu/splash.png");
		menuTexture = TextureManager.loadTextures(BearGame.TEXTURE_PATH + "menu/background.png");
	}
	
	public static void drawRectNoText(double x, double y, double w, double h)
	{
		GL11.glVertex2d(x, y);
		GL11.glVertex2d(x, y + h);
		GL11.glVertex2d(x + w, y + h);
		GL11.glVertex2d(x + w, y);
	}
	
	public static void drawRect(double x, double y, double w, double h, int texture)
	{
		Texture t = TextureManager.getTexture(texture);
		double cW = w * t.getTextureWidth() / BearGame.WINDOW_WIDTH; 
		double cH = h * t.getTextureHeight() / BearGame.WINDOW_HEIGHT;
		
		TextureManager.setTextureImage(texture);
		
		GL11.glTexCoord2d(0, 0);
		GL11.glVertex2d(x, y);
		GL11.glTexCoord2d(0, 1);
		GL11.glVertex2d(x, y + cH);
		GL11.glTexCoord2d(1, 1);
		GL11.glVertex2d(x + cW, y + cH);
		GL11.glTexCoord2d(1, 0);
		GL11.glVertex2d(x + cW, y);
	}
	
	public static void drawBackground(BearWorld w)
	{
		double backgroundWidth = TextureManager.getTexture(backgroundTexture).getImageWidth();
		double shift = -1 * ((w.getDMoved() * BACKGROUND_SPEED_MODIFIER) % backgroundWidth);
		drawRect(shift, 0, BearGame.WINDOW_WIDTH, BearGame.WINDOW_HEIGHT, backgroundTexture);
		drawRect(backgroundWidth + shift, 0, BearGame.WINDOW_WIDTH, BearGame.WINDOW_HEIGHT, backgroundTexture);
	}
	
	public static void drawRoar()
	{
		double x = BearGame.BEAR_LEFTPOS + 120;
		double y = BearGame.WINDOW_HEIGHT - (BearGame.GROUND_HEIGHT + 180);
		
		GL11.glVertex2d(x + 20, y + 3);
		GL11.glVertex2d(x + 120, y + 3);
		GL11.glVertex2d(x + 120, y - 3);
		GL11.glVertex2d(x + 20, y - 3);
		
		GL11.glVertex2d(x + 27, y + 15);
		GL11.glVertex2d(x + 105, y + 25);
		GL11.glVertex2d(x + 100, y + 30);
		GL11.glVertex2d(x + 20, y + 20);

		GL11.glVertex2d(x + 20, y - 23);
		GL11.glVertex2d(x + 100, y - 33);
		GL11.glVertex2d(x + 105, y - 28);
		GL11.glVertex2d(x + 27, y - 18);
	}
}
