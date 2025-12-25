package niv.burning.energy;

import static niv.burning.energy.BurningEnergy.ENERGY_CONTEXT;
import static niv.burning.energy.BurningEnergy.LAVA_ENERGY;

import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.world.item.Items;
import niv.burning.api.Burning;
import niv.burning.api.BurningContext;
import niv.burning.api.BurningStorage;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.base.DelegatingEnergyStorage;

public final class EnergyStorageAdapter extends DelegatingEnergyStorage implements BurningStorage {

    public EnergyStorageAdapter(EnergyStorage backingStorage) {
        super(backingStorage, null);
    }

    @Override
    public Burning insert(Burning burning, BurningContext context, TransactionContext transaction) {
        var maxAmount = burning.withFuel(Items.LAVA_BUCKET, context).getValue(ENERGY_CONTEXT).longValue();
        return Burning.LAVA_BUCKET
                .withValue((int) this.backingStorage.get().insert(maxAmount, transaction), ENERGY_CONTEXT)
                .withFuel(burning.getFuel(), context);
    }

    @Override
    public Burning extract(Burning burning, BurningContext context, TransactionContext transaction) {
        var maxAmount = burning.withFuel(Items.LAVA_BUCKET, context).getValue(ENERGY_CONTEXT).longValue();
        return Burning.LAVA_BUCKET
                .withValue((int) this.backingStorage.get().extract(maxAmount, transaction), ENERGY_CONTEXT)
                .withFuel(burning.getFuel(), context);
    }

    @Override
    public Burning getBurning(BurningContext context) {
        return Burning.LAVA_BUCKET.withValue(
                Math.clamp(this.backingStorage.get().getAmount(), 0, LAVA_ENERGY),
                ENERGY_CONTEXT);
    }

    @Override
    public boolean isBurning() {
        return this.backingStorage.get().getAmount() > 0;
    }
}
