package grp09616;

import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureImpl;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class ManagerTextures
{

	private static int currentTexture;

	public static ArrayList<Texture> textures;

	public static void initialize()
	{
		currentTexture = -1;
		textures = new ArrayList<Texture>();
	}

	// location relative to the main directory
	public static int loadTextures(String loc)
	{
		try
		{
			// load texture from PNG file
			Texture t = TextureLoader.getTexture("PNG",
					ResourceLoader.getResourceAsStream(loc));

			int id = t.getTextureID();

			/*
			 * Debugging Info System.out.println("Texture loaded: " + loc);
			 * System.out.println(">> Image width: " + t.getImageWidth());
			 * System.out.println(">> Image height: " + t.getImageHeight());
			 * System.out.println(">> Texture width: " + t.getTextureWidth());
			 * System.out.println(">> Texture height: " + t.getTextureHeight());
			 * System.out.println(">> Texture ID: " + id);
			 */

			textures.add(t);

			return id;
		} catch (IOException e)
		{
			System.out.println("Texture: " + loc + " failed to load");
			// e.printStackTrace();
		}
		return -1;
	}

	public static Texture getTexture(int id)
	{
		for (Texture t : textures)
		{
			if (t.getTextureID() == id)
			{
				return t;
			}
		}
		return null;
	}

	public static boolean setTextureImage(int id)
	{
		if (id == currentTexture)
		{
			return true;
		}
		TextureImpl.unbind();
		for (Texture t : textures)
		{
			if (t.getTextureID() == id)
			{
				currentTexture = id;
				t.bind();
				return true;
			}
		}
		return false;
	}
}
