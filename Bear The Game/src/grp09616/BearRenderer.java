package grp09616;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;


public class BearRenderer
{
	private static final int NUM_GROUND_TEXTURES = 1;
	private static final double GROUND_TEXT_WIDTH = BearGame.WINDOW_WIDTH;
	
	private static int[] groundTextures;
	private static int backgroundTexture;
	
	static
	{
		initGL();
		loadGlobalTextures();
	}
	
	public static void renderWorld(BearWorld w)
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glBegin(GL11.GL_QUADS);
		
		drawRect(0, 0, BearGame.WINDOW_WIDTH, BearGame.WINDOW_HEIGHT, backgroundTexture);
		
		drawRect(0, 0, GROUND_TEXT_WIDTH, BearGame.WINDOW_HEIGHT, groundTextures[0]);
		
		GL11.glEnd();
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
		backgroundTexture = TextureManager.loadTextures(BearGame.TEXTURE_PATH + "scenery/background.png");
	}
	
	public static void drawRect(double x, double y, double width, double height, int texture)
	{
		Texture t = TextureManager.getTexture(texture);
		double cX = x;
		double cY = y;
		double cW = width * t.getTextureWidth() / BearGame.WINDOW_WIDTH; 
		double cH = height * t.getTextureHeight() / BearGame.WINDOW_HEIGHT;
		
		TextureManager.setTextureImage(texture);
		
		GL11.glTexCoord2d(0, 0);
		GL11.glVertex2d(cX, cY);
		GL11.glTexCoord2d(0, 1);
		GL11.glVertex2d(cX, cY + cH);
		GL11.glTexCoord2d(1, 1);
		GL11.glVertex2d(cX + cW, cY + cH);
		GL11.glTexCoord2d(1, 0);
		GL11.glVertex2d(cX + cW, cY);
	}
}
