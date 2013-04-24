package grp09616;

public class BearWorld
{
	public static int MAX_ENTITIES = 64;
	
	private Entity[] entities;

	private double distanceMoved;
	private double prevDistanceMoved;
	private int width;
	private int height;
	private boolean roaring;
	
	public BearWorld(int w, int h)
	{
		entities = new Entity[MAX_ENTITIES];
		distanceMoved = 0;
		prevDistanceMoved = 0;
		width = w;
		height = h;
		roaring = false;
	}
	
	public void update()
	{
		if((distanceMoved % 1000) >= 500 && (prevDistanceMoved % 1000) < 500)
		{
			addEntity(new EntityEnemySimple(BearGame.WINDOW_WIDTH + distanceMoved, BearGame.GROUND_HEIGHT));
		}
		prevDistanceMoved = distanceMoved;
	}
	
	public void roar(int pwr)
	{
		for(int i = 0; i < MAX_ENTITIES; i++)
		{
			Entity e = entities[i];
			if(e != null && e instanceof EntityEnemy)
			{
				((EntityEnemy)e).roarAt(pwr);
			}
		}
	}
	
	public void setRoaring(boolean r)
	{
		roaring = r;
	}
	
	public boolean isRoaring()
	{
		return roaring;
	}
	
	public void incrementDistance(double d)
	{
		distanceMoved += d;
	}
	
	public double getDMoved()
	{
		return distanceMoved;
	}
	
	public boolean addEntity(Entity e)
	{
		for(int i = 0; i < MAX_ENTITIES; i++)
		{
			if(entities[i] == null)
			{
				e.setID(i, this);
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
		entities[id].onRemove();
		entities[id] = null;
	}
}
