package clientmods.fireflies.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.MathHelper;

import java.util.Optional;

public class Firefly extends SpriteBillboardParticle {

    protected Firefly(ClientWorld world, SpriteProvider spriteProvider, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        super(world, x, y, z, velocityX, velocityY, velocityZ);
        this.setSprite(spriteProvider);
        this.scale *= this.random.nextFloat() * 0.6f + 0.2f;
        this.velocityMultiplier = 1.0F;
        this.collidesWithWorld = false;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public int getBrightness(float tint) {
        if (this.age % 20 < 10) {
            return 255;
        } else {
            return 15;
        }
    }

    public static class FireflyFactory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public FireflyFactory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            Firefly firefly = new Firefly(clientWorld, this.spriteProvider, d, e, f, 0.0, -0.8f, 0.0){

                @Override
                public Optional<ParticleGroup> getGroup() {
                    return Optional.of(ParticleGroup.SPORE_BLOSSOM_AIR);
                }
            };
            firefly.maxAge = MathHelper.nextBetween(clientWorld.random, 500, 1000);
            firefly.setColor(1f, 1f, 0f);
            return firefly;
        }
    }


}
