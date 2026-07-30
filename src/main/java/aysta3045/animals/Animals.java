package aysta3045.animals;

import net.fabricmc.api.ModInitializer;

import java.util.logging.Logger;

public class Animals implements ModInitializer {
    public static final String MOD_ID = "animals";
    public static final Logger LOGGER = Logger.getLogger(MOD_ID);

    @Override
    public void onInitialize(){
        LOGGER.info("Initializing Animals Successfully");
    }
}