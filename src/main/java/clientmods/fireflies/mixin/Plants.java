package clientmods.fireflies.mixin;

import clientmods.fireflies.FirefliesClient;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeKeys;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@Mixin(PlantBlock.class)
public class Plants extends Block {

    protected Plants(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (!world.getBiome(pos).matchesKey(BiomeKeys.SWAMP) || world.getTimeOfDay() < 13000) return;
        if (random.nextInt(5) != 1) return;
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        mutable.set(i + MathHelper.nextInt(random, -2, 2), j + random.nextInt(2), k + MathHelper.nextInt(random, -2, 2));
        BlockState blockState = world.getBlockState(mutable);
        if (blockState.isFullCube(world, mutable)) return;
        world.addParticle(FirefliesClient.FIREFLY, (double)mutable.getX() + random.nextDouble(), (double)mutable.getY() + random.nextDouble(), (double)mutable.getZ() + random.nextDouble(), 0.0, 0.0, 0.0);
    }

}
