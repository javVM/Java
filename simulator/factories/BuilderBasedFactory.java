package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory<T>{

	private List<Builder<T>> _builders;
	private List<JSONObject> _factoryElements;
	
	public BuilderBasedFactory(List<Builder<T>> builders)
	{
		this._builders = new ArrayList<>(builders);
		for(Builder<?> b: builders)
		{
			this._factoryElements.add(b.getBuilderInfo());
		}
	}
	
	public T createInstance(JSONObject info) throws IllegalArgumentException{
		
			for(Builder<?> b: _builders)
			{
				if(b.createInstance(info) !=  null)
				{
					return (T) b.createInstance(info);
				}
				else
				{
					throw new IllegalArgumentException();
				}
			}
		
		return null;
		
	}

	@Override
	public  List<JSONObject> getInfo() {

		return this._factoryElements;
		
	}

}
