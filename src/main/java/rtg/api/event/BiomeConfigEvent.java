package rtg.api.event;

import cpw.mods.fml.common.eventhandler.Event;

public class BiomeConfigEvent extends Event
{

	public BiomeConfigEvent() {
		super();
	}

    public static class Pre extends BiomeConfigEvent
    {
        public Pre()
        {
            super();
        }
    }
    
    public static class Post extends BiomeConfigEvent
    {
        public Post()
        {
            super();
        }
    }
}