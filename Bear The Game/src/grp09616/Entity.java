package grp09616;

public abstract class Entity
{
	private BearWorld world;

	private int id;

	protected double xPos;
	protected double yPos;

	public Entity(double x, double y)
	{
		id = -1;
		xPos = x;
		yPos = y;
	}

	public abstract int getWidth();

	public abstract int getHeight();

	public double getXPos()
	{
		return xPos;
	}

	public void shiftXPos(double amt)
	{
		xPos += amt;
	}

	public double getYPos()
	{
		return yPos;
	}

	public void shiftYPos(double amt)
	{
		yPos += amt;
	}

	/**
	 * Code to be run every tick
	 */
	public void update()
	{
		if (((getXPos() + getWidth()) - world.getDMoved()) > 0)
		{
			world.removeEntity(getID());
		}
	}

	/**
	 * Code to be run when the object exits the screen
	 */
	public abstract void onRemove();

	/**
	 * @return The texture id returned by the TextureLoader when the texture was
	 *         loaded
	 */
	public abstract int getTextureID();

	/**
	 * @param newID
	 *            - the id the function is to be set to.
	 * @param w
	 *            - the world the entity is being placed in If the entity
	 *            already has an id + world, this function has no effect. This
	 *            should not be run before the entity is added to the world.
	 */
	public final void setID(int newID, BearWorld w)
	{
		if (id < 0)
		{
			id = newID;
			world = w;
		}
	}

	/**
	 * @return The entity's id.
	 */
	public final int getID()
	{
		return id;
	}
}
