package com.plummetstudios.gator_country.common.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.plummetstudios.gator_country.common.entity.custom.AlligatorEntity;
import com.plummetstudios.gator_country.common.entity.model.AlligatorModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import com.plummetstudios.gator_country.common.entity.model.AlligatorModel;

public class AlligatorRenderer extends GeoEntityRenderer<AlligatorEntity> {
    public AlligatorRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new AlligatorModel());
        this.shadowRadius = 0.7F; //change 0.7 to the desired shadow size.
    }

    @Override
    public void renderEarly(AlligatorEntity animatable, PoseStack stackIn, float ticks, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float partialTicks) {
        float scale = animatable.isBaby() ? 0.3F : 1F;
        stackIn.scale(scale, scale, scale);

    }
}

