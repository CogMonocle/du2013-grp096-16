package grp09616;

import java.util.Random;

public class EntityEnemySimple extends EntityEnemy
{
	private static final int WIDTH = 80;
	private static final int HEIGHT = 200;
	
	private final int level;
	
	Random r;

	boolean isAlive;

	public EntityEnemySimple(double x, double y)
	{
		super(x, y);
		isAlive = true;
		r = new Random();
		level = r.nextInt(3) + 1;
	}

	@Override
	public int getWidth()
	{
		return isAlive ? (WIDTH / (4 - level)) : (HEIGHT / (4 -level));
	}

	@Override
	public int getHeight()
	{
		return isAlive ? (HEIGHT / (4 - level)) : (WIDTH / (4 - level));
	}

	@Override
	public int getDifficulty()
	{
		return level - 1;
	}
	
	@Override
	public boolean isAlive()
	{
		return isAlive;
	}

	@Override
	public void kill()
	{
		isAlive = false;
	}
	
	@Override
	public void attemptAttack()
	{
		
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
