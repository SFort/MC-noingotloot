package tf.ssf.sfort.noingotloot;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.registry.Registry;

import java.util.Map;
import java.util.function.Consumer;

public class filter {
    public static Consumer<ItemStack> old;
    public static final Map<Item, Item> noIngotDrops = Map.of(
            Items.IRON_INGOT, Items.IRON_NUGGET,
            Items.GOLD_INGOT, Items.GOLD_NUGGET,
            Items.COPPER_INGOT, Items.AIR,
            Items.GOLD_NUGGET, Items.AIR,
            Items.IRON_NUGGET, Items.AIR
    );

    public filter(Consumer<ItemStack> o) {
        old = o;
    }

    public void transformed(ItemStack stack) {
        if (noIngotDrops.containsKey(stack.getItem())) {
            NbtCompound tag = new NbtCompound();
            stack.writeNbt(tag);
            tag.putString("id", Registry.ITEM.getId(noIngotDrops.get(stack.getItem())).toString());
            stack = ItemStack.fromNbt(tag);
        }

        old.accept(stack);
    }
}
