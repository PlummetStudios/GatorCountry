package com.plummetstudios.gator_country.client.event;

import com.plummetstudios.gator_country.GatorCountry;
import com.plummetstudios.gator_country.common.ModEntities;
import com.plummetstudios.gator_country.common.entity.render.AlligatorRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GatorCountry.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.ALLIGATOR.get(), AlligatorRenderer::new);
    }
}
