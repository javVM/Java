package tp.pr1;

public enum Level
{
	EASY, HARD, INSANE;
	

	
		
	public int getNumzombies(Level option)
	{
		int n;
		
		if( option == Level.EASY)
		{
			n = 3;
		}
		else if(option == Level.HARD)
		{
			n = 5;
		}
		else
		{
			n = 10;
		}
		
		return n;
	}

	public double getFrequency(Level option)
	{
		double frequency;
		
		if( option == Level.EASY)
		{
			frequency = 0.1;
		}
		else if(option == Level.HARD)
		{
			frequency = 0.2;
		}
		else
		{
			frequency = 0.3;
		}
		
		return frequency;
	}
	
	
	public static Level parse(String inputString) {
		for (Level level : Level. values() )
		if (level.name().equalsIgnoreCase(inputString)) return level;
		return null;
		}
	
		public static String all (String separator) {
		StringBuilder sb = new StringBuilder();
		for (Level level : Level. values() )
		sb. append(level.name() + separator);
		String allLevels = sb.toString();
		return allLevels.substring(0, allLevels.length() - separator.length());
		}
}
