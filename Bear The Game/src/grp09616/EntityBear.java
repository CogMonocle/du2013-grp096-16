package grp09616;


public class EntityBear extends Entity
{

	public static final int FPS = 4;
	public static final int WALKING_FRAMES = 4;
	public static final int ROARING_FRAMES = 1;

	static int[] textureIDs;

	enum BearState
	{
		STANDING, WALKING, ROARING
	}

	private BearState curState;
	private int energy;

	private long walkingTimestamp;

	public EntityBear()
	{
		super(BearGame.BEAR_LEFTPOS, BearGame.GROUND_HEIGHT);
		curState = BearState.STANDING;
		energy = 10;
	}

	@Override
	public void onRemove()
	{
		// TODO Auto-generated method stub

	}

	public void beginWalking(long timestamp)
	{
		curState = BearState.WALKING;
		walkingTimestamp = timestamp;
	}
	
	public void stopWalking()
	{
		curState = BearState.STANDING;
	}

	@Override
	public int getTextureID()
	{
		if (curState == BearState.STANDING)
		{
			return textureIDs[0];
		}
		if ((curState == BearState.WALKING))
		{
			long walkingTime = (System.currentTimeMillis() - walkingTimestamp);
			int framePos = (int) (((walkingTime / (1000 / FPS)) + 1) % WALKING_FRAMES);
			return textureIDs[framePos];
		}
		return -1;
	}

	public int getEnergy()
	{
		return energy;
	}
	
	public void hit(int a)
	{
		energy -= a;
	}
	
	public void heal(int a)
	{
		energy += a;
	}
	
	@Override
	public int getWidth()
	{
		return 0;
	}

	@Override
	public int getHeight()
	{
		return 0;
	}

	public static void loadTextures()
	{
		if (textureIDs == null)
		{
			textureIDs = new int[WALKING_FRAMES + ROARING_FRAMES];
			for (int i = 0; i < (WALKING_FRAMES + ROARING_FRAMES); i++)
			{
				if (i < WALKING_FRAMES)
				{
					textureIDs[i] = ManagerTextures
							.loadTextures(BearGame.TEXTURE_PATH
									+ "bear/teddisonrun" + i + ".png");
				} else
				{
					// textureIDs[i] =
					// TextureManager.loadTextures(BearGame.TEXTURE_PATH +
					// "bear/teddisonroar" + (i - WALKING_FRAMES) + ".png");
				}
			}
		}
	}
}
