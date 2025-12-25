package niv.burning.energy;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.minecraft.world.item.Items;
import niv.burning.api.Burning;
import niv.burning.api.BurningContext;
import niv.burning.api.BurningStorage;
import niv.burning.api.base.SimpleBurningContext;
import niv.burning.energy.config.Configuration;
import team.reborn.energy.api.EnergyStorage;

public class BurningEnergy implements ModInitializer {

    public static final String MOD_ID = "burning-energy";

    public static final String MOD_NAME = "Burning Energy";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final int LAVA_ENERGY = 50000;

    public static final BurningContext ENERGY_CONTEXT = new SimpleBurningContext(Map.of(Items.LAVA_BUCKET, LAVA_ENERGY));

    @Override
    public void onInitialize() {
        LOGGER.info("Initialize");

        EnergyStorage.SIDED.registerFallback(new BurningEnergyFallback<>(
                Configuration::enableEnergyToBurning, BurningStorage.SIDED, BurningStorageAdapter::new));

        BurningStorage.SIDED.registerFallback(new BurningEnergyFallback<>(
                Configuration::enableBurningToEnergy, EnergyStorage.SIDED, EnergyStorageAdapter::new));

        Configuration.LOADED.register(() -> LOGGER.info("Configuration loaded"));

        Configuration.init();
    }

    public static final Burning getBurning(long energy) {
        return Burning.LAVA_BUCKET.withValue(Math.clamp(energy, 0, LAVA_ENERGY), ENERGY_CONTEXT);
    }
}
