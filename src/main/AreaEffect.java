package main;

public abstract class AreaEffect extends GameObject {

	int TTL;
	
	public AreaEffect(ImageContainer container,int TTL, Player player){
		this.TTL = TTL;
		
	}
	
	public  boolean update(GamePlayState game, float delta){
		anim.update(delta);
		
		boolean result = false;
		TTL-=delta;
		if(TTL<=0)
			result = true;
		
		
		
		return result;
	}
	
}
