package niv.burningenergy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import niv.burning.api.BurningStorage;
import niv.burningenergy.config.Configuration;
import team.reborn.energy.api.EnergyStorage;

public class BurningEnergy implements ModInitializer {

    public static final String MOD_ID = "burning_energy";

    public static final String MOD_NAME = "Burning Energy";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    @Override
    public void onInitialize() {
        LOGGER.info("Initialize");

        Configuration.init();
        Configuration.LOADED.register(() -> LOGGER.info("Configuration loaded"));

        EnergyStorage.SIDED.registerFallback(new BurningEnergyFallback<>(
                Configuration::enableEnergyToBurning, BurningStorage.SIDED, BurningStorageAdapter::new));

        BurningStorage.SIDED.registerFallback(new BurningEnergyFallback<>(
                Configuration::enableBurningToEnergy, EnergyStorage.SIDED, EnergyStorageAdapter::new));
    }
}
