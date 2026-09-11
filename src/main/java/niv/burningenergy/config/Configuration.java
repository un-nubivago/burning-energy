package niv.burningenergy.config;

import java.util.stream.Stream;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

@SuppressWarnings("null")
@NullMarked
public final class Configuration {

    public static final Event<@NonNull Runnable> LOADED;

    private static final Loader<@NonNull Configuration> LOADER;

    static {
        LOADED = EventFactory.createArrayBacked(
                Runnable.class,
                runnables -> () -> Stream.of(runnables).forEach(Runnable::run));

        LOADER = new Loader<>(
                Configuration.class,
                () -> LOADED.invoker().run(),
                Configuration::new);
    }

    private boolean enableEnergyToBurning = true;
    private boolean enableBurningToEnergy = true;

    Configuration() {
    }

    private static final Configuration getInstance() {
        return LOADER.getConfiguration();
    }

    public static final void init() {
        getInstance();
    }

    public static final boolean enableEnergyToBurning() {
        return getInstance().enableEnergyToBurning;
    }

    public static final boolean enableBurningToEnergy() {
        return getInstance().enableBurningToEnergy;
    }
}
