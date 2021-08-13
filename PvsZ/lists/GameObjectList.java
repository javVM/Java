package tp.pr1.lists;

import tp.pr1.elements.GameObject;
import tp.pr1.elements.Plant;
import tp.pr1.elements.Zombie;

public class GameObjectList {

	private GameObject[] objects;
	private int size = 0;

	public GameObjectList()
	{
		this.objects =  new GameObject[32];
	}	

	public void add(GameObject gameObject)
	{

		if(size < objects.length)
		{
			objects[size] = gameObject;
			size++;
		}
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void erase()
	{
		int count = 0;

		for(int i=0; i < size; i++)
		{

			if((objects[i].getHealthPoints() == 0))
			{
				count = i;

				for(int j= count; j < size; j++)
				{
					objects[j] = objects[j+1];
				}

				size--;

			}
		}
	}

	public boolean gameObjectEmpty(int x, int y)
	{
		boolean empty = true;

		for(int i=0; i < size; i++)
		{
			if(objects[i].getX() == x && objects[i].getY() == y)
			{
				empty = false;
			}
		}

		return empty;
	}

	public void update()
	{
		for(int i=0; i < size; i++)
		{
			objects[i].update();
		}
	}

	public int position(int x, int y)
	{
		int position = 0;

		for(int i = 0; i < size; i++)
		{
			if(objects[i].getX() == x && objects[i].getY() == y)
			{
				position = i;
			}			
		}
		return position;
	}

	public String infoString(int i)
	{
		return objects[i].getInfo();
	}


	public String debugString()
	{
		String debug = " ";
		
		if(size >=1)
		{
			debug = objects[0].getDebugInfo() + " ";

			for(int i= 1; i < size; i++)
			{
				debug = debug + "space" +  objects[i].getDebugInfo() + " ";
			}
		}

		return debug;
	}

	public void damaged(int x, int y)
	{
		for(int i= 0; i < size; i++)
		{
			if(objects[i].getX() == x && objects[i].getY() == y)
			{
				if(objects[i] instanceof Zombie) {
					((Zombie)objects[i]).PeashooterDamage();
				}else {
					((Plant)objects[i]).Damage();
				}
			}
		}
	}

	public void bombdamaged(int x, int y)
	{
		for(int i= 0; i < size; i++)
		{
			if(objects[i].getX() == x && objects[i].getY() == y)
			{
				if(objects[i] instanceof Zombie) {
					((Zombie)objects[i]).CherryBombDamage();
				}else {
					((Plant)objects[i]).Damage();
				}
			}
		}
	}
	
	public String externalise()
	{
		String externalise = " ";
		
		if(size >=1)
		{
			externalise = objects[0].externalise();
			 if(size > 1)
			 {
				externalise = externalise + ", ";
			 }

			for(int i= 1; i < size; i++)
			{
				externalise = externalise+ objects[i].externalise(); 
				if(i < size-1)
				{
					externalise = externalise + ", "; 
				}
			}
		}
		
		return externalise;
	}


}
