package grp09616;

public abstract class EntityEnemy extends Entity
{
	public EntityEnemy(double x, double y)
	{
		super(x, y);
	}

	public abstract int getDifficulty();
	
	public abstract void kill();
	
	public void roarAt(int pwr)
	{
		if(pwr > getDifficulty())
		{
			kill();
		}
	}
}
