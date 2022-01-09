package com.plummetstudios.gator_country.common.block;

import com.plummetstudios.gator_country.GatorCountry;
import com.plummetstudios.gator_country.common.block.custom.GatorEggBlock;
import com.plummetstudios.gator_country.common.block.custom.ModFlammableRotatedPillarBlock;
import com.plummetstudios.gator_country.common.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, GatorCountry.MOD_ID);


    public static final RegistryObject<Block> GATOR_NEST_BLOCK = registerBlock("gator_nest_block",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.ROOTED_DIRT)),
                    CreativeModeTab.TAB_BUILDING_BLOCKS);



    public static final RegistryObject<Block> GATOR_EGG = registerBlock("gator_egg",
            () -> new GatorEggBlock(BlockBehaviour.Properties.of(Material.EGG)
                    .strength(0f)), CreativeModeTab.TAB_MISC);






    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }
    public static void register(IEventBus eventBus)
    {
BLOCKS.register(eventBus);
    }
}
