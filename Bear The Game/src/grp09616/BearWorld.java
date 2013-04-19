package grp09616;

public class BearWorld
{
	public int MAX_ENTITIES = 64;
	
	private Entity[] entities;

	private double distanceMoved;
	private int width;
	private int height;
	
	public BearWorld(int w, int h)
	{
		entities = new Entity[MAX_ENTITIES];
		distanceMoved = 0;
		width = w;
		height = h;
	}
	
	public void update()
	{

	}
	
	public boolean addEntity(Entity e)
	{
		for(int i = 0; i < MAX_ENTITIES; i++)
		{
			if(entities[i] == null)
			{
				e.setID(i);
				entities[i] = e;
				return true;
			}
		}
		return false;
	}
	
	public Entity getEntity(int id)
	{
		if(id < MAX_ENTITIES)
		{
			return entities[id];
		}
		return null;
	}
	
	public void removeEntity(int id)
	{
		entities[id] = null;
	}
}
