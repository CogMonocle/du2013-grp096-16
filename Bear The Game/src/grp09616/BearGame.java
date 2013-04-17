package grp09616;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;


public class BearGame
{
	public static final String TEXTURE_PATH = "resources/texures/";
	public static final String SOUND_PATH = "resources/sounds/";
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	public static double BEAR_SPEED = 0.1;
	
	private enum GameState
	{
		SPLASH, MENU, ACTIVE, PAUSED
	}
	
	private BearDisplay display;
	private BearWorld world;
	private BearRenderer renderer;
	private GameState curState;
	
	private long lastFrame;
	private double distanceMoved;
	private boolean shouldClose;
	
	public void start()
	{
		init();
		while(!shouldClose)
		{
			update();
		}
		cleanup();
	}	
	
	public void init()
	{
		shouldClose = false;
		distanceMoved = 0;
		initDisplay();
		curState = GameState.SPLASH;
		renderer = new BearRenderer();
	}
	
	public void initDisplay()
	{
		try 
		{
			display = new BearDisplay(WINDOW_WIDTH, WINDOW_HEIGHT);
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void pollInputs()
	{
		while(Keyboard.next())
		{
			int key = Keyboard.getEventKey();
			boolean state = Keyboard.getEventKeyState();
			if(state)
			{
				//If in menus, esc to close
				if(key == Keyboard.KEY_ESCAPE && curState == GameState.MENU || curState == GameState.SPLASH)
				{
					shouldClose = true;
				}
				//P to toggle paused (only works in game)
				else if(key == Keyboard.KEY_P)
				{
					togglePaused();
				}
				//Any key to go from splash to menu (except for escape)
				else if(curState == GameState.SPLASH)
				{
					curState = GameState.MENU;
				}
			}
		}
		if(Display.isCloseRequested())
		{
			shouldClose = true;
		}
	}
	
	public void move(int dir)
	{
		distanceMoved += dir * BEAR_SPEED;
	}
	
	public void update()
	{
		pollInputs();
		if(curState == GameState.ACTIVE)
		{
			world.update();
			renderer.renderWorld(world);
		}
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		display.update();
	}
	
	public void togglePaused()
	{
		if(curState == GameState.ACTIVE)
		{
			curState = GameState.PAUSED;
		}
		else if(curState == GameState.PAUSED)
		{
			curState = GameState.ACTIVE;
		}
	}
	
	public void cleanup()
	{
		display.cleanup();
	}
	
	public int getDelta()
	{
	    long time = getTime();
	    int delta = (int) (time - lastFrame);
	    lastFrame = time;
	    	
	    return delta;
	}
	
	public long getTime()
	{
	    return System.nanoTime() / 1000000;
	}
	
	public static void main(String[]  args)
	{
		BearGame bear = new BearGame();
		bear.start();
	}
}
