package grp09616;

public class EntityEnemySimple extends EntityEnemy
{
	private static final int WIDTH = 80;
	private static final int HEIGHT = 160;
	
	boolean isAlive;
	
	public EntityEnemySimple(double x, double y)
	{
		super(x, y);
		isAlive = true;
	}
	
	public int getWidth()
	{
		return isAlive ? WIDTH : HEIGHT;
	}
	
	public int getHeight()
	{
		return isAlive ? HEIGHT : WIDTH;
	}

	@Override
	public int getDifficulty()
	{
		return 0;
	}
	
	@Override
	public void kill()
	{
		isAlive = false;
	}

	@Override
	public void onRemove()
	{

	}

	@Override
	public int getTextureID()
	{
		return -1;
	}
	
	
}
