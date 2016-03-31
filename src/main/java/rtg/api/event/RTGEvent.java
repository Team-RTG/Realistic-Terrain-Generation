package rtg.api.event;

import net.minecraftforge.fml.common.eventhandler.Event;

public class RTGEvent extends Event {

    public RTGEvent() {
        super();
    }

    public static class BiomeConfigEvent extends RTGEvent{

        public static class Pre extends BiomeConfigEvent {
            public Pre() {
                super();
            }
        }

        public static class Post extends BiomeConfigEvent {
            public Post() {
                super();
            }
        }
    }
}