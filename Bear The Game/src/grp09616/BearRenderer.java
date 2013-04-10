package grp09616;
import org.lwjgl.opengl.GL11;


public class BearRenderer
{
	public void init()
	{
		initGL();
	}
	
	public void initGL()
	{
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
}
