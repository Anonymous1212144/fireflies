package clientmods.fireflies;

import clientmods.fireflies.particle.Firefly;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@Environment(EnvType.CLIENT)
public class FirefliesClient implements ClientModInitializer {

    public static DefaultParticleType FIREFLY = FabricParticleTypes.simple();

    @Override
    public void onInitializeClient() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier("fireflies", "firefly"), FIREFLY);
        ParticleFactoryRegistry.getInstance().register(FIREFLY, Firefly.FireflyFactory::new);
    }

}
