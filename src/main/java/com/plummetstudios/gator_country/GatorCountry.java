package com.plummetstudios.gator_country;

import com.plummetstudios.gator_country.common.block.ModBlocks;
import com.plummetstudios.gator_country.common.ModEntities;
import com.plummetstudios.gator_country.common.entity.custom.AlligatorEntity;
import com.plummetstudios.gator_country.common.entity.model.AlligatorModel;
import com.plummetstudios.gator_country.common.entity.render.AlligatorRenderer;
import com.plummetstudios.gator_country.common.item.ModItems;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.example.registry.EntityRegistry;
import software.bernie.geckolib3.GeckoLib;

import javax.swing.text.html.parser.Entity;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(GatorCountry.MOD_ID)
public class GatorCountry {
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "gatorcountry";



    public GatorCountry() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModEntities.ENTITY_TYPES.register(eventBus);
        eventBus.addListener(this::setup);
        GeckoLib.initialize();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }



    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

}

