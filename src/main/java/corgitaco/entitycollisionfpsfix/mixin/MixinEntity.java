package corgitaco.entitycollisionfpsfix.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class MixinEntity {

    @Shadow public World level;

    @Inject(method = "isInWall", at = @At("HEAD"), cancellable = true)
    private void noClientCheck(CallbackInfoReturnable<Boolean> cir) {
        if(this.level.isClientSide) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}
