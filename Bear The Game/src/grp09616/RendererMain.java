package grp09616;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.ResourceLoader;

public class RendererMain
{
	private static final double GROUND_TEXT_HEIGHT = BearGame.WINDOW_HEIGHT
			- BearGame.GROUND_HEIGHT;
	private static final double GROUND_TEXT_WIDTH = BearGame.WINDOW_WIDTH;
	private static final double BACKGROUND_SPEED_MODIFIER = 0.5;
	private static final double BACKGROUND_SCALE_MODIFIER = 0.95;
	private static final double BEAR_OFFSET = 80;
	private static final double BEAR_HEIGHT = (BearGame.WINDOW_HEIGHT + BEAR_OFFSET)
			- BearGame.GROUND_HEIGHT;
	private static final int NUM_GROUND_TEXTURES = 1;
	private static final int WALKING_FRAMES = 4;
	private static final int BACKGROUND_LAYERS = 10;
	private static final int NUM_BACKGROUND_TEXTURES = 3;

	private static IntegerQueue groundPieces;
	private static Random r;
	private static TrueTypeFont bearFont;

	private static int[] groundTextures;
	private static int[] walkingTextures;
	private static int[] foodTextures;
	private static int backgroundTextures[];
	private static double totalGroundPieceWidth;
	private static double groundDisplacement;
	private static int keyTexture;

	static
	{
		initGL();
		loadTextures();
		groundPieces = new IntegerQueue();
		totalGroundPieceWidth = 0;
		r = new Random();
	}

	public static void renderWorld(BearWorld w)
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		// Draw background
		GL11.glColor3f(1.0f, 1.0f, 1.0f);
		drawBackground(w);

		// Draw ground
		GL11.glColor3f(1.0f, 1.0f, 1.0f);
		drawGround(w);

		// Draw bear
		GL11.glColor3f(1.0f, 1.0f, 1.0f);
		drawRectScale(BearGame.BEAR_LEFTPOS, BEAR_HEIGHT, 1, 1,
				w.getBearTexture());

		GL11.glDisable(GL11.GL_TEXTURE_2D);
		// Draw roar effects
		if (w.isRoaring())
		{
			GL11.glColor3f(0.8f, 0.0f, 0.0f);
			drawRoar();
		}

