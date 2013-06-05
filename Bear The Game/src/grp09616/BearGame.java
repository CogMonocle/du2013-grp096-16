package grp09616;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class BearGame
{
	public static final String TEXTURE_PATH = "resources/textures/";
	public static final String SOUND_PATH = "resources/sound/";
	public static final String FONT_PATH = "resources/fonts/";
	public static final double BEAR_SPEED = 0.25;
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;
	public static final int GROUND_HEIGHT = 80;
	public static final int GROUND_POS = WINDOW_HEIGHT - GROUND_HEIGHT;
	public static final int BEAR_LEFTPOS = 80;

	private enum GameState
	{
		SPLASH, MENU, ACTIVE, PAUSED
	}

	private BearDisplay display;
	private BearWorld world;
	private GameState curState;

	private long lastFrame;
	private boolean shouldClose;

	public void start()
	{
		int delta;
		
		init();
		while (!shouldClose)
		{
			delta = getDelta();
			update(delta);
		}
		cleanup();
	}

	public void init()
	{
		shouldClose = false;
		initDisplay();
		curState = GameState.MENU;
		world = new BearWorld(WINDOW_WIDTH, WINDOW_HEIGHT);
		ManagerTextures.initialize();
		ManagerSound.initialize();
		getDelta();
	}

	public void initDisplay()
	{
		try
		{
			display = new BearDisplay(WINDOW_WIDTH, WINDOW_HEIGHT);
		} catch (LWJGLException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void pollInputs()
	{
		ManagerMenu.updateMousePos(Mouse.getX(), Mouse.getY());
		while(Mouse.next())
		{
			int button = Mouse.getEventButton();
			switch(curState)
			{
			case SPLASH:
				break;
			case MENU:
				if(button == 0)
				{
					ManagerMenu.click(this);
				}
				break;
				case ACTIVE:
				break;
			case PAUSED:
				break;
			default:
				break;
			}
		}
		while (Keyboard.next())
		{
			int key = Keyboard.getEventKey();
			boolean state = Keyboard.getEventKeyState();
			if (state)
			{
				// If in menus, esc to close
				if (key == Keyboard.KEY_ESCAPE /*
												 * && (curState ==
												 * GameState.MENU || curState ==
												 * GameState.SPLASH)
												 */)
				{
					shouldClose = true;
				}
				else if((key == Keyboard.KEY_Q) || (key == Keyboard.KEY_A)
						|| (key == Keyboard.KEY_E) || (key == Keyboard.KEY_D))
				{
					world.enterRoarCharacter(Keyboard.getEventCharacter());
				}
				// Right arrow to move forward
//				else if ((key == Keyboard.KEY_RIGHT) && (curState == GameState.ACTIVE))
//				{
//					world.beginWalk(BEAR_SPEED);
//				}
				// P to toggle paused (only works in game)
				else if ((key == Keyboard.KEY_P)
						&& (curState == GameState.ACTIVE))
				{
					// togglePaused();
				} 
				else if ((key == Keyboard.KEY_W)
						&& (curState == GameState.ACTIVE))
				{
					world.setRoaring(true);
				}
				// Any key to go from splash to menu (except for escape)
				else if (curState == GameState.SPLASH)
				{
					curState = GameState.MENU;
				}
			} else
			{
//				if ((key == Keyboard.KEY_RIGHT) && (curState == GameState.ACTIVE))
//				{
//					world.stopWalk();
//				}
				if ((key == Keyboard.KEY_W) && (curState == GameState.ACTIVE))
				{
					world.roar();
					world.setRoaring(false);
				}
			}
		}
		if (Display.isCloseRequested())
		{
			shouldClose = true;
		}
	}

	public void move(int dir)
	{
		world.beginWalk(BEAR_SPEED * dir);
	}
	
	public void play()
	{
		curState = GameState.ACTIVE;
		world.beginWalk(BEAR_SPEED);
	}

	public void update(int delta)
	{
		pollInputs();
		switch(curState)
		{
		case MENU:
			ManagerMenu.render();
			break;
		case ACTIVE:
			world.update(delta);
			RendererMain.renderWorld(world);
			break;
		default:
			break;
		}
		display.update();
	}

	public void togglePaused()
	{
		if (curState == GameState.ACTIVE)
		{
			curState = GameState.PAUSED;
		} else if (curState == GameState.PAUSED)
		{
			play();
		}
	}

	public void cleanup()
	{
		display.cleanup();
		ManagerSound.cleanup();
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

	public static void main(String[] args)
	{
		//System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/natives/");
		BearGame bear = new BearGame();
		bear.start();
	}
}
