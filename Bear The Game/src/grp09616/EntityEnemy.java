package grp09616;
import java.util.Random;
  
  
public class EntityEnemy extends Entity
{
    public static final int WALKING_FRAMES = 4;
    public static final int FPS = 4;
	
	private static int[] childTextures;
    private static int[] camperTextures;
    private static int[] rangerTextures;
	private static int[] swatTextures;
    
	private final long timestamp;
	
    private boolean isAlive;
      
    private enum Enemy
    {
        CHILD, CAMPER, RANGER, SWAT;        
    }
      
    private final Enemy person;
      
    @Override
	public void update()
    {
        super.update();
        if (isAlive == false)
        {
            super.shiftYPos(1.0);
        }
    }
      
    public EntityEnemy (double x, double y)
    {
        super(x, y);
        isAlive = true;
          
        Random randomEnemy = new Random();
          
        int enemy = randomEnemy.nextInt(Enemy.values().length);
          
        person = Enemy.values()[enemy];
        
        timestamp = System.currentTimeMillis();
          
    }
  
    public int getDifficulty()
    {
        int level = 0;
          
        switch(person)
        {
        case CHILD: level = 1;
        break;
        case CAMPER: level = 2;
        break;
        case RANGER: level = 3;
        break;
        case SWAT: level = 4;
        break;
        }
  
        return level;
          
    }
  
    public void kill()
    {
        isAlive = false;
    }
  
    public void attemptAttack()
    {
        if(isAlive)
		{
			world.damageBear(getDifficulty() * 5);
		}
    }
      
    public boolean isAlive()
    {
        return isAlive = true;
    }
      
    public boolean roarAt(int pwr)
    {
        if (pwr > getDifficulty())
        {
            kill();
            return true;
        }
        return false;
    }

	@Override
	public int getWidth()
	{
		switch(person)
		{
		case CHILD:
			return 20;
		default:
			return 50;
		}
	}

	@Override
	public int getHeight()
	{
		switch(person)
		{
		case CHILD:
			return 100;
		default:
			return 200;
		}
	}

	@Override
	public void onRemove()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTextureID()
	{
		int[] textureIDs;
		switch(person)
		{
		case CHILD:
			textureIDs = childTextures;
			break;
		case CAMPER:
			textureIDs = camperTextures;
			break;
		case RANGER:
			textureIDs = rangerTextures;
			break;
		case SWAT:
			textureIDs = swatTextures;
			break;
		default:
			textureIDs = null;
			break;
		}
		long walkingTime = (System.currentTimeMillis() - timestamp);
		int framePos = (int) (((walkingTime / (1000 / FPS)) + 1) % WALKING_FRAMES);
		return textureIDs[framePos];
	} 
	
	public static void loadTextures()
	{
		childTextures = RendererMain.loadTextureArray("enemies/kid/kid", WALKING_FRAMES);
		camperTextures = RendererMain.loadTextureArray("enemies/camper/camper", WALKING_FRAMES);
		rangerTextures = RendererMain.loadTextureArray("enemies/ranger/ranger", WALKING_FRAMES);
		swatTextures = RendererMain.loadTextureArray("enemies/swat/swat", WALKING_FRAMES);
	}
} 