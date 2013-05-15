package grp09616;

import org.lwjgl.input.Keyboard;

public class BearWorld
{
	public static final int MAX_ENTITIES = 64;

	private final Entity[] entities;

	private final EntityBear bear;

	private double distanceMoved;
	private double prevDistanceMoved;
	private double walkSpeed;
	private final int width;
	private final int height;
	private int curRoarPower;
	private boolean roaring;
	private boolean walking;
	private final boolean wasWalking;

	public BearWorld(int w, int h)
	{
		entities = new Entity[MAX_ENTITIES];
		distanceMoved = 0;
		prevDistanceMoved = 0;
		walkSpeed = 0;
		width = w;
		height = h;
		curRoarPower = 0;
		roaring = false;
		bear = new EntityBear();
		addEntity(bear);
		walking = false;
		wasWalking = false;
	}

	public void update(int delta)
	{
		if(walking)
		{
			incrementDistance(walkSpeed * delta);
		}
		if (((distanceMoved % 1000) >= 500)
				&& ((prevDistanceMoved % 1000) < 500))
		{
			addEntity(new EntityEnemySimple(BearGame.WINDOW_WIDTH
					+ distanceMoved, BearGame.GROUND_HEIGHT));
		}
		for(Entity e : entities)
		{
			if(e instanceof EntityEnemy)
			{
				EntityEnemy enemy = (EntityEnemy) e;
				if((enemy.getXPos() < (((BearGame.BEAR_LEFTPOS + 50)) + distanceMoved)) && enemy.isAlive())
				{
					bear.hit(enemy.getDifficulty() + 1);
					enemy.kill();
				}
			}
		}
		prevDistanceMoved = distanceMoved;
	}
	
	public void enterRoarCharacter(int key)
	{
		if(((curRoarPower == 0) && (key == Keyboard.KEY_E))
				|| ((curRoarPower == 1) && (key == Keyboard.KEY_Q))
				|| ((curRoarPower == 2) && (key == Keyboard.KEY_A)))
		{
			curRoarPower++;
		}
	}

	public void roar()
	{
		for (int i = 0; i < MAX_ENTITIES; i++)
		{
			Entity e = entities[i];
			if ((e != null) && (e instanceof EntityEnemy))
			{
				((EntityEnemy) e).roarAt(curRoarPower);
			}
		}
	}

	public void setRoaring(boolean r)
	{
		roaring = r;
		curRoarPower = 0;
	}

	public boolean isRoaring()
	{
		return roaring;
	}
	
	public int getBearEnergy()
	{
		return bear.getEnergy();
	}
	
	public void beginWalk(double d)
	{
		bear.beginWalking(System.currentTimeMillis());
		walkSpeed = d;
		walking = true;
	}
	
	public void stopWalk()
	{
		walking = false;
		walkSpeed = 0;
		bear.stopWalking();
	}

	public void incrementDistance(double d)
	{
		distanceMoved += d;
		BearRenderer.shiftGroundDisplacement(d);
	}

	public double getDMoved()
	{
		return distanceMoved;
	}

	public int getBearTexture()
	{
		return bear.getTextureID();
	}

	public boolean addEntity(Entity e)
	{
		for (int i = 0; i < MAX_ENTITIES; i++)
		{
			if (entities[i] == null)
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
		if (id < MAX_ENTITIES)
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
