package io.github.fallOut015.norsecraft.block.trees;

import io.github.fallOut015.norsecraft.world.gen.feature.FeaturesNorsecraft;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class FrostbarkTree extends Tree {
    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random rand, boolean beehive) {
        return FeaturesNorsecraft.Features.FROSTBARK;
    }
}