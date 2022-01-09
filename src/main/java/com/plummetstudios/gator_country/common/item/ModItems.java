package com.plummetstudios.gator_country.common.item;

import com.plummetstudios.gator_country.GatorCountry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, GatorCountry.MOD_ID);

public static final RegistryObject<Item> SLAB_OF_GATOR = ITEMS.register("slab_of_gator",
        () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(ModFoods.SLAB_OF_GATOR)));

    public static final RegistryObject<Item> BANJO = ITEMS.register("banjo",
            () -> new Banjo(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
