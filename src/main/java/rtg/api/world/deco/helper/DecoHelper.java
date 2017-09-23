package rtg.api.world.deco.helper;

import java.util.ArrayList;

import rtg.api.world.deco.DecoBase;


public abstract class DecoHelper extends DecoBase {

    protected ArrayList<DecoBase> helperDecos;

    public DecoHelper() {
        super();
        this.helperDecos = new ArrayList<DecoBase>(){};
    }

    protected void addHelperDecos(DecoBase... helperDecos) {
        for (DecoBase helperDeco : helperDecos) {
            this.helperDecos.add(helperDeco);
        }
    }

    public ArrayList<DecoBase> getHelperDecos() {
        return this.helperDecos;
    }
}
