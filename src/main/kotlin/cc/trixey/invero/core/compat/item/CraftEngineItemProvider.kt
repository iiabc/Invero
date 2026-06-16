package cc.trixey.invero.core.compat.item

import cc.trixey.invero.common.ItemSourceProvider
import cc.trixey.invero.core.Context
import cc.trixey.invero.core.compat.DefItemProvider
import cc.trixey.invero.core.compat.PluginHook
import net.momirealms.craftengine.bukkit.api.CraftEngineItems
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * @author postyizhan
 * @since 2025/8/2 17:30
 */
@DefItemProvider(["craftengine", "ce"])
class CraftEngineItemProvider : ItemSourceProvider, PluginHook() {

    override val pluginName = "CraftEngine"

    override fun getItem(identifier: String, context: Any?): ItemStack? {
        return try {
            // CraftEngineItems.byId 内部已处理 "namespace:id" 的解析
            val def = CraftEngineItems.byId(identifier) ?: return null
            val player = (context as? Context)?.viewer?.get<Player>()
            // 有玩家上下文时按玩家构建（支持占位符等），否则使用无参兜底
            if (player != null) def.buildBukkitItem(player) else def.buildBukkitItem()
        } catch (e: Throwable) {
            // 如果出现异常（比如 CraftEngine 未安装/缺类），返回 null，让系统使用默认纹理
            null
        }
    }
}
