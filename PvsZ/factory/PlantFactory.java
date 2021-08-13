package tp.pr1.factory;
import tp.pr1.elements.*;
import tp.pr1.command.*;
import tp.pr1.*;
import tp.pr1.*;

public class PlantFactory {
	
	private static Plant[] availablePlants = { new Sunflower(), new Peashooter(), new CherryBomb(), new WallNut()};

	public static Plant getPlant(String plantName){

		Plant plant;
		
		for(int i = 0; i < availablePlants.length; i++)
		{
			plant = availablePlants[i].parse(plantName);
			if(plant != null) {
				return plant;
			}
		}
		
		return null;
	}

	public static String listOfAvilablePlants() 
	{
		String list = "";
		for (Plant plantmsg: availablePlants) {
			list = list + "\n" +  plantmsg.getPlantMsg();
		}
		return list;
	}

}
