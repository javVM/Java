package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory<T>{

	private List<Builder<T>> _builders;
	
	public BuilderBasedFactory(List<Builder<T>> builders)
	{
		this._builders = new ArrayList<>(builders);
	}	
	
	public T createInstance(JSONObject info) throws IllegalArgumentException{
		
		try
		{
			for(Builder<T> b: _builders)
			{
				if(b.createInstance(info) !=  null)
				{
					return (T) b.createInstance(info);
					
				}
				
			}
		}
		catch(IllegalArgumentException ex)
		{
			throw new IllegalArgumentException("Cannot create instance");
		}
		return null;
		
	}

	@Override
	public  List<JSONObject> getInfo() {

		List<JSONObject> _factoryElements = new ArrayList<JSONObject>();
		
		for(Builder<?> b: this._builders)
		{
			_factoryElements.add(b.getBuilderInfo());
		}
		
		return  _factoryElements;
		
	}


}
