package niv.burningenergy;

import com.google.common.collect.Streams;

import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.StorageView;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import niv.burning.api.FuelVariant;
import team.reborn.energy.api.EnergyStorage;

public final class BurningStorageAdapter implements EnergyStorage {

    private final Storage<FuelVariant> upstream;

    public BurningStorageAdapter(Storage<FuelVariant> upstream) {
        this.upstream = upstream;
    }

    @Override
    public boolean supportsInsertion() {
        return this.upstream.supportsInsertion();
    }

    @Override
    public long insert(long maxAmount, TransactionContext transaction) {
        return this.upstream.insert(FuelVariant.LAVA_BUCKET, maxAmount * 5 / 2, transaction) * 2 / 5;
    }

    @Override
    public boolean supportsExtraction() {
        return this.upstream.supportsExtraction();
    }

    @Override
    public long extract(long maxAmount, TransactionContext transaction) {
        return this.upstream.extract(FuelVariant.LAVA_BUCKET, maxAmount * 5 / 2, transaction) * 2 / 5;
    }

    @Override
    public long getAmount() {
        return Streams.stream(this.upstream.nonEmptyIterator()).mapToLong(StorageView::getAmount).sum();
    }

    @Override
    public long getCapacity() {
        return Streams.stream(this.upstream.nonEmptyIterator()).mapToLong(StorageView::getCapacity).sum();
    }
}
