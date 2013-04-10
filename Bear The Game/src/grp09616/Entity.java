package grp09616;

public abstract class Entity
{
	private int id;
	
	protected int width;
	protected int height;
	
	protected double xPos;
	protected double yPos;	
	
	public Entity()
	{
		id = -1;
	}
	
	public abstract int getWidth();
	
	public abstract int getHeight();
	
	public abstract double getXPos();
	
	public abstract double getYPos();
	
	public abstract void onDeath();
	
	public final void setID(int newID)
	{
		if(id < 0)
		{
			id = newID;
		}
	}
	
	public final int getID()
	{
		return id;
	}
}
