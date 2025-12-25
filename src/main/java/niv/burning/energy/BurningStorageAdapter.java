package niv.burning.energy;

import static niv.burning.api.base.SimpleBurningContext.legacyInstance;
import static niv.burning.energy.BurningEnergy.ENERGY_CONTEXT;

import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.world.item.Items;
import niv.burning.api.BurningStorage;
import niv.burning.api.base.DelegatingBurningStorage;
import team.reborn.energy.api.EnergyStorage;

public final class BurningStorageAdapter extends DelegatingBurningStorage implements EnergyStorage {

    public BurningStorageAdapter(BurningStorage backingStorage) {
        super(backingStorage, null);
    }

    @Override
    public long insert(long maxAmount, TransactionContext transaction) {
        return this.backingStorage.get()
                .insert(BurningEnergy.getBurning(maxAmount), legacyInstance(), transaction)
                .getValue(ENERGY_CONTEXT).longValue();
    }

    @Override
    public long extract(long maxAmount, TransactionContext transaction) {
        return this.backingStorage.get()
                .extract(BurningEnergy.getBurning(maxAmount), legacyInstance(), transaction)
                .getValue(ENERGY_CONTEXT).longValue();
    }

    @Override
    public long getAmount() {
        return this.backingStorage.get()
                .getBurning(legacyInstance())
                .withFuel(Items.LAVA_BUCKET, legacyInstance())
                .getValue(ENERGY_CONTEXT).longValue();
    }

    @Override
    public long getCapacity() {
        return ENERGY_CONTEXT.burnDuration(Items.LAVA_BUCKET);
    }
}
