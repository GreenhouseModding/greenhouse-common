package dev.greenhouseteam.greenhouse_common.registry.test;

import dev.greenhouseteam.greenhouse_common.core.api.entrypoint.CommonEntry;
import dev.greenhouseteam.greenhouse_common.registry.api.Lazy;
import dev.greenhouseteam.greenhouse_common.registry.api.RegistryContext;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

public class RegistryTest implements CommonEntry {
    public static final RegistryContext<Item> ITEMS = new RegistryContext<>(BuiltInRegistries.ITEM, "greenhouse_registry_test");
    public static final Lazy<Item> TEST_ITEM = ITEMS.register(
            () -> new Item(
                    new Item.Properties()
            ),
            "test"
    );

    @Override
    public void init() {

    }
}