		// Draw entities
		GL11.glColor3f(0.6f, 1.0f, 0.7f);
		for (int i = 0; i < BearWorld.MAX_ENTITIES; i++)
		{
			Entity e = w.getEntity(i);
			if (e != null)
			{
				drawRectNoText(e.getXPos() - w.getDMoved(),
						BearGame.WINDOW_HEIGHT - (e.getYPos() + e.getHeight()),
						e.getWidth(), e.getHeight());
			}
		}
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
		drawHUD(w);
	}

	public static void initGL()
	{
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, BearGame.WINDOW_WIDTH, BearGame.WINDOW_HEIGHT, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glEnable(GL11.GL_TEXTURE);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}

	public static void loadTextures()
	{
		try
		{
			bearFont = new TrueTypeFont(Font.createFont(Font.TRUETYPE_FONT, ResourceLoader.getResourceAsStream(BearGame.FONT_PATH + "ANUDRG__.ttf")), false);
		}
		catch (FontFormatException | IOException e)
		{
			System.err.println("Failed to load font.");
			e.printStackTrace();
		}
		groundTextures = loadTextureArray("scenery/ground", NUM_GROUND_TEXTURES);
		backgroundTextures = loadTextureArray("scenery/background", NUM_BACKGROUND_TEXTURES);
		keyTexture = ManagerTextures.loadTextures(BearGame.TEXTURE_PATH + "HUD/keyboardbutton.png");
		EntityBear.loadTextures();
	}
	
	public static int[] loadTextureArray(String path, int amt)
	{
		int[] temp = new int[amt];
		for(int i = 0; i < amt; i++)
		{
			temp[i] = ManagerTextures.loadTextures(BearGame.TEXTURE_PATH + path + i + ".png");
		}
		return temp;
	}

	public static void drawRectNoText(double x, double y, double w, double h)
	{
		GL11.glBegin(GL11.GL_QUADS);

		GL11.glVertex2d(x, y);
		GL11.glVertex2d(x, y + h);
		GL11.glVertex2d(x + w, y + h);
		GL11.glVertex2d(x + w, y);

		GL11.glEnd();
	}

	public static void drawRect(double x, double y, double w, double h,
			int texture, boolean ybot)
	{
		Texture t = ManagerTextures.getTexture(texture);
		double cW = (w * t.getTextureWidth()) / BearGame.WINDOW_WIDTH;
		double cH = (h * t.getTextureHeight()) / BearGame.WINDOW_HEIGHT;
		if(ybot)
		{
			y = BearGame.WINDOW_HEIGHT - (y + h);
		}

		ManagerTextures.setTextureImage(texture);

		GL11.glBegin(GL11.GL_QUADS);

		GL11.glTexCoord2d(0, 0);
		GL11.glVertex2d(x, y);
		GL11.glTexCoord2d(0, 1);
		GL11.glVertex2d(x, y + cH);
		GL11.glTexCoord2d(1, 1);
		GL11.glVertex2d(x + cW, y + cH);
		GL11.glTexCoord2d(1, 0);
		GL11.glVertex2d(x + cW, y);

		GL11.glEnd();
	}

	public static void drawRectScale(double x, double yBot, double wScale,
			double hScale, int texture)
	{
		Texture t = ManagerTextures.getTexture(texture);
		double cW = wScale * t.getImageWidth();
		double cH = hScale * t.getImageHeight();
		double y = (yBot - cH);

		ManagerTextures.setTextureImage(texture);

		GL11.glBegin(GL11.GL_QUADS);

		GL11.glTexCoord2d(0, 0);
		GL11.glVertex2d(x, y);
		GL11.glTexCoord2d(0, 1);
		GL11.glVertex2d(x, y + cH);
		GL11.glTexCoord2d(1, 1);
		GL11.glVertex2d(x + cW, y + cH);
		GL11.glTexCoord2d(1, 0);
		GL11.glVertex2d(x + cW, y);

		GL11.glEnd();
	}
	
	public static void drawRectScaleCenter(double x, double y, double wScale,
			double hScale, int texture)
	{
		Texture t = ManagerTextures.getTexture(texture);
		double cW = (wScale * t.getImageWidth()) / 2;
		double cH = (hScale * t.getImageHeight()) / 2;
		double cY = (y - t.getImageHeight());

		ManagerTextures.setTextureImage(texture);

		GL11.glBegin(GL11.GL_QUADS);

		GL11.glTexCoord2d(0, 0);
		GL11.glVertex2d(x - cW, cY - cH);
		GL11.glTexCoord2d(0, 1);
		GL11.glVertex2d(x - cW, cY + cH);
		GL11.glTexCoord2d(1, 1);
		GL11.glVertex2d(x + cW, cY + cH);
		GL11.glTexCoord2d(1, 0);
		GL11.glVertex2d(x + cW, cY - cH);

		GL11.glEnd();
	}
	
	public static void drawHUD(BearWorld w)
	{
		int energy = w.getBearEnergy();
		GL11.glColor3f(1.0f, 1.0f, 1.0f);
		if(w.isRoaring())
		{
			drawRectScale(BearGame.BEAR_LEFTPOS + 70, BearGame.WINDOW_HEIGHT + 60, 1, 1, keyTexture);
			GL11.glColor3f(0.0f, 0.0f, 0.0f);
			bearFont.drawString(BearGame.BEAR_LEFTPOS + 70, BearGame.WINDOW_HEIGHT + 60, String.valueOf(ManagerRoars.getRoarChar()));
			GL11.glColor3f(1.0f, 1.0f, 1.0f);
		}
	}
	
	public static void drawBackground(BearWorld w)
	{
		double backgroundWidth;
		double shift;
		double speedMod = Math
				.pow(BACKGROUND_SPEED_MODIFIER, BACKGROUND_LAYERS);
		double scaleMod = Math
				.pow(BACKGROUND_SCALE_MODIFIER, BACKGROUND_LAYERS);
		int numTiles;
		for (int i = 0; i < BACKGROUND_LAYERS; i++)
		{
			if (i == 0)
			{
				backgroundWidth = ManagerTextures.getTexture(
						backgroundTextures[i]).getImageWidth();
				shift = -1 * ((w.getDMoved() * speedMod) % backgroundWidth);
				drawRect(shift, 0, BearGame.WINDOW_WIDTH,
						BearGame.WINDOW_HEIGHT, backgroundTextures[i], false);
				drawRect(backgroundWidth + shift, 0, BearGame.WINDOW_WIDTH,
						BearGame.WINDOW_HEIGHT, backgroundTextures[i], false);
			} else
			{
				backgroundWidth = ManagerTextures.getTexture(
						backgroundTextures[(i % (NUM_BACKGROUND_TEXTURES - 1)) + 1]).getImageWidth()
						* scaleMod;
				shift = -1 * ((w.getDMoved() * speedMod) % backgroundWidth);
				numTiles = (int) Math.floor(1 / scaleMod) + 2;
				for(int q = 0; q < numTiles; q++)
				{
					drawRect((backgroundWidth * q) + shift, 0 - BearGame.GROUND_HEIGHT, BearGame.WINDOW_WIDTH * scaleMod,
							BearGame.WINDOW_HEIGHT * scaleMod, backgroundTextures[((i % NUM_BACKGROUND_TEXTURES) - 1) + 1], true);
				}
			}
			speedMod /= BACKGROUND_SPEED_MODIFIER;
			scaleMod /= BACKGROUND_SCALE_MODIFIER;
		}
	}

	public static void drawGround(BearWorld w)
	{
		Texture curText;
		int textPiece;
		int width;
		if (groundPieces.peek() >= 0)
		{
			while (groundDisplacement > (width = ManagerTextures.getTexture(
					groundPieces.peek()).getImageWidth()))
			{
				groundPieces.poll();
				groundDisplacement -= width;
				totalGroundPieceWidth -= width;
			}
		}
		while ((totalGroundPieceWidth - groundDisplacement) < BearGame.WINDOW_WIDTH)
		{
			textPiece = groundTextures[r.nextInt(groundTextures.length)];
			groundPieces.append(textPiece);
			totalGroundPieceWidth += ManagerTextures.getTexture(textPiece)
					.getImageWidth();
		}

		int[] textures = groundPieces.view();
		int cumWidth = 0;

		for (int t : textures)
		{
			curText = ManagerTextures.getTexture(t);
			drawRect(cumWidth - groundDisplacement, BearGame.WINDOW_HEIGHT
					- curText.getImageHeight(), curText.getImageWidth(),
					curText.getImageHeight(), t, false);
			cumWidth += curText.getImageWidth();
		}
	}

	public static void shiftGroundDisplacement(double amt)
	{
		groundDisplacement += amt;
	}

	public static void drawRoar()
	{
		double x = BearGame.BEAR_LEFTPOS + 180;
		double y = BearGame.WINDOW_HEIGHT - (BearGame.GROUND_HEIGHT + 210);

		GL11.glBegin(GL11.GL_QUADS);

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

		GL11.glEnd();
	}
}
