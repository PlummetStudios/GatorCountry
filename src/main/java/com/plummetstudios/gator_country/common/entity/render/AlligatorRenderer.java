package com.plummetstudios.gator_country.common.entity.render;

import com.plummetstudios.gator_country.common.entity.custom.AlligatorEntity;
import com.plummetstudios.gator_country.common.entity.model.AlligatorModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class AlligatorRenderer extends GeoEntityRenderer<AlligatorEntity> {
    public AlligatorRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new AlligatorModel());
        this.shadowRadius = 0.7F; //change 0.7 to the desired shadow size.
    }
}
