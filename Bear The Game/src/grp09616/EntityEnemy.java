package grp09616;

public abstract class EntityEnemy extends Entity
{
	public EntityEnemy(double x, double y)
	{
		super(x, y);
	}

	public abstract int getDifficulty();

	public abstract void kill();

	public abstract void attemptAttack();
	
	public abstract boolean isAlive();
	
	public void roarAt(int pwr)
	{
		if (pwr > getDifficulty())
		{
			kill();
		}
	}
}
