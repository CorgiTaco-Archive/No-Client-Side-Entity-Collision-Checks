package corgitaco.entitycollisionfpsfix.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {

    public MixinLivingEntity(EntityType<?> $$0, World $$1) {
        super($$0, $$1);
    }

    @Inject(method = "isInWall", at = @At("HEAD"), cancellable = true)
    private void noClientCheck(CallbackInfoReturnable<Boolean> cir) {
        if(this.level.isClientSide) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "pushEntities", at = @At("HEAD"), cancellable = true)
    private void noPushEntitiesClient(CallbackInfo ci) {
        if (this.level.isClientSide) {
            ci.cancel();
        }
    }
}
