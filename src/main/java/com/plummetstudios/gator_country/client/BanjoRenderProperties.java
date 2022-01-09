package com.plummetstudios.gator_country.client;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ForgeModelBakery;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.plummetstudios.gator_country.client.ItemISR;
@Mod.EventBusSubscriber(modid = "gatorcountry", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)

public class BanjoRenderProperties implements IItemRenderProperties {

    @Override
    public BlockEntityWithoutLevelRenderer getItemStackRenderer()
    {
        return new ItemISR();
    }

@SubscribeEvent
public static void hookMethod(ModelRegistryEvent event)
    {
        ForgeModelBakery.addSpecialModel(new ModelResourceLocation("gatorcountry:banjo_gui", "inventory"));
        ForgeModelBakery.addSpecialModel(new ModelResourceLocation("gatorcountry:banjo_in_hand", "inventory"));

    }
    public static final BanjoRenderProperties RENDER_PROPERTIES = new BanjoRenderProperties();

}
