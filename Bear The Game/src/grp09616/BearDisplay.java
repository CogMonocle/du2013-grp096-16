package grp09616;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class BearDisplay
{
	private int width;
	private int height;

	public BearDisplay(int w, int h) throws LWJGLException
	{
		width = w;
		height = h;
		Display.setDisplayMode(new DisplayMode(width, height));
		Display.create();
	}

	public void update()
	{
		Display.update();
	}

	public void cleanup()
	{
		Display.destroy();
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}
}
