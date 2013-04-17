package grp09616;

public class EntityBear extends Entity
{

	public static final int WALKING_FPS = 4;
	public static final int WALKING_FRAMES = 2;
	
	static int[] textureIDs;
	
	enum BearState
	{
		STANDING, WALKING, ROARING
	}
	
	BearState curState;
	
	public EntityBear()
	{
		curState = BearState.STANDING;
	}
	
	static
	{
		textureIDs = new int[4];
		textureIDs[0] = TextureManager.loadTextures(BearGame.TEXTURE_PATH + "bear/standing.png");
		textureIDs[1] = TextureManager.loadTextures(BearGame.TEXTURE_PATH + "bear/walking1.png");
		textureIDs[2] = TextureManager.loadTextures(BearGame.TEXTURE_PATH + "bear/walking2.png");
		textureIDs[3] = TextureManager.loadTextures(BearGame.TEXTURE_PATH + "bear/roaring.png");
	}
	
	@Override
	public void onDeath()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTextureID() 
	{
		if(curState == BearState.STANDING)
		{
			return textureIDs[0];
		}
		if(curState == BearState.WALKING && ((System.nanoTime() % (WALKING_FRAMES * 1000000000 / WALKING_FPS)) < 1000000000 / WALKING_FPS))
		{
			return textureIDs[1];
		}
		if(curState == BearState.WALKING)
		{
			return textureIDs[2];
		}
		if(curState == BearState.ROARING)
		{
			return textureIDs[3];
		}
		return -1;
	}
}
