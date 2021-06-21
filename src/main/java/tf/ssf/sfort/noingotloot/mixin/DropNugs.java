package tf.ssf.sfort.noingotloot.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import tf.ssf.sfort.noingotloot.filter;

import java.util.function.Consumer;

@Mixin(value = LivingEntity.class, priority = 101)
public abstract class DropNugs {
	//coding in java feels illegal
	@ModifyArg(method = "dropLoot(Lnet/minecraft/entity/damage/DamageSource;Z)V", at=@At(value = "INVOKE", target = "Lnet/minecraft/loot/LootTable;generateLoot(Lnet/minecraft/loot/context/LootContext;Ljava/util/function/Consumer;)V"))
	public Consumer<ItemStack> generateLoot(Consumer<ItemStack> lootConsumer) {
		return new filter(lootConsumer)::transformed;
	}
}
