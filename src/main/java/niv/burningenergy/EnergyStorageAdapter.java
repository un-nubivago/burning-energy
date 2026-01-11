package niv.burningenergy;

import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import niv.burning.api.FuelVariant;
import team.reborn.energy.api.EnergyStorage;

public final class EnergyStorageAdapter implements SingleSlotStorage<FuelVariant> {

    private final EnergyStorage upstream;

    public EnergyStorageAdapter(EnergyStorage upstream) {
        this.upstream = upstream;
    }

    @Override
    public boolean supportsInsertion() {
        return this.upstream.supportsInsertion();
    }

    @Override
    public long insert(FuelVariant resource, long maxAmount, TransactionContext transaction) {
        return this.upstream.insert(maxAmount * 2 / 5, transaction) * 5 / 2;
    }

    @Override
    public boolean supportsExtraction() {
        return this.upstream.supportsExtraction();
    }

    @Override
    public long extract(FuelVariant resource, long maxAmount, TransactionContext transaction) {
        return this.upstream.extract(maxAmount * 2 / 5, transaction) * 5 / 2;
    }

    @Override
    public boolean isResourceBlank() {
        return this.upstream.getCapacity() == 0;
    }

    @Override
    public FuelVariant getResource() {
        return isResourceBlank() ? FuelVariant.BLANK : FuelVariant.LAVA_BUCKET;
    }

    @Override
    public long getAmount() {
        return this.upstream.getAmount();
    }

    @Override
    public long getCapacity() {
        return this.upstream.getCapacity();
    }
}
