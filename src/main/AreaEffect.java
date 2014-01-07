package main;

public abstract class AreaEffect extends GameObject {

	int TTL;
	
	public AreaEffect(int TTL, float angle){
		this.TTL = TTL;
		this.angle = angle;
	}
	
	public  boolean update(GamePlayState game, float delta){
		boolean result = false;
		TTL-=delta;
		if(TTL<=0)
			result = true;
		
		
		
		return result;
	}
	
}
