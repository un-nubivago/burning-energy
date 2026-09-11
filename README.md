# Burning Energy

**Burning Energy** is a compatibility mod that makes mods made with either **TechReborn**'s [**Energy API**](https://github.com/TechReborn/Energy) or my [**Burning**](https://github.com/un-nubivago/burning) able to exchange energy with one another.

**Burning Energy** allows mods that produce and transfer energy through [**TechReborn's Energy API**](https://github.com/TechReborn/Energy) to transfer it to every furnace-like block [**Burning**](https://github.com/un-nubivago/burning) recognises, and vice versa with burning fuel and energy storage.

For instance, you can use a **Heater** from my [**homonymous mod**](https://github.com/un-nubivago/heater) to power a [**TechReborn**](https://github.com/TechReborn/TechReborn)'s **Electric Furnace**, or use the latter's **Generator** to power a good old vanilla **Furnace**.

## Configurations

Burning Energy only has two configurations.

```json
"enableEnergyToBurning": true // enabled by default
```

This will enable energy mods to transfer energy to any burning storage.

```json
"enableBurningToEnergy": true // enabled by default
```

This will enable burning mods to transfer burning fuel to any energy storage.
