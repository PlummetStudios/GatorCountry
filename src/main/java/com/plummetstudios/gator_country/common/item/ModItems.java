package com.plummetstudios.gator_country.common.item;

import com.plummetstudios.gator_country.GatorCountry;
import com.plummetstudios.gator_country.common.ModEntities;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.event.RegistryEvent;
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
            () -> new Banjo(new Item.Properties().tab(CreativeModeTab.TAB_MISC).durability(200)));

    public static void registerItem(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().register(new SpawnEggItem(ModEntities.ALLIGATOR.get(), 0X746C4F,0X564231, new Item.Properties().tab(CreativeModeTab.TAB_MISC)).setRegistryName("gatorcountry:alligator_spawn_egg"));
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
