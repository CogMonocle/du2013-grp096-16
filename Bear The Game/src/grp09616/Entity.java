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
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
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
	 * Code to be run when the object exits the screen
	 */
	public abstract void onDeath();
	
	/**
	 * @return The texture id returned by the TextureLoader when the 
	 * texture was loaded
	 */
	public abstract int getTextureID();
	
	/**
	 * @return True if texture is successfully loaded after function runs
	 * regardless if the texture was loaded previously or loaded during
	 * current function call. False otherwise.
	 */
	public abstract boolean loadTextures();
	
	/**
	 * @param newID - the id the function is to be set to.
	 * If the entity already has an id, this function has no effect.
	 * This should not be run before the entity is added to the world.
	 */
	public final void setID(int newID)
	{
		if(id < 0)
		{
			id = newID;
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
